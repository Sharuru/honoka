<%--
  Created by Sharuru
  Date: 2015/10/09 0008
  Time: 16:29
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
			},//function(data) {
		//TODO: 这里的 data 应该是获取到的解析结果
		//$("#" + dest + "Content").html(data);
		//}
		});
	}
</script>
</head>
<body>
	<div>
		<div class="input-group">
			<input type="text" class="form-control" id="inputUserInput"
				placeholder="请输入需要解析的地址……"> <span class="input-group-btn">
				<button class="btn btn-default" type="button"
					onclick="reqGeoCoding();">解析</button>
			</span>
		</div>
	</div>

</body>
</html>
