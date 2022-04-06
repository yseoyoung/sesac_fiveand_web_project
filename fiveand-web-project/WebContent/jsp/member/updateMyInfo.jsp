<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>내 정보 수정</title>

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
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/simple-modal.min.css">
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/simple-modal-default.min.css">
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	let resultFlag = true

	$(document).ready(function() {
		$('#pwd, #pwd2').on('keyup', pwdCheck)
	})

	function pwdCheck() {
		let pass1 = $('#pwd').val();
		let pass2 = $('#pwd2').val();

		if (pass1 != "" || pass2 != "") {
			if (pass1 == pass2) {
				$('#pwdCheck').css('color', '#8d99ae'), $('#pwdCheck').html(
						'* 패스워드가 일치합니다.')
				resultFlag = true
			} else {
				$('#pwdCheck').css('color', 'red'), $('#pwdCheck').html(
						'* 패스워드가 일치하지 않습니다.')
				resultFlag = false
			}
		}
	}
	
	function checkForm() {

			if(!resultFlag )
			alert('오류')
			return resultFlag
		}
	
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
	<!-- /HEADER -->

	<!-- BREADCRUMB -->
	<div id="breadcrumb" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<h3 class="breadcrumb-header">내 정보 수정</h3>
					<ul class="breadcrumb-tree">
						<li><a href="${ pageContext.request.contextPath }/myPage.do">My
								Page</a></li>
						<li class="active">Update My Information</li>
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
				<div class="col-md-7">

					<form name="SignupForm" method="post"
						action="${ pageContext.request.contextPath }/updateMyInfo.do" onsubmit="return checkForm()">
						<input type="hidden" name="id" value="${ param.id }">
						<div class="billing-details">
							<div class="section-title"></div>

							<div class="form-group">
								<input class="input" type="password" name="pwd" id="pwd"
									placeholder="변경하실 패스워드를 입력해 주세요." required>
							</div>
							<div class="form-group">
								<input class="input" type="password" name="pwdCheck" id="pwd2"
									placeholder="변경하실 패스워드를 다시 입력해 주세요." required> <span
									id="pwdCheck"> </span>
							</div>

							<div class="form-group">
								<input class="input" type="tel" name="phone"
									placeholder="변경하실 휴대폰 번호 '-'표 없이 입력해 주세요." required>
							</div>
							<div class="form-group">
								<input class="input" type="email" name="email"
									placeholder="변경하실 이메일을 입력해 주세요." required>
							</div>
							<div class="form-group">
								<button class="primary-btn order-submit" id="signBtn"
									type="submit">수정하기</button>

							</div>
						</div>
					</form>
					
					<!-- blank  -->
					<div>
						<br> <br> <br> <br> <br> <br> <br>
						<br> <br> <br>
					</div>
					<!-- /blank  -->

				</div>

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
	<script
		src="${ pageContext.request.contextPath }/js/jquery.zoom.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/main.js"></script>
	<script
		src="${ pageContext.request.contextPath }/js/simple-modal.min.js"></script>
</body>
</html>