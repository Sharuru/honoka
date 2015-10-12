<%--
  Created by Sharuru
  Date: 2015/10/10 0008
  Time: 17:08
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//子功能加载后绑定点击事件
	$(document).ready(function() {
		$("#pageNav").click(function(event) {
			loadMetroListContent(event.target.id);
		});
		//$("#metroModal").click(function(event) {
		//	loadStaffDetailInfo(event.target.id);
		//});
	});
	//AJAX 获取子页面的内容
	function loadMetroListContent(dest) {
		$.ajax({
			type : "GET",
			url : "metroAdmin&reqPage=" + dest,
			success : function(data) {
				$("#metroListDiv").html(data);
			}
		});
	}
	function loadStaffDetailInfo(staffId) {
		$.ajax({
			type : "GET",
			url : "staffDetail&reqStaffId=" + staffId,
			success : function(data) {
				$("#staffInfoModal").html(data);
			}
		});
	}
</script>
</head>
<body>
	<div id="metroListDiv">
		<div class="panel panel-default">
			<div class="panel-heading">
				地铁站点数据一览 <a href="reqRefreshMetroInfo" style="float: right;"
					class="btn btn-warning btn-xs" role="button">更新数据</a>
			</div>
			<div class="panel-body">
				<table class="table table-hover table-striped  table-condensed"
					contenteditable="false">
					<thead>
						<tr>
							<th>编号</th>
							<th>线路名称</th>
							<th>站点名称</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${metroInfoList != null && metroInfoList.size() > 0}">
							<c:forEach items="${metroInfoList}" var="currStation">
								<tr>
									<td>${currStation.recordId}</td>
									<td>${currStation.lineName}</td>
									<td>${currStation.staName}</td>
									<td><button id="metroModal" type="button"
											class="btn btn-xs btn-default" data-toggle="modal"
											data-target="#staffInfoModal">查看详情</button></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>

				<nav style="text-align: center">
					<ul class="pagination" id="pageNav">
						<!-- 若为第一页不显示左箭头 -->
						<c:if test="${currPage gt 1}">
							<li><a href="#" id="${currPage -1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<!-- 循环设置页码 -->
						<c:forEach var="pageNum" begin="1" end="${totalCount/2+1}">
							<li id="pageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
						</c:forEach>
						<!-- 若为最后一页不显示右箭头 -->
						<c:if test="${currPage lt totalCount/2}">
							<li><a href="#" id="${currPage + 1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
					</ul>
				</nav>
				<!-- 员工详情模态框，通过 AJAX 进行替换 -->
				<div class="modal fade" id="staffInfoModal" tabindex="-1"
					role="dialog"></div>
				<!-- 页面加载完毕设置分页激活样式 -->
				<script type="text/javascript">
					$(document).ready(function() {
						$("#pageNav${currPage}").addClass("active");
					});
				</script>
			</div>
		</div>
</body>
</html>
