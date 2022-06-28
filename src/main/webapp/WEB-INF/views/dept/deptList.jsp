<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
</head>
<body>
<h1>부서목록</h1>
<c:set var="path" value="${pageContext.request.contextPath }" />
<a href="${path}/dept/deptInsert.do">신규 부서 등록</a>

<hr>

${resultMessage }
<hr> 

<br><br>
<table>
<tr>
  <td></td>
  <td>부서번호</td>
  <td>부서이름</td>
  <td>메니져</td>
  <td>지역번호</td>
  <td></td>
</tr>
<c:forEach items="${deptlist}" var="dept" varStatus="rowStatus">
<tr>
  <td>${rowStatus.count%2==0?'짝수':"홀수"}</td>
  <td><a href="${path}/dept/deptUpdate.do?deptid=${dept.department_id}">${dept.department_id}</a></td>
  <td><a href="${path}/dept/deptUpdate.do?deptid=${dept.department_id}">${dept.department_name}</a></td>
  <td>${dept.manager_id}</td>
  <td>${dept.location_id}</td>
  <td><button class="btnDel" data-deptid="${dept.department_id}">삭제하기</button></td>
</tr>

</c:forEach>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function(){
	
	var resultMessage = "${resultMessage}";
	if(resultMessage!=="") alert(resultMessage);
	
	$(".btnDel").on("click", f);
});
function f(e){
	//debugger;
	var deptid = $(this).attr("data-deptid");
	location.href = "${path}/dept/deptDelete.do?deptid=" + deptid;
}
</script>

<script>
 
const dog = { name: 'aa', age: 10 };
const { name, age } = dog;
console.log(name, age);  
 
</script>
</body>
</html>




