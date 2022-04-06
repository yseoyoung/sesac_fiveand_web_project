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

		<title>카테고리-도서 및 음반</title>

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
						<ul class="breadcrumb-tree">
							<li><a href="#">Category</a></li>
							<li class="active">Books and Albums</li>
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
					<!-- STORE -->
					<div id="store" class="col-md-11">
						<div align="left">
						
						</div>

						<!-- store products -->
						<div class="row">
						
						
							<!-- product -->
							<c:forEach items="${ list }" var="list" varStatus="status">
							<div class="col-md-4 col-xs-7">
							
								<div class="product" style="width: 357.5px; height: 509.77px; padding: 10px;">
									<div class="product-img">
										<%-- 이미지 링크하기 --%>
										<img src="${ pageContext.request.contextPath }/upload/${ list.fileSaveName }" alt="">
										<div class="product-label">
											<%-- 마감일까지의 차이 등록 --%>
											<span class="sale">마감 ${ list.dueDate  }</span>
											<%-- 등록일 기준 오늘 등록한 제품 NEW 
											<span class="new">NEW</span>--%>
											
										</div>
									</div>
									<div class="product-body">
										<p class="product-category">${ list.cName }</p>
										<h3 class="product-name"><a href="#">${ list.pdName }</a></h3>
										<h4 class="product-price">시작가 ${ list.startPrice } 원</h4>
										
										<div class="product-btns">
												<button class="add-to-wishlist"><i class="fa fa-heart-o"></i>&nbsp;${ list.likeCnt }<!-- <span class="tooltipp">add to My Heart</span> --></button>
												<button class="quick-view"><i class="fa fa-eye"></i>&nbsp;${ list.viewCnt }</button>
										</div>
									</div>
									<div class="add-to-cart"><a href="${ pageContext.request.contextPath }/auction/viewcnt.do?no=${ list.pdNo }">
										<button class="add-to-cart-btn"><i class="fa fa-hand-o-up"></i>경매 참여</button></a>
									</div>
									
								</div>
								<c:if test="${ (i+1) mod 3 eq 0 }">
										<div class="clearfix visible-lg visible-md"></div>
									</c:if>
						
							</div>
							</c:forEach>
							<!-- /product -->

							
						</div>
						<!-- /store products -->
						



						
					
						<!-- /store bottom filter -->
					</div>
					<!-- /STORE -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

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
