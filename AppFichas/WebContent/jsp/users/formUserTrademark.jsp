<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<input type="hidden" id="_id" value="<c:out value="${requestScope.id }" />" />
	<div id="left">		
		<fieldset class="select_container">
			<legend>&nbsp;Marcas:</legend>
			<ul>
				<c:forEach var="trademark" items="${requestScope.trademarks}" varStatus="status">
					<li class=""><c:out value="${trademark.name }" /><input type="hidden" value="<c:out value="${trademark.id }" />"/></li>					
				</c:forEach>				
			</ul>
		</fieldset>
	</div>
	<div id="commands">
		<button class="button_red" onclick="RemoveTrademark()">&lt;&lt;</button>
		<button class="button_green" onclick="AddTrademark()">&gt;&gt;</button>
	</div>
	<div id="right">
		<fieldset class="select_container">
			<legend>&nbsp;Marcas de usuario:</legend>
			<ul>
				<c:forEach var="userTrademark" items="${requestScope.userTrademarks}" varStatus="status">
					<li class=""><c:out value="${userTrademark.name }" /><input type="hidden" value="<c:out value="${userTrademark.id }" />"/></li>					
				</c:forEach>
			</ul>
		</fieldset>
	</div>
</div>
<div class="script">
	<script type="text/javascript">InitFormUserTrademark();</script> 
</div>