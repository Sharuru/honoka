<%--
  Created by Sharuru
  Date: 2015/10/12 0008
  Time: 16:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//解析地址
	function reqGeoCoding() {
		var userInput = document.getElementById("inputUserInput").value;
		$.ajax({
			type : "POST",
			url : "reqGeoCoding",
			data : {
				reqAddr : userInput
			},
			success : function(data) {
				//TODO: 目前是重绘页面，理论可以直接发送需要的数据
				$("#geoCodingContent").html(data);
			}
		});
	}
</script>
</head>
<body>
	<div id="geoCodingContent">
		<div class="input-group">
			<input type="text" class="form-control" id="inputUserInput"
				placeholder="请输入需要解析的地址……" value="${userInput}"><span
				class="input-group-btn">
				<button class="btn btn-default" type="button"
					onclick="reqGeoCoding();">解析</button>
			</span>
		</div>
		<div style="margin-top: 15px;margin-bottom: 5px;" class="alert alert-${bdReqStatus}" role="alert">
			<b>${bdGeocodingResult }</b>
		</div>
		<div style="margin-top: 15px;margin-bottom: 5px;" class="alert alert-${apReqStatus}" role="alert">
			<b>${apGeocodingResult }</b>
		</div>
	</div>

</body>
</html>
