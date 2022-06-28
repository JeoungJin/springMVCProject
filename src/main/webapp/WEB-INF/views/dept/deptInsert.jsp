<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- <link rel="stylesheet" href="../css/common.css"> -->
<style>
  label { display: inline-block; width:100px;}

</style>
</head>
<body>
<h1>부서등록</h1>
<c:set var="path" value="${pageContext.request.contextPath }" />
<form action="${path}/dept/deptInsert.do" method="post">
 부서번호:<input type="number" name="department_id" ><br>
 부서이름:<input type="text" name="department_name" ><br>
 메니져번호:<input type="number" name="manager_id" value="100"><br>
 지역번호:<input type="number" name="location_id" value="1700"><br>
 <input type="submit" value="입력">
 <input type="reset" value="취소">
</form>
</body>
</html>