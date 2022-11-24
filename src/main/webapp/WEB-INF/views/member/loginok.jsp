<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SocksOrder</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
</head>
<body>
	<main>
		<section></section>
	</main>

	<script>
		alert('세션 검사를 하나?');

		<c:if test="${not empty auth}">
		alert('if문을 이용하나?');
		location.href = '/Socks/product/list.do';
		</c:if>
		
		<c:if test="${empty auth}">
		alert('failed');
		</c:if>
	</script>
</body>
</html>