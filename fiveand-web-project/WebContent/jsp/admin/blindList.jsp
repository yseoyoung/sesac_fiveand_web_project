<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>게시물 블라인드 처리 목록</title>

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css" />

<!-- Slick -->
<link type="text/css" rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/slick.css" />
<link type="text/css" rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/style.css" />
	
<style>
table {
	width: 100%;
	border-color: #E4E7ED;
}

th {
	text-align: center;
	font-size: 12pt;
	height: 30px;
	color: ##1E1F29;
	background-color: #FBFBFC;
}

td {
	text-align: center;
	height: 25px;
	color: ##1E1F29;
}
</style>
<script src="${ pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
<script>
	
</script>
</head>
<body>
	<!-- HEADER -->
	<header>
		<jsp:include page="/jsp/include/topMenuAdmin.jsp" />
	</header>
	<!-- /HEADER -->

	<!-- BREADCRUMB -->
	<div id="breadcrumb" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<h3 class="breadcrumb-header">블라인드 게시물 목록</h3>
					<ul class="breadcrumb-tree">
						<li><a href="${ pageContext.request.contextPath }/main.do">Home</a></li>
						<li class="active">블라인드 게시물 목록</li>
					</ul>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /BREADCRUMB -->

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
			<!-- 블라인드 게시물 목록 -->
			<table border="1" class="list">
				<tr>
					<th>번호</th>
					<th>회원ID</th>
					<th>제품명</th>
					<th>분류</th>
					<th>희망가</th>
					<th>시작가</th>
					<th>등록일</th>
					<th>마감일</th>
					<th>삭제일</th>
				</tr>

				<c:forEach items="${ list }" var="blind">
					<tr>
						<td>${ blind.pdNo }</td>
						<td>${ blind.id }</td>
						<td>${ blind.pdName }</td>
						<td>${ blind.cName }</td>
						<td>${ blind.hopePrice }</td>
						<td>${ blind.startPrice }</td>
						<td>${ blind.regDate }</td>
						<td>${ blind.dueDate }</td>
						<td>${ blind.delDate }</td>
					</tr>
				</c:forEach>
			</table>
			<!-- /블라인드 게시물 목록 -->
			
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->

<!-- blank  -->
               <div>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
               </div>
               <!-- /blank  -->
	<!-- FOOTER -->
	<footer id="footer">
		<jsp:include page="/jsp/include/footer.jsp" />
	</footer>
	<!-- /FOOTER -->

	<!-- jQuery Plugins -->
	<script src="${ pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/slick.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/nouislider.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/jquery.zoom.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/main.js"></script>

</body>
</html>