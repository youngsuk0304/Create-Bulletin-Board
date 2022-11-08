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

		<label>제목</label> ${view.board_title}<br /> <label>작성자</label>
		${view.board_writer}<br /> <label>내용</label><br />
		${view.board_content}<br /> <label name="board_regDate"><%=now%></label>

	</form>
	<div>
		<a href="/board/modify?board_no=${view.board_no}">게시물 수정</a>, <a
			href="/board/delete?board_no=${view.board_no}">게시물 삭제</a>

	</div>
	<!-- 댓글 시작 -->
	<hr />
	<ul>
		<!--  <li>
			<div>
				<p>첫번째 댓글 작성자</p>
				<p>첫번째 댓글</p>
			</div>
		</li>
		<li>
			<div>
				<p>두번째 댓글 작성자</p>
				<p>두번째 댓글</p>
			</div>
		</li>
		<li>
			<div>
				<p>세번째 댓글 작성자</p>
				<p>세번째 댓글</p>
			</div>
		</li>-->
		<c:forEach items="${reply}" var="reply">
			<li>
			    <div>
			        <p>${reply.re_writer} / ${reply.re_regDate}</p>
			        <p>${reply.re_content }</p>
			    </div>
			</li>    
		</c:forEach>
	</ul>
	

	<div>
		<form method="post" action="/reply/write">
		
			<p>
				<label>댓글 작성자</label> <input type="text" name="re_writer">
			</p>
			<p>
				<textarea rows="5" cols="50" name="re_content"></textarea>
			</p>
			<p>
				<input type="hidden" name="brd_no" value="${view.board_no}">
				<button type="submit">댓글 작성</button>
			</p>
		</form>
	</div>
	<!-- 댓글 끝 -->

</body>
</html>