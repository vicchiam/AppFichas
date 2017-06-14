<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<table>		
		<tr>
			<td>
				<input type="hidden" id="_id" value="<c:out value='${requestScope.id}'/>" />
				<label for="password">Contraseña:</label>
			</td>
			<td><input type="password" value="" id="password" name="password" /></td>
		</tr>
		<tr>
			<td><label for="rpassword">Repita la contraseña:</label></td>
			<td><input type="password" value="" id="rpassword" name="rpassword" /></td>
		</tr>
		<tr>
			<td colspan="2" class="center">
				<input type="button" value="Guardar" onclick="SavePassword()" />
			</td>
		</tr>
	</table>
</div>