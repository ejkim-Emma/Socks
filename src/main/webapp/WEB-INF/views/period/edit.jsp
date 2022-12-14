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
				<form method="POST" action="/Socks/period/editok.do">
					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap float-end align-items-center pb-2">
						<button class="btn btn-outline-success btn-block"
							onclick="location.href='/Socks/period/list.do';">저장</button>
					</div>

					<table class="table table-bordered text-center">
						<tr>
							<th class="table-active align-middle text-center">신청년월</th>
							<td colspan="3">
								<div class="d-flex col-8 flex-wrap flex-md-nowrap align-items-center">
									
									<!-- 월 -->
									<select class="form-select w-50" name="year" id="year" required>
										<option value="${edit.year}" selected>${edit.year}</option>
										<c:forEach items="${ylist}" var="ylist">
											<option value="${ylist.year}">${ylist.year}</option>
										</c:forEach>
									</select>
									<label class="col-1 col-form-label text-center">년</label>
									
									<!-- 년 -->
									<select class="form-select w-50" name="month" required>
										<option value="${edit.month}" selected>${edit.month}</option>
										<c:forEach items="${mlist}" var="mlist">
											<option value="${mlist.month}">${mlist.month}</option>
										</c:forEach>
									</select>
									<label class="col-1 col-form-label text-center">월</label>
								
								</div>
							</td>
						</tr>
						<tr>
							<th class="table-active align-middle">신청시작일</th>
							<td>
								<input type="date" value="${edit.sta_Date}" name="start" class="form-control" required>
							</td>
							<th class="table-active align-middle">신청종료일</th>
							<td>
								<input type="date" value="${edit.closing_Date}" name="end" class="form-control" required>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${edit.due_id}" name="due_ID" />
				</form>
				<c:set var="select" value="${edit.month}" />
				<c:forEach items="${mlist}" var="list">
					<c:if test="${list.month eq select}">
						${list.month}
					</c:if>
				</c:forEach>
				<c:set var="sel2" value="${edit.year}" />
				<c:forEach items="${ylist}" var="list">
					<c:if test="${list.year eq sel2}">
						${list.year}
					</c:if>
				</c:forEach>
			</main>
		</section>
	</main>
	<script>
	/* $('#year').val("1").prop("selected", true); */
	</script>
</body>
</html>