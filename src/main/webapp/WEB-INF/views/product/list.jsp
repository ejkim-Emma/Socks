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
		<section>
			<div class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div
					class="d-flex flex-wrap flex-md-nowrap align-items-center pt-3">
					<h1 class="h2">양말 품목 관리</h1>

					<form method="GET" action="/Socks/product/list.do" class="col-7">
						<div
							class="d-flex flex-wrap flex-md-nowrap align-items-center">
							<div
								class="d-flex flex-wrap flex-md-nowrap align-items-center">

								<!-- 상품명 검색창 -->
								<select name="product_name" id="product_name"
									class="form-select me-2">
									<option value="" disabled selected>상품명</option>
									<c:forEach items="${nlist}" var="nlist">
										<option value="${nlist.product_name}">${nlist.product_name}</option>
									</c:forEach>
								</select>

								<!-- 사이즈 검색창 -->
								<select name="product_size" id="product_size"
									class="form-select me-2">
									<option value="" disabled selected>사이즈</option>
									<c:forEach items="${slist}" var="slist">
										<option value="${slist.product_size}">${slist.product_size}</option>
									</c:forEach>
								</select>

								<!-- 색상 검색창 -->
								<select name="product_color" id="product_color"
									class="form-select me-2">
									<option value="" disabled selected>색상</option>
									<c:forEach items="${clist}" var="clist">
										<option value="${clist.product_color}">${clist.product_color}</option>
									</c:forEach>
								</select>

								<!-- 검색버튼 -->
								<button class="btn btn-primary">search</button>
							</div>
					</form>
				</div>
			</div>
			<a class="btn btn-warning float-end mb-1" href="/Socks/product/add.do">항목 추가</a>
			<form method="POST" action="/Socks/product/list.do">
				<button class="btn btn-outline-success mb-1" onclick="test()" id="del">저장</button>

				<div class="table-wrapper"
					style="overflow-y: auto; border-collapse: collapse; height: 500px; padding-top: 0px;">

					<table class="table table-bordered" id="update">
						<thead>
							<tr>
								<th style="width: 5%;">No</th>
								<th scope="col" style="width: 5%;">삭제</th>
								<th scope="col" style="width: 15%;">상품코드</th>
								<th scope="col" style="width: 25%;">상품명</th>
								<th scope="col" style="width: 10%;">사이즈</th>
								<th scope="col" style="width: 10%;">컬러</th>
								<th scope="col" style="width: 10%;">단가</th>
								<th scope="col" style="width: 10%;">항목 당 수량</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="list">
								<c:set var="i" value="${i+1}" />
								<input name="pcode" type="hidden"
									class="form-control form-control-sm"
									value="${list.product_code}" />
								<tr>
									<td>${i}</td>
									<td>
										<!-- onclick 이벤트를 할때 ? 앞에 명시하는 것이 서버(서블릿)에서 가져올 수 있는 것 > 이름이 같아야 한다. -->
										<!-- html에서 name은 태그명, 폼: submit 시  서버(서블릿)에서 name명으로 값을 가져올 수 있다.
                               value는 해당 태그의 값 > 서버(서블릿)에서 name명으로 꺼낸 값에는 value에 해당하는 값이 들어있음
                            --> <%-- <button type="submit"
                                 onclick="location.href='/Socks/product/del.do?product_code=${list.product_code}'">삭제</button> --%>
										<!-- Button trigger modal --> <%-- <input type="text" value="${list.product_code}" name="product_code"> --%>

										<input id="checking" name="product_code" type="checkbox"
										value="${list.product_code}" <%-- data-bs-toggle="modal"
                           data-bs-target="#del" data-bs-code="${list.product_code}" --%> />
										<%-- / <input type="submit" value="수정"
                           onclick="location.href='/Socks/product/edit.do?product_code=${list.product_code}&product_name=${list.product_name}&unit_price=${list.unit_price}&product_size=${list.product_size}&product_color=${list.product_color}'"> --%>
									</td>
									<td><input type="text"
										class="form-control form-control-sm"
										value="${list.product_code}" disabled /></td>
									<td><input name="pname" type="text"
										class="form-control form-control-sm"
										value="${list.product_name}" /></td>
									<td><input name="psize" type="text"
										class="form-control form-control-sm"
										value="${list.product_size}" /></td>
									<td><input name="pcolor" type="text"
										class="form-control form-control-sm"
										value="${list.product_color}" /></td>
									<td><input name="pprice" type="text"
										class="form-control form-control-sm"
										value="${list.unit_price}<%-- <fmt:formatNumber value="${list.unit_price}" pattern="#,###" /> --%>" /></td>
									<td><input name="punit" type="text"
										class="form-control form-control-sm"
										value="${list.order_unit}" /></td>
									
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
			</form>
		</section>
	</main>

	<script>
		<c:if test="${result == 1}">
			location.href = '/Socks/product/list.do';
			alert('성공');
		</c:if>

		<c:if test="${result == 0}">
			alert('failed');
			history.back();
		</c:if>

		// 첫번째 값 보여주기
		var b = $('input:checkbox[id="checking"]').val();
		//console.log('값은: ' + b);
      
		// 리스트의 길이 구하기
		var length = ${fn:length(list)};
		// console.log('길이: ' + length);
      
		// 상품코드를 배열로 만들기
		/* var list = new Array();
		<c:forEach var = "list" items="${list}">
			list.push("${list.product_code}");
		</c:forEach>
		console.log(list);
 */
		
		// 체크된 값 배열로 만들기
		function test() {
			var d = [];
			$('input:checkbox[id="checking"]:checked').each(function(index) {
				d[index] = $(this).val();
			});
			console.log('배열의 값은?' +  d);
			var e;
			e = $('#del').val(d);
			console.log('저장버튼에 담겼는지 확인하기' + $('#del').val());
		};

		// 코드 배열 만들기
		var pcode = new Array();
		<c:forEach var = "list" items="${list}">
			pcode.push("${list.product_code}");
		</c:forEach>
		// console.log(pcode);

		// 상품명 배열 만들기
		var pname = new Array();
		<c:forEach var = "list" items="${list}">
			pname.push("${list.product_name}");
		</c:forEach>
		// console.log(pname);
		
		// 사이즈 배열 만들기
		var psize = new Array();
		<c:forEach var = "list" items="${list}">
			psize.push("${list.product_size}");
		</c:forEach>
		// console.log(psize);
		
		// 컬러 배열 만들기
		var pcolor = new Array();
		<c:forEach var = "list" items="${list}">
			pcolor.push("${list.product_color}");
		</c:forEach>
		// console.log(pcolor);
		
		// 단가 배열 만들기
		var pprice = new Array();
		<c:forEach var = "list" items="${list}">
			pprice.push("${list.unit_price}");
		</c:forEach>
		// console.log(pprice);
		
		// 항목당 수량 배열 만들기
		var punit = new Array();
		<c:forEach var = "list" items="${list}">
			punit.push("${list.order_unit}");
		</c:forEach>
		// console.log(punit);
		
		// for문 돌아서 코드의 값 전부 가져오기(연습)
		for (var i = 0; i < length; i++) {
			var array;
			array = pcode[i];
			// console.log('배열 값 가져오기' + array);
		};
		
		// 변수 선언하기
		var proCode;
		var proName;
		var proSize;
		var proColor;
		var proPrice;
		var proUnit;
		
		// HTML 로딩이 다끝나면
		$(document).ready(function() {
			// console.log('들어와지나?');
			
			for (var i = 0; i < length; i++) {
				// console.log('반복문안에 들어오나?');
				
				proCode= pcode[i];
				
				proName = pname[i];
				
				proSize = psize[i];
				
				proColor = pcolor[i];
				
				proPrice = pprice[i];
				
				proUnit = punit[i];
				
				// console.log('코드: ' + proCode + '상품명: ' + proName + '사이즈' + proSize + '컬러: ' + proColor + '단가' + proPrice + '항목 당 수량: ' + proUnit);
			};
		});
			
			
			
			
	
	
	
	
	
	
	
      // 검색 조건 유지
      $(function() {
         $("#product_name").val("${param.product_name}").attr("selected",
               "selected");
      });

      $(function() {
         $("#product_color").val("${param.product_color}").attr("selected",
               "selected");
      });

      $(function() {
         $("#product_size").val("${param.product_size}").attr("selected",
               "selected");
      });


      
   // 체크된 값 가져오기
		/* function test() {
    
		var d = 0;
		$('input:checkbox[id="checking"]:checked').each(function(index) {
    
			d += $(this).val();
		});
			console.log(d);
		}; */
      
      /* var id = '';
      
      function getProductCode(code) {
         
         var pro_code = $(code).val();
         id = pro_code;
         
         id = $(code).val();
         
         console.log('클릭시 ' + id);
         var text = $("#prodcutId").val();
         console.log('input ' + text);
      } */

      /* function getId() {
         console.log('모달 안으로 들어옴');
         var cat = $("#productId").val();
         console.log('고양이 ' + cat);
         
      } */

      $('#exampleModal').on('shown.bs.modal', function(event) {
         console.log('가져오니 ' + id);
      })

      /* $(document).on("click", "#exampleModal", function () {
         console.log('클릭');
      
      //var myBookId = $(this).data('id');
          //$(".modal-body #bookId").val( myBookId );
          // As pointed out in comments, 
          // it is unnecessary to have to manually call the modal.
          // $('#addBookDialog').modal('show');
      }); */

      // 'table' 변수 선언 > Id로 가져옴
      /* var table = document.getElementById('update');
      // 'table' 에서 'tagname'으로 가져오는 'td'를 'cells'로 선언
      var cells = table.getElementsByTagName('td');

      for (var i = 0; i < cells.length; i++) {
         cells[i].onclick = function() {

            // 다시 원래 텍스트로 되돌려 주는 방식
            if (this.hasAttribute('clicked')) {
               return;
            }

            this.setAttribute('clicked', 'yes');
            this.setAttribute('data-text', this.textContent);

            var input = document.createElement('input');
            input.setAttribute('type', 'text');
            input.value = this.textContent;
            input.style.width = this.offsetWidth - (this.clientLeft * 2)
                  + "px";
            input.style.height = this.offsetHeight - (this.clientTop * 2)
                  + "px";
            input.style.border = "0px;"
            input.style.fontFamily = "inherit";
            input.style.fontSize = "inherit";
            input.style.textAlign = "inherit";
            input.style.backgroundColor = "#FFFFB3";

            input.onblur = function() {

               var td = input.parentElement;
               var orig_text = input.parentElement
                     .getAttribute('data-text');
               var current_text = this.value;

               if (orig_text != current_text) {
                  // there are changes in the cell's text
                  // save to db with Ajax
                  td.removeAttribute('clicked');
                  td.removeAttribute('data-text');
                  td.textContent = current_text;
                  td.style.cssText = "padding: 8px 8px";
               } else {
                  td.removeAttribute('clicked');
                  td.removeAttribute('data-text');
                  td.textContent = orig_text;
                  td.style.cssText = "padding: 8px 8px";
               }
            }

            input.onkeypress = function() {
               if (event.keyCode == 13) {
                  this.blur();
               }
            }

            this.textContent = '';
            this.style.cssText = 'padding: 0px 0px';
            this.append(input);
            this.firstElementChild.select();
         }
      } */
   </script>
</body>
</html>