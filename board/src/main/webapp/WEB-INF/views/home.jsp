<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<P> board_no is ${board_no}</P>
<P> board_title is ${board_title}</P>
<P> board_content is ${board_content}</P>
<P> board_writer is ${board_writer}</P>
<p><a href="/board/list">게시물 목록</a></p>

</body>
</html>
