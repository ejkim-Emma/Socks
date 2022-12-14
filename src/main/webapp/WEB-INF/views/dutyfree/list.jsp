<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>
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
		<%@ include file="/WEB-INF/views/template_store.jsp"%>
		<div class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<div
				class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3">
				<h1 class="h2">양말 오더</h1>
			</div>

			<div class="flex-wrap flex-md-nowrap align-items-center">
				<form name="formSearch" id="formSearch" method="get"
					action="/Socks/dutyfree/list.do"
					class="d-flex col-8 flex-wrap flex-md-nowrap align-items-center pb-2">
					<!-- 신청일자 -->
					<!-- 신청일자 라벨 -->
					<label class="col-2 col-form-label text-center">신청일자</label>

					<!-- 현재 날짜  가져와서 숫자로 만들기 -->
					<%-- <jsp:useBean id="now" class="java.util.Date" />
				<fmt:parseNumber value="${now.time / (1000*60*60*24)}" integerOnly="true" var="today" scope="request" />
				<fmt:parseNumber value="${period.closing_date.time / (1000*60*60*24)}" integerOnly="true" var="close" scope="request" />
				<c:forEach items="${plist}" var="period">
					<option value="${period.due_id}">
						<fmt:parseNumber value="${period.closing_date.time / (1000*60*60*24)}" integerOnly="true" var="close" type="number" />
						가져온 날짜: ${period.closing_date}
						현재날짜: <c:out value="${today}" />
						빼기: ${close - today}
					</option>
				</c:forEach> --%>

					<!-- 신청일자 고르는 곳 -->
					<!-- onChange="selectboxSelected()" -->
					<select name="period" id="period" class="col-2 form-select float-star" style="width:40%;" required>
						<option value="" disabled selected>Period</option>
						<%-- <c:forEach items="${plist}" var="period">
							<option value="${period.due_id}">
							<fmt:parseNumber value="${period.closing_date.time / (1000*60*60*24)}" integerOnly="true" var="close" type="number" />
							${period.period}/
								${period.closing_date} /빼기: ${close - today}</option>
						</c:forEach> --%>
						<jsp:useBean id="now" class="java.util.Date" />
						<fmt:parseNumber value="${now.time / (1000*60*60*24)}"
							integerOnly="true" var="today" scope="request" />
						<%-- <fmt:parseNumber value="${period.closing_date.time / (1000*60*60*24)}" integerOnly="true" var="close" scope="request" /> --%>
						<c:forEach items="${plist}" var="period">
							<option value="${period.due_id}" closeYN="${period.day }">
								<fmt:parseNumber
									value="${period.closing_date.time / (1000*60*60*24)}"
									integerOnly="true" var="close" type="number" />
								${period.period}
								<%-- 가져온 날짜: ${period.closing_date} --%>
								<%-- 현재날짜: <c:out value="${today}" /> --%>
								<%-- 빼기: ${close - today} --%>
								
							</option>
							<%-- <option value="${period.day }" name="day">${period.day }</option> --%>
							<%-- <input type="text" value="${period.day}"/> --%>
						</c:forEach>
					</select>
					<!-- 검색버튼 -->
					<button class="btn btn-primary ms-2">search</button>
					<div class="ps-2" id="warning" style="color: red;"></div>
				</form>

			</div>

			<!-- 신청인원-->
			<form name="formCheck" id="formCheck" method="post"
				action="/Socks/dutyfree/list.do">
				<div class="d-flex col-8 flex-row mb-2">
					<label class="col-2 col-form-label text-center">신청인원</label> <input
						type="text" class="d-flex flex-row w-25 me-2 form-control"
						id="people" name="count" value="" />
					<button class="btn btn-outline-success" id="saveData">save</button>
					<!-- d-flex flex-row 를 사용하면 display: none 이 안되는 이유ㅜ -->
					<input type="hidden" id="wrong" name="wrong" value="" />
				</div>
				<%-- <c:set var="now" value="<%=new java.util.Date()%>" />
				<c:set var="today">
					<fmt:formatDate value="${now}" pattern="yyyy" />
				</c:set --%>

				<%-- <fmt:parseNumber value="${period.closing_date.time / (1000*60*60*24)}" integerOnly="true" var="close" scope="request" />
				<c:forEach items="${plist}" var="period">
					<option value="${period.due_id}">
						<fmt:parseNumber value="${period.closing_date.time / (1000*60*60*24)}" integerOnly="true" var="close" type="number" />
						가져온 날짜: ${period.closing_date}
						현재날짜: <c:out value="${today}" />
						빼기: ${close - today}
					</option>
				</c:forEach> --%>


				<!-- 검색 바 종료 -->
				<div class="table-wrapper"
					style="overflow-y: auto; border-collapse: collapse; height: 500px; padding-top: 0px;">
					<table class="table table-bordered table-hover" id="update">
						<thead>
							<tr>
								<th style="width: 20%;">상품명</th>
								<th style="width: 10%;">사이즈</th>
								<th style="width: 10%;">색상</th>
								<th style="width: 20%;">주문단위</th>
								<th style="width: 20%;">주문수량</th>
								<th style="width: 20%;">주문가능수량</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${qty}" var="list">
									<c:set var="i" value="${i+1}" />
									<input type="hidden" value="${list.order_id}" name="order_id" />
									<input type="hidden" value="${list.store_id}" name="store_id" />
									<input type="hidden" value="${list.due_id}" name="due_id" />
									<input type="hidden" value="${list.product_code}" name="product_code" />
									<%-- <input type="text" value="${list.closing_date}" name="order_id" /> --%>
									<tr>
										<%-- <th>${list.day}</th> --%>
										<th>${list.product_name}</th>
										<th>${list.product_size}</th>
										<th>${list.product_color}</th>
										<th><input type="text" class="form-control"
											value="${list.order_unit}" disabled /></th>
										<td>
											<input type="text" class="form-control input_number" id="${list.product_code}" name="product_qty" value="${list.product_qty}" />
										</td>
										<!-- onload="textboxChange()" -->
										<th><input type="text" class="form-control show_number"
											value="" id="${i}" disabled /></th>
									</tr>
								</c:forEach>
						</tbody>
						<!-- <tbody>
						<tr class="">
							<th class="align-middle w-10 bg-light text-center">주문단위</th>
							<td class="w-70 pl-4"><input type="text"
								class="form-control num_only" id="A" name="fn_type1_price"
								value="1"></td>
							<th class="align-middle w-10 bg-light text-center">주문수량</th>
							<td class="w-70 pl-4"><input type="text"
								class="form-control num_only" id="B" value="2"></td>
							<th class="align-middle bg-light text-center p-2">합계</th>
							<td class="pl-4" colspan="2"><input type="text"
								class="form-control" id="total" value="">
						</tr>
					</tbody> -->
					</table>
				</div>
			</form>
		</div>
	</main>
	<script>
	
	// 두번째 value 값을 가져올 때부터  attr 를 사용한다. 선택한 요소의 속성 값을 가져온다. or 속성의 값을 변경할 수 도 있음  .att("값", true/false)
	
	var length = ${fn:length(qty)};
	
	// 페이지가 열릴때 
	window.onload = function() {
		// alert("열림");
		// var a = $('#period option:selected').val();
		// alert("셀렉트 값을 가져오는지:" + a);
		var b = $('#period option:selected').attr("closeYN");
		// alert("y/n을 가져오나?" + b);
		if ( b == 'N') {
			for (var i=0; i < length; i++) {
				// 제이쿼리 eq('숫자'): 선택한 $()의 인덱스 번호에 해당하는 요소를 찾는 것
				$('input[name=product_qty]').eq(i).attr("disabled", true);
				// alert("val 값을 가져오나?" + $('input[name=product_qty]').eq('0').val());
				$('#warning').text("※ 마감되었습니다.");
			}
			
			$('#people').attr("disabled",true);
			$('#saveData').css("display" ,"none");
			
		}
	};
	
	/* $('#period').on('change', function textboxChange() {
		console.log("들어왓는가?");
		var a = $('#period option:selected').val();
		console.log("선택된 value 값: " + a);
	}); */
	
		/* period 라는 아이디 를 가지는 option이 셀렉트 될때 값을 가져오시오.
		var a = $('#period option:selected').val();
		console.log("선택된 value 값: " + a);
	
		
		var b = $('#period option:selected').attr("closeYN");
		console.log("여닫이 속성을 가져오는 지: " + b);
		
		
		if ( b == 'Y') {
			$("#txtBox").attr("disabled",true);
		}
		
	} */
	
	
	
	<c:if test="${result == 1}">
	location.href = '/Socks/dutyfree/list.do';
	alert('성공');
	</c:if>

	<c:if test="${result == 0}">
	alert('failed');
	history.back();
	</c:if>
	$(function() {
		$("#period").val("${param.period}").attr("selected",
				"selected");
	});
	/* var list = $("#unit").val();
	console.log('단위 출력:' + list);

	var B = $("#qty").val();
	console.log('수량 출력:' + B);

	// 계산
	$(function(){
	
		$('.num_only').on('keyup',function(){
		 
			var cnt = $(".num_only").length;
		  
			for( var i=1; i< cnt; i++){
		
				var sum = parseInt($(this).val() || 0 );
			 
				sum++
	  
			}
			var sum1 = parseInt($("#unit").val() || 0 );

			var sum2 = parseInt($("#qty").val() || 0);
		
			var sum = sum1 * sum2;
	
			$("#total").val(sum);
	
		});

	});
	//페이지 로드 될때 처음 부터 돌기

	$(document).ready(function() {
	
		var sum1 = parseInt($("#A").val() || 0 );

		var sum2 = parseInt($("#B").val() || 0);
	
		var sum = sum1 * sum2;

		$("#total").val(sum);

	}); */
		// 주문 수량 아이디 배열에 담기
		var id = new Array();
		<c:forEach var = "list" items="${qty}">
		id.push("${list.product_code}");
		</c:forEach>
		// console.log('확인:' + id);
		

		// 주문단위 배열에 담기
		var list = new Array();
		<c:forEach var = "list" items="${qty}">
		list.push("${list.order_unit}");
		</c:forEach>

		// 주문수량 배열에 담기
		var qty = new Array();
		<c:forEach var = "list" items="${qty}">
		qty.push("${list.product_qty}");
		</c:forEach>

		
		var d = new Array();
		<c:forEach var = "list" items="${qty}">
		<c:set var="j" value="${j+1}" />
		d.push("${j}");
		</c:forEach>

		
		// 열의 길이
		var length = ${fn:length(qty)};

		$(document).ready(function() {
			
			// console.log(list);
			var cnt = length;     
			// console.log('배열의 길이: ' + cnt);
			
			// 주문 단위
		  	var oUnit;
		  	// 주문 수량
		  	var oQty;
		  	// 주문 수량에 대한 아이디
			var oId;
		  	// 합계
			var sum;
		  	
		  	var asum;
		  	
		  	var dD;

			for(var i=0; i< cnt; i++){
		 		// console.log('들어와 지나?');
	 
				oUnit = list[i];
				
				oQty = qty[i];
				
				// console.log('주문단위를 가져오나?' + (oUnit+oQty));
				// console.log('주문수량을 가져오나?' + oQty);
				
				sum = (1 / oUnit) * oQty;
				
				// var a = parseInt(sum);
				
				// console.log('각 행의 합계 타입 확인:' + sum);
				
			}
			
			var p = parseInt($('#people').val());

			// console.log('인원:' + (p+1));
			// console.log(typeof(p));
			
			for(var j=0; j<cnt; j++) {
				// console.log('각 행의 합계 타입 확인:' + sum);
				
				oUnit = list[j];
				
				dD = d[j];
				// console.log('확인' + typeof(d[0]));
				
				asum = (p - sum) * oUnit;
				// console.log('주문가능수량 타입' + (oUnit + 1));
				console.log('주문가능수량' + typeof(asum));
				// console.log('아이디:' + oId);
				
				if (isNaN(asum)) {
					// console.log("안으로 들어오는 지");
					asum = 0;
				}
				
				
				// 변수로 받은 것이 아니라 텍스트 자체로 받은 것이기때문에 따로 큰따옴표로 분리를 시켜줄 필요가 없다.
				$('#' + dD).val(asum);
				// $("'#" + Number(dD) + "'").val(asum);
				// $('#1').val(asum);
				
			}
			
			

		});
		 
		
		// 주문수량이 바뀌었을 때

		$('.input_number').on('keyup', function() {

			// var는 기본이 String > int끼리 계산해서 집어넣을 때 int 타입으로 초기화를 해서 사용할 수 있음 
			// 주문단위
		  	var mUnit;
		  	var iId;
		  	// 중요 > 초기화를 할때 
		  	var nSum = 0;
		  	var dd;
		  	var mSum = 0;
		  	var nUnit;
		  	var pp = parseInt($('#people').val());
		  	
		  	var lSum = 0;
		  	
		  	for(var i=0; i<length; i++){
		  		
		  		// 주문수량 아이디 가져오기
		  		iId = id[i];
		  		// console.log(i + "주문수량" + iId);
		  		
		  		// 입력한 값과 
		  		var inputId = parseInt($('#' + iId).val());
		  		// console.log('안에 들어있는 값:' + inputId);
		  		
		  		mUnit = parseInt(list[i]);
		  		// console.log('주문단위:' + typeof(mUnit));
		  		// console.log('들어있는 값:' + inputId + '/' + '주문단위:' + mUnit);
		  		
		  		nSum += (1/mUnit) * inputId;
		  		// console.log('합계:' + nSum);
		  		
		  	}
		  	
		  	
		  	for(var j=0; j<length; j++) {
		  		
		  		mUnit = parseInt(list[j]);
		  		
				mSum = (pp-nSum) * mUnit;
				
				lSum += mSum;
				// console.log('주문가능수량들의 합계: ' + lSum);

				if (isNaN(mSum)) {
					// console.log("안으로 들어오는 지");
					mSum = 0;
				}
				
				// console.log(dd);
				// console.log('인원:' + pp + ', 곱한값:' + nSum +',주문단위:' + mUnit + ',(인원 - 합계)*두달수량: ' + mSum);
				dd = d[j];
				$('#' + dd).val(mSum);
			}
		  	cSum = $('#wrong').val(lSum);
		  	//cSum = lSum;
		  	/* if ( lSum < 0) {
		  		alert('주문가능한 수량을 초과하였습니다.');
		  	} */
		});
		
		
		// 인원 수가 바뀌었을 때
		$('#people').on('keyup', function() {
			// console.log('들어오는지');
			// 사람수 입력한 것
			var pep = $('#people').val();
			// console.log(pep);
			
			// 주문수량 아이디
			var aId;
			
			// 주문단위
			var alist;
			
			// (1/주문단위) * 주문수량
			var aSum = 0;
			
			// (인원 - aSum) * 주문단위
			var bSum = 0;
			
			// 주문가능수량 아이디
			var ad;
			
			for(var i=0; i<length; i++){
				
				// 주문수량 아이디 가져오기
		  		aId = id[i];
				// console.log(aId);
				// 입력한 값
				var inNum = parseInt($('#' + aId).val());
				// console.log('입력한 값:' + inNum);
				
				alist = parseInt(list[i]);
				// console.log('주문단위: ' + alist);
				// console.log('주문단위타입: ' + typeof(alist));
				
				aSum += (1/alist) * inNum;
				// console.log('(1/주문단위) * 주문수량' + aSum);
				// console.log('(1/주문단위) * 주문수량의 타입' + typeof(aSum));
			
			}
			
			for(var j=0; j<length; j++){
				
				// console.log('인원: ' + pep);
				
				alist = parseInt(list[j]);
				// console.log('주문단위배열: ' + alist);
				
				bSum = (pep - aSum) * alist;
				console.log(bSum);
				
				ad = d[j];
				$('#' + ad).val(bSum);
			}
			
		});
		function search() {

			var formCheck = document.formSearch;
			console.log(formCheck);
				alert('get 을 던진다.');
				formCheck.mehtod = "GET";
				formCheck.action = "/Socks/dutyfree/list.do";
				formCheck.submit();
		}
		
		function saveCheck() {
			//console.log('마이너스인가?' + cSum);
			//alert('위치는?' + lSum);
			var formCheck = document.formCheck;
			
			
			/* if ($('#wrong').val() < 0) {
				alert('수량 초과');
			} else { */
				alert('수량성공');
				formCheck.mehtod = "post";
				formCheck.action = "/Socks/dutyfree/list.do";
				formCheck.submit();
			// }
		}
		
		var formCheck = document.formSearch;
		console.log(formCheck);
	</script>
</body>
</html>