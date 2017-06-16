<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Listado Usuarios</h2>
<div id="users">
	<table class="list">
		<tr class="filtro">
			<td colspan=2></td>
			<td>Ver Pasivos:&nbsp;&nbsp;<input type="checkbox" id="f_state" <c:if test = "${requestScope.f_state==0}">checked</c:if> /></td>
			<td></td>
		</tr>
		<tr class="filtro">
			<td><input id="f_user" type="text" value="<c:out value='${requestScope.f_user}'/>" /></td>
			<td><input id="f_mail" type="text" value="<c:out value='${requestScope.f_mail}'/>" /></td>
			<td>
				<select id="f_type">
					<option value="">*</option>
					<c:forEach var="typeName" items="${requestScope.typeNames}" varStatus="status">
					<option value="<c:out value='${status.count}'/>"  <c:if test = "${status.count==requestScope.f_type}">selected</c:if> ><c:out value='${typeName}'/></option>
					</c:forEach>
				</select>				
			</td>
			<td class="der"><img src="/AppFichas/resources/images/search.png" class="img_button" onclick="Search()"/></td>
		</tr>
		<tr>
			<th>Usuario</th>
			<th>Email</th>
			<th>Tipo</th>
			<th class="der"><img src="/AppFichas/resources/images/add.png" class="img_button" onclick="ShowNewUser()" /></th>				
		</tr>			
		<c:forEach var="user" items="${requestScope.listUsers}">
		<tr class="row">
			<td><c:out value = "${user.user}"/></td>
			<td><c:out value = "${user.mail}"/></td>
			<td><c:out value = "${user.typeName}"/></td>						
			<td class="der">
				<img title="Editar Usuario" src="/AppFichas/resources/images/edit.png" class="img_button" onclick="ShowUpdateUser('<c:out value = "${user.id}"/>')" />
				<img title="Modificar contraseña" src="/AppFichas/resources/images/password.png" class="img_button" onclick="ShowChangePassword('<c:out value = "${user.id}"/>')" />
				<img title="Cambiar estado Usuario" src="/AppFichas/resources/images/recycle.png" class="img_button" onclick="ChangeStateUser('<c:out value = "${user.id}"/>')" />
			</td>
         	</tr>
      	</c:forEach>
	</table>
	<div class="script">
		<script type="text/javascript">Init();</script> 
	</div>
</div>
		