<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SocksOrder</title>
</head>
<body>

	<main>
		<%@ include file="/WEB-INF/views/template.jsp"%>
		<section></section>
	</main>

	<script>
		<c:if test="${result == 1}">
		location.href = '/Socks/period/list.do';
		</c:if>

		<c:if test="${result == 0}">
		alert('중복된 데이터가 존재합니다.');
		history.back();
		</c:if>
	</script>

</body>
</html>