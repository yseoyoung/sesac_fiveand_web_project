<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>내 계정 삭제</title>

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

<style>
.deleteForm {
	width: 560px;
	align-content: center;
	align-items: center;
	text-align: center;
}

.container_f {
	align-content: center;
	align-items: center;
	padding-right: 15px;
	padding-left: 15px;
	margin-right: 35%;
	margin-left: 35%;
}
</style>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>

	function isNull(obj, msg) {
		if (obj.value == '') {
			alert(msg)
			obj.focus()
			return true
		}
		return false
	}

	function checkForm() {
		let f = document.loginForm;
		if (isNull(f.password, '패스워드를 입력하세요'))
			return false
		return true
	}
	
	$(document).ready(function(){
		$("#signBtn").click(function(){
			let conf = confirm('계정 삭제가 되었습니다.')
		})
	})
	
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
					<h3 class="breadcrumb-header">내 계정 삭제</h3>
					<ul class="breadcrumb-tree">
						<li><a href="${ pageContext.request.contextPath }/myPage.do">My
								Page</a></li>
						<li class="active">Delete My Information</li>
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
		<div class="container_f">
			<!-- row -->
			<div class="row">
				<div class="col-md-7">
					<form name="deleteForm" method="post" class="deleteForm"
						action="${ pageContext.request.contextPath }/deleteProcess.do"
						onsubmit="return checkForm()">
						<input type="hidden" name="id" value="${ param.id }">
						<div class="billing-details">

							<div class="section-title">
								<h3 class="title">Fiveand 탈퇴</h3>
							</div>

							<div class="form-group">
								<input class="input" type="password" name="pwd" id="pwd"
									placeholder="패스워드를 입력하세요." required>
							</div>

							<br>

							<div class="form-group">
								<button class="primary-btn order-submit" id="signBtn"
									type="submit">탈퇴하기</button>

							</div>


						</div>
					</form>


					<!-- 
<button role="button" data-modal="open-modal" >클릭하면 모달 !</button> -->

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