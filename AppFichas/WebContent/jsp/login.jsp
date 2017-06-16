<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Inicio de Sessión</title>
	<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet" />
	<c:import url="/jsp/header.jsp"></c:import>    
</head>
<body>
	<section id="container">
		<section id="login">
			
			<form method="post" action="/AppFichas/?action=startSession" >
				<h2>Introduzca sus datos de usuario</h2>
				<c:if test = "${requestScope.error!=null}">				
					<span class="error">Usuario o password incorrecto</span>
				</c:if>				
				<table>
					<tr>
						<td><label for="user">Usuario:</label></td>
					</tr>
					<tr>
						<td><input type="text" name="user" id="user" value="" required/></td>					
					</tr>
					<tr>
						<td><label for="password">Contraseña:</label></td>					
					</tr>
					<tr>
						<td><input type="password" name="password" id="password" value="" required /></td>					
					</tr>			
					<tr>
						<td>
							<input type="submit" class="button" value="Iniciar Sessión" />
						</td>
					</tr>					
				</table>				
			</form>
			
		</section>		
	</section>	
</body>
</html>