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
					<span class="input-group-addon" id="basic-addon1">解析坐标</span> <input
						type="text" class="form-control" placeholder="StaffPoint"
						aria-describedby="basic-addon1">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" data-dismiss="modal">保存</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">删除</button>
			</div>
		</div>
	</div>
</body>
</html>