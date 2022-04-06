<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>제품 상세 페이지> QnA</title>

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

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

<!-- jquery 적용 스크립트 -->
<script
	src="${ pageContext.request.contextPath }/js/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"
	integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E="
	crossorigin="anonymous"></script>

<!-- 모달창 관련 -->
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/simple-modal.min.css">
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/simple-modal-default.min.css">
<script
	src="${ pageContext.request.contextPath }/js/simple-modal.min.js"></script>

<style>
p.textBtn {
	text-align: right;
}

table {
	width: 70%;
	border-color: #E4E7ED;
}

th {
	text-align: center;
	font-size: 10pt;
	height: 25px;
	color: ##1E1F29;
	background-color: #FBFBFC;
}

td {
	height: 25px;
	color: ##1E1F29;
}
</style>
<script>

$(document).ready(
		function(){
			$('span.qty-up').click(function() {
				let upPrice = Number($("#suggest").val()) + 1000
				$("#suggest").val(upPrice)
			})
		})
		
$(document).ready(
		function(){
			$('span.qty-down').click(function() {
				let downPrice = Number($("#suggest").val()) - 1000
				$("#suggest").val(downPrice)
			})
		}
)

$(document).ready(
		function(){
			$('p#deleteProduct').click(function() {
				let conf = confirm('정말 삭제하시겠습니까?')
				if (conf) {
					location.href = "${ pageContext.request.contextPath }/remove.do?no=${product.pdNo}"
				}
			})
		}
)

$(document).ready(
		function(){
			$('p#modifyProduct').click(function() {
				location.href = "${ pageContext.request.contextPath }/auction/updateForm.do?no=${product.pdNo}"
			})
		}
)


function remindTime() {
	var today = new Date();
	var dday = new Date('${ product.dueDate }');
	if (today < dday) {
		var gap = dday.getTime() - today.getTime();
		var day = Math.ceil(gap / (1000*60*60*24));
		var hour = Math.ceil((gap % (1000*60*60*24)) / (1000*60*60));
		var min = Math.ceil((gap % (1000*60*60)) / (1000*60));
		var sec = Math.ceil((gap % (1000*60)) / 1000);
		
		$("h6.time-title").html("경매 마감 D-"+day+" "+hour+":"+min+":"+sec)
	}
	else {
		$("h6.time-title").html("경매 마감")
		$("h2.product-name").html("<del>${product.pdName}</del>")		
	}
}

let timers = setInterval(remindTime, 1000);

function doAction(){
	location.href = "${ pageContext.request.contextPath }/suggest.do?no=${product.pdNo}"
}

function checkSuggest() {	
	if(${ empty userVO }){
		alert('로그인을 해주세요')
		return false
	}
	if(new Date() >= new Date('${ product.dueDate }')) {
		alert('이미 마감된 경매입니다')
		return false
	}
	
	if(${ empty suggestList[0].sugPrice }) {
		if ($("#suggest").val() <= ${product.startPrice}) {
			alert('시작가보다 높게 입력해주세요')
			return false
		}
	}
	<c:if test="${ not empty suggestList[0].sugPrice }">
	else {
		if($("#suggest").val() <= ${ suggestList[0].sugPrice }) {
			alert('최고 경매가보다 높게 입력해주세요')
			return false
		}
	}
	</c:if>
	alert('성공적으로 제시되었습니다')
	
	return true
}

$(window).on("beforeunload", function(){
	clearInterval(timers)
})



$(document).ready(function() {
	$('#QnABtn').get(0).click();
})
$(document).ready(function() {
	
	$('#goWriteForm').click(function() {
		
		$('#qnaList').css('display', 'none');
		$('#qnaWriteForm').css('display', 'block');
		$('#qnaDetailForm').css('display', 'none');
		$(this).css('display', 'none')
		})
		})

		
		
function doWrite(){
let f = document.inputForm

if(f.title.value == ''){
	alert('제목을 입력하세요')
	f.title.focus()
	return false
}
if(f.content.value == ''){
	alert('내용 입력하세요')
	f.content.focus()
	return false
}
else{
	alert('문의글 등록이 완료되었습니다!')
}

return true
}


