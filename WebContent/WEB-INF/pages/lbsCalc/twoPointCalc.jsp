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
		loadBaiduJScript();
	});
	// 提交计算请求
	function postPoint(destPointLng, destPointLat) {
		$.ajax({
			type : "POST",
			url : "reqTwoPointCalc",
			data : {
				destPointLng : destPointLng,
				destPointLat : destPointLat
			},
			success : function(returnData) {
				alert("所有员工至目标点的平均直线距离为：" + returnData + " 米");
			}
		});
	}
	// 百度地图 API 功能
	function loadBaiduJScript() {
		// 如果资源内没 JS 则加载并回调
		if(typeof(window.BMap_loadScriptTime)=="undefined"){
			$.getScript('http://api.map.baidu.com/api?v=2.0&ak=${pageParaMap.bdAPIKey}&callback=initBaiduMap()')
		}
		// JS 已加载，直接初始化地图实例
		else{
			initBaiduMap();	
		}
	}
	// 地图实例初始化
	function initBaiduMap() {
		// 创建 Map 实例
		var map = new BMap.Map("mapContent"); 
		// 默认设定 MBP 上海的坐标点 
	    var mbpShPoint = new BMap.Point(121.538487, 31.223365);
		// 设定覆盖物
	    var mbpShMarker = new BMap.Marker(mbpShPoint);
	    map.addOverlay(mbpShMarker);
	    mbpShMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
		// 设定地图左上角比例尺和平移控件
	    var topLeftControl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
		var topLeftNavigation = new BMap.NavigationControl();
		map.addControl(topLeftControl);        
		map.addControl(topLeftNavigation);
		// 绑定点击事件
		map.addEventListener("click",function(e){
			// 设置地图
			map.clearOverlays();
			var destPoint = new BMap.Point(e.point.lng, e.point.lat);
			var destMarker = new BMap.Marker(destPoint);
			map.addOverlay(destMarker);
			destMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
			map.centerAndZoom(destPoint, map.getZoom());
			// 发起请求
			postPoint(e.point.lng, e.point.lat);
		});
		// 设定返回公司控件
		BackToCompanyControl.prototype = new BMap.Control();
		BackToCompanyControl.prototype.initialize = function(map){
			  // 创建 DOM 元素
			  var div = document.createElement("div");
			  // 添加文字说明
			  div.appendChild(document.createTextNode("返回公司"));
			  // 设置样式
			  div.style.cursor = "pointer";
			  div.style.border = "1px solid gray";
			  div.style.backgroundColor = "white";
			  // 绑定事件事件
			  div.onclick = function(e){
				  map.clearOverlays();
				  map.addOverlay(mbpShMarker);
				  mbpShMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
				  map.centerAndZoom(mbpShPoint, 18);
			  }
			  // 添加 DOM 元素到地图中
			  map.getContainer().appendChild(div);
			  // 将 DOM 元素返回
			  return div;
			}
			// 创建控件
			var backToCtrl = new BackToCompanyControl();
			// 添加到地图当中
			map.addControl(backToCtrl);
		// 设定缩放级别
	    map.centerAndZoom(mbpShPoint, 18);
		// 开启滚轮缩放功能
	    map.enableScrollWheelZoom();   
	    map.enableContinuousZoom();
	}
	// 百度地图返回公司控件
	function BackToCompanyControl(){
	  // 停靠位置和偏移量
	  this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
	  this.defaultOffset = new BMap.Size(10, 10);
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
