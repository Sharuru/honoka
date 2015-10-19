<%--
  Created by Sharuru
  Date: 2015/10/09 0008
  Time: 15:58
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//子功能加载后绑定点击事件
 	$(document).ready(function() {
		$("#lbsNav").click(function(event) {
			loadlbsApplContent(event.target.id);
		});
	}); 
	//AJAX 获取子页面的内容
	function loadlbsApplContent(dest) {
		$.ajax({
			type : "GET",
			url : dest,
			success : function(data) {
				$("#" + dest + "Content").html(data);
			}
		});
	}
</script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">功能选择</div>
		<div class="panel-body">
			<!-- LBS 应用功能导航 -->
			<ul id="lbsNav" class="nav nav-pills">
				<li class="active"><a href="#geoCodingContent" id="geoCoding"
					data-toggle="tab">地址解析</a></li>
				<li><a href="#twoPointCalcContent" id="twoPointCalc"
					data-toggle="tab">兴趣点</a></li>
				<li><a href="#geoFencingContent" id="geoFencing"
					data-toggle="tab">地理围栏<span class="badge">Alpha!</span></a></li>
			</ul>
			<!-- LBS 应用页面内容区，通过 AJAX 进行替换 -->
			<div id="lbsContent" class="tab-content" style="margin-top: 20px;">
				<div class="tab-pane fade in active" id="geoCodingContent"></div>
				<div class="tab-pane fade" id="twoPointCalcContent"></div>
				<div class="tab-pane fade" id="geoFencingContent"></div>
			</div>
			<!-- 页面加载完毕后自动点击地址解析按钮替换内容 -->
 			<script type="text/javascript">
				$(document).ready(function() {
					$("#geoCoding").click();
				});
			</script>
		</div>
</body>
</html>
