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

		<title>MY PAGE</title>
		
		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Gothic+A1:200,300,400,500,600" rel="stylesheet">
		
		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" />

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/slick.css" />
		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/slick-theme.css" />

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/nouislider.min.css" />

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" />
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->   
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

	<!-- BREADCRUMB -->
	<div id="breadcrumb" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<h3 class="breadcrumb-header">마이페이지</h3>
					<ul class="breadcrumb-tree">
						<li><a href="${ pageContext.request.contextPath }/main.do">HOME</a></li>
						<li class="active">My PAGE</li>
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
				
					 <a href="${ pageContext.request.contextPath }/myInfo.do?id=${ userVO.id }"> 
					<div class="col-md-4 col-xs-6">	
						<div class="mypage">
							<div class="mypage-img">
								<img src="${ pageContext.request.contextPath }/img/info.png" style="width: 300px; height: 300px;">
							</div>
							<div class="shop-body">
								<h3>MY INFO</h3>
								<a href="${ pageContext.request.contextPath }/myInfo.do?id=${ userVO.id }" class="cta-btn">now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					</a>

					<a href="${ pageContext.request.contextPath }/myAcution.do?id=${ userVO.id }">
					<div class="col-md-4 col-xs-6">	
						<div class="mypage">
							<div class="mypage-img">
								<img src="${ pageContext.request.contextPath }/img/law.png"  style="width: 300px; height: 300px;">
							</div>
							<div class="shop-body">
								<h3>MY AUCTION</h3>
								<a href="${ pageContext.request.contextPath }/myAcution.do?id=${ userVO.id }" class="cta-btn">now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					</a>

					<a href="${ pageContext.request.contextPath }/myHeart.do?id=${ userVO.id }">
					<div class="col-md-4 col-xs-6">	
						<div class="mypage">
							<div class="mypage-img">
								<img src="${ pageContext.request.contextPath }/img/love.png"  style="width: 300px; height: 300px;">
							</div>
							<div class="shop-body">
								<h3>MY HEART</h3>
								<a href="${ pageContext.request.contextPath }/myHeart.do?id=${ userVO.id }" class="cta-btn">now <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					</a>
					
			
			
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

<br><br><br><br><br><br>
		<!-- NEWSLETTER -->
		<div id="newsletter" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="newsletter">
							<!-- <div class="logo_black" padding="100px">
								<img src="./img/fiveand_Logo_black.png" alt="fiveand_Logo" width="200" height="55">
							</div>	
							 -->
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /NEWSLETTER -->
		



		
		<!-- FOOTER -->
		<footer id="footer">
			<jsp:include page="/jsp/include/footer.jsp" />
		</footer>
		<!-- /FOOTER -->
		
		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>
	</body>
</html>
    