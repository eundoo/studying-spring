<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<title>샘플 애플리케이션</title>
</head>
<body>
<c:set var="menu" value="product" />
<%@ include file="../common/nav.jsp" %>
<div class="container my-3">
	<main>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-dark text-white fw-bold">전체 상품 리스트</div>
			</div>
		</div>
		<div class="row mb-3">
			<div class="col">
				<div class="border p-2 bg-light">
					<table class="table">
						<colgroup>
							<col width="7%">
							<col width="15%">
							<col width="*">
							<col width="15%">
							<col width="15%">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>카테고리</th>
								<th>상품명</th>
								<th class="text-end">가격</th>
								<th class="text-end">할인가격</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="product" items="${products }">
								<tr>
									<td>${product.no }</td>
									<td>${product.category }</td>
									<td><a class="text-decoration-none" href="detail?no=${product.no }">${product.name }</a></td>
									<td class="text-end"><fmt:formatNumber value="${product.price }" /> 원</td>
									<td class="text-end"><span class="text-danger"><fmt:formatNumber value="${product.discountPrice }" /></span> 원</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
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