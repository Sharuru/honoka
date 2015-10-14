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
		//var script = document.createElement("script");
		//script.type = "text/javascript";
		//debugger; 
		//script.src = "http://api.map.baidu.com/api?v=2.0&ak=${pageParaMap.bdAPIKey}&callback=init";
		//debugger; 
		if(typeof(window.BMap_loadScriptTime)=="undefined"){
			$.getScript('http://api.map.baidu.com/api?v=2.0&ak=${pageParaMap.bdAPIKey}&callback=init')
		}
		//document.getElementById("twoPointContent").appendChild(script);
		//document.body.appendChild(script);
	}
	function init() {
		var map1 = new BMap.Map("mapContent");            // 创建Map实例
	    var point1 = new BMap.Point(116.404, 39.915);  
	    map1.centerAndZoom(point1,15);               
	    map1.enableScrollWheelZoom();   
	}
</script>
</head>
<body>
	<div id="twoPointContent">
		<p>请点击任意位置计算目前系统所有员工至目标点的平均直线距离</p>
		<div id="mapContent" style="height: 630px"></div>
	</div>
</body>
</html>
