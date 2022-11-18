<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>SocksOrder</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
</style>
</head>

<body class="p-3 m-0 border-0 bd-example"
	style="border: 1px solid black;">
	<main>
		<section>
			<form method="post" action="/Socks/member/.do">
				<!-- 브랜드 이름 -->
				<div>
					<div class="d-grid col-6 mb-3 mx-auto">
						<label for="formFile" class="form-label">브랜드 이름</label> <select
							id="country" value="" class="form-select"
							placeholder="Brand_Name" required>
							<option value="" disabled selected>Brand_name</option>
							<option value="">브랜드</option>
						</select>
					</div>

					<!-- 매장 위치 -->
					<div class="d-grid col-6 mb-3 mx-auto">
						<label for="formFile" class="form-label">매장 위치</label> <select
							id="state" value="" class="form-select"
							placeholder="Location_Name" required>
							<option value="" disabled selected>Location_Name</option>
							<option value="">브랜드</option>
						</select>
					</div>
				</div>
				<!-- 비밀번호 -->
				<div class="d-grid col-6 mb-3 mx-auto">
					<label for="formFile" class="form-label">비밀번호</label> <input
						type="password" class="form-control" placeholder="Password"
						required>
				</div>

				<!-- 로그인 버튼 -->
				<div class="d-grid col-6 mb-3 mx-auto">
					<button type="submit" class="btn btn-primary btn-block">Login</button>
				</div>
			</form>
		</section>
	</main>
	<script>
		
	</script>
</body>
</html>
