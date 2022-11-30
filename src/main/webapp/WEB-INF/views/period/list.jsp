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
				<h1 class="h2">신청 기간 관리</h1>
			</div>
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2">
				<div></div>
				<div>
					<a class="btn btn-warning" href="/Socks/period/add.do">항목 추가</a>
					<button type="button" class="btn btn-outline-success btn-block"
						asp-controller="Home" asp-action="양말품목관리">저장</button>
				</div>
			</div>
			<div class="table-scroll"
				style="height: 76.1%; overflow-x: hidden; overflow-y: auto;">
				<table class="table table-bordered" id="update">
					<thead>
						<tr>
							<th scope="col" style="width: 15%;">Order_No</th>
							<th scope="col" style="width: 35%;">신청기간</th>
							<th scope="col" style="width: 25%;">신청시작일</th>
							<th scope="col" style="width: 25%;">신청종료일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td>${list.due_ID}</td>
								<td>${list.period}</td>
								<td>${list.sta_Date}</td>
								<td>${list.closing_Date}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script src="~/js/UpDel.js"></script>
	</main>
</body>
</html>