<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="emptyWeight">
<input type="hidden" id="_id" value="<c:out value='${requestScope.pack.id}'/>"/>
<table id="weights">
	<tr>
		<th class="left">Peso</th>
		<th class="left">Unidad</th>
		<th class="der">
			<img class="img_button" src="/AppFichas/resources/images/add.png" onclick="ShowNewPackWeight()" />
		</th>
	</tr>
</table>
</div>
<div style="display:none" id="ghost_select">
	<select>
		<c:forEach var="weightUnit" items="${requestScope.weightUnits}" varStatus="status">
			<option value="<c:out value='${weightUnit.id}'/>" ><c:out value='${weightUnit.name}'/></option>
		</c:forEach>
	</select>
</div>