<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<input type="hidden" id="_idWeight" value="<c:out value="${weight.id }" />" />	 
	<table>
		<tr>
			<th>Peso</th>
			<th>Unidad</th>			
		</tr>
		<tr>
			<td>
				<input class="right" type="text" value="<c:out value="${weight.value}" />" id="weightValue" />
			</td>
			<td>
				<select id="weightUnit">
					<c:forEach var="weightUnit" items="${requestScope.weightUnits}" varStatus="status">
						<option value="<c:out value='${weightUnit.id}'/>" <c:if test = "${weightUnit.id==weight.weightUnit.id}">selected</c:if> ><c:out value='${weightUnit.name}'/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" class="center">
				<input type="button" class="button_green" value="Guardar" onclick="SavePackWeight(this)" />
			</td>
		</tr>
	</table>
</div>