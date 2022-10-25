<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
	</thead>
 
 <tbody>
	<c:forEach items="${list}" var="list">
		<tr>
			<td>${list.board_no}</td>
			<td>
			    <a href="/board/view?board_no=${list.board_no}">${list.board_title}</a>
			</td>
			<td>${list.board_regDate}</td>
			<td>${list.board_writer}</td>
			<td>${list.board_readCount}</td>
		</tr>
	</c:forEach>
 </tbody>

</table>
	
</body>
</html>