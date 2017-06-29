<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<table>		
		<tr>
			<td>
				<input type="hidden" id="_id" value="<c:out value='${requestScope.weightUnit.id}'/>" />
				<label for="name">Nombre:</label>
			</td>
			<td><input type="text" value="<c:out value='${requestScope.weightUnit.name}'/>" id="name" name="name" placeholder="Nombre de la unidad" required/></td>
			
		</tr>
		<tr>
			<td>
				<label for="conversion">Conversión a Kgm:</label>
			</td>
			<td>
				<input class="der" type="text" value="<c:out value='${requestScope.weightUnit.conversionToKgm}'/>" id="conversion" name="conversion" required/>
			</td>
		</tr>		
		<tr>
			<td colspan="2" class="center">
				<input type="button" class="button_green" value="Guardar" onclick="SaveWeightUnit()" />
			</td>
		</tr>
	</table>
</div>