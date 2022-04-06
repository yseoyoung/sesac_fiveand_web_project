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

		<title>FIVEAND AUCTION</title>

		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Gothic+A1:200,300,400,500,600" rel="stylesheet">
		
		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

		<!-- Slick -->
		<link type="text/css" rel="stylesheet" href="css/slick.css"/>
		<link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

		<!-- nouislider -->
		<link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="css/font-awesome.min.css">

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="css/style.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
<!-- 	<script>
<!--
	$(document).ready(
			function(){
				$('#wishList').click(function() {
					if ( ${ empty userVO } ) {
						alert('로그인을 해주시기 바랍니다.')
					}
				});
			});
			
	$(document).ready(
			function(){
				$('#auctionReg').click(function() {
					if ( "${ empty userVO }" ) {
						alert('로그인을 해주시기 바랍니다.')
					}
				});
			});
	</script> -->
	
	
	
	<script>
		<c:if test="${not empty userVO}">
			<c:if test="${userVO.warningCnt ge 2}">
				alert('${userVO.warningCnt}회 경고로 인해 이용이 제한됩니다.')
				location.href="${ pageContext.request.contextPath }/logout.do"
			</c:if>
		</c:if>
	</script>
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

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
				
				
				
					<!-- Recent : 최근 등록 순으로 정렬된 페이지 -->
						<a href="${ pageContext.request.contextPath }/boardList/recent.do">
					<div class="col-md-4 col-xs-6">
						<div class="shop">
						<!-- 이미지 -->
							<div class="shop-img">
								<img src="./img/shop01.png" alt="">
							</div>
							<div class="shop-body">
								<h3>Recent</h3>
								<a href="${ pageContext.request.contextPath }/boardList/recent.do" class="cta-btn">now <i class="fa fa-comments"></i></a>
							</div>
						</div>
					</div>
						</a>
					<!-- /Recent -->
					
					<!-- View : 조회 순으로 정렬된 페이지 -->
					<a href="${ pageContext.request.contextPath }/boardList/view.do">
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="./img/shop03.png" alt="">
							</div>
							<div class="shop-body">
								<h3>View</h3>
								<a class="cta-btn" href="${ pageContext.request.contextPath }/boardList/view.do">now <i class="fa fa-mouse-pointer"></i></a>
							</div>
						</div>
					</div>
						</a>
					<!-- /View -->

					<!-- Hearts : 하트 순으로 정렬된 페이지 -->
					<a href="${ pageContext.request.contextPath }/boardList/heart.do">
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="./img/shop02.png" alt="">
							</div>
							<div class="shop-body">
								<h3>Hearts</h3>
								<a class="cta-btn" href="${ pageContext.request.contextPath }/boardList/heart.do">now <i class="fa fa-heart"></i></a>
							</div>
						</div>
					</div>
						</a>
					<!-- /Hearts -->
					
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->


		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">신규 경매</h3>
						</div>
					</div>
					<!-- /section title -->
					
					
					<!-- 새로 등록된 경매 5개가 넘어가면서 보이도록 체크
						사진, 카테고리, 등록된 제품명, 금액,  -->
					<!-- Products tab & slick -->
					
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab1" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-1">
									
									
										<c:forEach var="i" begin="0" end="4">
									<!-- product -->
										<div class="product">
											<div class="product-img">
											<%-- 서버에 저장된 이미지 가져오기(메인서버 하나)
												  확인할 때에는 upload 파일에서 확인  --%>
												<img src="${ pageContext.request.contextPath }/upload/${ recentFileList[i].fileSaveName }" alt="">
												<div class="product-label">
													<span class="new">NEW</span>
													<span class="sale">마감 ${ recentList[i].dueDate }</span>
												</div>
											</div>
											
											<div class="product-body">
													<p class="product-category">${ recentList[i].cName }</p>
													<h3 class="product-name"><a href="${ pageContext.request.contextPath }/auction/viewcnt.do?no=${ recentList[i].pdNo }">${ recentList[i].pdName }</a></h3>
													<h4 class="product-price">시작가 ${ recentList[i].startPrice } 원</h4>
												
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i>&nbsp;${ recentList[i].likeCnt }<!-- <span class="tooltipp">add to My Heart</span> --></button>
						
													<button class="quick-view"><i class="fa fa-eye"></i>&nbsp;${ recentList[i].viewCnt }</button>
												</div>
											</div>
											<div class="add-to-cart"><a href="${ pageContext.request.contextPath }/auction/viewcnt.do?no=${ recentList[i].pdNo }">
												<button class="add-to-cart-btn"><i class="fa fa-hand-o-up"></i>경매 참여</button></a>
											</div>
											
											
											
											
											
										</div>
									</c:forEach>
										
								<!-- /product -->

									</div>
									<div id="slick-nav-1" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->




		<!-- 중간에 홈페이지 광고 느낌처럼 넣기(페이지 분리) => 필요 없는 제품을 판매할 수 있다st -->
		<div id="hot-deal" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="hot-deal">
							<ul class="hot-deal-countdown">
								<!-- <li>
									<div>
										<h3>07</h3>
										<span>Days</span>
									</div>
								</li>
								<li>
									<div>
										<h3>10</h3>
										<span>Hours</span>
									</div>
								</li>
								<li>
									<div>
										<h3>34</h3>
										<span>Mins</span>
									</div>
								</li>
								<li>
									<div>
										<h3>60</h3>
										<span>Secs</span>
									</div>
								</li> -->
							</ul>
							<h2 class="text-uppercase">Auction Old, <br> Make Gold!</h2>
							<p> 안 쓰는 물건으로 새로운 즐거움을 낙찰하세요! </p>
							
							<div class="dropdown">
								<c:if test="${ empty userVO.id }">
		   							<a class="primary-btn cta-btn" onclick="alert('로그인이 필요합니다.')" href="${ pageContext.request.contextPath }/login.do">
										<span>Auction now</span>
									</a>
								</c:if>
								<c:if test="${ userVO.type eq 'U' }">
									<a class="primary-btn cta-btn" href="${ pageContext.request.contextPath }/auction/writeForm.do">
										<span>Auction now</span>
									</a>
								</c:if>
							
							</div>

				
				
				
				
				
				
				
				
				
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /HOT DEAL SECTION -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					
					<!-- 당일로부터 일주일 기준으로 가장 많이 제시를 받은 제품 탑5를 정렬-->
					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title"><i class="fa fa-thumbs-o-up"></i>&nbsp;Top suggested</h3>
						</div>
					</div>
					<!-- /section title -->
					<!-- Products tab & slick -->
					
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab1" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-2">
									
									
									<c:forEach items="${ sugList }" var="sugList">
									<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="${ pageContext.request.contextPath }/upload/${ sugList.fileSaveName }" alt="">
												<div class="product-label">
													<span class="sale">마감 ${ sugList.dueDate }</span>
												</div>
											</div>
											
											<div class="product-body">
													<p class="product-category">${ sugList.cName }</p>
													<h3 class="product-name"><a href="#">${ sugList.pdName }</a></h3>
													<h4 class="product-price">시작가 ${ sugList.startPrice } 원</h4>
												
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i>&nbsp;${ sugList.likeCnt }<!-- <span class="tooltipp">add to My Heart</span> --></button>
						
													<button class="quick-view"><i class="fa fa-eye"></i>&nbsp;${ sugList.viewCnt }</button>
												</div>
											</div>
											<div class="add-to-cart"><a href="${ pageContext.request.contextPath }/auction/viewcnt.do?no=${ sugList.pdNo }">
												<button class="add-to-cart-btn"><i class="fa fa-hand-o-up"></i>경매 참여</button></a>
											</div>
										</div>
									</c:forEach>
								<!-- /product -->

									</div>
									<div id="slick-nav-2" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->



		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">요즘 뜨는 경매</h4>
							<div class="section-nav">
								<div id="slick-nav-3" class="products-slick-nav"></div>
							</div>
						</div>
						
						<div class="products-widget-slick" data-nav="#slick-nav-3">
							<div>
							<c:forEach items="${ topLike }" var="list">
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="${ pageContext.request.contextPath }/upload/${ list.fileSaveName }" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">${ list.cName }</p>
										<h3 class="product-name"><a href="${ pageContext.request.contextPath }/auction/viewcnt.do?no=${ list.pdNo }">${ list.pdName }</a></h3>
										<h4 class="product-price">시작가 ${ list.startPrice } 원</h4>
									</div>
								</div>
								<!-- product widget -->
								</c:forEach>
							</div>
						</div>
					</div>



					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">오늘의 경매</h4>
							<div class="section-nav">
								<div id="slick-nav-4" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-4">
							<div>
							<c:forEach items="${ today }" var="list">
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="${ pageContext.request.contextPath }/upload/${ list.fileSaveName }" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">${ list.cName }</p>
										<h3 class="product-name"><a href="${ pageContext.request.contextPath }/auction/viewcnt.do?no=${ list.pdNo }">${ list.pdName }</a></h3>
										<h4 class="product-price">시작가 ${ list.startPrice } 원</h4>
									</div>
								</div>
								<!-- product widget -->
								</c:forEach>
							</div>
						</div>
					</div>

					<div class="clearfix visible-sm visible-xs"></div>

					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">마감 임박 경매</h4>
							<div class="section-nav">
								<div id="slick-nav-5" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-5">
							<div>
							<c:forEach items="${ deadLine }" var="deadLine">
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="${ pageContext.request.contextPath }/upload/${ deadLine.fileSaveName }" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">${ deadLine.cName }</p>
										<h3 class="product-name"><a href="${ pageContext.request.contextPath }/auction/viewcnt.do?no=${ deadLine.pdNo }">${ deadLine.pdName }</a></h3>
										<h4 class="product-price">시작가 ${ deadLine.startPrice } 원</h4>
									</div>
								</div>
								<!-- product widget -->
								</c:forEach>
							</div>
						</div>
					</div>

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		
		
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
		<script>logInOut()</script>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>
		
	</body>
</html>
    