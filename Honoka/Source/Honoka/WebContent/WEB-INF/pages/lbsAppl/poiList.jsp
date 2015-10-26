<%--
  Created by Sharuru
  Date: 2015/10/22 0008
  Time: 16:46
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	 // 子功能加载后绑定点击事件
	$(document).ready(function() {
		$("#poiPageNav").click(function(event) {
            reqPOIList(document.getElementById("inputPlaceSearch").value.trim(),event.target.id);
		});
	});
</script>
</head>
<body>
    <c:if test="${pageParaMap.poiSearchList != null && pageParaMap.poiSearchList.size() > 0}">
        <c:forEach items="${pageParaMap.poiSearchList}" var="currPOI">
        <div class="list-group">
          <a href="#" class="list-group-item">
                <h5 class="list-group-item-heading">
                    <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>&nbsp;${currPOI.poiName}
                    <span class="list-group-item-text" style="float:right;font-size: 13px;">平均直线距离: ${currPOI.lineDistance}</span>
                </h5>
                <p class="list-group-item-text" style="font-size: 13px">${currPOI.poiAddr}</p>
                <p class="list-group-item-text" style="font-size: 13px">${currPOI.poiTelephone}</p>
                <span class="list-group-item-text" style="font-size: 12px;">平均自驾: ${currPOI.drivingDistance}（${currPOI.drivingDuration}）</span>
                <span class="list-group-item-text" style="font-size: 12px;">平均公共交通: ${currPOI.transitDistance}（${currPOI.transitDuration}）</span>
          </a>
        </div>
        </c:forEach>
    </c:if>
    <nav style="text-align: center;margin-top: -20px;">
        <ul class="pagination" id="poiPageNav">
            <%-- 若为第一页不显示左箭头 --%>
            <c:if test="${pageParaMap.currPage gt 1}">
                <li><a href="#" id="${pageParaMap.currPage -1}">&laquo;</a></li>
            </c:if>
            <%-- 循环设置页码 --%>
            <c:choose>
                <%-- 当页面总数小于 5 --%>
                <c:when test="${pageParaMap.totalCount/5 + 1 le 5}">
                    <c:forEach var="pageNum" begin="1" end="${pageParaMap.totalCount/5 + 1}">
                        <li id="poiPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
                    </c:forEach>
                </c:when>
                <%-- 当页面总数大于 5 并且当前页面大于 3 --%>
                <c:when test="${pageParaMap.totalCount/5 + 1 gt 5 and pageParaMap.currPage gt 3}">
                    <c:choose>
                        <%-- 当当前页 + 3 页码大于最大页数，不滚动显示 --%>
                        <c:when test="${pageParaMap.currPage + 3 gt pageParaMap.totalCount/5 + 1 }">
                            <c:forEach var="pageNum" begin="${pageParaMap.totalCount/5 + 1 - 4 }" end="${pageParaMap.totalCount/5 + 1 }">
                                <li id="poiPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
                            </c:forEach>
                        </c:when>
                        <%-- 页码滚动 --%>
                        <c:otherwise>
                            <c:forEach var="pageNum" begin="${pageParaMap.currPage - 2}" end="${pageParaMap.currPage + 2 }">
                                <li id="poiPageNav${pageNum}"><a href="#" id="${pageNum}">A${pageNum}</a></li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <%-- 总页数大于 5 当前页小于 3 --%>
                <c:otherwise>
                    <c:forEach var="pageNum" begin="1" end="5">
                        <li id="poiPageNav${pageNum}"><a href="#" id="${pageNum}">${pageNum}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <%-- 若为最后一页不显示右箭头 --%>
            <c:if test="${pageParaMap.currPage lt pageParaMap.totalCount/5}">
                <li><a href="#" id="${pageParaMap.currPage + 1}" >&raquo;</a></li>
            </c:if>
        </ul>
    </nav>
<%--				<!-- 员工详情模态框，通过 AJAX 进行替换 -->
				<div class="modal fade" id="staffInfoModal" tabindex="-1"
					role="dialog"></div>--%>
				<!-- 页面加载完毕设置分页激活样式 -->
				<script type="text/javascript">
 					$(document).ready(function() {
						$("#poiPageNav${pageParaMap.currPage}").addClass("active");
					});
				</script>
			</div>
		</div>
	</div>
</body>
</html>
