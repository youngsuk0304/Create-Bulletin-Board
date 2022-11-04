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
<title>게시물 작성</title>
</head>
<body>
	<div id="nav">
		<%@ include file="../include/nav.jsp"%>
	</div>
	<form method="post">

		<label>제목</label> 
		${view.board_title}<br /> 
		
		<label>작성자</label> 
		${view.board_writer}<br /> 
		
		<label>내용</label><br />
		${view.board_content}<br />
		 
		<label name="board_regDate"><%= now %></label>

	</form>
	<div>
		<a href="/board/modify?board_no=${view.board_no}">게시물 수정</a>,
		<a href="/board/delete?board_no=${view.board_no}">게시물 삭제</a>

	</div>


</body>
</html>