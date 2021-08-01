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
									<c:when test="${empty items }">
										<tr class="align-middle">
											<td colspan="7" class="text-center">장바구니에 저장된 상품이 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="item" items="${items }">
											<tr class="align-middle">
												<td><input type="checkbox" name="cartNo" value="100"></td>
												<td><button class="btn btn-link btn-sm" data-bs-toggle="modal" data-bs-target="#model-product-detail">${item.NAME }</button></td>
												<td class="text-end"><fmt:formatNumber value="${item.PRICE }"/> 원</td>
												<td class="text-end"><span class="text-danger"><fmt:formatNumber value="${item.DISCOUNTPRICE }"/></span> 원</td>
												<td class="d-flex justify-content-center">
													<div class="input-group w-75">
				  										<button class="btn btn-outline-secondary btn-sm" type="button" id="button-addon1"><i class='fas fa-minus'></i></button>
				  										<input type="text" class="form-control form-control-sm" value="${item.AMOUNT }" >
				  										<button class="btn btn-outline-secondary btn-sm" type="button" id="button-addon1"><i class='fas fa-plus'></i></button>
													</div>
												</td>
												<td class="text-end"><span class="fw-bold"><fmt:formatNumber value="${item.ORDERPRICE }"/></span> 원</td>
												<td class="text-center">
													<a href="" class="btn btn-secondary btn-sm">삭제</a>
													<a href="" class="btn btn-primary btn-sm">주문</a>
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
						<div class="col text-end">
							총 구매금액 : <strong class="text-danger">6,700,000</strong> 원
						</div>
					</div>
				</div>			
			</div>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="model-product-detail" tabindex="-1" aria-hidden="true">
	  		<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">상품 상세 정보</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
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
	$(".btn-link").click(function(event) {
		event.preventDefault();
	});
})
</script>
</body>
</html>