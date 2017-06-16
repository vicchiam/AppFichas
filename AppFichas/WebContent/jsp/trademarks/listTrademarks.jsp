<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Listado Marcas</h2>
<div id="trademarks">
	<table class="list">
		<tr class="filtro">
			<td></td>
			<td><input id="f_name" type="text" value="<c:out value='${requestScope.f_name}'/>" /></td>
			<td class="der"><img src="/AppFichas/resources/images/search.png" class="img_button" onclick="Search()"/></td>
		</tr>
		<tr>
			<th>Imagen</th>
			<th>Nombre</th>			
			<th class="der"><img src="/AppFichas/resources/images/add.png" class="img_button" onclick="ShowNewTrademark()" /></th>				
		</tr>			
		<c:forEach var="trademark" items="${requestScope.listTrademarks}">
		<tr class="row">
			<td class="center"><img class="trademark_image" src="${trademark.path}" onclick="ShowImageUpdater('<c:out value = "${trademark.id}"/>','<c:out value = "${trademark.path}"/>')"/></td>
			<td><c:out value = "${trademark.name}"/></td>
			<td class="der">
				<img title="Editar marca" src="/AppFichas/resources/images/edit.png" class="img_button" onclick="ShowUpdateTrademark('<c:out value = "${trademark.id}"/>')" />
				<img title="Cambiar estado Usuario" src="/AppFichas/resources/images/delete.png" class="img_button" onclick="DeleteTrademark('<c:out value = "${trademark.id}"/>')" />
			</td>
         	</tr>
      	</c:forEach>
	</table>
	<div class="script">
		<script type="text/javascript">Init();</script> 
	</div>
</div>