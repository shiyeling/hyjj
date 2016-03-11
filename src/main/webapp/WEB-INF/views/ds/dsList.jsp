<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>任务管理</title>
</head>

<body>
	<ul class="nav nav-tabs">
	  <li role="presentation" ><a href="${ctx}/">Home</a></li>
	  <li role="presentation" class="active"><a href="#">Data Sources</a></li>
	</ul>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span4 offset7">
			<form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_title" class="input-medium" value="${param.search_LIKE_title}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th colspan="3">Data Source</th><th>Action</th></tr></thead>
		<tbody>
		<c:forEach items="${dataSources.content}" var="ds">
			<tr>
				<td><a href="${ctx}/ds/update/${ds.id}">${ds.name}</a></td>
				<td><a href="${ctx}/ds/update/${ds.id}">${ds.description}</a></td>
				<td><a href="${ctx}/ds/update/${ds.id}">${ds.type}</a></td>
				<td><a href="${ctx}/ds/delete/${ds.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<tags:pagination page="${dataSources}" paginationSize="5"/>

	<div><a class="btn" href="${ctx}/ds/create">Create</a></div>
</body>
</html>
