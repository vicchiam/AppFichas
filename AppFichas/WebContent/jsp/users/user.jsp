<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link href="<c:url value="/resources/css/user.css" />" rel="stylesheet" />
		<c:import url="/jsp/header.jsp"></c:import>
		<script type="text/javascript" src="<c:url value="/resources/js/user.js" />"></script> 
	</head>
<body>
	<section id="container">
		<c:import url="/jsp/menu.jsp"></c:import>
		<section id="content">
		
			<c:import url="/jsp/users/listUsers.jsp"></c:import>
			
		</section>
	</section>
</body>
</html>