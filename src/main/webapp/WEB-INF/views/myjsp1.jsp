<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
//권장되지않음
String mm = (String)request.getAttribute("major");
%>
</head>
<body>
<p>${major }</p>
<p>${phone }</p>
<p>${car.model }</p>
<p>${car.price }</p>
<p>${car.color }</p>


<%-- 권장되지않음 --%>
<p><%=request.getAttribute("major") %></p>
<P><%=mm %></P>



</body>
</html>