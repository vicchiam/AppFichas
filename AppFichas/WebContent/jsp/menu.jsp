<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<nav id="menu">
		<ul>
			<li class="level1">
				<a class="level1" href="#">Gestión</a>
				<ul class="level2">
					<li><a class="level2" href="#">Usuarios</a></li>
					<li><a class="level2" href="#">Marcas</a></li>
					<li><a class="level2" href="#">Centros</a></li>
					<li><a class="level2" href="#">Envase</a></li>
					<li><a class="level2" href="#">Embalaje</a></li>
					<li><a class="level2" href="#">Químicos</a></li>
				</ul>
			</li>
			<li class="level1">
				<a class="level1" href="#">Fichas</a>
			</li>
			<li class="level1">
				<a class="level1" href="?action=logout">Salir</a>
			</li>
		</ul>
		<div id="user_nav">
			<c:out value= "${sessionScope.user.user}" />
		</div>
	</nav>
</header>