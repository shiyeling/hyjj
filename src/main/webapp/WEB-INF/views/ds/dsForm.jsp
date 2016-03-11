<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Data Source Management</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/ds/${action}" method="post"
		class="form-horizontal">
		<input type="hidden" name="id" value="${ds.id}" />
		<fieldset>
			<legend>
				<small>Data Source</small>
			</legend>
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Name:</label>
				<div class="col-sm-4">
					<input type="text" id="name" name="name" value="${ds.name}"
						class="input-large required  form-control" minlength="3" />
				</div>
			</div>
			<div class="form-group">
				<label for="description" class="col-sm-2 control-label">Description:</label>
				<div class="col-sm-4">
					<textarea id="description" name="description" class="input-large  form-control">${ds.description}</textarea>
				</div>
			</div>
			<div class="form-group">
				<label for="type" class="col-sm-2 control-label">Type:</label>
				<div class="col-sm-2">
					<select id="type" name="type" class="form-control">
						<option value="mysql" selected='selected'>MySql</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="connectionUrl" class="col-sm-2 control-label">Connection
					URL:</label>
				<div class="col-sm-8 ">
					<input type="text" id="connectionUrl" name="connectionUrl"
						value="${ds.connectionUrl}" class="input-large required form-control"
						minlength="10" />
				</div>
			</div>
			<div class="form-group">
				<label for="username" class="col-sm-2 control-label">User
					Name:</label>
				<div class="col-sm-4">
					<input type="text" id="username" name="username"
						value="${ds.username}" class="input-large required  form-control" minlength="1" />
				</div>
			</div>
			<div class="form-group">
				<label for="password" class="col-sm-2 control-label">Password:</label>
				<div class="col-sm-4">
					<input type="text" id="password" name="password"
						value="${ds.password}" class="input-large required  form-control" minlength="1" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-2">
					<input id="submit_btn" class="btn btn-primary" type="submit"
						value="提交" />
						&nbsp; 
					<input id="cancel_btn" class="btn"
						type="button" value="返回" onclick="history.back()" />
					
				</div>
				
				<div class="col-sm-offset-1 col-sm-2">
					<input id="test_btn" class="btn"
						type="button" value="测试连接" onclick="" />
				</div>
			</div>
		</fieldset>
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#task_title").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
