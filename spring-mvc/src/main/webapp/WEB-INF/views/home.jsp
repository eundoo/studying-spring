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
<c:set var="menu" value="home"/>
<%@ include file="common/nav.jsp" %>
<div class="container my-3">
	<div class="p-5 mb-4 bg-dark rounded-3 text-white">
		<div class="container-fluid py-3">
			<h1 class="display-5 fw-bold">샘플 애플리케이션</h1>
			<p class="col-10 fs-4">회원가입, 로그인, 로그아웃, 상품목록 조회, 상품상세정보 조회, 상품 리뷰쓰기, 장바구니 담기, 구매하기 연습용 샘플 애플리케이션 입니다.</p>
			<c:choose>
				<c:when test="${empty LOGINED_USER }">
					<a class="btn btn-primary btn-lg" href="login">로그인</a>
				</c:when>
				<c:otherwise>
					<p class="col-8 fs-1"><strong  style="color: yellow;">${LOGINED_USER.name }</strong>님 사용자 인증이 완료되었습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

</script>
</body>
</html>