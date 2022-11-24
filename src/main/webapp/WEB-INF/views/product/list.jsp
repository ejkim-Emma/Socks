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
				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3">
					<h1 class="h2">양말 품목 관리</h1>
				</div>

				<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2">
					<form method="GET" action="/Socks/product/list.do">
						<div class="d-flex col-7 justify-content-between flex-wrap flex-md-nowrap align-items-center">
							
							<!-- 상품명 검색창 -->
							<select name="product" class="form-select me-2">
								<option value="" disabled selected>상품명</option>
								<c:forEach items="${nlist}" var="nlist">
									<option value="${nlist.product_name}">${nlist.product_name}</option>
								</c:forEach>
							</select>
							
							<!-- 사이즈 검색창 -->
							<select name="size" class="form-select me-2">
								<option value="" disabled selected>사이즈</option>
								<c:forEach items="${slist}" var="slist">
									<option value="${slist.product_size}">${slist.product_size}</option>
								</c:forEach>
							</select>
							
							<!-- 색상 검색창 -->
							<select name="color" class="form-select me-2">
								<option value="" disabled selected>색상</option>
								<c:forEach items="${clist}" var="clist">
									<option value="${clist.product_color}">${clist.product_color}</option>
								</c:forEach>
							</select>
							
							<!-- 검색버튼 -->
							<button class="btn btn-primary">search</button>
							<%-- <a class="btn btn-primary align-items-center"> <svg
									xmlns="http://www.w3.org/2000/svg" width="20" height="20"
									fill="none" stroke="currentColor" stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2" class="mx-1"
									role="img" viewBox="0 0 24 24">
                    				<circle cx="10.5" cy="10.5" r="7.5"></circle>
                    				<path d="M21 21l-5.2-5.2"></path>
                			</svg>
							</a> --%>
						</div>
					</form>
					<div>
						<a class="btn btn-warning" href="/Socks/product/add.do">항목 추가</a>
						<button type="button" class="btn btn-outline-success btn-block">저장</button>
					</div>

				</div>

				<div class="table-scroll"
					style="height: 76%; overflow-x: hidden; overflow-y: auto;">
					<table class="table table-bordered" id="update">
						<thead>
							<tr>
								<th scope="col" style="width: 22%;">상품명</th>
								<th scope="col" style="width: 10%;">삭제</th>
								<th scope="col" style="width: 16%;">상품코드</th>
								<th scope="col" style="width: 16%;">사이즈</th>
								<th scope="col" style="width: 16%;">컬러</th>
								<th scope="col" style="width: 16%;">단가</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="list">
								<tr>
									<td>${list.product_name}</td>
									<th>
										<div>
											<input class="form-check-input" type="checkbox" value="">
										</div>
									</th>
									<td>${list.product_code}</td>
									<td>${list.product_size}</td>
									<td>${list.product_color}</td>
									<td>${list.unit_price}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</section>
	</main>
</body>
</html>