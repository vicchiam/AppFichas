<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<nav id="menu">
		<ul>
			<c:if test ="${sessionScope.user.type==1}">				
			<li class="level1">
				<a class="level1" href="#">Gestión</a>
				<ul class="level2">
					<li><a class="level2" href="/AppFichas/Users?action=list">Usuarios</a></li>
					<li><a class="level2" href="/AppFichas/Trademarks?action=list">Marcas</a></li>
					<li><a class="level2" href="#">Centros</a></li>
					<li><a class="level2" href="#">Envase</a></li>
					<li><a class="level2" href="#">Embalaje</a></li>
					<li><a class="level2" href="#">Químicos</a></li>
					<li><a class="level2" href="/AppFichas/WeightUnits?action=list">Unidades Peso</a></li>
				</ul>
			</li>
			</c:if>
			<li class="level1">
				<a class="level1" href="#">Fichas</a>
			</li>
			<li class="level1">
				<a class="level1" href="/AppFichas/?action=logout">Salir</a>
			</li>
		</ul>
		<div id="user_nav">
			<c:out value= "${sessionScope.user.user}" />
			<c:out value= "${sessionScope.user.type}" />
		</div>
	</nav>
</header>