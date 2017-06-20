<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form">
	<div id="left">		
		<fieldset class="select_container">
			<legend>&nbsp;Marcas:</legend>
			<ul>
				<c:forEach var="trademark" items="${requestScope.trademarks}" varStatus="status">
					<li class=""><c:out value="${trademark.name }" /></li>					
				</c:forEach>				
			</ul>
		</fieldset>
	</div>
	<div id="commands">
		<button class="button_red">&lt;&lt;</button>
		<button class="button_green">&gt;&gt;</button>
	</div>
	<div id="right">
		<fieldset class="select_container">
			<legend>&nbsp;Marcas de usuario:</legend>
			<ul>
				<li class="">La Sirena</li>
			</ul>
		</fieldset>
	</div>
</div>
<div class="script">
	<script type="text/javascript">InitFormUserTrademark();</script> 
</div>