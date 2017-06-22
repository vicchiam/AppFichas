<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Listado Conversión Pesos</h2>
<div id="weightUnit">
	<table class="list">
		<tr class="filtro">
			<td></td>
			<td>
				<label for="f_state">Ver Pasivos
					<input type="checkbox" id="f_state" name="f_state" <c:if test = '${requestScope.f_state==0}'>checked</c:if> />
				</label>
			</td>			
			<td class="der"><img src="/AppFichas/resources/images/search.png" class="img_button" onclick="Search()"/></td>
		</tr>
		<tr>
			<th>Nombre</th>
			<th>Conversión a Kgm</th>			
			<th class="der"><img src="/AppFichas/resources/images/add.png" class="img_button" onclick="ShowNewWeightUnit()" /></th>				
		</tr>			
		<c:forEach var="weightUnit" items="${requestScope.listWeightUnits}">
		<tr class="row">
			<td><c:out value = "${weightUnit.name}"/></td>
			<td class="der"><c:out value = "${weightUnit.conversionToKgm}"/></td>
			<td class="der">
				<img title="Editar marca" src="/AppFichas/resources/images/edit.png" class="img_button" onclick="ShowUpdateWeightUnit('<c:out value = "${weightUnit.id}"/>')" />
				<img title="Cambiar estado Usuario" src="/AppFichas/resources/images/recycle.png" class="img_button" onclick="ChangeStateWeightUnit('<c:out value = "${weightUnit.id}"/>')" />
			</td>
         </tr>
      	</c:forEach>
	</table>
	<div class="script">
		<script type="text/javascript">Init();</script> 
	</div>
</div>