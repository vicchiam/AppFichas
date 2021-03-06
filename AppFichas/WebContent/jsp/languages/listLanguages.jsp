<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="dateValue" class="java.util.Date" />

<h2>Listado Marcas</h2>
<div id="trademarks">
	<table class="list">
		<tr class="filtro">
			<td></td>
			<td></td>
			<td>
				<input id="f_name" type="text" value="<c:out value='${requestScope.f_name}'/>" />
				<label for="f_state">Ver Pasivos
					<input type="checkbox" id="f_state" name="f_state" <c:if test = '${requestScope.f_state==0}'>checked</c:if> />
				</label>
			</td>
			<td class="der"><img src="/AppFichas/resources/images/search.png" class="img_button" onclick="Search()"/></td>
		</tr>
		<tr>
			<th>Imagen</th>
			<th></th>
			<th>Nombre</th>			
			<th class="der"><img src="/AppFichas/resources/images/add.png" class="img_button" onclick="ShowFormLanguage('0')" /></th>				
		</tr>			
		<c:forEach var="language" items="${requestScope.listLanguages}">
		<tr class="row">
			<td class="center">
				<img class="language_image" src="/AppFichas<c:out value = "${language.path}"/>?<c:out value='${dateValue.time}'/>" onclick="ShowImageUpdater('<c:out value = "${language.id}"/>','<c:out value = "${language.path}"/>')"/>
			</td>
			<td class="center">
				<img title="Eliminar imagen" src="/AppFichas/resources/images/delete_image.png" class="img_button" onclick="DeleteImage('<c:out value = "${language.id}"/>')" />
			</td>
			<td>
				<c:out value = "${language.name}"/>
			</td>
			<td class="der">
				<img title="Editar marca" src="/AppFichas/resources/images/edit.png" class="img_button" onclick="ShowFormLanguage('<c:out value = "${trademark.id}"/>')" />
				<img title="Cambiar estado Usuario" src="/AppFichas/resources/images/recycle.png" class="img_button" onclick="ChangeStateLanguage('<c:out value = "${trademark.id}"/>')" />
			</td>
         </tr>
      	</c:forEach>
	</table>	
</div>
<div class="script">
	<script type="text/javascript">Init();</script> 
</div>