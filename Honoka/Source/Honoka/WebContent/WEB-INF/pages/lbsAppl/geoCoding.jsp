<%--
  Created by Sharuru
  Date: 2015/10/09 0008
  Time: 16:29
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//子功能加载后绑定点击事件
		$(document).ready(function() {
		$("#btnReqGeoCoding").click(function(event) {
			reqGeoCoding();
		});
	}); 
	//解析地址请求
	function reqGeoCoding() {
		var userInput = document.getElementById("inputReqAddr").value;
		$.ajax({
			type : "POST",
			url : "reqGeoCoding",
			data : {
				reqAddr : userInput
			},
			success : function(data) {
				//重绘页面，显示结果数据
				$("#geoCodingContent").html(data);
			}
		});
	}
</script>
</head>
<body>
	<div id="geoCodingContent">
		<div class="input-group">
			<input type="text" class="form-control" id="inputReqAddr"
				placeholder="请输入需要解析的地址……" value="${pageParaMap.inputReqAddr}"><span
				class="input-group-btn">
				<button class="btn btn-default" type="button" id="btnReqGeoCoding">解析</button>
			</span>
		</div>
		<div style="margin-top: 15px;margin-bottom: 5px;" class="alert alert-${pageParaMap.bdReqStatus}" role="alert">
			<b>${pageParaMap.bdGeocodingResult }</b>
		</div>
		<div style="margin-top: 15px;margin-bottom: 5px;" class="alert alert-${pageParaMap.apReqStatus}" role="alert">
			<b>${pageParaMap.apGeocodingResult }</b>
		</div>
	</div>
</body>
</html>
