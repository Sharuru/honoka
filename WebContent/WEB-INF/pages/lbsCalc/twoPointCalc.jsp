<%--
  Created by Sharuru
  Date: 2015/10/12 0008
  Time: 16:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	$(document).ready(function() {
		loadJScript();
	});
	//百度地图API功能
	function loadJScript() {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "http://api.map.baidu.com/api?v=2.0&ak=${bdAPIKey}&callback=init";
		document.body.appendChild(script);
	}
	function init() {
		var map = new BMap.Map("mapContent"); // 创建Map实例
		var point = new BMap.Point(121.53794, 31.223341); // 创建点坐标
		var marker = new BMap.Marker(point);	//设置覆盖物
		var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
		var overView = new BMap.OverviewMapControl();
		var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});
		map.centerAndZoom(point, 15);
		map.addOverlay(marker);  
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		map.addControl(top_left_control);        
		map.addControl(top_left_navigation);     
		map.addControl(overView);          //添加默认缩略地图控件
		map.addControl(overViewOpen);      //右下角，打开
		map.enableScrollWheelZoom(); //启用滚轮放大缩小
	}
</script>
</head>
<body>
	<div id="twoPointContent">
		<p>请点击任意位置计算目前系统所有员工至目标点的平均直线距离</p>
		<div id="mapContent" style="height:630px"></div>
	</div>

</body>
</html>
