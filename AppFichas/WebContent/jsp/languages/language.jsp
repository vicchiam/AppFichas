<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link href="<c:url value="/resources/css/languages.css" />" rel="stylesheet" />
		<c:import url="/jsp/header.jsp"></c:import>
		<script type="text/javascript" src="<c:url value="/resources/js/languages.js" />"></script> 
	</head>
<body onload="Init()">
	<section id="container">
		<c:import url="/jsp/menu.jsp"></c:import>
		<section id="content">
		
			<c:import url="/jsp/packs/listlanguages.jsp"></c:import>
			
		</section>
	</section>
	<c:import url="/jsp/windows.jsp"></c:import>
</body>
</html>