<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="reply.do?id=${param.id }" method="post">
		<section>
		<div align = "center">
		<hr>
		<table border="1">
			<tr>
				<th width="25%">번호</th>
				<td>${param.no }</td>
			</tr>
			<tr>
				<th width="25%">제목</th>
				<td id="input"><input type="text" name="title" style="width: 728px; height: 92px;"></td>
			</tr>
			<tr>
				<th width="25%">작성자</th>
				<td>${param.id }</td>
			</tr>
			<tr>
				<th width="25%">내용</th>
				<td id="input"><textarea rows="7" cols="80" size="100%" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button type="submit">답글쓰기</button></td>
			</tr>
		</table>
	</form>
</body>
</html>