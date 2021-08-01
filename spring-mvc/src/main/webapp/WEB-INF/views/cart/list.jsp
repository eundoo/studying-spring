<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" >
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<c:set var="menu" value="my" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<main>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-dark text-white fw-bold">장바구니</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-light">
					<form method="get" action="">
						<table class="table" id="table-cart">
							<colgroup>
								<col width="5%">
								<col width="*">
								<col width="11%">
								<col width="11%">
								<col width="10%">
								<col width="11%">
								<col width="12%">
							</colgroup>
							<thead>
								<tr>
									<th><input type="checkbox" id="checkbox-toggle"/></th>
									<th>상품명</th>
									<th class="text-end">가격</th>
									<th class="text-end">할인가격</th>
									<th class="text-center">수량</th>
									<th class="text-end">구매가격</th>
									<th class="text-center"></th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty cartItemList.items }">
										<tr class="align-middle">
											<td colspan="7" class="text-center">장바구니에 저장된 상품이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="item" items="${cartItemList.items }">
											<tr class="align-middle">
												<td><input type="checkbox" name="itemNo" value="${item.no }"></td>
												<td><button class="btn btn-link btn-sm" data-product-no="${item.productNo }">${item.name }</button></td>
												<td class="text-end"><fmt:formatNumber value="${item.price }"/> 원</td>
												<td class="text-end"><span class="text-danger"><fmt:formatNumber value="${item.discountPrice }"/></span> 원</td>
												<td class="d-flex justify-content-center">
													<div class="input-group w-75">
				  										<button class="btn btn-outline-secondary btn-sm" type="button" id="button-addon1"><i class='fas fa-minus'></i></button>
				  										<input type="text" class="form-control form-control-sm" value="${item.amount }" >
				  										<button class="btn btn-outline-secondary btn-sm" type="button" id="button-addon1"><i class='fas fa-plus'></i></button>
													</div>
												</td>
												<td class="text-end"><span class="fw-bold"><fmt:formatNumber value="${item.orderPrice }"/></span> 원</td>
												<td class="text-center">
													<a href="delete?no=${item.no }" class="btn btn-secondary btn-sm">삭제</a>
													<a href="../order/add?no=${item.no }" class="btn btn-primary btn-sm">주문</a>
												</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2">
					<div class="row">
						<div class="col">
							<button class="btn btn-outline-dark btn-sm">전체삭제</button>
							<button class="btn btn-outline-dark btn-sm">선택삭제</button>
							<button class="btn btn-outline-primary btn-sm">주문하기</button>
						</div>
						<div class="col text-end d-flex justify-content-between">
							<span>
							총 상품금액 : <strong class="text-danger"><fmt:formatNumber value="${cartItemList.totalPrice }"/> </strong> 원
							</span>
							<span>
							총 상품갯수 : <strong class="text-danger"><fmt:formatNumber value="${cartItemList.totalAmount }"/> </strong> 개
							</span>
							<span>
							총 구매금액 : <strong class="text-danger"><fmt:formatNumber value="${cartItemList.totalOrderPrice }"/> </strong> 원
							</span>
						</div>
					</div>
				</div>			
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="modal-product-detail" tabindex="-1" aria-hidden="true">
	  		<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">상품 상세 정보</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<table class="table table-bordered">
							<colgroup>
								<col width="15%">
								<col width="35%">
								<col width="15%">
								<col width="35%">
							</colgroup>
							<tbody>
								<tr>
									<th>번호</th>
									<td><span id="modal-product-no"></span></td>
									<th>등록날짜</th>
									<td><span id="modal-product-created-date"></span></td>
								</tr>
								<tr>
									<th>카테고리</th>
									<td><span id="modal-product-category"></span></td>
									<th>리뷰갯수</th>
									<td><span id="modal-product-review-cnt"></span></td>
								</tr>
								<tr>
									<th>상품명</th>
									<td colspan="3"><span id="modal-product-name"></span></td>
								</tr>
								<tr>
									<th>제조사</th>
									<td colspan="3"><span id="modal-product-maker"></span></td>
								</tr>
								<tr>
									<th>가격</th>
									<td><span id="modal-product-price"></span> 원</td>
									<th>할인 가격</th>
									<td><strong class="text-danger" id="modal-product-discount-price"></strong> 원</td>
								</tr>
								<tr>
									<th>재고량</th>
									<td><span id="modal-product-stock"></span></td>
									<th>판매여부</th>
									<td><strong class="text-success" id="modal-product-sold-out"></strong></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</main>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function() {
	// <div id="modal-product-detail" />로 모달을 생성한다.
	var productDetailModal = new bootstrap.Modal(document.getElementById('modal-product-detail'), {
		keyboard: false
	});
	
	$("#table-cart .btn-link").click(function(event) {
		event.preventDefault();
		
		var productNo = $(this).data('product-no')
		$('#modal-product-no').text(productNo)
		
		$.ajax({
			type:'GET',						//요청방식
			url:"../product/detail/json",	//요청URL
			data:{no:productNo},			//요청파라미터 값: ?no=42
			dataType:"json"					//응답데이터 타입
		})
		.done(function(product) {			//성공적인 응답이 왔을 때 실행할 함수를 done()메소드를 지정한다.
			$('#modal-product-created-date').text(product.createdDate)
			$('#modal-product-category').text(product.category)
			$('#modal-product-review-cnt').text(product.reviewCnt)
			$('#modal-product-name').text(product.name)
			$('#modal-product-maker').text(product.maker)
			$('#modal-product-price').text(new Number(product.price).toLocaleString())
			$('#modal-product-discount-price').text(new Number(product.discountPrice).toLocaleString())
			$('#modal-product-stock').text(product.stock)
			$('#modal-product-sold-out').text(product.soldOut)
	
			productDetailModal.show();
		})
	})
})
</script>
</body>
</html>