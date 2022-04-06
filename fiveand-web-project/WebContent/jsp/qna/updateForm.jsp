<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>문의글 수정 페이지</title>

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



<!-- 모달창 관련 -->
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/simple-modal.min.css">
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/simple-modal-default.min.css">
<script
	src="${ pageContext.request.contextPath }/js/simple-modal.min.js">
</script>
<script>

function doAction(type) {
	switch(type) {
		/* case 'U':
			//location.href="${ pageContext.request.contextPath }/qna/update.do"
			break; */
		case 'L':
			location.href="${ pageContext.request.contextPath }/auction/detail.do?no=${update.pdNo}"
			break;
	}
}

function doWrite(){
	let f = document.updateForm

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
		alert('수정이 완료되었습니다!')
	}

	return true
}

</script>

<style>
.bttn {
  display: inline-block;
  padding: 8px 20px;
  background-color: #FFF;
  border-color: #E4E7ED;
  border-radius: 15px;
  color: #000;
  font-weight: 700;
  text-align: center;
  -webkit-transition: 0.2s all;
  transition: 0.2s all;
}

table {
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
						<h3 class="breadcrumb-header">QnA Page</h3>
						<ul class="breadcrumb-tree">
							<li><a href="${ pageContext.request.contextPath }/auction/detail.do?no=${update.pdNo}">List</a></li>
							<li class="active">Current Page</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->
		
		
		
	<section>
			<div align="center" id="qnaUpdateForm">
			<br>
			<form action="${ pageContext.request.contextPath }/qna/update.do" 
				  method="post" name="updateForm" onsubmit="return doWrite()">
				<input type="hidden" name="bNo" value="${ update.bNo }">
				<input type="hidden" name="id" value="${ update.id }">
				<input type="hidden" name="pdNo" value="${ update.pdNo }">
				<input type="hidden" name="regDate" value="${ update.regDate }">
				
			<table border="1" width="650px" class="list">
				
				<tr>
					<th width=23%>제목</th>
					<td>&nbsp;${ update.title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><strong>&nbsp;${ update.id }</strong></td>			
				</tr>
				<tr>
					<th>등록일</th>
					<td>&nbsp;${ update.regDate }</td>		
				</tr>
				<tr>
					<th style="height: 200px;">내용</th>
					<td colspan="6">&nbsp;${ update.content }</td>			
				</tr>
			</table>
			
			
			<br>
			
			<c:if test="${ update.id eq userVO.id }">
				<button type="submit" class="bttn">수정</button>
				
			</c:if>
				<button class="bttn" onclick="doAction('L')">취소</button>
		</form>
		</div>
		
		

		
		<br><br>

		
		
		

		<div align="center" id="qnaReplyForm">
			<hr>
			<h2>답글 등록</h2>
			<br>
			<form action="${ pageContext.request.contextPath }/qna/reply.do"
				method="post" name="inputForm" onsubmit="return doWrite()">
				
				<input type="hidden" name="id" value="${ userVO.id }">
				<input type="hidden" name="pdNo" value="${ result.pdNo }">
				<input type="hidden" name="groupId" value="${ result.groupId }"> 
				<input type="hidden" name="depth" value="${ result.depth }"> 
				<input type="hidden" name="pos" value="${ result.pos }">
				

				<table border="1">
					<tr>
						<th width=23%>제목</th>
						<td>
							<input type="text" name="title" value="	&#8627;[Re]&nbsp;" required>
						</td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><strong>&nbsp;${ userVO.id }</strong>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" rows="10" cols="60" placeholder="글을 적어 주세요." required></textarea></td>
					</tr>
				</table>
				<br> 
				<input type="submit" class="bttn" value="답글등록">
				<input type="button" class="bttn" value="취소" onclick="doAction('L')">
					
			</form>
		</div>
	</section>
	
	
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