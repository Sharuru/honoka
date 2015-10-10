<%--
  Created by Sharuru
  Date: 2015/10/08 0008
  Time: 17:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">系统信息</div>
		<div class="panel-body">
			<h3>欢迎使用本系统</h3>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">系统状态</div>
		<div class="panel-body">
			<p>当前系统在库员工条目数为：</p>
			<p>当前系统在库地铁站点条目数为：</p>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">API 状态</div>
		<div class="panel-body">
			<p>当前使用的百度地图 API KEY 为：${obj.baiduKey}</p>
			<p>剩余可用额度为：${obj.baiduAmount}</p>
			<p>当前使用的高德地图 API KEY 为：${obj.amapKey}</p>
			<p>谷歌地图剩余可用 API 额度为：</p>
		</div>
	</div>
</body>
</html>
