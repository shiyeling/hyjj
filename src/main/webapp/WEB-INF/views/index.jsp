<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>HYJJ Web App Home</title>
<meta charset="UTF-8">
<meta name="fragment" content="!" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <script src="static/require.js" type="text/javascript" data-main="static/page/index"></script> -->
</head>
<body>
	<ul class="nav nav-tabs">
	  <li role="presentation" class="active"><a href="#">Home</a></li>
	  <li role="presentation"><a href="${ctx}/ds/">Data Sources</a></li>
	</ul>
<!-- 	<div ms-controller="hyjj"> -->
<!-- 		<ul class="nav nav-tabs"> -->
<!-- 			<li role="presentation" id="nav-home" class="hyjj-top-nav active" ms-click="showHome()"><a href="#">Home</a></li> -->
<!-- 			<li role="presentation" id="nav-ds" class="hyjj-top-nav" ms-click="showDs()"><a href="#">My Data Sources</a></li> -->
<!-- 		</ul> -->
<!-- 		<div style="color:red">{{contentUrl}}</div> -->
<!-- 		<div class='container' ms-include-src="contentUrl"> -->
<!-- 			<!--这里使用ms-include-src接口，它会引入pageUrl属性所对应的文件--> -->
<!-- 		</div> -->
<!-- 	</div> -->
</body>
</html>