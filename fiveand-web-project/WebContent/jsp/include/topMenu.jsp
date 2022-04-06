<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src='<%=request.getContextPath() %>/js/login.js'></script>



		<!-- HEADER -->
		
		<!-- TOP HEADER -->
		<div id="top-header">
			<div class="container">
		
				<ul class="header-links pull-right">
					<span>
					<c:if test="${ not empty userVO.id }">
		   				[&emsp;${ userVO.id }님 환영합니다&emsp;<c:if test="${ userVO.warningCnt > 0 }">|&emsp;경고 : ${ userVO.warningCnt } 회&emsp;</c:if>]
					</c:if>
					</span>
		
				<c:choose>
				<c:when test="${ empty userVO.id }">
      
			<li><a href="${ pageContext.request.contextPath }/signup.do"><i
					class="fa fa-arrow-right"></i> Join</a></li>
			
			<li><a href="${ pageContext.request.contextPath }/login.do"><i
					class="fa fa-user-o" id="btnLogin"></i> Login</a></li>
								   </c:when>
						<c:otherwise>
			
			<li><a href="${ pageContext.request.contextPath }/logout.do"><i
					class="fa fa-user-o" id="btnLogout"></i> Logout</a></li>
			 <li><a href="${ pageContext.request.contextPath }/myPage.do"><i
					class="fa fa-user-o"></i> 마이페이지</a></li>
				</c:otherwise>
				
			</c:choose>
			
			
			
	
			
			
		</ul>
	</div>
</div>

<header>
			<!-- /TOP HEADER -->
			<!-- MAIN HEADER -->
			<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
					
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="${ pageContext.request.contextPath }/main.do" class="logo">
									<img src="${ pageContext.request.contextPath }/img/fiveand_Logo5.png" alt="fiveand_Logo" width="150" height="80">
								</a>
							</div>
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form action="${ pageContext.request.contextPath }/search.do" name="search" method="get">
									<input class="input" placeholder="상품명을 입력하세요" value="${param.findStr }" name="findStr">
									<button type="submit" class="search-btn">검색</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->


						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn" style="width: 280px;">
							
								
								<div>
								<c:if test="${ empty userVO.id }">
									<a onclick="alert('로그인이 필요합니다.')" id="suggList" href="${ pageContext.request.contextPath }/login.do">
										<i class="fa fa-heart-o"></i>
										<span>참여한 경매</span>
									</a>
								</c:if>
								
								
								<c:if test="${ not empty userVO.id }">
									<a id="suggList" href="${ pageContext.request.contextPath }/mySuggList.do?id=${userVO.id}" >
										<i class="fa fa-heart-o"></i>
										<span>참여한 경매</span>
										<c:if test="${ winBidCnt > 0 }">
										<div class="qty">${ winBidCnt }</div>
										</c:if>
									</a>
								</c:if>
								
								</div>
								<div class="dropdown">
								<c:if test="${ empty userVO.id }">
		   							<a onclick="alert('로그인이 필요합니다.')" id="auctionReg" href="${ pageContext.request.contextPath }/login.do">
										<i class="fa fa-handshake-o"></i>
										<span>경매 등록</span>
									</a>
								</c:if>
								<c:if test="${ not empty userVO.id }">
									<a id="auctionReg" href="${ pageContext.request.contextPath }/auction/writeForm.do">
										<i class="fa fa-handshake-o"></i>
										<span>경매 등록</span>
									</a>
								</c:if>
								
								</div>
								
								<!-- /Cart -->

								<!-- Menu Toogle -->
								<div class="menu-toggle">
									<a href="#">
										<i class="fa fa-bars"></i>
										<span>Menu</span>
									</a>
								</div>
								<!-- /Menu Toogle -->
							</div>
						</div>
						<!-- /ACCOUNT -->
					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->

		<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li><a href="${ pageContext.request.contextPath }/main.do">Home</a></li>
						
						<li><a href="${ pageContext.request.contextPath }/category/digital.do">디지털기기</a></li>
						<li><a href="${ pageContext.request.contextPath }/category/electronics.do">생활가전</a></li>
						<li><a href="${ pageContext.request.contextPath }/category/furniture.do">가구/인테리어</a></li>
						<li><a href="${ pageContext.request.contextPath }/category/clothes.do">의류/잡화</a></li>
						<li><a href="${ pageContext.request.contextPath }/category/beauty.do">뷰티/미용</a></li>
						<li><a href="${ pageContext.request.contextPath }/category/books.do">도서/음반</a></li>
						<li><a href="${ pageContext.request.contextPath }/category/etc.do">기타</a></li>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->
 <script>logInOut()</script>