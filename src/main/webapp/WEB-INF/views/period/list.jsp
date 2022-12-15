<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				</div>
			</div>
			<form method="POST" action="/Socks/period/list.do">
				<button type="submit" id="save" class="btn btn-outline-success"
					onclick="saving()">저장</button>
				<div class="table-wrapper"
					style="overflow-y: auto; border-collapse: collapse; height: 500px; padding-top: 0px;">
					<table class="table table-bordered" id="update">
						<thead>
							<tr>
								<th scope="col" style="width: 5%;">No</th>
								<th scope="col" style="width: 5%;">삭제</th>
								<th scope="col" style="width: 25%;">신청기간</th>
								<th scope="col" style="width: 10%;">년</th>
								<th scope="col" style="width: 10%;">월</th>
								<th scope="col" style="width: 20%;">신청시작일</th>
								<th scope="col" style="width: 20%;">신청종료일</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="list">
								<input type="hidden" name="allPeriodId" value="${list.due_ID}" />
								<c:set var="num" value="${list.due_ID}" />
								<tr>
									<td>${fn:substring(num,8,10)}</td>
									<th><input type="checkbox" id="checking"
										name="selectPeriodId" value="${list.due_ID}" /></th>
									<td><input type="text"
										class="form-control form-control-sm" id="period"
										name="allPeriod" value="${list.period}" />
									<%-- <input type="text" class="form-control form-control-sm" id="year" value="${list.year}" /> --%></td>
									<td><input type="number"
										class="form-control form-control-sm" id="year" name="allYear"
										value="${list.year}" /></td>
									<td><input type="number"
										class="form-control form-control-sm" id="month" name="allMonth"
										value="${list.month}" /></td>
									<td><input type="date"
										class="form-control form-control-sm" name="allStart"
										value="${list.sta_Date}" /></td>
									<td><input type="date"
										class="form-control form-control-sm" name="allClose"
										value="${list.closing_Date}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</form>
		</div>
		<script>
			<c:if test="${result == 1}">
				location.href = '/Socks/period/list.do';
				alert('성공');
			</c:if>

			<c:if test="${result == 0}">
				alert('failed');
				history.back();
			</c:if>
		
		
			// 년 / 월 바꾸기

				var period = $('#period').val();
				// console.log(period);	// 2044년6월(7~8월분)
				
				var yyyy = period.substring(4,5);
				// console.log('년도 자르기: ' + yyyy);	// 년
				
				var mm = period.substring(5,14);
				// console.log('월 자르기: ' + mm);	// 6월(7~8월분)
				
				var yyy = period.substring(0, 5);
				// console.log('년까지 자르기: ' + yyy);	// 2044년
				
				// 년 -> 월 바꾸기
				$('#year').on('propertychange change keyup paste input keyup', function () {
					// console.log('들어오나?');
					
					var year = parseInt($('#year').val());
					
					// console.log('년도의 변경값: ' + typeof(year));
					if(isNaN(year)) {
						year = 0;
					} else if (isNaN(year) && isNaN(month)) {
						year = 0;
						month = 0;
					}
					var setMonth = parseInt($('#month').val());
					
					$('#month').on('propertychange change keyup paste input keyup', function() {
						var setMonth = parseInt($('#month').val());
						// console.log('변경값을 가져오나?' + typeof(month));
					});
					
					var yymm = $('#period').val(year + '년' + setMonth + '월(' + (setMonth+1) + '~' + (setMonth+2) + '월분)');
				});

				
				// 월 바꾸기
				$('#month').on('propertychange change keyup paste input keyup', function() {
					// console.log('들어오나?');
					var month = parseInt($('#month').val());
					// console.log('변경값을 가져오나?' + typeof(month));
					if (isNaN(month)) {
						month = 0;
						var setYear = $('#year').val();
						var yyyymm = $('#period').val(setYear + '년' + month + '월(' + (month+1) + '~' + (month+2) + '월분)');
					} else {
						
						var setYear = $('#year').val();
						// console.log('적은 날짜' + month);
						
						var calMonth = month;
						if (month == 12) {
							
							calMonth = 0;
						};
						var yyyymm = $('#period').val(setYear + '년' + (month) + '월(' + (calMonth + 1) + '~' + (calMonth + 2) + '월분)');
						// console.log(yyyymm);
					}
					
				});
				
			
			// 체크박스 한개 불러오기
			var checkOne = $('input:checkbox[id="checking"]').val();
			// console.log('id가 checking인 체크박스: ' + checkOne);
			
			// 배열의 길이 가져오기
			var length = ${fn:length(list)};
			// console.log('전체길이: ' + length);
			
			// PK 배열로 만들기
			var dueId = new Array();
			<c:forEach var = "list" items="${list}">
				dueId.push("${list.due_ID}");
			</c:forEach>
			// console.log('배열: ' + dueId);
			
			// 배열만큼 PK 가져오기
			for (var i =0; i < length; i++) {
				// console.log(i+ '번째 값: ' + dueId[i]);
			};
			
			// 선택한 값을 버튼에 담기
			function saving() {
				
				var arraySelect = [];
				$('input:checkbox[id="checking"]:checked').each(function(index) {
					arraySelect[index] = $(this).val();
				});
				// console.log('배열의 값은?' + arraySelect);
				
				var saveValue;
				saveValue = $('#save').val(arraySelect);
				console.log('버튼에 담겼는가?' + $('#save').val());
				
			};
			
			// 신청기간 코드 배열 만들기
			var codeList = new Array();
			<c:forEach var="list" items="${list}">
				codeList.push("${list.due_ID}");
			</c:forEach>
			// console.log('신청기간 코드 전체 배열: ' + codeList);
			
			// 신청기간 배열 만들기
			var periodList = new Array();
			<c:forEach var="list" items="${list}">
				periodList.push("${list.period}");
			</c:forEach>
			// console.log('신청기간 전체 배열: ' + periodList);
			
			// 신청시작일 배열 만들기
			var startList = new Array();
			<c:forEach var="list" items="${list}">
				startList.push("${list.sta_Date}");
			</c:forEach>
			// console.log('신청시작일 전체 배열: ' + startList);
			
			// 신청종료일 배열 만들기
			var closeList = new Array();
			<c:forEach var="list" items="${list}">
				closeList.push("${list.closing_Date}");
			</c:forEach>
			// console.log('신청종료일 배열만들기' + closeList);
			
			// 변수 선언
			var codeArr;
			var periodArr;
			var startArr;
			var closeArr;
			
			$(document).ready(function() {
				// console.log('들어옴?');
				
				for(var i = 0; i < length; i++) {
					
					codeArr = codeList[i];
					
					periodArr = periodList[i];
					
					startArr = startList[i];
					
					closeArr = closeList[i];
					
				};
				
			});
			
		</script>
	</main>
</body>
</html>