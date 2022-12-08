<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SocksOrder</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp" %>

<style>
<%@ include file="/asset/css/template.css"  %>
</style>
</head>
<body>
	<header class="navbar navbar-dark sticky-top p-0"
		style="background-color: #6998AD;">
		<img src="/Socks/asset/images/Bluebell.png" class="d-flex justify-content-around ms-5" height="30px" />
		<div class="navbar-nav">
			<div class="nav-item d-flex">
			<c:if test="${not empty auth}">
				<div class="nav-link text-dark" style="font-weight: bold">${description}</div>
			</c:if>
				<a class="nav-link px-3" href="/Socks/member/login.do">로그아웃</a>
			</div>
		</div>
	</header>

	<div class="container-fluid">
		<div class="row">
			<nav id="sidebarMenu"
				class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
				<div class="position-sticky sidebar-sticky">
					<h6
						class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted text-uppercase">
						<span>매장</span>
					</h6>
					<ul class="nav flex-column">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/Socks/dutyfree/list.do"> > <span
								data-feather="file-text" class="align-text-bottom"></span> 양말 오더
						</a></li>
					</ul>
				</div>
			</nav>

		</div>
	</div>
</body>
</html>