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