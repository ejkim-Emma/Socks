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
		<%@ include file="/WEB-INF/views/template.jsp"%>
		<section>
			<form method="POST" action="/Socks/product/delok.do">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap float-end align-items-center">
					<input type="button" value="돌아가기" class="btn btn-secondary"
						onclick="location.href='/Socks/product/list.do?product_code=${list.product_code}';">
						
						<button class="btn btn-outline-success btn-block">저장</button>
						<input type="text" name="product_code"
							value="${product_code}">
				</div>
			</form>
		</section>
	</main>
	<script>
		
	</script>
</body>
</html>