function doAction(bNo){
	
	<c:choose>
		<c:when test="${ not empty userVO }">
			location.href="${ pageContext.request.contextPath }/qna/detail.do?bNo=" + bNo
			//alert(bNo)
		</c:when>
		<c:otherwise>
			if(confirm('로그인이 필요한 서비스입니다.\n이동하시겠습니까?')){
				location.href="${ pageContext.request.contextPath }/login.do"
			}
		</c:otherwise>
	</c:choose>
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


	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- Product main img -->
				<div class="col-md-5 col-md-push-2">
					<div id="product-main-img">
						<c:forEach items="${ fileList }" var="file">
							<div class="product-preview">
								<img
									src="${ pageContext.request.contextPath }/upload/${ file.fileSaveName }"
									alt="">
							</div>
						</c:forEach>
						<!-- 
							<div class="product-preview">
								<img src="./img/product03.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product06.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product08.png" alt="">
							</div>
							 -->
					</div>
				</div>
				<!-- /Product main img -->

				<!-- Product thumb imgs -->
				<div class="col-md-2  col-md-pull-5">
					<div id="product-imgs">
						<c:forEach items="${ fileList }" var="file">
							<div class="product-preview">
								<img
									src="${ pageContext.request.contextPath }/upload/${ file.fileSaveName }"
									alt="">
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- /Product thumb imgs -->

				<!-- Product details -->
				<div class="col-md-5">
					<div class="product-details">
						<h6 class="product-price time-title"></h6>
						<div class="count"></div>
						<h2 class="product-name">${ product.pdName }</h2>
						<h5>판매자 ${ product.id }</h5>
						<div>
							<h3 class="product-price">${ product.startPrice }&#8361
								<del class="product-old-price">${ product.hopePrice }&#8361</del>
							</h3>
							<!-- <span class="product-available">In Stock</span>  -->
						</div>
						<p>${ product.pdSimpleInfo }</p>
						<hr>


						<c:if test="${ userVO.id ne product.id }">
							<div class="add-to-cart">
								<form action="${ pageContext.request.contextPath }/suggest.do"
									method="post" name="inputSuggest"
									onsubmit="return checkSuggest()">
									<input type="hidden" name="id" value="${ userVO.id }">
									<input type="hidden" name="pdNo" value="${ product.pdNo }">
									<div class="qty-label">
										제시가
										<div class="input-number">
											<c:choose>
												<c:when test="${ empty suggestList }">
													<input id="suggest" type="number" name="sugPrice"
														value="${ product.startPrice }">
												</c:when>
												<c:otherwise>
													<input id="suggest" type="number" name="sugPrice"
														value="${ suggestList[0].sugPrice }">
												</c:otherwise>
											</c:choose>
											<span class="qty-up">+</span> <span class="qty-down">-</span>
										</div>
									</div>
									<button type="submit" class="add-to-cart-btn">
										<i class="fa fa-hand-o-up"></i>제시하기
									</button>
								</form>
							</div>
						</c:if>

						<!-- suggest 모달창 -->
						<div class="modal" data-modal>
							<div class="modal-content">
								<button role="button" class="close-icon"
									data-modal="close-modal">X</button>
								<div class="modal-body">
									<br>
									<p id="suggestFailMsg">내용입니다</p>
									<br>
								</div>
								<div class="modal-footer">
									<button role="button" class="close-button"
										data-modal="close-modal">취소</button>
									<button role="button">확인</button>
								</div>
							</div>
						</div>

						<ul class="product-btns">
							<li><a href="#"><i class="fa fa-heart-o"></i> add to
									Heart</a></li>
							<!-- <li><a href="#"><i class="fa fa-exchange"></i> add to compare</a></li>  -->
						</ul>

						<ul class="product-links">
							<li>Category :</li>
							<li><a href="#">${ product.cName }</a></li>
							<!-- <li><a href="#">Accessories</a></li>  -->
						</ul>
						<hr>
						<div>
							<p>경매 진행 현황 (TOP 3)</p>
							<hr>
							<c:forEach items="${ suggestList }" var="suggest">
								<h4>${ suggest.sugPrice }&#8361</h4>
								<h6>${ suggest.id }(${ suggest.sugDate })</h6>
								<hr>
							</c:forEach>

						</div>
					</div>
					<!-- <ul class="product-links">
								<li>제시 현황:</li><br>
								<li>1200(ad**) 최고가</li>
								<li>1100 (ad**)</li>
								<li>1000 (ad**)</li>
							</ul>  -->
					<c:if test="${ userVO.type eq 'A' }">
						<div class="deletebtn">
							<p class="textBtn" id="deleteProduct">삭제하기</p>
						</div>
					</c:if>
					<c:if test="${ userVO.id eq product.id }">
						<p class="textBtn" id="modifyProduct">수정하기</p>
					</c:if>

				</div>
				<!-- /Product details -->



				<div class="col-md-12">
					<div id="product-tab">
						<!-- product tab nav -->
						<ul class="tab-nav">
							<li><a data-toggle="tab" href="#tab1">Description</a></li>
							<li class="active"><a id="QnABtn" data-toggle="tab" href="#tab3" >QnA</a></li>
						</ul>
						<!-- /product tab nav -->

						<!-- product tab content -->
						<div class="tab-content">
							<!-- tab1  -->
							<div id="tab1" class="tab-pane fade in active">
								<div class="row">
									<div class="col-md-12">
										<p>${ product.pdInfo }</p>
									</div>
								</div>
							</div>
							<!-- /tab1  -->

							<div id="tab3" class="tab-pane fade in">

								<div align="center">
									<div id="qnaList">
									<table width="700">
										<tr>
											<c:if test="${ not empty userVO }">
												<td align="right" id="goWriteForm"><i class="fa fa-pencil"></i>&nbsp;문의글 작성 <%-- <a href="/bbs/writeForm.bbs?pageNum=${pageNum}">글쓰기</a> --%>
												</td>
											</c:if>
										</tr>
									</table>

									<table border="1" class="list">
										<tr>
											<th width="5%">NO</th>
											<th width="40%">TITLE</th>
											<th width="10%">ID</th>
											<th width="15%">DATE</th>
										</tr>
										
										<c:forEach var="list" items="${list}"
											varStatus="status">
											<tr align="center" height="30">
												<td>${list.bNo}</td>
												<td align="left" id="title">
													<c:if test="${list.depth > 0}">
														<c:forEach var="i" begin="1" end="${list.depth}">
															<span>&nbsp;&nbsp;&nbsp;</span>
														</c:forEach>
													</c:if>
														<a href="javascript:doAction(${ list.bNo })">
														<c:out value="${list.title}"></c:out>
													</a> 
												</td>
												<td>${list.id}</td>
												<td>${list.regDate}</td>
											<tr>
										</c:forEach>
									</table>
										<!-- <tr>
											<td colspan="5" align="center" height="40">
												<%-- ${pageCode} --%>
											</td>
										</tr> -->
									</div>
									<!-- 게시판 리스트 -->

									<form action="${ pageContext.request.contextPath }/qna/write.do" 
										method="post" name="inputForm" onsubmit="return doWrite()">
										<input type="hidden" name="id" value="${ userVO.id }">
										<input type="hidden" name="pdNo" value="${ product.pdNo }"> 
										<input type="hidden" name="pageNum" value="${pageNum}">
										<input type="hidden" name="depth" value="${article.depth}"> 
										<input type="hidden" name="pos" value="${article.pos}"> 
										<input type="hidden" name="groupId" value="${article.groupId}"> 
										
										<div align="center" id="qnaWriteForm">
										<hr>
										<h2>QnA 문의글 등록</h2>
										<hr>
										<br>
										<table border="1" style="width:650px">
											<tr>
												<th width=23%>제목</th>
												<td>
													<input type="text" style="width:100%;"
													name="title" id="title" required>
												</td>
											</tr>
											<tr>
												<th>작성자</th>
												<td><strong>${ userVO.id }</strong>
												</td>
											</tr>
											<tr>
												<th>내용</th>
												<td><textarea name="content" rows="7" cols="60" style="width:100%;"
														id="content" placeholder="글을 적어 주세요." required></textarea></td>
											</tr>
										</table>
										<br>
										<input type="submit" value="문의 등록">
										<input type="reset" value="전체 삭제">
									</div>
									</form>
									<!-- 문의글 등록 -->
									
									
								</div>
								<!-- 전체 리스트 -->
							</div>
							<!-- /tab3  -->
						</div>
						<!-- /product tab content  -->
					</div>
				</div>
				<!-- /product tab -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->


	<!-- Section -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">

				<div class="col-md-12">
					<div class="section-title text-center">
						<h3 class="title">Related Products</h3>
					</div>
				</div>

				<!-- product -->
				<div class="col-md-3 col-xs-6">
					<div class="product">
						<div class="product-img">
							<img src="./img/product01.png" alt="">
							<div class="product-label">
								<span class="sale">-30%</span>
							</div>
						</div>
						<div class="product-body">
							<p class="product-category">Category</p>
							<h3 class="product-name">
								<a href="#">product name goes here</a>
							</h3>
							<h4 class="product-price">
								$980.00
								<del class="product-old-price">$990.00</del>
							</h4>
							<div class="product-rating"></div>
							<div class="product-btns">
								<button class="add-to-wishlist">
									<i class="fa fa-heart-o"></i><span class="tooltipp">add
										to wishlist</span>
								</button>
								<button class="add-to-compare">
									<i class="fa fa-exchange"></i><span class="tooltipp">add
										to compare</span>
								</button>
								<button class="quick-view">
									<i class="fa fa-eye"></i><span class="tooltipp">quick
										view</span>
								</button>
							</div>
						</div>
						<div class="add-to-cart">
							<button class="add-to-cart-btn">
								<i class="fa fa-shopping-cart"></i> add to cart
							</button>
						</div>
					</div>
				</div>
				<!-- /product -->

				<!-- product -->
				<div class="col-md-3 col-xs-6">
					<div class="product">
						<div class="product-img">
							<img src="./img/product02.png" alt="">
							<div class="product-label">
								<span class="new">NEW</span>
							</div>
						</div>
						<div class="product-body">
							<p class="product-category">Category</p>
							<h3 class="product-name">
								<a href="#">product name goes here</a>
							</h3>
							<h4 class="product-price">
								$980.00
								<del class="product-old-price">$990.00</del>
							</h4>
							<div class="product-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i>
							</div>
							<div class="product-btns">
								<button class="add-to-wishlist">
									<i class="fa fa-heart-o"></i><span class="tooltipp">add
										to wishlist</span>
								</button>
								<button class="add-to-compare">
									<i class="fa fa-exchange"></i><span class="tooltipp">add
										to compare</span>
								</button>
								<button class="quick-view">
									<i class="fa fa-eye"></i><span class="tooltipp">quick
										view</span>
								</button>
							</div>
						</div>
						<div class="add-to-cart">
							<button class="add-to-cart-btn">
								<i class="fa fa-shopping-cart"></i> add to cart
							</button>
						</div>
					</div>
				</div>
				<!-- /product -->

				<div class="clearfix visible-sm visible-xs"></div>

				<!-- product -->
				<div class="col-md-3 col-xs-6">
					<div class="product">
						<div class="product-img">
							<img src="./img/product03.png" alt="">
						</div>
						<div class="product-body">
							<p class="product-category">Category</p>
							<h3 class="product-name">
								<a href="#">product name goes here</a>
							</h3>
							<h4 class="product-price">
								$980.00
								<del class="product-old-price">$990.00</del>
							</h4>
							<div class="product-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star-o"></i>
							</div>
							<div class="product-btns">
								<button class="add-to-wishlist">
									<i class="fa fa-heart-o"></i><span class="tooltipp">add
										to wishlist</span>
								</button>
								<button class="add-to-compare">
									<i class="fa fa-exchange"></i><span class="tooltipp">add
										to compare</span>
								</button>
								<button class="quick-view">
									<i class="fa fa-eye"></i><span class="tooltipp">quick
										view</span>
								</button>
							</div>
						</div>
						<div class="add-to-cart">
							<button class="add-to-cart-btn">
								<i class="fa fa-shopping-cart"></i> add to cart
							</button>
						</div>
					</div>
				</div>
				<!-- /product -->

				<!-- product -->
				<div class="col-md-3 col-xs-6">
					<div class="product">
						<div class="product-img">
							<img src="./img/product04.png" alt="">
						</div>
						<div class="product-body">
							<p class="product-category">Category</p>
							<h3 class="product-name">
								<a href="#">product name goes here</a>
							</h3>
							<h4 class="product-price">
								$980.00
								<del class="product-old-price">$990.00</del>
							</h4>
							<div class="product-rating"></div>
							<div class="product-btns">
								<button class="add-to-wishlist">
									<i class="fa fa-heart-o"></i><span class="tooltipp">add
										to wishlist</span>
								</button>
								<button class="add-to-compare">
									<i class="fa fa-exchange"></i><span class="tooltipp">add
										to compare</span>
								</button>
								<button class="quick-view">
									<i class="fa fa-eye"></i><span class="tooltipp">quick
										view</span>
								</button>
							</div>
						</div>
						<div class="add-to-cart">
							<button class="add-to-cart-btn">
								<i class="fa fa-shopping-cart"></i> add to cart
							</button>
						</div>
					</div>
				</div>
				<!-- /product -->

			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /Section -->




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
	<script src="${ pageContext.request.contextPath }/js/main.js"></script>
</body>
</html>

