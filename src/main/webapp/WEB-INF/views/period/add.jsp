<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<section>
			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3">
					<h1 class="h2">신청 기간 등록</h1>
				</div>
				<form method="POST" action="/Socks/period/addok.do">
					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap float-end align-items-center pb-2">
						<button class="btn btn-outline-success btn-block"
							onclick="location.href='/Socks/order/list.do';">저장</button>
					</div>

					<table class="table table-bordered text-center">
						<!-- <tr>
							<th class="table-active align-middle text-center">기간</th>
							<td colspan="3">
							<input type="text" name="period" class="form-control" style="width: 20%" placeholder="내용을 입력하세요" required>
							</td>
						</tr> -->
						<tr>
							<th class="table-active align-middle text-center">신청년월</th>
							<td colspan="3">
								<div
									class="d-flex col-8 flex-wrap flex-md-nowrap align-items-center">
									<select class="form-select w-50" name="year" required>
										<option value="" disabled selected>Year</option>
										<c:forEach items="${year}" var="year">
											<option value="${year.year}">${year.year}</option>
										</c:forEach>
									</select> <label class="col-1 col-form-label text-center">년</label> <select
										class="form-select w-50" name="month" required>
										<option value="" disabled selected>Month</option>
										<c:forEach items="${month}" var="month">
											<option value="${month.month}">${month.month}</option>
										</c:forEach>
									</select> <label class="col-1 col-form-label text-center">월</label>
								</div>
							</td>
						</tr>
						<tr>
							<th class="table-active align-middle">신청시작일</th>
							<td><input type="date" name="start" class="form-control"
								required></td>
							<th class="table-active align-middle">신청종료일</th>
							<td><input type="date" name="end" class="form-control"
								required></td>
						</tr>
					</table>
				</form>
			</main>
		</section>
	</main>
</body>
</html>