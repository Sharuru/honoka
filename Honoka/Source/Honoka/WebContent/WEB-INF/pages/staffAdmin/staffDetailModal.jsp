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
	var staffInfoUpdateConfirm = false;
	var staffInfoDeleteConfirm = false;
	$(document).ready(function() {
		// 监听解析按钮 class，sdrgbtn 为不存在的样式 
		$('.sdrgbtn').click(function(event) {
			reqGeoCodingByInput();
		});
		// 更新员工信息
		$('#btnReqUpdateStaffInfo').on('click', function () {
			if(staffInfoUpdateConfirm){
			    var $btn = $(this).button('loading')
			    reqUpdateStaffInfo();
			}
			else{
				document.getElementById("btnReqUpdateStaffInfo").innerText = "确定保存";
				staffInfoUpdateConfirm = true;
			}
		});
		// 删除员工信息
		$('#btnReqDeleteStaffInfo').on('click', function () {
			if(staffInfoDeleteConfirm){
			    var $btn = $(this).button('loading')
			    reqDeleteStaffInfo();
			}
			else{
				document.getElementById("btnReqDeleteStaffInfo").innerText = "确定删除";
				staffInfoDeleteConfirm = true;
			}
		});		
		// 延迟加载百度地图以刷新 DOM
		setTimeout(function(){initStaffDetailBaiduMap();},200);
	});
	// 员工信息详情地图实例初始化
	function initStaffDetailBaiduMap() {
		// 创建 Map 实例
		var map = new BMap.Map("staffDetailMapContent",{enableMapClick:false}); 
		// 默认设定记录坐标点 
	    var orgPoint = new BMap.Point(${pageParaMap.staffPoint.baiduRecordLng }, ${pageParaMap.staffPoint.baiduRecordLat });
		// 设定覆盖物
	    var orgMarker = new BMap.Marker(orgPoint);
	    map.addOverlay(orgMarker);
	    //orgMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
		// 设定地图左上角比例尺和平移控件
	    var topLeftControl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
		var topLeftNavigation = new BMap.NavigationControl();
		map.addControl(topLeftControl);        
		map.addControl(topLeftNavigation);
		// 设定缩放级别
	    map.centerAndZoom(orgPoint, 16);
		// 开启滚轮缩放功能
	    map.enableScrollWheelZoom();   
	    map.enableContinuousZoom();
	}
	function reqGeoCodingByInput(){
		var map = new BMap.Map("staffDetailMapContent",{enableMapClick:false});
		// 创建地址解析器实例
		var reqGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		var newReqAddr = document.getElementById("inputStaffAddr").value.trim();
		document.getElementById("inputStaffAddr").value = " " + newReqAddr;
		reqGeo.getPoint(newReqAddr, function(point){
			if (point) {
				var newMarker = new BMap.Marker(point);
				map.addOverlay(newMarker);
				//newMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
				var topLeftControl = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});
				var topLeftNavigation = new BMap.NavigationControl();
				map.addControl(topLeftControl);        
				map.addControl(topLeftNavigation);
				map.centerAndZoom(point, 16);
			    map.enableScrollWheelZoom();   
			    map.enableContinuousZoom();
			    //回写界面信息
			    document.getElementById("inputStallPoint").value = " " + point.lng + "," + point.lat;
			}else{
				alert("选择的地址没有解析到结果!");
			}
		});
	}
	function reqUpdateStaffInfo(){
		$.ajax({
			type : "POST",
			url : "reqUpdateStaffInfo",
			data : {
				staffId: document.getElementById("inputStaffId").value.trim(),
				staffName: document.getElementById("inputStaffName").value.trim(),
				staffComId: document.getElementById("selectCom").value.trim(),
				staffDeptId: document.getElementById("selectDept").value.trim(),
				staffLevelId: document.getElementById("selectLevel").value.trim(),
				staffTel: document.getElementById("inputStaffTel").value.trim(),
				staffAddr: document.getElementById("inputStaffAddr").value.trim(),
				staffPointLng: document.getElementById("inputStallPoint").value.trim().split(",")[0],
				staffPointLat: document.getElementById("inputStallPoint").value.trim().split(",")[1]
			},success : function(data) {
				$('#btnReqUpdateStaffInfo').button('reset');
				$('#staffInfoModal').modal('hide');
				// 成功后返回第一页，理论上应该停留在当前页
				setTimeout(function(){loadStaffListContent(1);},200);
				//loadStaffListContent(1);
			}
		});
	}
	function reqDeleteStaffInfo(){
		$.ajax({
			type : "POST",
			url : "reqDeleteStaffInfo",
			data : {
				staffId: document.getElementById("inputStaffId").value.trim()
			},success : function(data) {
				$('#btnReqDeleteStaffInfo').button('reset');
				$('#staffInfoModal').modal('hide');
				// 成功后返回第一页，理论上应该停留在当前页
				setTimeout(function(){loadStaffListContent(1);},200);
				//loadStaffListContent(1);
			}
		});
	}
</script>
</head>
<body>
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">
					<b>员工详情</b>
				</h4>
			</div>
			<div class="modal-body">
	 			<div class="container-fluid">
	              <div class="row">
	                <div class="col-xs-8 col-sm-6">
						<div class="input-group">
							<span class="input-group-addon" >员工工号</span> 
							<input id="inputStaffId" type="text" class="form-control" disabled value="&nbsp;${pageParaMap.staffDetail.staffId}">
						</div>
						<div class="input-group">
							<span class="input-group-addon" >员工姓名</span> 
							<input id="inputStaffName" type="text" class="form-control" placeholder="员工姓名" value="&nbsp;${pageParaMap.staffDetail.staffName}">
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
							  <select class="form-control" id="selectLevel">
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
							<span class="input-group-addon" >联系电话</span>
							<input id="inputStaffTel" type="text" class="form-control" placeholder="联系电话" value="&nbsp;${pageParaMap.staffDetail.staffTel}">
						</div>
						<div class="input-group">
							<span class="input-group-addon" >家庭住址</span> 
							<input id="inputStaffAddr" type="text" class="form-control" placeholder="家庭住址" value="&nbsp;${pageParaMap.staffDetail.staffAddr}">
							<span class="input-group-btn">
								<button class="btn btn-default sdrgbtn" type="button" id="btnReqGeoCoding">解析</button>
							</span>
						</div>
						<div class="input-group">
							<span class="input-group-addon" >解析坐标</span> 
							<input type="text" class="form-control" placeholder="StaffPoint" id="inputStallPoint" disabled value="&nbsp;${pageParaMap.staffPoint.baiduRecordLng },${pageParaMap.staffPoint.baiduRecordLat }">
						</div>
	                </div>
	                <div class="col-xs-4 col-sm-6">
	                  <div id="staffDetailMapContent" style="height: 272px"></div>
	                </div>
	              </div>
	            </div>
          	</div>
			<div class="modal-footer">
				<button type="button" id="btnReqUpdateStaffInfo"  data-loading-text="正在保存…" class="btn btn-success" autocomplete="off">保存</button>
				<button type="button" id="btnReqDeleteStaffInfo"  data-loading-text="正在删除…" class="btn btn-danger" autocomplete="off">删除</button>
			</div>
		</div>
	</div>
</body>
</html>