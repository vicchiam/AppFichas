<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="emptyWeight">
	<input type="hidden" id="_idPack" value="<c:out value='${requestScope.id}'/>"/>
	<table id="weights">
		<tr>
			<th class="left">Peso</th>
			<th class="left">Unidad</th>
			<th class="der">
				<img class="img_button" src="/AppFichas/resources/images/add.png" onclick="ShowFormPackWeight()" />
			</th>
		</tr>
	</table>
</div>