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
			<p>使用访问时会被强制缓存数据，您的更改可能无法实时更新。</p>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">系统状态</div>
		<div class="panel-body">
			<p>当前系统有效在库员工条目数为：${pageParaMap.inSystemStaffInfoCount} 条</p>
			<p>当前系统在库地铁站点条目数为：${pageParaMap.inSystemStationInfoCount} 条</p>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">API 状态</div>
		<div class="panel-body">
			<p>当前使用的百度地图 API KEY 为：${pageParaMap.baiduUsingKey}</p>
			<p>当前使用的高德地图 API KEY 为：${pageParaMap.amapUsingKey}</p>
		</div>
	</div>
</body>
</html>
