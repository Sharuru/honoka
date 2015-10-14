<%--
  Created by Sharuru
  Date: 2015/10/14 0008
  Time: 10:14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//子功能加载后绑定点击事件
	$(document).ready(function() {
		$("#reqGeoFencing").click(function(event) {
			loadGeoFencingContent();
		});
	});
	//AJAX 获取子页面的内容
	function loadGeoFencingContent() {
		$.ajax({
			type : "POST",
			url : "reqCalcGeoFencing",
			data : {
				reqRange : document.getElementById("reqRange").value
			},
			success : function(data) {
				//$("#metroListDiv").html(data);
				alert("You win");
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
			<a id="reqGeoFencing" href="#" class="btn btn-primary btn-xs" role="button">开始计算</a>
		</div>
		<div id="geoFencingResult"></div>
	</div>
</body>
</html>
