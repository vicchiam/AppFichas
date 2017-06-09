<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inicio de Sessión</title>
	<link href="<c:url value="/css/main.css" />" rel="stylesheet">
	<link href="<c:url value="/css/login.css" />" rel="stylesheet">
	<link href="<c:url value="/css/classes.css" />" rel="stylesheet">
	<link href="<c:url value="/css/jquery-ui.css" />" rel="stylesheet">
    <script src="<c:url value="/js/jquery-3.2.1.js" />"></script>
    <script src="<c:url value="/js/jquery-ui.js" />"></script>    
</head>
<body>
	<div id="content">
		<div id="login">
			
			<form method="post" action="User" >
				<h2>Introduzca sus datos de usuario</h2>
				<input type="hidden" name="action" id="action" value="startSession" />
				<% if(request.getAttribute("error")!=null){ %>
					<span class="error">Usuario o password incorrecto</span>
				<% } %>						
				<table>
					<tr>
						<td><label for="user">Usuario:</label></td>
					</tr>
					<tr>
						<td><input type="text" name="user" id="user" value="" /></td>					
					</tr>
					<tr>
						<td><label for="password">Contraseña:</label></td>					
					</tr>
					<tr>
						<td><input type="password" name="password" id="password" value="" /></td>					
					</tr>			
					<tr>
						<td>
							<input type="submit" value="Iniciar Sessión" />
						</td>
					</tr>					
				</table>				
			</form>
			
		</div>		
	</div>	
</body>
</html>