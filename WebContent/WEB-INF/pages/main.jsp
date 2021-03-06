<%--
  Created by Sharuru
  Date: 2015/10/3 0003
  Time: 16:17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签用以自适用布局匹配，所以*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<title>Project Honoka</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${pageParaMap.baiduAPIKey }"></script>
<script type="text/javascript">
	//绑定主导航栏点击事件
	$(document).ready(function() {
		$("#mainNav").click(function(event) {
			loadContent(event.target.id);
		});
	});
	//AJAX 获取子页面的内容
	function loadContent(dest) {
		$.ajax({
			cache: false,
			type : "GET",
			url : dest,
			success : function(data) {
				$("#" + dest + "Content").html(data);
			}
		});
	}
</script>
</head>
<body role="document">
	<div class="container theme-showcase" style="width:95%" role="main">
		<div style="margin-top: 20px;" class="jumbotron">
			<h1>
				<b>PROJECT HONOKA</b>
			</h1>
			<p>MBPSH Location Based Service Center</p>
		</div>
		<!-- 导航 -->
		<ul id="mainNav" class="nav nav-pills">
			<li class="active"><a href="#dashboardContent" id="dashboard"
				data-toggle="tab">仪表盘</a></li>
			<li><a href="#staffAdminContent" id="staffAdmin"
				data-toggle="tab">员工数据管理</a></li>
			<li><a href="#metroAdminContent" id="metroAdmin"
				data-toggle="tab">地铁站点数据管理</a></li>
			<li><a href="#lbsApplContent" id="lbsAppl"
				data-toggle="tab">LBS 应用<span class="badge">Beta!</span></a></li>
<%--			<li><a href="#importStaffContent" id="importStaff"
				data-toggle="tab" >员工数据批量导入</a></li>--%>
		</ul>
		<!-- 页面内容区，通过 AJAX 进行替换 -->
		<div id="mainContent" class="tab-content" style="margin-top: 20px;">
			<div class="tab-pane fade in active" id="dashboardContent"></div>
			<div class="tab-pane fade" id="staffAdminContent"></div>
			<div class="tab-pane fade" id="metroAdminContent"></div>
			<div class="tab-pane fade" id="lbsApplContent"></div>
			<div class="tab-pane fade" id="importStaffContent"></div>
		</div>
		<!-- 页面加载完毕后自动点击仪表盘按钮替换内容 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$("#dashboard").click();
			});
		</script>
	</div>
</body>
</html>
