<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<table>		
		<tr>
			<td>
				<input type="hidden" id="_id" value="<c:out value='${requestScope.trademark.id}'/>" />
				<label for="name">Nombre:</label>
			</td>
			<td><input type="text" value="<c:out value='${requestScope.trademark.name}'/>" id="name" name="name" placeholder="Nombre de la marca" required/></td>
		</tr>		
		<tr>
			<td colspan="2" class="center">
				<input type="button" class="button_green" value="Guardar" onclick="SaveTrademark()" />
			</td>
		</tr>
	</table>	
</div>
<div class="script">
	<script type="text/javascript">InitForm();</script> 
</div>