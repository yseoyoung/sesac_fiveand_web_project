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

<title>문의글 상세 페이지</title>

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
		case 'U':
			location.href="${ pageContext.request.contextPath }/qna/updateForm.do?bNo=${result.bNo}"
			break;
		case 'D':
			location.href="${ pageContext.request.contextPath }/qna/delete.do?bNo=${result.bNo}&pdNo=${result.pdNo}"
			alert('삭제 완료되었습니다.')
			break;
		case 'R':
			//location.href="reply.jsp?id=${ param.id }"
			$('#qnaReplyForm').css('display', 'block');
			break;
		case 'L':
			location.href="${ pageContext.request.contextPath }/auction/detail.do?no=${result.pdNo}"
			break;
	}
}

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
		alert('답글 등록이 완료되었습니다!')
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
							<li><a href="${ pageContext.request.contextPath }/auction/detail.do?no=${result.pdNo}">List</a></li>
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
		<div align = "center">
		
		<br>
			
			<table border="1" width="650px" class="list">
				
				<tr>
					<th width=23%>제목</th>
					<td>&nbsp;${ result.title }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><strong>&nbsp;${ result.id }</strong></td>			
				</tr>
				<tr>
					<th>등록일</th>
					<td>&nbsp;${ result.regDate }</td>		
				</tr>
				<tr>
					<th style="height: 200px;">내용</th>
					<td colspan="6">&nbsp;${ result.content }</td>			
				</tr>
			</table>

			<%-- <table border="1" width="900px">
				<tr>
					<td width="500px">ID: ${userVO.id }&nbsp;&nbsp;&nbsp;
						<button id="reply_save" name="reply_save">댓글 등록</button>
					</td>
				</tr>
				<tr>
					<td><textarea id="reply_content" name="reply_content" rows="4"
							cols="50" placeholder="댓글을 입력하세요."></textarea></td>
				</tr>
			</table> --%>
			<br>
			
		<c:if test="${ result.id eq userVO.id }">
			<button class="bttn" onclick="doAction('U')">수정</button>
			<button class="bttn"onclick="doAction('D')">삭제</button>
		</c:if>
		<c:if test="${ result.productId eq userVO.id }">
			<button class="bttn"onclick="doAction('R')">답글</button>
		</c:if>
			<button class="bttn" onclick="doAction('L')">목록</button>
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
				

				<table border="1" style="width:650px;">
					<tr>
						<th width=23%>제목</th>
						<td>
							<input type="text" name="title" value="	&#8627;[Re]&nbsp;" style="width:100%;" required>
						</td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td><strong>&nbsp;${ userVO.id }</strong>
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea name="content" rows="10" cols="60" placeholder="글을 적어 주세요." style="width:100%;" required></textarea></td>
					</tr>
				</table>
				<br>
				<input type="submit" class="bttn" value="답글등록">
				<input type="button" class="bttn" value="취소" onclick="doAction('L')">
					<br><br><br><br><br>
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
	
	<script>

 $(document).ready(function(){
            	
            	var reply_count = 0;
            	var status = false;
                 
                $("#list").click(function(){
                	alert("게시판 리스트로 이동");
                    //location.href = "/board/list";
                });
                 
                $("#reply_save").click(function(){
                     
                    if($("#reply_writer").val().trim() == ""){
                        alert("이름을 입력하세요.");
                        $("#reply_writer").focus();
                        return false;
                    }
                     
                    if($("#reply_password").val().trim() == ""){
                        alert("패스워드를 입력하세요.");
                        $("#reply_password").focus();
                        return false;
                    }
                     
                    if($("#reply_content").val().trim() == ""){
                        alert("내용을 입력하세요.");
                        $("#reply_content").focus();
                        return false;
                    }
                     
                    var reply_content = $("#reply_content").val().replace("\n", "<br>");
                     
                    var objParams = {
                            board_id        : $("#board_id").val(),
                            parent_id       : "0",  
                            depth           : "0",
                            reply_writer    : $("#reply_writer").val(),
                            reply_password  : $("#reply_password").val(),
                            reply_content   : reply_content
                    };
                     
                    var reply_id;
                     
                    /*
                    $.ajax({
                        url         :   "/board/reply/save",
                        dataType    :   "json",
                        contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                        type        :   "post",
                        async       :   false, //동기: false, 비동기: ture
                        data        :   objParams,
                        success     :   function(retVal){
 
                            if(retVal.code != "OK") {
                                alert(retVal.message);
                            }else{
                                reply_id = retVal.reply_id;
                            }
                             
                        },
                        error       :   function(request, status, error){
                            console.log("AJAX_ERROR");
                        }
                    });
                    */
                    
                    
                    reply_id = reply_count++;
                    
                    var reply_area = $("#reply_area");
                     
                    var reply = 
                        '<tr reply_type="main">'+
                        '   <td width="820px">'+
                        reply_content+
                        '   </td>'+
                        '   <td width="100px">'+
                        $("#reply_writer").val()+
                        '   </td>'+
                        '   <td width="100px">'+
                        '       <input type="password" id="reply_password_'+reply_id+'" style="width:100px;" maxlength="10" placeholder="패스워드"/>'+
                        '   </td>'+
                        '   <td align="center">'+
                        '       <button name="reply_reply" reply_id = "'+reply_id+'">댓글</button>'+
                        '       <button name="reply_modify" r_type = "main" reply_id = "'+reply_id+'">수정</button>      '+
                        '       <button name="reply_del" reply_id = "'+reply_id+'">삭제</button>      '+
                        '   </td>'+
                        '</tr>';
                         
                     if($('#reply_area').contents().size()==0){
                         $('#reply_area').append(reply);
                     }else{
                         $('#reply_area tr:last').after(reply);
                     }
 
                    $("#reply_writer").val("");
                    $("#reply_password").val("");
                    $("#reply_content").val("");
                     
                });
                 
                $(document).on("click","button[name='reply_del']", function(){
                     
                    var check = false;
                    var reply_id = $(this).attr("reply_id");
                    var reply_password = "reply_password_"+reply_id;
                     
                    if($("#"+reply_password).val().trim() == ""){
                        alert("패스워드을 입력하세요.");
                        $("#"+reply_password).focus();
                        return false;
                    }
                     
                    var objParams = {
                            reply_password  : $("#"+reply_password).val(),
                            reply_id        : reply_id
                    };
                     
                    //ajax 호출
                    /*
                    $.ajax({
                        url         :   "/board/reply/del",
                        dataType    :   "json",
                        contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                        type        :   "post",
                        async       :   false, //동기: false, 비동기: ture
                        data        :   objParams,
                        success     :   function(retVal){
 
                            if(retVal.code != "OK") {
                                alert(retVal.message);
                            }else{
                                 
                                check = true;
                                                                 
                            }
                             
                        },
                        error       :   function(request, status, error){
                            console.log("AJAX_ERROR");
                        }
                    });
                    */
                    
                    check = true;
                     
                    if(check){
                        
                        var prevTr = $(this).parent().parent().next(); 
                         
                        while(prevTr.attr("reply_type")=="sub"){
                            prevTr = prevTr.next();
                            prevTr.prev().remove();
                        }
                         
                        if(prevTr.attr("reply_type") == undefined){
                            prevTr = $(this).parent().parent();
                            prevTr.remove();
                        }
                         
                        $(this).parent().parent().remove(); 
                    }
                     
                });
                
                $(document).on("click","button[name='reply_modify']", function(){
                	
                    var check = false;
                    var reply_id = $(this).attr("reply_id");
                    var r_type = $(this).attr("r_type");
                    var reply_password = "reply_password_"+reply_id;
                     
                    if($("#"+reply_password).val().trim() == ""){
                        alert("패스워드을 입력하세요.");
                        $("#"+reply_password).focus();
                        return false;
                    }
                     
                    var objParams = {
                            reply_password  : $("#"+reply_password).val(),
                            reply_id        : reply_id
                    };
                     
                    //ajax 호출
                    /*
                    $.ajax({
                        url         :   "/board/reply/check",
                        dataType    :   "json",
                        contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                        type        :   "post",
                        async       :   false, //동기: false, 비동기: ture
                        data        :   objParams,
                        success     :   function(retVal){
 
                            if(retVal.code != "OK") {
                                alert(retVal.message);
                            }else{
                                 
                                check = true;
                                                                 
                            }
                             
                        },
                        error       :   function(request, status, error){
                            console.log("AJAX_ERROR");
                        }
                    });
                    */
                    
                    check = true;
                    
                    if(status){
                		alert("수정과 대댓글은 동시에 불가합니다.");
                		return false;
                	}
                	
                	status = true;
                    
                    if(check){
                    	
                    	var txt_reply_content = $(this).parent().prev().prev().prev().html().trim(); 
                    	if(r_type=="sub"){
                    		txt_reply_content = txt_reply_content.replace("→ ","");
                    	}
                    	
                    	var txt_reply_writer = $(this).parent().prev().prev().html().trim(); 
                    	
                        var replyEditor = 
                           '<tr id="reply_add" class="reply_modify">'+
                           '   <td width="820px">'+
                           '       <textarea name="reply_modify_content_'+reply_id+'" id="reply_modify_content_'+reply_id+'" rows="3" cols="50">'+txt_reply_content+'</textarea>'+ //기존 내용 넣기
                           '   </td>'+
                           '   <td width="100px">'+
                           '       <input type="text" name="reply_modify_writer_'+reply_id+'" id="reply_modify_writer_'+reply_id+'" style="width:100%;" maxlength="10" placeholder="작성자" value="'+txt_reply_writer+'"/>'+ //기존 작성자 넣기
                           '   </td>'+
                           '   <td width="100px">'+
                           '       <input type="password" name="reply_modify_password_'+reply_id+'" id="reply_modify_password_'+reply_id+'" style="width:100%;" maxlength="10" placeholder="패스워드"/>'+
                           '   </td>'+
                           '   <td align="center">'+
                           '       <button name="reply_modify_save" r_type = "'+r_type+'" reply_id="'+reply_id+'">등록</button>'+
                           '       <button name="reply_modify_cancel" r_type = "'+r_type+'" r_content = "'+txt_reply_content+'" r_writer = "'+txt_reply_writer+'" reply_id="'+reply_id+'">취소</button>'+
                           '   </td>'+
                           '</tr>';
                        var prevTr = $(this).parent().parent();
                       	
                        prevTr.after(replyEditor);
                        
                        $(this).parent().parent().remove(); 
                    }
                     
                });
              
                $(document).on("click","button[name='reply_modify_cancel']", function(){
                
                	var r_type = $(this).attr("r_type");
                	var r_content = $(this).attr("r_content");
                	var r_writer = $(this).attr("r_writer");
                	var reply_id = $(this).attr("reply_id");
                	
                	var reply;

                	if(r_type=="main"){
                		reply = 
                            '<tr reply_type="main">'+
                            '   <td width="820px">'+
                            r_content+
                            '   </td>'+
                            '   <td width="100px">'+
                            r_writer+
                            '   </td>'+
                            '   <td width="100px">'+
                            '       <input type="password" id="reply_password_'+reply_id+'" style="width:100px;" maxlength="10" placeholder="패스워드"/>'+
                            '   </td>'+
                            '   <td align="center">'+
                            '       <button name="reply_reply" reply_id = "'+reply_id+'">댓글</button>'+
                            '       <button name="reply_modify" r_type = "main" reply_id = "'+reply_id+'">수정</button>      '+
                            '       <button name="reply_del" reply_id = "'+reply_id+'">삭제</button>      '+
                            '   </td>'+
                            '</tr>';
                	}else{
                		reply = 
                            '<tr reply_type="sub">'+
                            '   <td width="820px"> → '+
                            r_content+
                            '   </td>'+
                            '   <td width="100px">'+
                            r_writer+
                            '   </td>'+
                            '   <td width="100px">'+
                            '       <input type="password" id="reply_password_'+reply_id+'" style="width:100px;" maxlength="10" placeholder="패스워드"/>'+
                            '   </td>'+
                            '   <td align="center">'+
                            '       <button name="reply_modify" r_type = "sub" reply_id = "'+reply_id+'">수정</button>'+
                            '       <button name="reply_del" reply_id = "'+reply_id+'">삭제</button>'+
                            '   </td>'+
                            '</tr>';
                	}
                	
                	var prevTr = $(this).parent().parent();
                   
                    prevTr.after(reply);
                   	
                    $(this).parent().parent().remove(); 
                  	
                    status = false;
                	
                });
                
                $(document).on("click","button[name='reply_modify_save']", function(){
                	
                	var reply_id = $(this).attr("reply_id");
                	
                    if($("#reply_modify_writer_"+reply_id).val().trim() == ""){
                        alert("이름을 입력하세요.");
                        $("#reply_modify_writer_"+reply_id).focus();
                        return false;
                    }
                     
                    if($("#reply_modify_password_"+reply_id).val().trim() == ""){
                        alert("패스워드를 입력하세요.");
                        $("#reply_modify_password_"+reply_id).focus();
                        return false;
                    }
                     
                    if($("#reply_modify_content_"+reply_id).val().trim() == ""){
                        alert("내용을 입력하세요.");
                        $("#reply_modify_content_"+reply_id).focus();
                        return false;
                    }
                	var reply_content = $("#reply_modify_content_"+reply_id).val().replace("\n", "<br>");
                    
                	var r_type = $(this).attr("r_type");
                	
                	var parent_id;
                	var depth;
                	if(r_type=="main"){
                		parent_id = "0";
                		depth = "0";
                	}else{
                		parent_id = $(this).attr("reply_id");
                		depth = "1";
                	}
                	
                    var objParams = {
                            board_id        : $("#board_id").val(),
                            parent_id       : parent_id, 
                            depth           : depth,
                            reply_writer    : $("#reply_modify_writer_"+reply_id).val(),
                            reply_password  : $("#reply_modify_password_"+reply_id).val(),
                            reply_content   : reply_content
                    };
                    /*
                    $.ajax({
                        url         :   "/board/reply/update",
                        dataType    :   "json",
                        contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                        type        :   "post",
                        async       :   false, //동기: false, 비동기: ture
                        data        :   objParams,
                        success     :   function(retVal){
 
                            if(retVal.code != "OK") {
                                alert(retVal.message);
                            }else{
                                reply_id = retVal.reply_id;
                            }
                             
                        },
                        error       :   function(request, status, error){
                            console.log("AJAX_ERROR");
                        }
                    });
                    */
                    if(r_type=="main"){
                		reply = 
                            '<tr reply_type="main">'+
                            '   <td width="820px">'+
                            $("#reply_modify_content_"+reply_id).val()+
                            '   </td>'+
                            '   <td width="100px">'+
                            $("#reply_modify_writer_"+reply_id).val()+
                            '   </td>'+
                            '   <td width="100px">'+
                            '       <input type="password" id="reply_password_'+reply_id+'" style="width:100px;" maxlength="10" placeholder="패스워드"/>'+
                            '   </td>'+
                            '   <td align="center">'+
                            '       <button name="reply_reply" reply_id = "'+reply_id+'">댓글</button>'+
                            '       <button name="reply_modify" r_type = "main" reply_id = "'+reply_id+'">수정</button>      '+
                            '       <button name="reply_del" reply_id = "'+reply_id+'">삭제</button>      '+
                            '   </td>'+
                            '</tr>';
                	}else{
                		reply = 
                            '<tr reply_type="sub">'+
                            '   <td width="820px"> → '+
                            $("#reply_modify_content_"+reply_id).val()+
                            '   </td>'+
                            '   <td width="100px">'+
                            $("#reply_modify_writer_"+reply_id).val()+
                            '   </td>'+
                            '   <td width="100px">'+
                            '       <input type="password" id="reply_password_'+reply_id+'" style="width:100px;" maxlength="10" placeholder="패스워드"/>'+
                            '   </td>'+
                            '   <td align="center">'+
                            '       <button name="reply_modify" r_type = "sub" reply_id = "'+reply_id+'">수정</button>'+
                            '       <button name="reply_del" reply_id = "'+reply_id+'">삭제</button>'+
                            '   </td>'+
                            '</tr>';
                	}
                    
                    var prevTr = $(this).parent().parent();
                    prevTr.after(reply);
                   	
                    $(this).parent().parent().remove(); 
                  	
                    status = false;
                	
                });
                $(document).on("click","button[name='reply_reply']",function(){
                	
                	if(status){
                		alert("수정과 대댓글은 동시에 불가합니다.");
                		return false;
                	}
                	
                	status = true;
                     
                    $("#reply_add").remove();
                     
                    var reply_id = $(this).attr("reply_id");
                    var last_check = false;
                     
                    var replyEditor = 
                       '<tr id="reply_add" class="reply_reply">'+
                       '   <td width="820px">'+
                       '       <textarea name="reply_reply_content" rows="3" cols="50"></textarea>'+
                       '   </td>'+
                       '   <td width="100px">'+
                       '       <input type="text" name="reply_reply_writer" style="width:100%;" maxlength="10" placeholder="작성자"/>'+
                       '   </td>'+
                       '   <td width="100px">'+
                       '       <input type="password" name="reply_reply_password" style="width:100%;" maxlength="10" placeholder="패스워드"/>'+
                       '   </td>'+
                       '   <td align="center">'+
                       '       <button name="reply_reply_save" reply_id="'+reply_id+'">등록</button>'+
                       '       <button name="reply_reply_cancel">취소</button>'+
                       '   </td>'+
                       '</tr>';
                         
                    var prevTr = $(this).parent().parent().next();
                     
                    if(prevTr.attr("reply_type") == undefined){
                        prevTr = $(this).parent().parent();
                    }else{
                        while(prevTr.attr("reply_type")=="sub"){
                            prevTr = prevTr.next();
                        }
                         
                        if(prevTr.attr("reply_type") == undefined){
                            last_check = true;
                        }else{
                            prevTr = prevTr.prev();
                        }
                         
                    }
                     
                    if(last_check){
                        $('#reply_area tr:last').after(replyEditor);    
                    }else{
                        prevTr.after(replyEditor);
                    }
                     
                });
                 
                $(document).on("click","button[name='reply_reply_save']",function(){
                                         
                    var reply_reply_writer = $("input[name='reply_reply_writer']");
                    var reply_reply_password = $("input[name='reply_reply_password']");
                    var reply_reply_content = $("textarea[name='reply_reply_content']");
                    var reply_reply_content_val = reply_reply_content.val().replace("\n", "<br>"); 
                     
                    if(reply_reply_writer.val().trim() == ""){
                        alert("이름을 입력하세요.");
                        reply_reply_writer.focus();
                        return false;
                    }
                     
                    if(reply_reply_password.val().trim() == ""){
                        alert("패스워드를 입력하세요.");
                        reply_reply_password.focus();
                        return false;
                    }
                     
                    if(reply_reply_content.val().trim() == ""){
                        alert("내용을 입력하세요.");
                        reply_reply_content.focus();
                        return false;
                    }
                     
                    var objParams = {
                            board_id        : $("#board_id").val(),
                            parent_id       : $(this).attr("reply_id"), 
                            depth           : "1",
                            reply_writer    : reply_reply_writer.val(),
                            reply_password  : reply_reply_password.val(),
                            reply_content   : reply_reply_content_val
                    };
                     
                    var reply_id;
                     
                    //ajax 호출
                    /*
                    $.ajax({
                        url         :   "/board/reply/save",
                        dataType    :   "json",
                        contentType :   "application/x-www-form-urlencoded; charset=UTF-8",
                        type        :   "post",
                        async       :   false, //동기: false, 비동기: ture
                        data        :   objParams,
                        success     :   function(retVal){
 
                            if(retVal.code != "OK") {
                                alert(retVal.message);
                            }else{
                                reply_id = retVal.reply_id;
                            }
                             
                        },
                        error       :   function(request, status, error){
                            console.log("AJAX_ERROR");
                        }
                    });
                    */
                    
                    reply_id = reply_count++;
                     
                    var reply = 
                        '<tr reply_type="sub">'+
                        '   <td width="820px"> → '+
                        reply_reply_content_val+
                        '   </td>'+
                        '   <td width="100px">'+
                        reply_reply_writer.val()+
                        '   </td>'+
                        '   <td width="100px">'+
                        '       <input type="password" id="reply_password_'+reply_id+'" style="width:100px;" maxlength="10" placeholder="패스워드"/>'+
                        '   </td>'+
                        '   <td align="center">'+
                        '       <button name="reply_modify" r_type = "sub" reply_id = "'+reply_id+'">수정</button>'+
                        '       <button name="reply_del" reply_id = "'+reply_id+'">삭제</button>'+
                        '   </td>'+
                        '</tr>';
                         
                    var prevTr = $(this).parent().parent().prev();
                     
                    prevTr.after(reply);
                                         
                    $("#reply_add").remove();
                    
                    status = false;
                     
                });
                $(document).on("click","button[name='reply_reply_cancel']",function(){
                    $("#reply_add").remove();
                    
                    status = false;
                });
 });
	
</script>
	
	
</body>
</html>