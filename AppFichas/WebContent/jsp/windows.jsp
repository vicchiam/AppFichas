<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="window" items="${requestScope.windows}">
	<div class="modal" id="modal_<c:out value='${window.id}' />" style="display:none">
	</div>
	<div class="window" id="window_<c:out value='${window.id}' />" 
		style="display:none;
			width:<c:out value='${window.width}' />px;
			height:<c:out value='${window.height}' />px;
			margin-left:-<c:out value='${window.width/2}' />px;			
			">
		<div class="window_head">
			<span class="window_title"><c:out value='${window.title}' /></span>
			<img src="/AppFichas/resources/images/close.png" onclick="CloseWindow('<c:out value='${window.id}' />')" />
		</div>
		<div class="dynamic" id="dynamic_<c:out value='${window.id}' />"></div>
	</div>	
</c:forEach>