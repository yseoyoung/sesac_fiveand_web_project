<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- FOOTER -->
<!-- <footer id="footer"> -->
<!-- top footer -->
<div class="section">
	<!-- container -->
	<div class="container">
		<!-- row -->
		<div class="row">
			<div class="col-md-3 col-xs-6">
				<div class="footer">
					<h3 class="footer-title">About Us</h3>
					<p>Nous avons créé le site Web du premier projet sur le Web, la
						vente aux enchères.</p>
					<br>
					<ul class="footer-links">
						<li><i class="fa fa-map-marker"></i>08535 GasanRo 70 B</li>
						<li><i class="fa fa-phone"></i>+081-6673-1804</li>
						<li><i class="fa fa-envelope-o"></i>fiveand_support@gmail.com</li>
					</ul>
				</div>
			</div>

			<div class="col-md-3 col-xs-6">
				<div class="footer">
					<h3 class="footer-title">Categories</h3>
					<ul class="footer-links">
						<li><a
							href="${ pageContext.request.contextPath }/category/digital.do">디지털기기</a></li>
						<li><a
							href="${ pageContext.request.contextPath }/category/electronics.do">생활가전</a></li>
						<li><a
							href="${ pageContext.request.contextPath }/category/furniture.do">가구/인테리어</a></li>
						<li><a
							href="${ pageContext.request.contextPath }/category/clothes.do">의류/잡화</a></li>
						<li><a
							href="${ pageContext.request.contextPath }/category/beauty.do">뷰티/미용</a></li>
						<li><a
							href="${ pageContext.request.contextPath }/category/books.do">도서/음반</a></li>
						<li><a
							href="${ pageContext.request.contextPath }/category/etc.do">기타</a></li>
					</ul>
				</div>
			</div>

			<div class="clearfix visible-xs"></div>

			<div class="col-md-3 col-xs-6">
				<div class="footer">
					<h3 class="footer-title">Information</h3>
					<ul class="footer-links">
						<li>About Us</li>
						<li>Contact Us</li>
						<li>Privacy Policy</li>
						<li>Terms & Conditions</li>
					</ul>
				</div>
			</div>

			<div class="col-md-3 col-xs-6">
				<div class="footer">
					<h3 class="footer-title">Service</h3>
					<ul class="footer-links">
						<li><c:if test="${ empty userVO.id }">
								<a onclick="alert('로그인이 필요합니다.')"
									href="${ pageContext.request.contextPath }/login.do">
									Account </a>
							</c:if> <c:if test="${ not empty userVO.id }">
								<a
									href="${ pageContext.request.contextPath }/myInfo.do?id=${ userVO.id }">
									Account </a>
							</c:if></li>
						<li>
						
						<li><c:if test="${ empty userVO.id }">
								<a onclick="alert('로그인이 필요합니다.')"
									href="${ pageContext.request.contextPath }/login.do">
									View Registration </a>
							</c:if> <c:if test="${ not empty userVO.id }">
								<a
									href="${ pageContext.request.contextPath }/myAcution.do?id=${ userVO.id }">
									View Registration </a>
							</c:if></li>
						<li>
						
						<li><c:if test="${ empty userVO.id }">
								<a onclick="alert('로그인이 필요합니다.')"
									href="${ pageContext.request.contextPath }/login.do">
								Heart </a>
							</c:if> <c:if test="${ not empty userVO.id }">
								<a
									href="${ pageContext.request.contextPath }/myHeart.do?id=${ userVO.id }">
									Heart </a>
							</c:if></li>

					</ul>
				</div>
			</div>
		</div>
		<!-- /row -->
	</div>
	<!-- /container -->
</div>
<!-- /top footer -->

<!-- bottom footer -->
<div id="bottom-footer" class="section">
	<div class="container">
		<!-- row -->
		<div class="row">
			<div class="col-md-12 text-center">
				<ul class="footer-payments">
					<li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
					<li><a href="#"><i class="fa fa-credit-card"></i></a></li>
					<li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
					<li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
					<li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
					<li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
				</ul>
				<span class="copyright"> <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;<script>
						document.write(new Date().getFullYear());
					</script>
					All rights reserved | This template is made with <i
					class="fa fa-heart-o" aria-hidden="true"></i> by <a
					href="https://colorlib.com" target="_blank">Colorlib</a> <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
				</span>
			</div>
		</div>
		<!-- /row -->
	</div>
	<!-- /container -->
</div>
<!-- /bottom footer -->
<!-- </footer> -->
<!-- /FOOTER -->