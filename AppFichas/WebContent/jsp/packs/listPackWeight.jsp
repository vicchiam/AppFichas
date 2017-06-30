<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="emptyWeight">
	<input type="hidden" id="_idPack" value="<c:out value='${requestScope.id}'/>"/>
	<table id="weights">
		<tr>
			<th class="left">Peso</th>
			<th class="left">Unidad</th>
			<th class="der">
				<img class="img_button" src="/AppFichas/resources/images/add.png" onclick="ShowFormPackWeight('0')" />
			</th>
		</tr>
		<c:forEach var="weight" items="${requestScope.weights}">
		<tr class="row">
			<td><c:out value = "${weight.value}"/></td>			
			<td><c:out value = "${weight.weightUnit.name}"/></td>
			<td class="der">				
				<img title="Editar Peso" src="/AppFichas/resources/images/edit.png" class="img_button" onclick="ShowFormPackWeight('<c:out value = "${weight.id}"/>')" />
				<img title="Eliminar peso" src="/AppFichas/resources/images/delete.png" class="img_button" onclick="DeletePackWeight('<c:out value = "${weight.id}"/>')" />
			</td>
         </tr>
      	 </c:forEach>      	 
	</table>	
</div>
<div class="center">
	<input type="button" class="button_green" value="Aceptar" onclick="ReloadListPacks()" />
</div>