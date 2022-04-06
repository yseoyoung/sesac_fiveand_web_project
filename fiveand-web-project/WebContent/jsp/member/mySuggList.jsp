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

		<title>내가 참여한 경매 리스트</title>

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
						<h3 class="breadcrumb-header">낙찰된 경매</h3>
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
					
					
					<!-- store products -->
						<div class="row">
						
						
						<!-- product -->
						<c:forEach items="${ winList }" var="list" varStatus="status">
						<c:if test="${ list.payment eq 0 }">
						
							<div class="col-md-4 col-xs-7">
								<div class="product" style="width: 357.5px; height: 509.77px; padding: 10px;">
									<div class="product-img">
										
											<img src="${ pageContext.request.contextPath }/upload/${ list.fileSaveName }">
										
										<div class="product-label">
											<span class="sale">마감된 경매</span>
											
										</div>
									</div>
									<div class="product-body">
										<p class="product-category">${ list.cName }</p>
										<h3 class="product-name">${ list.pdName }</h3>
										<h4 class="product-price">최종가 ${ list.sugPrice }</h4>
										
										<div class="product-btns">
											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to My Heart</span></button>
											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
										</div>
									</div>
									<div class="add-to-cart"><a href="${ pageContext.request.contextPath }/pay.do?id=${ userVO.id }&pdNo=${ list.pdNo }">
										<button class="add-to-cart-btn" onclick="javascript:btn()"><i class="fa fa-qrcode"></i>결제하기</button></a>
									</div>
									
								</div>
								<c:if test="${ (i+1) mod 3 eq 0 }">
									<div class="clearfix visible-lg visible-md"></div>
								</c:if>
						
							</div>
						</c:if>
							</c:forEach>
							
							<c:forEach items="${ winList }" var="list" varStatus="status">
						<c:if test="${ list.payment ne 0 }">
							<div class="col-md-4 col-xs-7">
								<div class="product" style="width: 357.5px; height: 509.77px; padding: 10px;">
									<div class="product-img">
										
											<img src="${ pageContext.request.contextPath }/upload/${ list.fileSaveName }">
										
										<div class="product-label">
											<span class="sale">결제 완료</span>
											
										</div>
									</div>
									<div class="product-body">
										<p class="product-category">${ list.cName }</p>
										<h3 class="product-name">${ list.pdName }</h3>
										<h4 class="product-price">최종가 ${ list.sugPrice }</h4>
										
										<div class="product-btns">
											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to My Heart</span></button>
											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
										</div>
									</div>
									
								</div>
								<c:if test="${ (i+1) mod 3 eq 0 }">
									<div class="clearfix visible-lg visible-md"></div>
								</c:if>
							</div>
							
						</c:if>
						</c:forEach>
						<!-- /product -->

							
						</div>
						<!-- /store products -->
						

					</div>
					<!-- /STORE -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">참여한 경매</h3>
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
					

					<!-- store products -->
						<div class="row">
						
						
							<!-- product -->
							<c:forEach items="${ suggList }" var="list" varStatus="status">
							<div class="col-md-4 col-xs-7">
								<div class="product" style="width: 357.5px; height: 509.77px; padding: 10px;">
									<div class="product-img">
										
											<img src="${ pageContext.request.contextPath }/upload/${ list.fileSaveName }">
										
										<div class="product-label">
											<span class="sale">마감 ${ list.dueDate }</span>
											
										</div>
									</div>
									<div class="product-body">
										<p class="product-category">${ list.cName }</p>
										<h3 class="product-name">${ list.pdName }</h3>
										<h4 class="product-price">시작가 ${ list.startPrice }</h4>
										
										<div class="product-btns">
											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to My Heart</span></button>
											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
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
