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
.table-scroll {
	overflow: auto;
	text-align: center;
}

.table-scroll thead th {
	background: #859395;
	color: #fff;
	position: sticky;
	top: 0;
}
</style>
</head>
<body>
	<main>
		<%@ include file="/WEB-INF/views/template.jsp"%>
		<div class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3">
				<h1 class="h2">양말 오더</h1>
			</div>

			<div class="flex-wrap flex-md-nowrap align-items-center">
				<form method="GET" action="/Socks/dutyfree/list.do"
					class="d-flex col-6 flex-wrap flex-md-nowrap align-items-center pb-2">
					<!-- 신청일자 -->
					<!-- 신청일자 라벨 -->
					<label class="col-2 col-form-label text-center">신청일자</label>


					<!-- 신청일자 고르는 곳 -->
					<select name="period" id="period"
						class="form-select float-start" style="width: 50%;" required>
						<option value="" disabled selected>Period</option>
						<c:forEach items="${plist}" var="period">
							<option value="${period.due_id}">${period.period}</option>
						</c:forEach>
					</select>
					<!-- 신청일자 종료-->
					<!-- 검색버튼 -->
					<button class="btn btn-primary ms-2">search</button>
				</form>
			</div>
			<!-- 검색 바 종료 -->

			<div class="table-scroll"
				style="height: 53.65%; overflow-x: hidden; overflow-y: auto;">
				<table class="table table-bordered table-hover" id="update">
					<thead>
						<tr>
							<th style="width: 20%;">상품명</th>
							<th style="width: 10%;">사이즈</th>
							<th style="width: 10%;">색상</th>
							<th style="width: 20%;">최대수량</th>
							<th style="width: 20%;">주문수량</th>
							<th style="width: 20%;">주문가능수량</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${qty}" var="list">
							<tr>
								<td>${list.product_name}</td>
								<td>${list.product_size}</td>
								<td>${list.product_color}</td>
								<td>-</td>
								<td>${list.product_qty}</td>
								<td>-</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</main>
	<script>
		$(function() {
			$("#period").val("${param.period}").attr("selected", "selected");
		});
	</script>
</body>
</html>