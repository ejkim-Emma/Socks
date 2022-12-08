<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SocksOrder</title>
<%@ include file="/WEB-INF/views/inc/asset.jsp"%>
<style>
.table-wrapper {
	overflow: auto;
	text-align: center;
}

.table-wrapper thead th {
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
					<!-- <button type="button" class="btn btn-outline-success btn-block"
						asp-controller="Home" asp-action="양말품목관리">저장</button> -->
				</div>
			</div>
			<div class="table-wrapper"
				style="overflow-y: auto; border-collapse: collapse; height: 500px; padding-top: 0px;">
				<table class="table table-bordered" id="update">
					<thead>
						<tr>
							<th scope="col" style="width: 5%;">No</th>
							<th scope="col" style="width: 25%;">신청기간</th>
							<th scope="col" style="width: 25%;">신청시작일</th>
							<th scope="col" style="width: 25%;">신청종료일</th>
							<th scope="col" style="width: 20%;">삭제 / 수정</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="list">
						<c:set var="num" value="${list.due_ID}"/>
							<tr>
								<td>${fn:substring(num,8,10)}</td>
								<td>${list.period}</td>
								<td>${list.sta_Date}</td>
								<td>${list.closing_Date}</td>
								<th><input type="submit" value="삭제" data-bs-toggle="modal"
									data-bs-target="#del" data-bs-code="${list.due_ID}"> /
									<input type="submit" value="수정"
									onclick="location.href='/Socks/period/edit.do?due_ID=${list.due_ID}&year=${list.year}&month=${list.month}&start=${list.sta_Date}&end=${list.closing_Date}'">
								</th>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="modal fade" id="del" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">저장하시겠습니까?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">취소</button>
									<button class="btn btn-outline-success btn-block" id="save"
										onclick="del();">저장</button>

								</div>
							</div>
						</div>
					</div>
			</div>
		</div>
		<script>
			var CODE = "";

			$(document).ready(function() {
				$('#del').on('show.bs.modal', function(event) {
					CODE = $(event.relatedTarget).data('bs-code');
				});
			});

			function del() {
				var save = $('#save').val();
				location.href = '/Socks/period/delok.do?due_ID=' + CODE
			}
		</script>
	</main>
</body>
</html>