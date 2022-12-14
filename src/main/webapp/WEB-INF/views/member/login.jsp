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
			<form method="POST" action="/Socks/member/loginok.do">
				<!-- 브랜드 이름 -->
				<div>
					<div class="d-grid col-6 mb-3 mx-auto">
						<label for="formFile" class="form-label">브랜드</label>
						<!-- 'onchange'는 클릭했을 때 변화하는 것을 의하는 것 / "내용"은 변수이름 -->
						<select name="brand" id="brand" class="form-select" onchange="brandChange(this)" required>
							<option value="" disabled selected>Brand_name</option>
							<!-- items는 login.java에서 setAttribute에서의 "" 안에 변수명을 가져온 것이고 var는 el을 쓰기 위해 새로운 변수명을 선언한 것이다.
							데이터를 가져올 때 처음 시작은 소문자로 하기 -->
							<c:forEach items="${brandlist}" var="brandlist">
								<option value="${brandlist.brand_ID}">${brandlist.description}</option>
							</c:forEach>
						</select>
					</div>

					<!-- 매장 위치 -->
					<div class="d-grid col-6 mb-3 mx-auto">
						<label for="formFile" class="form-label">매장</label>
						<select name="store" id="store"  class="form-select" required>
							<option value="" disabled selected>Store_Name</option>
						</select>
					</div>
				</div>
				<!-- 비밀번호 -->
<!-- 				<div class="d-grid col-6 mb-3 mx-auto">
					<label for="formFile" class="form-label">비밀번호</label> <input
						type="password" class="form-control" placeholder="Password"
						required>
				</div> -->

				<!-- 로그인 버튼 -->
				<div class="d-grid col-6 mb-3 mx-auto">
					<button class="btn btn-primary btn-block">Login</button>
				</div>
			</form>
		</section>
	</main>
	<script>

	function brandChange() {
		console.log('function 안인가?');
		$.ajax ({
			// 기본 타입은 GET
			type: 'GET',
			// url: 처음 만들 때 설정하는 /web location/ajax의 @WebServlet() 안에 내용
			url: '/Socks/ajax/store.do',
			// 넘길 데이터 가져오기
			data: 'Brand_ID=' + $(event.target).val(),
			// 여러 개의 타입이 존재
			dataType: 'json',
			// function () 안에 있는 것은 변수 이름
			success: function(result) {
				// alert('성공');
				$('#store').empty();
				// 'store'을 아이디로 가지고 있는 것 중에 어디 위치로 갈 것인 지 append()에서 괄호 안에 넣기
				$('#store').append('<option value="" disabled selected>Store_Name</option>');
				/* $('#store').val(''); */
				
				// 결과가 나오면 그것을 each문으로 돌리겠다.
				$(result).each(function (index, item) { // 데이터 = item
					// alert(JSON.stringify(item));
					$('#store').append('<option value="' + item.Store_ID + '">' + item.Description + '</option>')
				});
			},
			error: function () {
				alert('실패');
			}
		});
	}
	</script>
</body>
</html>
