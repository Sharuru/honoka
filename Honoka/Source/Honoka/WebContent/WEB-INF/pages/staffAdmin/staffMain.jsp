<%--
  Created by Sharuru
  Date: 2015/10/10 0008
  Time: 17:02
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//子功能加载后绑定点击事件
	$(document).ready(function() {
		$("#staffPageNav").click(function(event) {
			loadStaffListContent(event.target.id);
		});
		$("#staffModal").click(function(event) {
			loadStaffDetailInfo(event.target.id);
		});
	});
	//AJAX 获取子页面的内容
	function loadStaffListContent(dest) {
		$.ajax({
			type : "GET",
			url : "staffAdmin&reqPage=" + dest,
			success : function(data) {
				$("#staffListDiv").html(data);
			}
		});
	}
	function loadStaffDetailInfo(staffId) {
		$.ajax({
			type : "GET",
			url: "staffDetail&reqStaffId=S0000001",
			//url : "staffDetail&reqStaffId=" + staffId,
			success : function(data) {
				$("#staffInfoModal").html(data);
			}
		});
	}
</script>
</head>
<body>
	<div id="staffListDiv">
		<div class="panel panel-default">
			<div class="panel-heading">
				员工数据一览
				<button style="float: right;" type="button"
					class="btn btn-xs btn-default">新增数据</button>
			</div>
			<div class="panel-body">
				<table class="table table-hover table-striped  table-condensed"
					contenteditable="false">
					<thead>
						<tr>
							<th>编号</th>
							<th>工号</th>
							<th>姓名</th>
							<th>所属公司</th>
							<th>所属部门</th>
							<th>职称</th>
							<th>联系电话</th>
							<th>家庭住址</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${staffInfoList != null && staffInfoList.size() > 0}">
							<c:forEach items="${staffInfoList}" var="currStaff">
								<tr>
									<td>${currStaff.recordId}</td>
									<td>${currStaff.staffId}</td>
									<td>${currStaff.staffName}</td>
									<td>${currStaff.staffComId}</td>
									<td>${currStaff.staffDeptId}</td>
									<td>${currStaff.staffLevelId }</td>
									<td>${currStaff.staffTel}</td>
									<td>${currStaff.staffAddr}</td>
									<td><button id="staffModal" type="button"
											class="btn btn-xs btn-default" data-toggle="modal"
											data-target="#staffInfoModal">查看详情</button></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>

				<nav style="text-align: center">
					<ul class="pagination" id="staffPageNav">
						<!-- 若为第一页不显示左箭头 -->
						<c:if test="${currPage gt 1}">
							<li><a href="#" id="${currPage -1}" >&laquo;</a></li>
						</c:if>
						<!-- 循环设置页码 -->
						<c:forEach var="pageNum" begin="1" end="${totalCount/2+1}">
							<li id="staffPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
						</c:forEach>
						<!-- 若为最后一页不显示右箭头 -->
						<c:if test="${currPage lt totalCount/2}">
							<li><a href="#" id="${currPage + 1}" >&raquo;</a></li>
						</c:if>
					</ul>
				</nav>
				<!-- 员工详情模态框，通过 AJAX 进行替换 -->
				<div class="modal fade" id="staffInfoModal" tabindex="-1"
					role="dialog"></div>
				<!-- 页面加载完毕设置分页激活样式 -->
				<script type="text/javascript">
					$(document).ready(function() {
						$("#staffPageNav${currPage}").addClass("active");
					});
				</script>
			</div>
		</div>
</body>
</html>
