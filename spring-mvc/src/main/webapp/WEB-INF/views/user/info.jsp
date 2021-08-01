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
				<div class="border p-2 bg-dark text-white fw-bold">사용자 상세정보</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="p-2 mb-1 bg-light rounded-3 text-dark">
					<p class="col-8"><span class="fs-1">${LOGINED_USER.name }</span>님의 현재 적립포인트는 
						<strong class="text-danger fs-2"><fmt:formatNumber value="${user.point }"/> </strong>점 입니다.</p>
				</div>
			</div>
		</div>
		<div class="row mb-	">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold">장바구니</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
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
							<th>순번</th>
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
							<c:when test="${empty cartItems }">
								<tr class="align-middle">
									<td colspan="7" class="text-center">장바구니에 저장된 상품이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="item" items="${cartItems }" varStatus="loop">
									<tr class="align-middle">
										<td>${loop.count }</td>
										<td><button class="btn btn-link btn-sm" data-bs-toggle="modal" data-bs-target="#model-product-detail">${item.name }</button></td>
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
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold">최근 주문내역</div>
			</div>
		</div>
		<div class="row mb-1">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold">내가 작성한 리뷰</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<ul class="list-group">
					<c:choose>
						<c:when test="${empty reviews }">
							<li class="list-group-item text-center">등록된 리뷰가 없습니다.</li>
						</c:when>
						<c:otherwise>
							<c:forEach var="review" items="${reviews }">
								<li class="list-group-item">
									<div class="row mb-1">
										<div class="col-10"><span class="fw-bold">${review.title }</span></div>
										<div class="col-2 text-end"><small><fmt:formatDate value="${review.createdDate }"/> </small></div>
									</div>
									<div class="row">
										<div class="col-10">${review.content }</div>
										<c:if test="${LOGINED_USER.id eq review.userId }">
											<div class="col-2 text-end"><a href="deleteReview?no=${review.no }&productNo=${product.no }" class="text-danger"><small><i class="fas fa-trash"></i></small></a></div>
										</c:if>
									</div>
								</li>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold">포인트 변경 내역</div>
			</div>
		</div>
	</main>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

</script>
</body>
</html>