<%--
  Created by Sharuru
  Date: 2015/10/10 0008
  Time: 17:02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	//子功能加载后绑定点击事件
	/* $(document).ready(function() {
	$("#lbsNav").click(function(event) {
		loadContent(event.target.id);
	});
	});  */
	//AJAX 获取子页面的内容
	function loadContent(dest) {
		$.ajax({
			type : "GET",
			url : dest,
			success : function(data) {
				$("#" + dest + "Content").html(data);
			}
		});
	}
</script>
</head>
<body>
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
						<th>解析坐标</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>1</td>
						<td>S1500001</td>
						<td>爱新觉罗</td>
						<td>MBP 上海</td>
						<td>综合管理部</td>
						<td>软件工程师</td>
						<td>021-00001111</td>
						<td>(121.3331,112.33331)</td>
						<td><button type="button" class="btn btn-xs btn-default">查看详情</button></td>
					</tr>
					<tr>
						<td>2</td>
						<td>S1500002</td>
						<td>爱新觉罗2</td>
						<td>MBP 上海</td>
						<td>综合管理部</td>
						<td>软件工程师</td>
						<td>021-00001111</td>
						<td>(1221.3331,1122.33331)</td>
						<td><button type="button" class="btn btn-xs btn-default">查看详情</button></td>
					</tr>
				</tbody>
			</table>

			<nav style="text-align: center">
				<ul class="pagination">
					<li><a href="#" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
			</nav>


			<!-- LBS 计算页面内容区，通过 AJAX 进行替换 -->
			<!-- 			<div id="lbsContent" class="tab-content" style="margin-top: 20px;">
				<div class="tab-pane fade in active" id="geoCodingContent"></div>
				<div class="tab-pane fade" id="twoPointCalcContent"></div>
				<div class="tab-pane fade" id="geoFencingContent"></div>
			</div> -->
			<!-- 页面加载完毕后自动点击按钮替换内容 -->
			<!-- <script type="text/javascript">
				$(document).ready(function() {
					$("#geoCoding").click();
				});
			</script> -->
		</div>
</body>
</html>
