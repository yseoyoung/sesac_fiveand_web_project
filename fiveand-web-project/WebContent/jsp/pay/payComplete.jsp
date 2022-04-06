<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>결제 완료</title>

 		<!-- Google font -->
 		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

 		<!-- Bootstrap -->
 		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css"/>

 		<!-- Slick -->
 		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/slick.css"/>
 		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/slick-theme.css"/>

 		<!-- nouislider -->
 		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/nouislider.min.css"/>

 		<!-- Font Awesome Icon -->
 		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/font-awesome.min.css">

 		<!-- Custom stlylesheet -->
 		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css"/>



    </head>
	<body>
		<!-- HEADER -->

	<c:choose>
		<c:when test="${ userVO.type eq 'A' }">
			<header>
				<jsp:include page="/jsp/include/topMenuAdmin.jsp" />
			</header>	
		</c:when>
		<c:otherwise>
			<header>
				<jsp:include page="/jsp/include/topMenu.jsp" />
			</header>
		</c:otherwise>
	</c:choose>
		<!-- /HEADER -->
		
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">결제 완료</h3>
						<ul class="breadcrumb-tree">
							<li><a href="#">Home</a></li>
							<li class="active">pay</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->
		
		<div align="center">
			<br><br>
			<h2>결제 완료 되었습니다!</h2><br><br>
			<button class="primary-btn order-submit" id="UpdateInfo" onclick="location.href='${ pageContext.request.contextPath }/main.do'">
	                       메인으로 가기
	        </button>
	        <br><br><br><br><br>
		</div>
		
		
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