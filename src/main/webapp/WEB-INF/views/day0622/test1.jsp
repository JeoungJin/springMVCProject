<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>/spring/test1.do요청</h1>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<form action="${path}/spring/test2.do" method="post">
아이디 : <input type="text" name="userid" value="admin"><br>
비밀번호 : <input type="text" name="userpass" value="1234"><br>
<input type="submit" value="로그인">
</form>
</body>
</html>