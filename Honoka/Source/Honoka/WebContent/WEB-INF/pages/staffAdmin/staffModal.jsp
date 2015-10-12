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
				This is body
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">员工工号</span> <input
						type="text" class="form-control" placeholder="StaffId"
						aria-describedby="basic-addon1">
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">员工姓名</span> <input
						type="text" class="form-control" placeholder="StaffName"
						aria-describedby="basic-addon1">
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">所属公司</span>
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle" type="button"
							id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="true">
							Dropdown <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li><a href="#">Separated link</a></li>
						</ul>
					</div>
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">所属部门</span>
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle" type="button"
							id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="true">
							Dropdown <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li><a href="#">Separated link</a></li>
						</ul>
					</div>
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">员工职称</span>
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle" type="button"
							id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="true">
							Dropdown <span class="caret"></span>
						</button>
						<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li><a href="#">Separated link</a></li>
						</ul>
					</div>
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">联系电话</span> <input
						type="text" class="form-control" placeholder="StaffTel"
						aria-describedby="basic-addon1">
				</div>
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">家庭住址</span> <input
						type="text" class="form-control" placeholder="StaffAddr"
						aria-describedby="basic-addon1">
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