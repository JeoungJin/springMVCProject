<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>${title }</h1>
<hr>
<p>Car 모델은 ${car.model} </p>
<p>Car 가격은 ${car.price} </p>
<p>Car 색깔은 ${car.color} </p>
<p>email : ${email }</p>
<p>birthday : ${birthday }</p>
<P>user : ${sessionScope.user }</P>
</body>
</html>