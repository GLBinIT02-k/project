<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
  border-collapse: collapse;
  width: 60%;
  margin: auto;
  text-align: center;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: #ff0000;
  color: white;
}
#back {
	  position : absolute;
      top : 50px; left: 50px;
}
</style>
</head>
<body>
<a id=back href="index.jsp">뒤로가기</a>

<table>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>작성자</th>
    <th>작성일자</th>
    <th>조회</th> 
  </tr>
			<c:forEach var="ybbsList" items="${ybbsList}">
				<tr>
					<td>${ybbsList.no}</td>
					<td><a href="ybbs_detail?no=${ybbsList.no}">${ybbsList.subject}</a></td>
					<td>${ybbsList.id}</td>
					<td>${ybbsList.wdate}</td>
					<td>${ybbsList.visited}</td>
				</tr>
			</c:forEach>
</table>
</body>
</html>
