<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
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
  table, tr , td{ border: 1px solid black; border-collapse: collapse;}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
 
 $(function(){
	 $("#btnRetrieveAll").click(f1);
	 $("#btnRetrieveById").click(f2);
	 $("#btnRetrieveByManager").click(f3);
	 $("#btnRetrieveByManagerEmp").click(f4);
	 $("#btnRetrieveByEmail").click(f5);
	 $("#btnRetrieveByJobAll").click(f6);
	 $("#btnRetrieveByJobEmp").click(f7);
	 $("#btnRetrieveByDept").click(f8);
	 $("#btnRetrieveByCondition").click(f9); 
	 $("#btnInsert").click(insertFunction); 
	 $("#btnUpdate").click(updateFunction); 
	 $("#btnDelete").click(deleteFunction); 
 });
 
 function deleteFunction(){
	    var empid = $("input[name='employee_id']").val();
	    alert(empid);
		$.ajax({
			url:"${path}/emp/empDelete.do/"+empid,
			type:"delete",
			success:function(responseData){
				alert(responseData + "건 삭제")
			}
		}); 	 
}
 
 
 function makeJson(){
	//var s =  $("#myfrm").serialize(); //employee_id=100&first_name=kim&.......
	var arr =  $("#myfrm").serializeArray();//[{name:"employee_id", value:100},{},{}]
	//json => '{ "employee_id":100,  first_name:"kim" }' 
	var obj = {};
	$.each(arr, function(index, item){
		obj[item.name] = item.value;
	});
	return JSON.stringify(obj);
 }
 
 function updateFunction(){
	 
		$.ajax({
			url:"${path}/emp/empUpdate.do",
			type:"put",
			data: makeJson(),
			contentType : "application/json;charset=utf-8",
			success:function(responseData){
				alert(responseData + "건 수정")
			}
		}); 	 
 }
 function insertFunction(){
	$.ajax({
		url:"${path}/emp/empInsert.do",
		type:"post",
		data: makeJson(),
		contentType : "application/json;charset=utf-8",
		success:function(responseData){
			alert(responseData + "건 입력")
		}
	}); 
	 
 }
 
 function f9(){
	 var deptid =  $("#inputData").val() ;
	 var jobid =  $("#inputData2").val() ;
	 var sal =  $("#inputData3").val() ;
	 var sdate =  $("#inputData4").val() ;
	 $.ajax({
		 url:`${path}/emp/empByCondition.do/\${deptid}/\${jobid}/\${sal}/\${sdate}`,
		 success: function(responseData){
			 printEmp(responseData);
		 }
	 });
 }
 
 function f8(){
	 var deptid =  $("#inputData").val() ;
	 $.ajax({
		 url:"${path}/emp/empByDept.do/" + deptid,
		 success: function(responseData){
			 printEmp(responseData);
		 }
	 });
 }
 
 function f7(){
	 var job =  $("#inputData").val() ;
	 $.ajax({
		 url:"${path}/emp/empByJob.do/" + job,
		 success: function(responseData){
			 printEmp(responseData);
		 }
	 });
 }
 
 function f6(){
	 
	 $.ajax({
		 url:"${path}/emp/empByJobAll.do",
		 success: function(joblist){
			 var output = "<ul>";
		 
			 $.each(joblist, function(index, item){
				 output += "<li>";
				 for(var prop in item){
					 output += "  " + prop + "==>" + item[prop]  
				 }
				 output += "</li>";
			 });

			 $("#here").html(output + "</ul>");
			 
		 }
	 });
 }
 
 function f5(){
	 var email =  $("#inputData").val() ;
	 $.ajax({
		 url:"${path}/emp/empByEmail.do/" + email,
		 success: function(responseData){
			 $("#here").html(responseData);
		 }
	 });
 }
 
 function f4(){
	 var mid =  $("#inputData").val() ;
	 $.ajax({
		 url:"${path}/emp/empByManager.do/" + mid,
		 success: function(responseData){
			 printEmp(responseData);
		 }
	 });
 }
 
 function f3(){
	 $.ajax({
		 url:"${path}/emp/empByManagerAll.do",
		 success: function(responseData){
			 printEmp(responseData);
		 }
	 });
 }
 function f2(empid){
	 if(typeof(empid)!="number") empid = $("#inputData").val() 
	 $("#here").html("");
	 $.ajax({
		 url:"${path}/emp/empdetail.do/"+ empid,
		 success: function(responseData){
			 printEmpOne(responseData);
		 }
	 });
 }
 function f1(){
	 $.ajax({
		 url:"${path}/emp/emplist.do",
		 success: function(responseData){
			 printEmp(responseData);
		 }
	 });
 }
 function printEmpOne(item){
	 //일자, select tag의 selected설정 
	 $("input[name='hire_date']").val(new Date(item.hire_date).toJSON().split('T')[0]);
	 $("select[name='department_id']").val(item.department_id).prop("selected", true); 
	 $("select[name='manager_id']").val(item.manager_id).prop("selected", true);
	 $("select[name='job_id']").val(item.job_id).prop("selected", true);	 
	 $("input[name='employee_id']").val(item["employee_id"]);
	 $("input[name='first_name']").val(item["first_name"]);
	 $("input[name='last_name']").val(item["last_name"]);
	 $("input[name='email']").val(item["email"]);
	 $("input[name='phone_number']").val(item["phone_number"]);
	 $("input[name='commission_pct']").val(item["commission_pct"]);

	 $("input[name='salary']").val(item["salary"]);
	 
 
 }
 function printEmp(emplist){
	 var output = `
		 <table>
		 <tr>
		   <td>순서</td>
		   <td>상세보기</td>
		   <td>직원번호</td>
		   <td>성</td>
		   <td>이름</td>
		   <td>입사일</td>
		   <td>급여</td>
		   <td>전화번호</td>
		   <td>부서</td>
		   <td>커미션</td>
		   <td>메니져</td>
		   <td>직책</td>
		   <td>이메일</td>
		   <td></td>
		</tr>
	 `;
	 //console.log(emplist[0].employee_id);
	 $.each(emplist, function(index, item){	 
		 output += `
			   <tr>
			   <td>\${index}</td>
			   <td><button onclick="f2(\${item.employee_id})" >상세보기</button></td>
			   <td>\${item.employee_id}</td>
			   <td>\${item.first_name}</td>
			   <td>\${item.last_name}</td>
			   <td>\${item.hire_date}</td>
			   <td>\${item.salary}</td>
			   <td>\${item.phone_number}</td>
			   <td>\${item.department_id}</td>
			   <td>\${item.commission_pct}</td>
			   <td>\${item.manager_id}</td>
			   <td>\${item.job_id}</td>
			   <td>\${item.email}</td>
			   <td></td>
			</tr>
		 `;
	 });
	 $("#here").html(output + "</table>");
 }
