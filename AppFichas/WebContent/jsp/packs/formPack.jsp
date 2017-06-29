<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<table>		
		<tr>
			<td>
				<input type="hidden" id="_id" value="<c:out value='${requestScope.pack.id}'/>" />
				<label for="name">Descripción:</label>
			</td>
			<td><input type="text" value="<c:out value='${requestScope.pack.description}'/>" id="description" name="description" required/></td>
		</tr>
		<tr>
			<td>
				<label for="name">Medidas:</label>
			</td>
			<td><input type="text" value="<c:out value='${requestScope.pack.mesure}'/>" id="mesure" name="mesure" required/></td>
		</tr>			
		<tr>
			<td colspan="2">				
				<label for="no_apt">No Apto</label>
				<input type="radio" <c:if test = '${requestScope.pack.apt==1}'>checked</c:if> id="no_apt" name="apt" />
				<label for="apt">Apto</label>
				<input type="radio" <c:if test = '${requestScope.pack.apt==2}'>checked</c:if> id="apt" name="apt" />
			</td>			
		</tr>		
		<tr>
			<td colspan="2" class="center">
				<input type="button" class="button_green" value="Guardar" onclick="SavePack()" />
			</td>
		</tr>
	</table>
</div>
<div class="script">
	<script type="text/javascript">InitForm();</script> 
</div>