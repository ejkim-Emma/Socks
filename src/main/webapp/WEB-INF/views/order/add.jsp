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
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3">
				<h1 class="h2">Order 내역 추가</h1>
			</div>
			<form method="POST" action="/Socks/order/add.do">
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap float-end align-items-center pb-2">
					<button type="submit" class="btn btn-outline-success btn-block"
						onclick="location.href='/Socks/order/list.do';">저장</button>
				</div>
				<table class="table border-top" id="add">
					<tr>
						<th class="table-active align-middle text-center"
							style="width: 15%">brand</th>
						<td colspan="5"><select name="brand" id="brand"
							class="form-select" onchange="brandChange(this)" required>
								<option value="" disabled selected>브랜드</option>
								<c:forEach items="${brandlist}" var="brandlist">
									<option value="${brandlist.brand_ID}">${brandlist.description}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th class="table-active align-middle text-center">매장</th>
						<th colspan="5"><select name="store" id="store"
							class="form-select" required>
								<option value="" disabled selected>매장</option>
						</select></th>
					</tr>
					
					<tr>
						<th class="table-active align-middle text-center">기간</th>
						<th colspan="5"><select name="period" id="period"
								class="form-select me-2">
								<option value="" disabled selected>기간</option>
								<c:forEach items="${dlist}" var="dlist">
									<option value="${dlist.due_id}">${dlist.period}</option>
								</c:forEach>
							</select></th>
					</tr>
					<tr>
						<th class="table-active align-middle text-center">상품</th>
						<th colspan="5"><select name="product" id="product"
								class="form-select me-2">
								<option value="" disabled selected>상품</option>
								<c:forEach items="${plist}" var="plist">
									<option value="${plist.product_code}">${plist.product_name}&nbsp&nbsp${plist.product_size}&nbsp&nbsp${plist.product_color}</option>
								</c:forEach>
							</select></th>
					</tr>
					<tr>
						<th class="table-active align-middle text-center">합계: 수량</th>
						<td colspan="4"><input type="number" name="product_qty" id="product_qty"
							 style="width: 20%"
							class="form-control" maxlength="10" placeholder="숫자를 입력하세요"
							required></td>
					</tr>
				</table>
			</form>
		</div>

	</main>
	<script>
		function brandChange(e) {
			$.ajax({
				// 기본 타입은 GET
				type : 'GET',
				// url: 처음 만들 때 설정하는 /web location/ajax의 @WebServlet() 안에 내용
				url : '/Socks/ajax/store.do',
				// ajax.java에서 생성했던 String을 가져오는 것
				data : 'Brand_ID=' + $(event.target).val(),
				// 여러 개의 타입이 존재
				dataType : 'json',
				// function () 안에 있는 것은 변수 이름
				success : function(result) {
					// alert('성공');

					// 'store'을 아이디로 가지고 있는 것 중에 어디 위치로 갈 것인 지 append()에서 괄호 안에 넣기
					// $('#store').append('<option value="" disabled selected>Location_Name</option>');

					// 결과가 나오면 그것을 each문으로 돌리겠다.
					$(result).each(
							function(index, item) { // 데이터 = item
								// alert(JSON.stringify(item));

								$('#store').append(
										'<option value="' + item.Store_ID + '">'
												+ item.Description
												+ '</option>')
							});
				},
				error : function() {
					alert('실패');
				}
			});
		}
	</script>
</body>
</html>