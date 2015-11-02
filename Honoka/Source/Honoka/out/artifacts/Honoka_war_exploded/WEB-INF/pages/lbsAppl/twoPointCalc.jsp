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
    var currPoint;
	$(document).ready(function() {
		// 延迟加载以给 DOM 元素切换属性的处理时间
		setTimeout(function(){initBaiduMap(1);},200);
		// 绑定搜索事件
		$('#btnReqPlaceSearch').on('click', function () {
			reqPOIList(document.getElementById("inputPlaceSearch").value.trim(),1);
		});
	});
	// 提交 POI 检索请求
	function reqPOIList(reqKeyword, reqPage){
        // 清空结果面板
        document.getElementById("directionCalcResultDiv").innerText = "";
        $('#btnReqPlaceSearch').button('loading');
		$.ajax({
			cache : false,
			type : "POST",
			url : "reqPOIList&reqPage=" + reqPage,
			data : {
				reqKeyword : reqKeyword
			},
			success : function(returnData) {
                $('#btnReqPlaceSearch').button('reset');
                $("#placeSearchResult").html(returnData);
			}
		});
	}
    //
	// 提交距离计算请求
	function postPoint(destPointLng, destPointLat) {
		$.ajax({
			cache : false,
			type : "POST",
			url : "reqTwoPointCalc",
			data : {
				destPointLng : destPointLng,
				destPointLat : destPointLat
			},
			success : function(returnData) {
                document.getElementById("directionCalcResultDiv").innerText = "计算结果：" + "\n" + "平均直线距离：" + returnData;
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
        currPoint = mbpShPoint;
		// 设定覆盖物
        var fencingCircle;
	    var mbpShMarker = new BMap.Marker(mbpShPoint);
	    lbsMap.addOverlay(mbpShMarker);
	    //mbpShMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
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
                currPoint = destPoint;
				var destMarker = new BMap.Marker(destPoint);
				lbsMap.addOverlay(destMarker);
				//destMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
				lbsMap.centerAndZoom(destPoint, lbsMap.getZoom());
                // 去除 list 样式
                $("a[class='list-group-item active']").removeClass("active");
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
            div.id = "resetMapDivCtrl";
            // 设置样式
            div.style.cursor = "pointer";
            div.style.border = "1px solid gray";
            div.style.backgroundColor = "white";
            // 绑定事件事件
            div.onclick = function(e){
                initBaiduMap(1);
                //清空检索列表
                $("#placeSearchResult").empty();
                document.getElementById("inputPlaceSearch").value="";
            };
            // 添加 DOM 元素到地图中
            lbsMap.getContainer().appendChild(div);
            // 将 DOM 元素返回
            return div;
            };
        // 创建控件
        var backToCtrl = new ResetMapControl();
        // 添加到地图当中
        lbsMap.addControl(backToCtrl);
        document.getElementById("resetMapDivCtrl").className += " btn";
        // 行程计算控件
        DirectionCalcControl.prototype = new BMap.Control();
        DirectionCalcControl.prototype.initialize = function(lbsMap){
            // 创建 DOM 元素
            var div = document.createElement("div");
            // 添加文字说明
            div.appendChild(document.createTextNode("行程计算"));
            div.id = "directionCalcDiv";
            // 设置样式
            div.style.cursor = "pointer";
            div.style.border = "1px solid gray";
            div.style.backgroundColor = "white";
            // 绑定事件事件
            div.onclick = function(e){
                document.getElementById("directionCalcDiv").innerText = "正在计算…";
                $.ajax({
                	cache : false,
                    type : "POST",
                    url : "reqDirectionCalc",
                    data : {
                        destPointLng : currPoint.lng,
                        destPointLat : currPoint.lat
                    },
                    success : function(returnData) {
                        document.getElementById("directionCalcDiv").innerText = "行程计算";
                        if(document.getElementById("directionCalcResultDiv").innerText == ""){
                            document.getElementById("directionCalcResultDiv").innerText += "计算结果：";
                        }
                        document.getElementById("directionCalcResultDiv").innerText += "\n"
                               + "平均自驾: " + returnData[0] + "（"+ returnData[1] + "）" + "\n"
                        + "平均交通: " + returnData[2] + "（"+ returnData[3] + "）";
                    }
                });
            };
            // 添加 DOM 元素到地图中
            lbsMap.getContainer().appendChild(div);
            // 将 DOM 元素返回
            return div;
        };
        // 创建控件
        var directCalcCtrl = new DirectionCalcControl();
        // 添加到地图当中
        lbsMap.addControl(directCalcCtrl);
        document.getElementById("directionCalcDiv").className += " btn";
        // 计算结果控件
        DirectionCalcResultControl.prototype = new BMap.Control();
        DirectionCalcResultControl.prototype.initialize = function(lbsMap){
            // 创建 DOM 元素
            var div = document.createElement("div");
            div.id = "directionCalcResultDiv";
            div.style.border = "1px solid gray";
            div.style.backgroundColor = "white";
            // 添加 DOM 元素到地图中
            lbsMap.getContainer().appendChild(div);
            // 将 DOM 元素返回
            return div;
        };
        // 创建控件
        var directionCalcResultCtrl = new DirectionCalcResultControl();
        // 添加到地图当中
        lbsMap.addControl(directionCalcResultCtrl);
        document.getElementById("directionCalcResultDiv").className += " well well-sm";
        // 围栏计算控件
        FencingCalcControl.prototype = new BMap.Control();
        FencingCalcControl.prototype.initialize = function(lbsMap){
            // 创建 DOM 元素
            var div = document.createElement("div");
            // 添加文字说明
            div.appendChild(document.createTextNode("范围搜索"));
            div.id = "fencingCalcDiv";
            // 设置样式
            div.style.cursor = "pointer";
            div.style.border = "1px solid gray";
            div.style.backgroundColor = "white";
            // 绑定事件事件
            div.onclick = function(e){
                // 移除自带的点击属性
                $("#fencingCalcDiv").removeAttr("onclick");
                // 调整样式
                $("#fencingCalcDiv").css("width","200px");
                // 替换属性
                document.getElementById("fencingCalcDiv").innerHTML =
                        '<div class="input-group input-group-sm">' +
                        ' <span class="input-group-addon">在</span>' +
                        '<input id="inputStaffReqRange" type="text" class="form-control" >' +
                        '<span class="input-group-addon">米</span>' +
                        '<span class="input-group-btn">' +
                        '<button id="btnReqStaffFencing" class="btn btn-default" type="button" data-loading-text="正在搜索…" autocomplete="off">搜索</button>' +
                        '</span></div>';
                //alert(currPoint.lng + ", " + currPoint.lat);
                $("#btnReqStaffFencing").click(function(event) {
                    $('#btnReqStaffFencing').button('loading');
                    var reqRange =  document.getElementById("inputStaffReqRange").value.trim();
                    $.ajax({
                    	cache : false,
                        type : "POST",
                        url : "reqStaffFencing&reqPage=1",
                        data : {
                            oLng : currPoint.lng,
                            oLat : currPoint.lat,
                            //reqRange : 50000
                            reqRange : reqRange
                        },
                        success : function(returnData) {
                            // 添加覆盖物
                            lbsMap.removeOverlay(fencingCircle);
                            fencingCircle = new BMap.Circle(currPoint,reqRange,{fillColor:"blue", strokeWeight: 1 ,fillOpacity: 0.3, strokeOpacity: 0.3});
                            lbsMap.addOverlay(fencingCircle);
                            if(reqRange < 60){
                                lbsMap.zoomTo(19)
                            }
                            else if(reqRange < 200){
                                lbsMap.zoomTo(18)
                            }
                            else if(reqRange < 300){
                                lbsMap.zoomTo(17)
                            }
                            else if(reqRange < 600){
                                lbsMap.zoomTo(16)
                            }
                            else if(reqRange < 1000){
                                lbsMap.zoomTo(15)
                            }
                            else if(reqRange < 6000){
                                lbsMap.zoomTo(14)
                            }
                            else if(reqRange < 11000){
                                lbsMap.zoomTo(13)
                            }
                            else if(reqRange < 16000){
                                lbsMap.zoomTo(12)
                            }
                            else{
                                lbsMap.zoomTo(11);
                            }
                            $('#btnReqStaffFencing').button('reset');
                            $("#placeSearchResult").html(returnData);
                        }
                    });
                });
            };
            // 添加 DOM 元素到地图中
            lbsMap.getContainer().appendChild(div);
            // 将 DOM 元素返回
            return div;
        };
        // 创建控件
        var fencingCalcCtrl = new FencingCalcControl();
        // 添加到地图当中
        lbsMap.addControl(fencingCalcCtrl);
        document.getElementById("fencingCalcDiv").className += " btn";
      /*  // 设定右键菜单
        var rightMenu = new BMap.ContextMenu();
        var rightMenuItem = [
            {
                text:"在此处附近查找…",
                callback:function(e){
                    passMap.clearOverlays();
                    lbsMap.addOverlay( new BMap.Marker(e));
                    lbsMap.panTo(e);
                   // alert("go msg" + e + e.lng);
                }
            }
        ];
        var rightMenuOpts ={width:175};
        for(var i=0;i<rightMenuItem.length;i++){
            rightMenu.addItem(new BMap.MenuItem(rightMenuItem[i].text,rightMenuItem[i].callback,rightMenuOpts));
        }
        lbsMap.addContextMenu(rightMenu);*/
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
    // 百度地图行程计算控件
    function DirectionCalcControl(){
        this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
        this.defaultOffset = new BMap.Size(10, 50);
    }
    // 百度地图围栏计算控件
    function FencingCalcControl(){
        this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
        this.defaultOffset = new BMap.Size(10,90);
    }
    // 百度地图行程计算结果
    function DirectionCalcResultControl(){
        this.defaultAnchor = BMAP_ANCHOR_BOTTOM_RIGHT;
        this.defaultOffset = new BMap.Size(5, 5);
    }
</script>
</head>
<body>
	<div id="twoPointContent">
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-4 col-sm-4">
					<div class="panel panel-default">
					  <div class="panel-body">
					    <div class="input-group">
					      <input type="text" id="inputPlaceSearch" class="form-control" placeholder="请输入兴趣点关键字…">
					      <span class="input-group-btn">
					        <button class="btn btn-default" type="button" id="btnReqPlaceSearch" data-loading-text="正在检索…" autocomplete="off">检索</button>
					      </span>
					    </div>
					  	<div class="panel-body" id="placeSearchResult" style="margin-left:-3.5%;width:107%;height:655px;"> </div>
					  </div>
					</div>
				</div>
				<div class="col-xs-8 col-sm-8">
					<div class="panel panel-default">
						<div class="panel-body">
						 	<div id="lbsMapContent" style="height: 690px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
