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
				<select id="f_apt">
					<option value="0">*</option>
					<c:forEach var="aptName" items="${requestScope.aptNames}" varStatus="status">
						<option value="<c:out value='${status.count}'/>" <c:if test = "${status.count==requestScope.f_apt}">selected</c:if> ><c:out value='${aptName}'/></option>
					</c:forEach>
				</select>
			</td>			
			<td class="der"><img src="/AppFichas/resources/images/search.png" class="img_button" onclick="Search()"/></td>
		</tr>
		<tr>
			<th>Description</th>
			<th>Medidas</th>
			<th>Peso vacio</th>						
			<th class="der"><img src="/AppFichas/resources/images/add.png" class="img_button" onclick="ShowNewPack()" /></th>				
		</tr>			
		<c:forEach var="pack" items="${requestScope.listPacks}">
		<tr class="row">
			<td><c:out value = "${pack.description}"/></td>			
			<td><c:out value = "${pack.mesure}"/></td>
			<td>
				<c:out value = ""/>
				<img class="img_button float_right" src="/AppFichas/resources/images/user.png" onclick="ShowListPackWeight('<c:out value = "${pack.id}"/>')" />
			</td>
			<td class="der">				
				<img title="Editar Envase" src="/AppFichas/resources/images/edit.png" class="img_button" onclick="ShowUpdatePack('<c:out value = "${pack.id}"/>')" />
				<img title="Cambiar estado Envase" src="/AppFichas/resources/images/recycle.png" class="img_button" onclick="ChangeStatePack('<c:out value = "${pack.id}"/>')" />
			</td>
         </tr>
      	 </c:forEach>
	</table>	
</div>
<div class="script">
	<script type="text/javascript">Init();</script> 
</div>
		