</script>
</head>
<body>
<h1>RestController연습</h1>
<img alt="이미지" src="${path}/images/symbol.gif">
<hr>
조회조건1(공통):<input type="text" id="inputData" value="60"><br>
조회조건2(jobid):<input type="text" id="inputData2" value="IT_PROG"><br>
조회조건3(sal):<input type="number" id="inputData3" value="0"><br>
조회조건4(sdate):<input type="date" id="inputData4" value="2007-01-01"><br>

<button id="btnRetrieveAll">모든직원조회</button>
<button id="btnRetrieveById">특정직원조회</button>
<button id="btnRetrieveByManager">모든메니져조회</button>
<button id="btnRetrieveByManagerEmp">특정메니져의 부하직원조회</button>
<button id="btnRetrieveByEmail">Email해당직원존재?</button>
<button id="btnRetrieveByJobAll">모든 JOB</button>
<button id="btnRetrieveByJobEmp">특정 JOB직원</button>
<button id="btnRetrieveByDept">특정 DEPT직원</button>
<button id="btnRetrieveByCondition">조건조회</button>
<button id="btnInsert">입력</button>
<button id="btnUpdate">수정</button>
<button id="btnDelete">삭제</button>

<hr>
<div id="here">
</div>
<hr>
<h1>직원 신규등록/수정</h1>
<form id="myfrm"  >
<div class="form-group">
   <label>직원번호</label>
   <input  class="form-control" type="number" name="employee_id"  id="employee_id"> 
 
</div>

<div class="form-group">
<label>first name</label>
<input class="form-control" type="text" name="first_name" > 
</div>

<div class="form-group">
<label>last name</label>
<input class="form-control" type="text" name="last_name" >  
</div>

<div class="form-group">
<label>이메일</label>
<input class="form-control" type="text" name="email"  id="email"> 
 
</div>


<div class="form-group">
<label>전화번호</label>
<input class="form-control" type="text" name="phone_number"  > 
</div>

<div class="form-group">
<label>커미션</label>
<input class="form-control" type="text" name="commission_pct"  > 
</div>

<div class="form-group">
<label>메니져</label>
<select name="manager_id" class="form-control">
  <c:forEach items="${mlist}" var="m">
    <option value="${m.employee_id}" >${m.first_name}</option>
  </c:forEach>
</select> 
</div>



<div class="form-group">
<label>부서</label>
<select name="department_id" class="form-control">
  <c:forEach items="${dlist}" var="dept">
    <option value="${dept.department_id}">${dept.department_name}부서</option>
  </c:forEach>
</select>
</div>

<div class="form-group" >
<label>직책</label>
<select name="job_id" class="form-control">
  <c:forEach items="${jlist}" var="job">
    <option value="${job.job_id }">${job.job_id }</option>
  </c:forEach>
</select>
</div>


<div class="form-group">
<label>급여</label>
<input class="form-control" type="text" name="salary"  > 
</div>

<div class="form-group">
<label>입사일</label>
<input class="form-control" type="date" name="hire_date"  > 
</div>
</form>
</body>
</html>