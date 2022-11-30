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
		<div class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3">
				<h1 class="h2">양말항목 수정</h1>
			</div>
			<form method="POST" action="/Socks/product/editok.do">
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap float-end align-items-center pb-2">
					<button type="submit" class="btn btn-outline-success btn-block" onclick="location.href='/Socks/product/list.do';">저장</button>
				</div>
				<table class="table border-top" id="sockadd">
					<tr>
						<th class="table-active align-middle text-center" style="width: 15%">상품코드</th>
						<td colspan="5">
							<input type="text" name="code" class="form-control" style="width: 20%" placeholder="내용을 입력하세요" required value="${edit.product_code}">
						</td>
					</tr>
					<tr>
						<th class="table-active align-middle text-center">상품명</th>
						<th colspan="4">
							<input type="text" name="name" class="form-control" style="width: 20%" placeholder="내용을 입력하세요" required value="${edit.product_name}">
						</th>
					</tr>
					<tr>
						<th class="table-active align-middle text-center">컬러</th>
						<th colspan="4">
							<input type="text" name="color" class="form-control" style="width: 20%" placeholder="내용을 입력하세요" required value="${edit.product_color}">
						</th>
					</tr>
					<tr>
						<th class="table-active align-middle text-center">사이즈</th>
						<td colspan="4">
							<input type="text" name="size" class="form-control text-center" style="width: 8%" placeholder="" required value="${edit.product_size}">
						</td>
					</tr>
					<!-- <tr>
						<th class="table-active align-middle text-center">주문단위</th>
						<th class="table-active align-middle text-center"
							style="width: 15%">두달 기준</th>
						<td class="d-flex"><input type="number"
							class="form-control text-center" style="width: 20%" maxlength="2"
							oninput="numberMaxLength(this);" required></td>
						<th class="table-active align-middle text-center"
							style="width: 15%">항목 당</th>
						<td><input type="number" class="form-control text-center"
							style="width: 20%" maxlength="2" oninput="numberMaxLength(this);"
							required></td>
					</tr> -->
					<tr>
						<th class="table-active align-middle text-center">단가</th>
						<td colspan="4">
						<input type="number" name="price" style="width: 20%" class="form-control" maxlength="10" required value="${edit.unit_price}">
						</td>
					</tr>
				</table>
			</form>
		</div>

	</main>
</body>
</html>