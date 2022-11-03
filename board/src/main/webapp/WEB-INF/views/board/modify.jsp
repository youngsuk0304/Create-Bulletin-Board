<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%
Date now = new Date();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
</head>
<body>
	<div id="nav">
		<%@ include file="../include/nav.jsp"%>
	</div>
	<form method="post">

		<label>제목</label> <input type="text" name="board_title"
			value="${view.board_title}" /><br /> <label>작성자</label> <input
			type="text" name="board_writer" value="${view.board_writer}" /><br />

		<label>내용</label>
		<textarea cols="50" rows="5" name="board_content">${view.board_content}</textarea>
		<br /> <label name="board_regDate"><%=now%></label>

		<button type="submit">완료</button>

	</form>
</body>
</html>