<%--
  Created by Sharuru
  Date: 2015/10/3 0003
  Time: 16:17
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签用以自适用布局匹配，所以*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <script src="${pageContext.request.contextPath}../../resources/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}../../resources/js/bootstrap.min.js"></script>
    <title>Project Honoka</title>
    <link href="${pageContext.request.contextPath}../../resources/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        $(document).ready(function () {
            $("a").click(function (event) {
                alert(event.target.id);
                loadAjax(event.target.id);
            });
        });
        function loadAjax(route) {
            //$('#'+route.substring(0,route.length-3)).load("/staff");
            $.ajax({
                type: "POST",
                url: "staff",
                data: {
                    userInput: "abcdefg"
                }, success: function (data) {
                    //$('#'+route).html(data);
                    //alert("succuess" + data);
                    $('#staff').html(data);
                }
            });
        }
    </script>
</head>
<body role="document">
<div class="container theme-showcase" role="main">
    <div style="margin-top:20px;" class="jumbotron">
        <h1><b>PROJCET HONOKA</b></h1>

        <p> MBPSH Location Based Service Center </p>
    </div>
    <ul id="myTab" class="nav nav-pills">
        <li class="active"><a href="#home" id="homenavi" data-toggle="tab">首页</a></li>
        <li><a href="#staff" id="staffnav" data-toggle="tab">员工数据管理</a></li>
        <li><a href="#publicaa" data-toggle="tab">地铁数据管理</a></li>
        <li><a href="#bat" data-toggle="tab">LBS 计算</a></li>
        <li><a href="#logout" data-toggle="tab">退出登录</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="home">
            <p>非常酷炫的第一页</p>
        </div>
        <div class="tab-pane fade" id="staff">
            <p>1</p>
        </div>
        <div class="tab-pane fade" id="publicaa">
            <p>2</p>
        </div>
        <div class="tab-pane fade" id="bat">
            <p>3</p>
        </div>
    </div>
</div>
</body>
</html>
