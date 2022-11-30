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
					<h1 class="h2">양말 품목 관리</h1>
				</div>
				<form method="GET" action="/Socks/product/list.do">
					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2">

						<div
							class="d-flex col-7 justify-content-between flex-wrap flex-md-nowrap align-items-center">

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
				<div>
					<a class="btn btn-warning" href="/Socks/product/add.do">항목 추가</a>
				</div>
			</div>


			<div class="table-scroll"
				style="height: 76%; overflow-x: hidden; overflow-y: auto;">

				<table class="table table-bordered" id="update">
					<thead>
						<tr>
							<th scope="col" style="width: 22%;">상품명</th>

							<th scope="col" style="width: 16%;">상품코드</th>
							<th scope="col" style="width: 16%;">사이즈</th>
							<th scope="col" style="width: 16%;">컬러</th>
							<th scope="col" style="width: 16%;">단가</th>
							<th scope="col" style="width: 10%;">삭제 / 수정</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td>${list.product_name}</td>
								<td>${list.product_code}</td>
								<td>${list.product_size}</td>
								<td>${list.product_color}</td>
								<td>${list.unit_price}</td>
								<th>
									<!-- onclick 이벤트를 할때 ? 앞에 명시하는 것이 서버(서블릿)에서 가져올 수 있는 것 > 이름이 같아야 한다. -->
									<!-- html에서 name은 태그명, 폼: submit 시  서버(서블릿)에서 name명으로 값을 가져올 수 있다.
										 value는 해당 태그의 값 > 서버(서블릿)에서 name명으로 꺼낸 값에는 value에 해당하는 값이 들어있음
									 --> <%-- <button type="submit"
											onclick="location.href='/Socks/product/del.do?product_code=${list.product_code}'">삭제</button> --%>
									<!-- Button trigger modal --> <%-- <input type="text" value="${list.product_code}" name="product_code"> --%>

									<input type="button" value="삭제" data-bs-toggle="modal"
									data-bs-target="#del" data-bs-code="${list.product_code}">
									/ <input type="button" value="수정"
									onclick="location.href='/Socks/product/edit.do?product_code=${list.product_code}&product_name=${list.product_name}&unit_price=${list.unit_price}&product_size=${list.product_size}&product_color=${list.product_color}'">
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
		</section>
	</main>

	<script>
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

		var CODE = "";

		$(document).ready(function() {

			$('#del').on('show.bs.modal', function(event) {

				CODE = $(event.relatedTarget).data('bs-code');
				console.log('이벤트 발생시 모달에 코드가 들어가나요? :' + CODE);
			});
		});

		function del() {

			var save = $('#save').val();
			console.log('code가 저장안에 들어갔나요? ' + CODE);
			location.href = '/Socks/product/delok.do?product_code=' + CODE
		}

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
		var table = document.getElementById('update');
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
		}
	</script>
</body>
</html>