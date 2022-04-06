<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script type='text/javascript'>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Kakao.init('23d3236e95d78f95d78a5ca69eb39dc6');
$("#btnLogout")
.on("click",function() {
Kakao.API.request({
                url: '/v1/user/unlink',
                success: function(response) {
                    console.log(response);
                    window.location.href='${ pageContext.request.contextPath }/main.do'
                },
                fail: function(error) {
                    console.log('탈퇴 미완료')
                    console.log(error);
                },
            });
        })

</script> --%>
 <%
 			session.setAttribute("id", "seoyoung");
			response.sendRedirect("http://localhost:9999/fiveand-web-project/main.jsp");%>

</body>
</html>