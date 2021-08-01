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
<c:set var="menu" value="product"/>
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<main>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-dark text-white fw-bold">상품 상세정보</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col-8">
				<div class="border p-2 bg-light">
					<table class="table">
						<colgroup>
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="35%">
						</colgroup>
						<tbody>
							<tr>
								<th>번호</th>
								<td>${product.no }</td>
								<th>등록일자</th>
								<td><fmt:formatDate value="${product.createdDate }" pattern="yyyy년 M월 d일"/></td>
							</tr>
							<tr>
								<th>카테고리</th>
								<td>${product.category }</td>
								<th>리뷰갯수</th>
								<td><fmt:formatNumber value="${product.reviewCnt }"/> 개</td>
							</tr>
							<tr>
								<th>상품명</th>
								<td colspan="3">${product.name }</td>
							</tr>
							<tr>
								<th>제조사</th>
								<td colspan="3">${product.maker }</td>
							</tr>
							<tr>
								<th>가격</th>
								<td><fmt:formatNumber value="${product.price }" /> 원</td>
								<th>할인가격</th>
								<td><strong class="text-danger"><fmt:formatNumber value="${product.discountPrice }" /></strong> 원</td>
							</tr>
							<tr>
								<th>재고량</th>
								<td><fmt:formatNumber value="${product.stock }"></fmt:formatNumber> 개</td>
								<th>판매여부</th>
								<td>
									<c:choose>
										<c:when test="${'Y' eq product.soldOut }">
											<strong class="text-danger">판매 일시 중지</strong>
										</c:when>
										<c:otherwise>
											<strong class="text-success">판매중</strong>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">${product.name }</h5>
						<div class="border-top py-2 mb-3">
							<table class="table">
								<tbody>
									<tr>
										<th>할인 가격</th>
										<td><strong class="text-danger"><fmt:formatNumber value="${product.discountPrice }" /></strong> 원</td>
									</tr>
									<tr>
										<th>적립포인트</th>
										<td><fmt:formatNumber value="${product.discountPrice * 0.01 }" pattern="##,###"/>  점</td>
									</tr>
									<tr>
										<th>배송비</th>
										<td>무료</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="text-end">
							<a href="../cart/add?no=${product.no }" class="btn btn-outline-primary ${empty LOGINED_USER ? 'disabled' : '' }">장바구니 담기</a>
							<a href="../order/add??no=${product.no }" class="btn btn-success ${empty LOGINED_USER ? 'disabled' : '' }">바로구매</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-secondary text-white fw-bold d-flex justify-content-between">
					<span>리뷰 리스트</span>
					<c:if test="${not empty LOGINED_USER }">
						<button class="btn btn-light btn-sm" id="btn-show-review-modal">리뷰쓰기</button>	
					</c:if>
				</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<ul class="list-group">
					<c:choose>
						<c:when test="${empty product.reviews }">
							<li class="list-group-item text-center">등록된 리뷰가 없습니다.</li>
						</c:when>
						<c:otherwise>
							<c:forEach var="review" items="${product.reviews }">
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
	</main>
	<div class="modal fade" id="form-review-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<form id="form-review" method="post" action="addReview">
				<input type="hidden" name="productNo" value="${product.no }">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">새 리뷰쓰기</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="text-danger" id="form-alert">제목과 내용은 필수입력값입니다.</div>
						<div class="row mb-1 p-2">
							<input type="text" class="form-control" id="review-title" name="title" placeholder="제목을 입력하세요">
						</div>
						<div class="row mb-1 p-2">
							<textarea rows="5" class="form-control" id="review-content" name="content" placeholder="내용을 입력하세요"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
						<button type="submit" class="btn btn-primary" id="btn-add-review">등록</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function() {
	var formReviewModal = new bootstrap.Modal(document.getElementById('form-review-modal'), {
		keyboard: false
	});
	
	$("#form-alert").hide();
	
	$("#form-review-modal").on('hidden.bs.modal', function() {
		$("#form-alert").hide();
		$("#review-title").val("");
		$("#review-content").val("");
	})
	$("#btn-show-review-modal").click(function() {
		formReviewModal.show();
	});
	
	$("#form-review").submit(function() {
		if (!$.trim($("#review-title").val())) {
			$("#form-alert").show();
			return false;
		}
		if (!$.trim($("#review-content").val())) {
			$("#form-alert").show();
			return false;
		}
		
		return true;
	});
})
</script>
</body>
</html>