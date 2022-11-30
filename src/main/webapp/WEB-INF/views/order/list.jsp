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
		<section>
			<div class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3">
					<h1 class="h2">Order 내역 관리</h1>
				</div>

				<form method="get" action="/Socks/order/list.do">
					<div
						classs="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2">
						<!-- 년 / 월 입력 -->
						<div
							class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2">

							<div
								class="d-flex col-5 flex-wrap flex-md-nowrap align-items-center">
								<select class="form-select w-25" name="year" required>
									<option value="" disabled selected>Year</option>
									<c:forEach items="${ylist}" var="ylist">
										<option value="${ylist.year}">${ylist.year}</option>
									</c:forEach>
								</select> <label for="staticEmail"
									class="col-1 col-form-label text-center">년</label> <select
									class="form-select w-25" name="month" required>
									<option value="" disabled selected>Month</option>
									<c:forEach items="${mlist}" var="mlist">
										<option value="${mlist.month}">${mlist.month}</option>
									</c:forEach>
								</select> <label for="staticEmail"
									class="col-1 col-form-label text-center">월</label>
							</div>
						</div>

						<!-- 브랜드 / 매장 입력-->
						<div
							class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2">
							<div
								class="d-flex col-4 justify-content-between flex-wrap flex-md-nowrap align-items-center">
								<select class="form-select w-50 me-2" name="BrDescription">
									<option value="" disabled selected>Brand_Name</option>
									<c:forEach items="${blist}" var="blist">
										<option value="${blist.description}">${blist.description}</option>
									</c:forEach>
								</select> <select class="form-select w-50" name="DuDescription">
									<option value="" disabled selected>Location_Name</option>
									<c:forEach items="${dflist}" var="dflist">
										<option value="${dflist.description}">${dflist.description}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<!-- 품목명 / 사이즈 / 컬러 입력 -->
						<div
							class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2">
							<div
								class="d-flex col-5 justify-content-between flex-wrap flex-md-nowrap align-items-center">
								<!-- 품목명 입력-->
								<select class="form-select w-50 me-2" name="Product_name">
									<option value="" disabled selected>상품명</option>
									<c:forEach items="${nlist}" var="nlist">
										<option value="${nlist.product_name}">${nlist.product_name}</option>
									</c:forEach>
								</select>
								<!-- 사이즈 입력 -->
								<select class="form-select w-25 me-2" name="Product_size">
									<option value="" disabled selected>사이즈</option>
									<c:forEach items="${slist}" var="slist">
										<option value="${slist.product_size}">${slist.product_size}</option>
									</c:forEach>
								</select>
								<!-- 컬러 입력 -->
								<select class="form-select w-25 me-2" name="Product_color">
									<option value="" disabled selected>색상</option>
									<c:forEach items="${clist}" var="clist">
										<option value="${clist.product_color}">${clist.product_color}</option>
									</c:forEach>
								</select>
								<button class="btn btn-primary">search</button>
							</div>
						</div>
				</form>
				<div>
					<a class="btn btn-warning" href="/Socks/order/add.do">항목 추가</a>
				</div>
				<div class="table-scroll"
					style="height: 76%; overflow-x: hidden; overflow-y: auto;">
					<table class="table table-bordered table-hover text-center"
						id="update">
						<thead>
							<tr>
								<th style="width: 5%;">No</th>
								<th style="width: 10%;">삭제 / 수정</th>
								<th style="width: 20%;">Brand</th>
								<th style="width: 20%;">Location</th>
								<th style="width: 20%;">상품명</th>
								<th style="width: 8%;">사이즈</th>
								<th style="width: 7%;">컬러</th>
								<th style="width: 10%;">합계:수량</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="list">
								<c:set var="i" value="${i+1}" />
								<tr>
									<td>${i}</td>
									<th>
										<div>
											<input type="submit" value="삭제" data-bs-toggle="modal"
												data-bs-target="#del" data-bs-code="${list.order_id}">
											/ <input type="button" value="수정" onclick="location.href='/Socks/order/edit.do?order_id=${list.order_id}&due_id=${list.due_id}&product_code=${list.product_code}&period=${list.period}&product_name=${list.product_name}&product_size=${list.product_size}&product_color=${list.product_color}&brand_name=${list.brDescription}&store_name=${list.duDescription}&product_qty=${list.product_Qty}'">
										</div>
									</th>
									<td>${list.brDescription}</td>
									<td>${list.duDescription}</td>
									<td>${list.product_name}</td>
									<td>${list.product_size}</td>
									<td>${list.product_color}</td>
									<td>${list.product_Qty}</td>
								</tr>
								<input type="hidden" value="${list.product_code}"/>
								<input type="hidden" value="${list.due_id}" />
								<input type="hidden" value="${list.store_id}" />
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
		</section>
	</main>
	<script>
		var CODE = "";

		$(document).ready(function() {

			$('#del').on('show.bs.modal', function(event) {

				CODE = $(event.relatedTarget).data('bs-code');
				/* console.log('이벤트 발생시 모달에 코드가 들어가나요? :' + CODE); */
			});
		});

		function del() {

			var save = $('#save').val();
			/* console.log('code가 저장안에 들어갔나요? ' + CODE); */
			location.href = '/Socks/order/delok.do?order_id=' + CODE
		}
	</script>
</body>
</html>