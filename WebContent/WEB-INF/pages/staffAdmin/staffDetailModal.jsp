<%--
  Created by Sharuru
  Date: 2015/10/1 0008
  Time: 15:10
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){initBaiduMap();},200);
	});
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
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<b>员工详情</b>
				</h4>
			</div>
			<div class="modal-body">
				<div class="input-group">
					<span class="input-group-addon" id="inputStaffId">员工工号</span> <input
						type="text" class="form-control" disabled value="&nbsp;${pageParaMap.staffDetail.staffId}">
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="inputStaffName">员工姓名</span> <input
						type="text" class="form-control" placeholder="员工姓名"
						value="&nbsp;${pageParaMap.staffDetail.staffName}">
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="inputCom">所属公司</span>
						<div class="form-group">
						  <select class="form-control" id="selectCom">
							<c:forEach items="${pageParaMap.comMap}" var="map"
								varStatus="id" begin="0">
								<c:choose>
									<c:when test="${pageParaMap.staffDetail.staffComId eq map.key}">
										<option value="${map.key}" selected>${map.value}</option>
									</c:when>
									<c:otherwise>
										<option value="${map.key}">${map.value}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						  </select>
						</div>
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="inputDept">所属部门</span>
						<div class="form-group">
						  <select class="form-control" id="selectDept">
							<c:forEach items="${pageParaMap.deptMap}" var="map"
								varStatus="id" begin="0">
								<c:choose>
									<c:when test="${pageParaMap.staffDetail.staffDeptId eq map.key}">
										<option value="${map.key}" selected>${map.value}</option>
									</c:when>
									<c:otherwise>
										<option value="${map.key}">${map.value}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						  </select>
						</div>
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="inputLevel">员工职称</span>
					<div class="form-group">
						  <select class="form-control" id="selectDept">
							<c:forEach items="${pageParaMap.levelMap}" var="map"
								varStatus="id" begin="0">
								<c:choose>
									<c:when test="${pageParaMap.staffDetail.staffLevelId eq map.key}">
										<option value="${map.key}" selected>${map.value}</option>
									</c:when>
									<c:otherwise>
										<option value="${map.key}">${map.value}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						  </select>
					</div>
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="inputStaffTel">联系电话</span> <input
						type="text" class="form-control" placeholder="联系电话" value="&nbsp;${pageParaMap.staffDetail.staffTel}">
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="inputStaffAddr">家庭住址</span> <input
						type="text" class="form-control" placeholder="家庭住址" value="&nbsp;${pageParaMap.staffDetail.staffAddr}">
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="inputStallPoint">解析坐标</span> <input
						type="text" class="form-control" placeholder="StaffPoint"
						disabled value="&nbsp;${pageParaMap.staffPoint.baiduRecordLng },${pageParaMap.staffPoint.baiduRecordLat }">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal">保存</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">删除</button>
			</div>
			<div id="mapContent" style="height: 630px"></div>
		</div>
	</div>
</body>
</html>