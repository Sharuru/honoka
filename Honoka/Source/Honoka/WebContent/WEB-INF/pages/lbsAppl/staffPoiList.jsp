<%--
  Created by Sharuru
  Date: 2015/10/29 0029
  Time: 18:16
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <script type="text/javascript">
        // 设定 POI 坐标集
        <c:if test="${pageParaMap.fencingResultList != null && pageParaMap.fencingResultList.size() > 0}">
            var staffBaiduRecordLng = [];
            var staffBaiduRecordLat= [];
            var i =0;
            <c:forEach  var="currPOI" begin="0" end="${pageParaMap.fencingResultList.size() - 1}">
                staffBaiduRecordLng[i] = ${pageParaMap.fencingResultList.get(currPOI).baiduRecordLng};
                staffBaiduRecordLat[i] = ${pageParaMap.fencingResultList.get(currPOI).baiduRecordLat};
                i++;
            </c:forEach>
        </c:if>
        // 添加标记点
        for(var i = 0;i<staffBaiduRecordLat.length;i++){
            var marker = new BMap.Marker(new BMap.Point(staffBaiduRecordLng[i],staffBaiduRecordLat[i]));
            passMap.addOverlay(marker);
        }
        // 子功能加载后绑定点击事件
        $(document).ready(function() {
            $("#staffPoiPageNav").click(function(event) {
                $.ajax({
                	cache: false,
                    type : "POST",
                    url : "reqStaffFencing&reqPage=" + event.target.id,
                    data : {
                        oLng : currPoint.lng,
                        oLat : currPoint.lat,
                        reqRange : document.getElementById("inputStaffReqRange").value.trim()
                    },
                    success : function(returnData) {
                        //$('#btnReqPlaceSearch').button('reset');
                        $("#placeSearchResult").html(returnData);
                    }
                });
            });
            // 绑定员工 POI 结果卡片
            $("a[class='list-group-item']").click(function(){
                // 移除已有 active 样式
                $("a[class='list-group-item active']").removeClass("active");
                // 对当前选择的卡片添加 active 样式
                $(this).addClass("active");
                var targetId = (this.id.substr(12)-1);
                // 设定 POI
                var poiPoint = new BMap.Point( staffBaiduRecordLng[targetId], staffBaiduRecordLat[targetId]);
                currPoint = poiPoint;
                // 设定覆盖物
                //var poiMarker = new BMap.Marker(poiPoint);
                //passMap.clearOverlays();
                //passMap.addOverlay(poiMarker);
                //poiMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
                passMap.panTo(poiPoint);
            });
        });
    </script>
</head>
<body>
<c:if test="${pageParaMap.fencingResultList != null && pageParaMap.fencingResultList.size() > 0}">
    <div class="list-group" id="poiList">
        <c:forEach  var="currPOI" begin="0" end="${pageParaMap.fencingResultList.size() - 1}">
            <a href="####" id="staffPoiCard${currPOI + 1}" class="list-group-item">
                <h5 class="list-group-item-heading">
                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;${pageParaMap.fencingResultList.get(currPOI).staffName}
                    <span class="list-group-item-text" style="float:right;font-size: 13px;">距离: ${pageParaMap.fencingResultList.get(currPOI).dist}</span>
                </h5>
                <p class="list-group-item-text" style="font-size: 13px">${pageParaMap.fencingResultList.get(currPOI).staffId}</p>
                <p class="list-group-item-text" style="font-size: 13px">${pageParaMap.fencingResultList.get(currPOI).staffComId}</p>
                <p class="list-group-item-text" style="font-size: 13px">${pageParaMap.fencingResultList.get(currPOI).staffDeptId}</p>
                <p class="list-group-item-text" style="font-size: 13px;"> ${pageParaMap.fencingResultList.get(currPOI).staffLevelId}</p>
            </a>
        </c:forEach>
    </div>
    <nav style="text-align: center;margin-top: -20px;">
        <ul class="pagination" id="staffPoiPageNav">
                <%-- 若为第一页不显示左箭头 --%>
            <c:if test="${pageParaMap.currPage gt 1}">
                <li><a href="####" id="${pageParaMap.currPage -1}">&laquo;</a></li>
            </c:if>
                <%-- 循环设置页码 --%>
            <c:choose>
                <%-- 当页面总数小于等于 5 --%>
                <c:when test="${ pageParaMap.totalCount/5 le 5}">
                    <c:forEach var="pageNum" begin="1" end="${ pageParaMap.totalCount/5 + 0.8}">
                        <li id="staffPoiPageNav${pageNum}"><a href="####" id="${pageNum}">${pageNum}</a></li>
                    </c:forEach>
                </c:when>
                <%-- 当页面总数大于 5 并且当前页面大于 3 --%>
                <c:when test="${ pageParaMap.totalCount/5 + 1 gt 5 and pageParaMap.currPage gt 3}">
                    <c:choose>
                        <%-- 当当前页 + 3 页码大于最大页数，不滚动显示 --%>
                        <c:when test="${pageParaMap.currPage + 3 gt pageParaMap.totalCount/5 + 0.8 }">
                            <c:forEach var="pageNum" begin="${ pageParaMap.totalCount/5 + 0.8 - 4 }" end="${pageParaMap.totalCount/5 + 0.8}">
                                <li id="staffPoiPageNav${pageNum}"><a href="####" id="${pageNum}">${pageNum}</a></li>
                            </c:forEach>
                        </c:when>
                        <%-- 页码滚动 --%>
                        <c:otherwise>
                            <c:forEach var="pageNum" begin="${pageParaMap.currPage - 2}" end="${pageParaMap.currPage + 2 }">
                                <li id="staffPoiPageNav${pageNum}"><a href="####" id="${pageNum}">${pageNum}</a></li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <%-- 总页数大于 5 当前页小于 3 --%>
                <c:otherwise>
                    <c:forEach var="pageNum" begin="1" end="5">
                        <li id="staffPoiPageNav${pageNum}"><a href="####" id="${pageNum}">${pageNum}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
                <%-- 若为最后一页不显示右箭头 --%>
            <c:if test="${pageParaMap.currPage lt  pageParaMap.totalCount/5}">
                <li><a href="####" id="${pageParaMap.currPage + 1}" >&raquo;</a></li>
            </c:if>
        </ul>
    </nav>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("#staffPoiPageNav${pageParaMap.currPage}").addClass("active");
    });
</script>
</div>
</div>
</div>
</body>
</html>
