<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Listado Envases</h2>
<div id="packs">
	<table class="list">
		<tr class="filtro">
			<td><input id="f_description" type="text" value="<c:out value='${requestScope.f_description}'/>" /></td>
			<td>
				<label for="f_state">Ver Pasivos
					<input type="checkbox" id="f_state" name="f_state" <c:if test = '${requestScope.f_state==0}'>checked</c:if> />
				</label>
			</td>
			<td>
				<label for="f_apt">No aptos
					<input type="checkbox" id="f_apt" name="f_apt" <c:if test = '${requestScope.f_apt==0}'>checked</c:if> />
				</label>	
			</td>			
			<td class="der"><img src="/AppFichas/resources/images/search.png" class="img_button" onclick="Search()"/></td>
		</tr>
		<tr>
			<th>Description</th>
			<th>Medidas</th>
			<th>Peso vacio</th>						
			<th class="der"><img src="/AppFichas/resources/images/add.png" class="img_button" onclick="ShowNewUser()" /></th>				
		</tr>			
		<c:forEach var="pack" items="${requestScope.listPacks}">
		<tr class="row">
			<td><c:out value = "${pack.description}"/></td>			
			<td><c:out value = "${pack.mesure}"/></td>
			<td><c:out value = "${pack.weight}"/></td>
			<td class="der">				
				<img title="Editar Envase" src="/AppFichas/resources/images/edit.png" class="img_button" onclick="ShowUpdatePack('<c:out value = "${pack.id}"/>')" />
				<img title="Cambiar estado Envase" src="/AppFichas/resources/images/recycle.png" class="img_button" onclick="ChangeStatePackr('<c:out value = "${pack.id}"/>')" />
			</td>
         </tr>
      	 </c:forEach>
	</table>
	<div class="script">
		<script type="text/javascript">Init();</script> 
	</div>
</div>
		