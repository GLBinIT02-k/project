<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
</head>
<body>
	
	<ul>
		<li><a href="login_input">로그인</a></li>
	</ul>		
	
	<c:if test="${member != null}">
		<form action= "logout">
			<input type = "submit" value="로그아웃"/>
		</form>
	</c:if>
	
	<h2>필터테스트</h2>
	<ul>
		<li><a href="public">누구나 볼 수 있는 페이지 요청</a></li>
		<li><a href="private.do">로그인한 사람만 볼 수 있는 페이지 요청</a></li>
		<li><a href="private/test.do">로그인한 사람만 볼 수 있는 페이지2 요청</a></li>
	</ul>
	
	<a href="ybbs_insert.do">답변게시판-글쓰기로 이동</a><br />
	<a href="ybbs_list">게시판 리스트 가기</a>
	<p>${message}</p>
</body>
</html>