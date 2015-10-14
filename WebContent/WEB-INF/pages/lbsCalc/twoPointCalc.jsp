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
	function postPoint(destPointLng, destPointLat) {
		$.ajax({
			type : "POST",
			url : "reqTwoPointCalc",
			data : {
				destPointLng : destPointLng,
				destPointLat : destPointLat
			},
			success : function(returnData, textResponse) {
				alert("所有员工至目标点的平均直线距离为：" + returnData + " 米");
				var map = new BMap.Map("mapContent");
				var point = new BMap.Point(destPointLng, destPointLat);
				var marker = new BMap.Marker(point); //设置覆盖物
				var top_left_control = new BMap.ScaleControl({
					anchor : BMAP_ANCHOR_TOP_LEFT
				});// 左上角，添加比例尺
				var top_left_navigation = new BMap.NavigationControl(); //左上角，添加默认缩放平移控件
				map.addEventListener("click", function(e) {
					postPoint(e.point.lng, e.point.lat);
				});
				// 定义一个控件类,即function
				function ZoomControl() {
					// 默认停靠位置和偏移量
					this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
					this.defaultOffset = new BMap.Size(10, 10);
				}
				// 通过JavaScript的prototype属性继承于BMap.Control
				ZoomControl.prototype = new BMap.Control();
				// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
				// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
				ZoomControl.prototype.initialize = function(map) {
					// 创建一个DOM元素
					var div = document.createElement("div");
					// 添加文字说明
					div.appendChild(document.createTextNode("返回公司"));
					// 设置样式
					div.style.cursor = "pointer";
					div.style.border = "2px solid gray";
					div.style.backgroundColor = "white";
					// 绑定事件
					div.onclick = function(e) {
						map.clearOverlays(); 
						var point = new BMap.Point(121.538487, 31.223365); // 创建点坐标
						var marker = new BMap.Marker(point); //设置覆盖物
						map.addOverlay(marker);
						marker.setAnimation(BMAP_ANIMATION_BOUNCE);
						map.panTo(point);
						map.setZoom(15);
					}
					// 添加DOM元素到地图中
					map.getContainer().appendChild(div);
					// 将DOM元素返回
					return div;
				}
				// 创建控件
				var myZoomCtrl = new ZoomControl();
				// 添加到地图当中
				map.addControl(myZoomCtrl);
				map.centerAndZoom(point, 15);
				map.addOverlay(marker);
				marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
				map.addControl(top_left_control);
				map.addControl(top_left_navigation);
				map.enableScrollWheelZoom(); //启用滚轮放大缩小
			}
		});
	}
	//百度地图API功能
	function loadJScript() {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "http://api.map.baidu.com/api?v=2.0&ak=${bdAPIKey}&callback=init";
		document.body.appendChild(script);
	}
	function init() {
		var map = new BMap.Map("mapContent"); // 创建Map实例
		var point = new BMap.Point(121.538487, 31.223365); // 创建点坐标
		var marker = new BMap.Marker(point); //设置覆盖物
		var top_left_control = new BMap.ScaleControl({
			anchor : BMAP_ANCHOR_TOP_LEFT
		});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl(); //左上角，添加默认缩放平移控件
		map.addEventListener("click", function(e) {
			postPoint(e.point.lng, e.point.lat);
		});
		// 定义一个控件类,即function
		function ZoomControl() {
			// 默认停靠位置和偏移量
			this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
			this.defaultOffset = new BMap.Size(10, 10);
		}
		// 通过JavaScript的prototype属性继承于BMap.Control
		ZoomControl.prototype = new BMap.Control();
		// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
		// 在本方法中创建个div元素作为控件的容器,并将其添加到地图容器中
		ZoomControl.prototype.initialize = function(map) {
			// 创建一个DOM元素
			var div = document.createElement("div");
			// 添加文字说明
			div.appendChild(document.createTextNode("返回公司"));
			// 设置样式
			div.style.cursor = "pointer";
			div.style.border = "2px solid gray";
			div.style.backgroundColor = "white";
			// 绑定事件
			div.onclick = function(e) {
				map.clearOverlays(); 
				var point = new BMap.Point(121.538487, 31.223365); // 创建点坐标
				var marker = new BMap.Marker(point); //设置覆盖物
				map.addOverlay(marker);
				marker.setAnimation(BMAP_ANIMATION_BOUNCE);
				map.panTo(point);
				map.setZoom(15);
			}
			// 添加DOM元素到地图中
			map.getContainer().appendChild(div);
			// 将DOM元素返回
			return div;
		}
		// 创建控件
		var myZoomCtrl = new ZoomControl();
		// 添加到地图当中
		map.addControl(myZoomCtrl);
		map.centerAndZoom(point, 15);
		map.addOverlay(marker);
		marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		map.addControl(top_left_control);
		map.addControl(top_left_navigation);
		map.enableScrollWheelZoom(); //启用滚轮放大缩小
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
