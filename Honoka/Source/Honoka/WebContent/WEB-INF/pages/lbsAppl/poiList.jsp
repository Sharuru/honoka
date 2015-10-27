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
        // 绑定 POI 结果卡片
        $("a[class='list-group-item']").click(function(){
            alert("get" + this.id);
            // 移除已有 active 样式
            $("a[class='list-group-item active']").removeClass("active");
            // 对当前选择的卡片添加 active 样式
            $(this).addClass("active");
            var targetId = (this.id.substr(7)-1);
            // 设定 POI 坐标集
            <c:if test="${pageParaMap.poiSearchList != null && pageParaMap.poiSearchList.size() > 0}">
                var baiduRecordLng = new Array();
                var baiduRecordLat= new Array();
                var i =0;
                <c:forEach  var="currPOI" begin="0" end="${pageParaMap.poiSearchList.size() - 1}">
                        baiduRecordLng[i] = ${pageParaMap.poiSearchList.get(currPOI).baiduRecordLng};
                        baiduRecordLat[i] = ${pageParaMap.poiSearchList.get(currPOI).baiduRecordLat};
                        i++;
                </c:forEach>
            </c:if>
            // 默认设定 MBP 上海的坐标点
            var poiPoint = new BMap.Point( baiduRecordLng[targetId], baiduRecordLat[targetId]);
            // 设定覆盖物
            var poiMarker = new BMap.Marker(poiPoint);
            passMap.clearOverlays();
            passMap.addOverlay(poiMarker);
            poiMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
            passMap.centerAndZoom(poiPoint, 18);
        });
	});
</script>
</head>
<body>
        <c:if test="${pageParaMap.poiSearchList != null && pageParaMap.poiSearchList.size() > 0}">
            <div class="list-group" id="poiList">
            <c:forEach  var="currPOI" begin="0" end="${pageParaMap.poiSearchList.size() - 1}">
              <a href="####" id="poiCard${currPOI + 1}" class="list-group-item">
                    <p><h5 class="list-group-item-heading">
                        <span class="glyphicon glyphicon-lbsMap-marker" aria-hidden="true"></span>&nbsp;${pageParaMap.poiSearchList.get(currPOI).poiName}
                        <span class="list-group-item-text" style="float:right;font-size: 13px;">平均直线距离: ${pageParaMap.poiSearchList.get(currPOI).lineDistance}</span>
                    </h5>
                    <p class="list-group-item-text" style="font-size: 13px">${pageParaMap.poiSearchList.get(currPOI).poiAddr}</p>
                    <p class="list-group-item-text" style="font-size: 13px">${pageParaMap.poiSearchList.get(currPOI).poiTelephone}</p>
                    <span class="list-group-item-text" style="font-size: 12px;">平均自驾: ${pageParaMap.poiSearchList.get(currPOI).drivingDistance}（${pageParaMap.poiSearchList.get(currPOI).drivingDuration}）</span>
                    <span class="list-group-item-text" style="font-size: 12px;">平均公共交通: ${pageParaMap.poiSearchList.get(currPOI).transitDistance}（${pageParaMap.poiSearchList.get(currPOI).transitDuration}）</span>
              </a>
            </c:forEach>
            </div>
            <nav style="text-align: center;margin-top: -20px;">
                <ul class="pagination" id="poiPageNav">
                        <%-- 若为第一页不显示左箭头 --%>
                    <c:if test="${pageParaMap.currPage gt 1}">
                        <li><a href="####" id="${pageParaMap.currPage -1}">&laquo;</a></li>
                    </c:if>
                        <%-- 循环设置页码 --%>
                    <c:choose>
                        <%-- 当页面总数小于 5 --%>
                        <c:when test="${pageParaMap.totalCount/5 + 1 le 5}">
                            <c:forEach var="pageNum" begin="1" end="${pageParaMap.totalCount/5 + 1}">
                                <li id="poiPageNav${pageNum}"><a href="####" id="${pageNum}">${pageNum}</a></li>
                            </c:forEach>
                        </c:when>
                        <%-- 当页面总数大于 5 并且当前页面大于 3 --%>
                        <c:when test="${pageParaMap.totalCount/5 + 1 gt 5 and pageParaMap.currPage gt 3}">
                            <c:choose>
                                <%-- 当当前页 + 3 页码大于最大页数，不滚动显示 --%>
                                <c:when test="${pageParaMap.currPage + 3 gt pageParaMap.totalCount/5 + 1 }">
                                    <c:forEach var="pageNum" begin="${pageParaMap.totalCount/5 + 1 - 4 }" end="${pageParaMap.totalCount/5 + 1 }">
                                        <li id="poiPageNav${pageNum}"><a href="####" id="${pageNum}">${pageNum}</a></li>
                                    </c:forEach>
                                </c:when>
                                <%-- 页码滚动 --%>
                                <c:otherwise>
                                    <c:forEach var="pageNum" begin="${pageParaMap.currPage - 2}" end="${pageParaMap.currPage + 2 }">
                                        <li id="poiPageNav${pageNum}"><a href="####" id="${pageNum}">A${pageNum}</a></li>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <%-- 总页数大于 5 当前页小于 3 --%>
                        <c:otherwise>
                            <c:forEach var="pageNum" begin="1" end="5">
                                <li id="poiPageNav${pageNum}"><a href="####" id="${pageNum}">${pageNum}</a></li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                        <%-- 若为最后一页不显示右箭头 --%>
                    <c:if test="${pageParaMap.currPage lt pageParaMap.totalCount/5}">
                        <li><a href="####" id="${pageParaMap.currPage + 1}" >&raquo;</a></li>
                    </c:if>
                </ul>
            </nav>
        </c:if>
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
