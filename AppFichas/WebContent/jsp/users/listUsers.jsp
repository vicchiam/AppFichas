<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Listado Usuarios</h2>
<div id="users">
	<table class="list">
		<tr class="filtro">
			<td><input id="f_user" type="text" value="" /></td>
			<td><input id="f_mail" type="text" value="" /></td>
			<td>
				<select id="f_type">
					<option value="">*</option>
					<c:forEach var="typeName" items="${requestScope.typeNames}" varStatus="status">
					<option value="<c:out value='${status.count}'/>"><c:out value='${typeName}'/></option>
					</c:forEach>
				</select>
			</td>
			<td class="der"><img src="/AppFichas/resources/images/search.png" class="button" onclick="Search()"/></td>
		</tr>
		<tr>
			<th>Usuario</th>
			<th>Email</th>
			<th>Tipo</th>
			<th class="der"><img src="/AppFichas/resources/images/add.png" class="button" /></th>				
		</tr>			
		<c:forEach var="user" items="${requestScope.listUsers}">
		<tr class="row">
			<td><c:out value = "${user.user}"/></td>
			<td><c:out value = "${user.mail}"/></td>
			<td><c:out value = "${user.typeName}"/></td>						
			<td class="der">
				<img src="/AppFichas/resources/images/edit.png" class="button" onclick="Edit('<c:out value = "${user.id}"/>')" />
				<img src="/AppFichas/resources/images/delete.png" class="button" onclick="Delete('<c:out value = "${user.id}"/>')" />
			</td>
         	</tr>
      	</c:forEach>
	</table>
</div>
		