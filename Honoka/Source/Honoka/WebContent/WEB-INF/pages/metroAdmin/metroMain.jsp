<%--
  Created by Sharuru
  Date: 2015/10/10 0008
  Time: 17:08
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//子功能加载后绑定点击事件
	$(document).ready(function() {
		$("#metroPageNav").click(function(event) {
			loadMetroListContent(event.target.id);
		});
		$('#btnReqRefreshMetroInfo').on('click', function () {
		    var $btn = $(this).button('loading')
		    reqRefreshMetroInfo();
		})
	});
	//AJAX 获取子页面的内容
	function loadMetroListContent(dest) {
		$.ajax({
			type : "GET",
			url : "metroAdmin&reqPage=" + dest,
			success : function(data) {
				$("#metroListDiv").html(data);
			}
		});
	}
	function reqRefreshMetroInfo(){
		$.ajax({
			type : "GET",
			url : "reqRefreshMetroInfo",
			success : function(data) {
				$('#btnReqRefreshMetroInfo').button('reset');
				$("#metroAdmin").click();
			}
		});
	}
</script>
</head>
<body>
	<div id="metroListDiv">
		<div class="panel panel-default">
			<div class="panel-heading">
				地铁站点数据一览
					<button type="button" style="float: right;" id="btnReqRefreshMetroInfo" data-loading-text="正在更新……" class="btn btn-warning btn-xs" autocomplete="off">
						  更新数据
					</button>
			</div>
			<div class="panel-body">
				<table class="table table-hover table-striped  table-condensed"
					contenteditable="false">
					<thead>
						<tr>
							<th>编号</th>
							<th>线路名称</th>
							<th>站点名称</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${pageParaMap.metroInfoList != null && pageParaMap.metroInfoList.size() > 0}">
							<c:forEach items="${pageParaMap.metroInfoList}" var="currStation">
								<tr>
									<td>${currStation.recordId}</td>
									<td>${currStation.lineName}</td>
									<td>${currStation.staName}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<nav style="text-align: center">
					<ul class="pagination" id="metroPageNav">
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
											<li id="metroPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
										</c:forEach>
									</c:when>
									<%-- 页码滚动 --%>
									<c:otherwise>
										<c:forEach var="pageNum" begin="${pageParaMap.currPage - 5}" end="${pageParaMap.currPage + 5 }">
											<li id="metroPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:when>
							<%-- 当页面总数小于 11 --%>
							<c:when test="${pageParaMap.totalCount/20 + 1 lt 11}">
								<c:forEach var="pageNum" begin="1" end="${pageParaMap.totalCount/20 + 1}">
									<li id="metroPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
								</c:forEach>
							</c:when>
							<%-- 总页数大于 11 当前页小于 6 --%>
							<c:otherwise>
								<c:forEach var="pageNum" begin="1" end="11">
									<li id="metroPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						<%-- 若为最后一页不显示右箭头 --%>
						<c:if test="${pageParaMap.currPage lt pageParaMap.totalCount/20}">
							<li><a href="#" id="${pageParaMap.currPage + 1}" >&raquo;</a></li>
						</c:if>
					</ul>
				</nav>
				<!-- 员工详情模态框，通过 AJAX 进行替换 -->
				<div class="modal fade" id="staffInfoModal" tabindex="-1"
					role="dialog"></div>
				<!-- 页面加载完毕设置分页激活样式 -->
				<script type="text/javascript">
					$(document).ready(function() {
						$("#metroPageNav${pageParaMap.currPage}").addClass("active");
					});
				</script>
			</div>
		</div>
</body>
</html>
