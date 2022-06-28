<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

 <script>
 //SPA(Single Page Application)
 
 function call(url, sendData){
	 $.ajax({
		 url:url,
		 data:sendData,
		 type:"get",
		 success:function(responseData){
			alert(responseData);
			$("#here").html(responseData);
		 },
		 fail:function(){
			 
		 }
	 });   
 }
 
 $(function(){
	 $("#titleBtn").click(function(){
		 console.log("#titleBtn click~~"+$("#inputData").val())
		 call("${path}/board/titleSearch.do", {title : $("#inputData").val()}) ; 
	 });
	 $("#writerBtn").click(function(){
		 console.log("#writerBtn click~~")
		 call("${path}/board/writerSearch.do",{writer : $("#inputData").val()})  
		 
	 });
	 $("#dateBtn").click(function(){
		 console.log("#dateBtn click~~")
		 console.log($("#sdate").val());
		 console.log($("#edate").val());
		 call("${path}/board/dateSearch.do",
				 {sdate : $("#sdate").val(), edate : $("#edate").val()}); 
	 });
 });
 </script>
</head>
<body>
<h1>게시판 목록</h1>
 <p>${resultMessage }</p>

<a href="${path}/board/boardInsert.do">게시글 작성하기</a><br>
<input type="text" id="inputData">
<button id="titleBtn">title(like))로 조회</button>
<button id="writerBtn">writer(숫자)로 조회</button> <br>
<input type="date" id="sdate">
<input type="date" id="edate">
<button id="dateBtn">작성일자로 조회</button>

<br><br>
<div id="here">
	<table>
	 <tr>
	   <td>순서</td>
	   <td>번호</td>
	   <td>제목</td>
	   <td>내용</td>
	   <td>작성자</td>
	   <td>작성일</td>
	   <td>수정일</td>
	   <td></td>
	 </tr>
 
	 <c:set var="listSize" value="${boardDatas.size()}" ></c:set>
	    
	 <c:forEach items="${boardDatas}" var="board" varStatus="rowStatus">
	  <tr class="${rowStatus.count%2==0?'color1':'color2'}">
	   <td>${rowStatus.count}....${listSize-rowStatus.index} </td>
	   <td><a href="${path}/board/boardDetail.do?boardid=${board.bno}">${board.bno}</a></td>
	   <td>${board.title }</td>
	   <td>${board.content }</td>
	   <td>${board.writer }</td>
	   <td>${board.regdate }</td>
	   <td>${board.updatedate }</td>
	   <td><button class="btnDel btn-primary" data-bno="${board.bno}" >삭제하기</button></td>
	 </tr>
	 </c:forEach>
	</table>

</div>

<script>
$(function(){
	//# : 아이디를 의미, 아이디는 문서내에서 융ㄹ하다. 
	//.:class를 의미

	$(".btnDel").click(function(){ 
		var bno = $(this).attr("data-bno");
		if(confirm(bno + "삭제?")){
			location.href = "${path}/board/boardDelete.do?bno=" + bno;
		}
	});
});
</script>
</body>
</html>




