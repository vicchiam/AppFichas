<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<table>		
		<tr>
			<td>
				<input type="hidden" id="_id" value="<c:out value='${requestScope.user.id}'/>" />
				<label for="user">Usuario:</label>
			</td>
			<td><input type="text" value="<c:out value='${requestScope.user.user}'/>" id="user" name="user" placeholder="Nombre de usuario" /></td>
		</tr>
		<tr>
			<td><label for="mail">Email:</label></td>
			<td><input type="text" value="<c:out value='${requestScope.user.mail}'/>" id="mail" name="mail" placeholder="Direccion de correo electronico" size="30"/></td>
		</tr>
		<tr>
			<td><label for="type">Tipo:</label></td>
			<td>
				<select id="type">
					<c:forEach var="typeName" items="${requestScope.typeNames}" varStatus="status">
					<option value="<c:out value='${status.count}'/>"  <c:if test = "${status.count==requestScope.user.type}">selected</c:if> ><c:out value='${typeName}'/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="center">
				<input type="button" class="button_green" value="Guardar" onclick="Save()" />
			</td>
		</tr>
	</table>
</div>
<div class="script">
	<script type="text/javascript">InitFormUser();</script> 
</div>