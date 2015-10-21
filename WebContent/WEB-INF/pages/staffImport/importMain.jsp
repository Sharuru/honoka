<%--
  Created by Sharuru
  Date: 2015/10/19 0008
  Time: 18:18
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<script type="text/javascript">
	 // 模块加载后绑定事件
	$(document).ready(function() {
		// 监听上传按钮
		$('#btnUploadFile').click(function(event) {
		 var formData = new FormData();
	        formData.append('file', $('input[type=file]')[0].files[0]);
	        // POST 数据
	        $.ajax({
	            url : 'uploadStaffFile',
	            data : formData,
	            processData : false,
	            contentType : false,
	            type : 'POST',
	            success : function(data) {
	                alert(data);
	            },
	            error : function(err) {
	                alert(err);
	            }
	        });
		});
	});
</script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">数据导入</div>
		<div class="panel-body">
			<div class="input-group">
			  <input id="filePath" type="file" class="form-control" placeholder="请选择上传文件……">
			  <span class="input-group-btn">
			    <button class="btn btn-default" id="btnUploadFile" type="button">上传</button>
			  </span>
			</div>
		</div>
	</div>
</body>
</html>
