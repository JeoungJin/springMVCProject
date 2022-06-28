<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="${path}/css/common.css">
<style>
  label { display: inline-block; width:100px;}

</style>
</head>
<body>
<h1>Board의 상세내역</h1>
<form action="${path}/board/boardUpdate.do" method="post">
 bno:${board.bno } <input type="hidden" name="bno" value="${board.bno }"><br>
 title:<input type="text" name="title"  value="${board.title }"><br>
 content:<input type="text" name="content"  value="${board.content }"><br>
 작성자:<input type="text" value="${board.writer }" disabled="disabled"><br>
 작성일:<input type="text" value="${board.regdate }"  disabled="disabled"><br>
 수정일:<input type="text" value="${board.updatedate }" readonly="readonly"><br> 
 이미지:
 <a href="${pageContext.request.contextPath}/download.do?fileName=${board.pic}"> 
	 <img alt="" 
	 width="300"
	 height="300"
	 src="${path}/uploads/${board.pic}">
 </a>
 <input type="submit" value="수정">
 <input type="reset" value="취소">
</form>

</body>
</html>