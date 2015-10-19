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
	//子功能加载后绑定事件
	$(document).ready(function() {
		$("#staffPageNav").click(function(event) {
			loadStaffListContent(event.target.id);
		});
		//监听列表模态按钮 class，dbtn 为不存在的样式 
		$('.dbtn').click(function(event) {
			loadStaffDetailInfo(event.target.id);
		});
		//监听列新增按钮 class，adbtn 为不存在的样式 
		$('.adbtn').click(function(event) {
			loadAddStaffModal();
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
	function loadStaffDetailInfo(staffId){ 
		$.ajax({
			type : "GET",
			url: "staffDetail&reqStaffId=" + staffId,
			success : function(data) {
				$("#staffInfoModal").html(data);
			}
		});
	}
	function loadAddStaffModal(){
		$.ajax({
			type : "GET",
			url : "addStaffInfo",
			success : function(data) {
				$("#addStaffInfoModal").html(data);
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
					class="btn adbtn btn-xs btn-default" data-toggle="modal" data-target="#addStaffInfoModal">新增数据</button>
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
						<c:if test="${pageParaMap.staffInfoList != null && pageParaMap.staffInfoList.size() > 0}">
							<c:forEach items="${pageParaMap.staffInfoList}" var="currStaff">
								<tr>
									<td>${currStaff.recordId}</td>
									<td>${currStaff.staffId}</td>
									<td>${currStaff.staffName}</td>
									<td>${currStaff.staffComId}</td>
									<td>${currStaff.staffDeptId}</td>
									<td>${currStaff.staffLevelId }</td>
									<td>${currStaff.staffTel}</td>
									<td>${currStaff.staffAddr}</td>
									<td><button id="${currStaff.staffId}" type="button" data-toggle="modal" data-target="#staffInfoModal"
											class="dbtn btn btn-xs btn-default" >查看详情</button></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<nav style="text-align: center">
					<ul class="pagination" id="staffPageNav">
						<%-- 若为第一页不显示左箭头 --%>
						<c:if test="${pageParaMap.currPage gt 1}">
							<li><a href="#" id="${pageParaMap.currPage -1}">&laquo;</a></li>
						</c:if>
						<%-- 循环设置页码 --%>
						<c:choose>
							<%-- 当页面总数大于 11 并且当前页面大于 6 --%>
							<c:when test="${pageParaMap.totalCount/20 + 1 gt 11 and pageParaMap.currPage gt 6}">
								<c:choose>
									<%-- 当当前页 + 6 页码大于最大页数，不滚动显示 --%>
									<c:when test="${pageParaMap.currPage + 6 gt pageParaMap.totalCount/20 + 1 }">
										<c:forEach var="pageNum" begin="${pageParaMap.totalCount/20 + 1 - 11 }" end="${pageParaMap.totalCount/20 + 1 }">
											<li id="staffPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
										</c:forEach>
									</c:when>
									<%-- 页码滚动 --%>
									<c:otherwise>
										<c:forEach var="pageNum" begin="${pageParaMap.currPage - 5}" end="${pageParaMap.currPage + 5 }">
											<li id="staffPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:when>
							<%-- 当页面总数小于 11 --%>
							<c:when test="${pageParaMap.totalCount/20 + 1 lt 11}">
								<c:forEach var="pageNum" begin="1" end="${pageParaMap.totalCount/20 + 1}">
									<li id="staffPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
								</c:forEach>
							</c:when>
							<%-- 总页数大于 11 当前页小于 6 --%>
							<c:otherwise>
								<c:forEach var="pageNum" begin="1" end="11">
									<li id="staffPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<%-- 若为最后一页不显示右箭头 --%>
						<c:if test="${pageParaMap.currPage lt pageParaMap.totalCount/20}">
							<li><a href="#" id="${pageParaMap.currPage + 1}" >&raquo;</a></li>
						</c:if>
					</ul>
				</nav>
				<!-- 新增模态框，通过 AJAX 进行替换 -->
				<div class="modal fade" id="addStaffInfoModal" tabindex="-1"
					role="dialog"></div>
				<!-- 员工详情模态框，通过 AJAX 进行替换 -->
				<div class="modal fade" id="staffInfoModal" tabindex="-1"
					role="dialog"></div>
				<!-- 页面加载完毕设置分页激活样式 -->
				<script type="text/javascript">
					$(document).ready(function() {
						$("#staffPageNav${pageParaMap.currPage}").addClass("active");
					});
				</script>
			</div>
		</div>
</body>
</html>
