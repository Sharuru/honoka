<%--
  Created by Sharuru
  Date: 2015/10/12 0008
  Time: 16:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
    //var lbsMap = new BMap.Map("lbsMapContent");
    var passMap;
	$(document).ready(function() {
		// 延迟加载以给 DOM 元素切换属性的处理时间
		setTimeout(function(){initBaiduMap(1);},200);
		// 绑定搜索事件
		$('#btnReqPlaceSearch').on('click', function () {
			//var lbsMap = initBaiduMap(-1);
			/* var local = new BMap.LocalSearch(lbsMap, {
					renderOptions: {
						lbsMap: lbsMap, 
						panel: "placeSearchResult",
						selectFirstResult: false},
						pageCapacity: 6
				});
			// 设置回调函数
			local.setInfoHtmlSetCallback(function(poi){
				postPoint(poi.point.lng, poi.point.lat);
				});
			local.setSearchCompleteCallback(function(poi){
				// 调整结果面板样式
				setTimeout(function(){
					$("#placeSearchResult").children().css("border","");
					$("#placeSearchResult").children().children().children()[1].remove();
				},0);
			});
			local.search(document.getElementById("inputPlaceSearch").value.trim()); */
			// 二阶段：将前端 API 调用转移至后台
			reqPOIList(document.getElementById("inputPlaceSearch").value.trim(),1);
		});
	});
	// 提交 POI 检索请求
	function reqPOIList(reqKeyword, reqPage){
        $('#btnReqPlaceSearch').button('loading');
		$.ajax({
			type : "POST",
			url : "reqPOIList&reqPage=" + reqPage,
			data : {
				reqKeyword : reqKeyword
			},
			success : function(returnData) {
				//alert(returnData);
				//document.getElementById("avgDist").innerText = "系统所有员工至目标点的平均直线距离为：" + returnData;
                $('#btnReqPlaceSearch').button('reset');
                $("#placeSearchResult").html(returnData);
			}
		});
	}
	// 提交距离计算请求
	function postPoint(destPointLng, destPointLat) {
		$.ajax({
			type : "POST",
			url : "reqTwoPointCalc",
			data : {
				destPointLng : destPointLng,
				destPointLat : destPointLat
			},
			success : function(returnData) {
				document.getElementById("avgDist").innerText = "系统所有员工至目标点的平均直线距离为：" + returnData;
			}
		});
	}
	// 地图实例初始化
	function initBaiduMap(initType) {
		// 创建 Map 实例
		var lbsMap = new BMap.Map("lbsMapContent");
        passMap = lbsMap;
		// 默认设定 MBP 上海的坐标点 
	    var mbpShPoint = new BMap.Point(121.538487, 31.223365);
		// 设定覆盖物
	    var mbpShMarker = new BMap.Marker(mbpShPoint);
	    lbsMap.addOverlay(mbpShMarker);
	    mbpShMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
		// 设定地图左上角比例尺和平移控件
	    var topLeftControl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
		var topLeftNavigation = new BMap.NavigationControl();
		lbsMap.addControl(topLeftControl);        
		lbsMap.addControl(topLeftNavigation);
		if(initType == 1){
			// 绑定点击事件
			lbsMap.addEventListener("click",function(e){
				// 设置地图
				lbsMap.clearOverlays();
				var destPoint = new BMap.Point(e.point.lng, e.point.lat);
				var destMarker = new BMap.Marker(destPoint);
				lbsMap.addOverlay(destMarker);
				destMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
				lbsMap.centerAndZoom(destPoint, lbsMap.getZoom());
				// 发起请求
				postPoint(e.point.lng, e.point.lat);
			});
		}
		// 设定还原状态控件
		ResetMapControl.prototype = new BMap.Control();
		ResetMapControl.prototype.initialize = function(lbsMap){
			  // 创建 DOM 元素
			  var div = document.createElement("div");
			  // 添加文字说明
			  div.appendChild(document.createTextNode("还原状态"));
			  // 设置样式
			  div.style.cursor = "pointer";
			  div.style.border = "1px solid gray";
			  div.style.backgroundColor = "white";
			  // 绑定事件事件
			  div.onclick = function(e){
				  initBaiduMap(1);
				  document.getElementById("avgDist").innerText = "系统所有员工至目标点的平均直线距离为：等待点选";
				  //清空检索列表
				  $("#placeSearchResult").empty();
				  document.getElementById("inputPlaceSearch").value="";
			  }
			  // 添加 DOM 元素到地图中
			  lbsMap.getContainer().appendChild(div);
			  // 将 DOM 元素返回
			  return div;
			}
			// 创建控件
			var backToCtrl = new ResetMapControl();
			// 添加到地图当中
			lbsMap.addControl(backToCtrl);
		// 设定缩放级别
	    lbsMap.centerAndZoom(mbpShPoint, 18);
		// 开启滚轮缩放功能
	    lbsMap.enableScrollWheelZoom();   
	    lbsMap.enableContinuousZoom();
		return lbsMap;
	}
	// 百度地图还原状态控件
	function ResetMapControl(){
	  // 停靠位置和偏移量
	  this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
	  this.defaultOffset = new BMap.Size(10, 10);
	}
</script>
</head>
<body>
	<div id="twoPointContent">
		<div style="margin-left:1.5%;"><p>请点击任意位置计算目前系统所有员工至目标点的平均直线距离</p>
		<p id="avgDist">系统所有员工至目标点的平均直线距离为：等待点选</p>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-4 col-sm-4">
					<div class="panel panel-default">
					  <div class="panel-body">
					    <div class="input-group">
					      <input type="text" id="inputPlaceSearch" class="form-control" placeholder="请输入兴趣点关键字……">
					      <span class="input-group-btn">
					        <button class="btn btn-default" type="button" id="btnReqPlaceSearch" data-loading-text="正在检索……" autocomplete="off">检索</button>
					      </span>
					    </div>
					  	<div class="panel-body" id="placeSearchResult" style="margin-left:-3.5%;width:107%;height:665px;"> </div>
					  </div>
					</div>
				</div>
				<div class="col-xs-8 col-sm-8">
					<div class="panel panel-default">
						<div class="panel-body">
						 	<div id="lbsMapContent" style="height: 700px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
