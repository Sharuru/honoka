<%--
  Created by Sharuru
  Date: 2015/10/14 0008
  Time: 10:14
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	// 子功能加载后绑定点击事件
	$(document).ready(function() {
		$("#reqGeoFencing").click(function(event) {
			var $btn = $(this).button('loading');
			loadGeoFencingContent();
		});
	});
	// AJAX 获取子页面的内容
	function loadGeoFencingContent() {
		$.ajax({
			cache: false,
			type : "POST",
			url : "reqCalcGeoFencing",
			data : {
				reqRange : document.getElementById("reqRange").value
			},
			success : function(data) {
				$("#geoFencingContent").html(data);
				$('#reqGeoFencing').button('reset');
			}
		});
	}
</script>
</head>
<body>
	<div id="geoFencingContent">
		<div id="geoFencingFunction">
			选择围栏范围后点击按钮进行计算
			 <select id="reqRange" class="selectRange">
				<option value="500">500 米</option>
				<option value="1000">1000 米</option>
				<option value="1500">1500 米</option>
			</select> 
			<button type="button" id="reqGeoFencing" data-loading-text="正在计算…" class="btn btn-primary btn-xs"  autocomplete="off" >开始计算</button>
		</div>
		<div id="geoFencingResultDiv">
			<table class="table table-hover table-striped  table-condensed" contenteditable="false">
				<thead>
					<tr>
						<th>工号</th>
						<th>姓名</th>
						<th>线路名称</th>
						<th>站点名称</th>
						<th>距离</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${pageParaMap.fencingResultList != null && pageParaMap.fencingResultList.size() > 0}">
						<c:forEach items="${pageParaMap.fencingResultList}" var="currStation">
							<tr>
								<td>${currStation.staffId}</td>
								<td>${currStation.staffName}</td>
								<td>${currStation.lineName}</td>
								<td>${currStation.staName}</td>
								<td>${currStation.dist}</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
