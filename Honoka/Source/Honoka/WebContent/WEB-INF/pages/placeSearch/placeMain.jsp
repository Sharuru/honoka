<%--
  Created by Sharuru
  Date: 2015/10/15 0008
  Time: 19:30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//子功能加载后绑定点击事件
 	$(document).ready(function() {
		$("#placeNav").click(function(event) {
			//loadPlaceSearchContent(event.target.id);
		});
	}); 
	//AJAX 获取子页面的内容
	function loadPlaceSearchContent(dest) {
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
			<!-- 区域检索功能导航 -->
			<ul id="placeNav" class="nav nav-pills">
				<li class="active"><a href="#listPOIContent" id="listPOI"
					data-toggle="tab">已有兴趣点一览</a></li>
				<li><a href="#addNewPOIContent" id="addNewPOI"
					data-toggle="tab">新增兴趣点</a></li>
			</ul>
			<!-- 区域检索页面内容区，通过 AJAX 进行替换 -->
			<div id="lbsContent" class="tab-content" style="margin-top: 20px;">
				<div class="tab-pane fade in active" id="listPOIContent"></div>
				<div class="tab-pane fade" id="addNewPOIContent"></div>
			</div>
			<!-- 页面加载完毕后自动点击地址解析按钮替换内容 -->
 			<script type="text/javascript">
				$(document).ready(function() {
					$("#listPOI").click();
				});
			</script>
		</div>
</body>
</html>
