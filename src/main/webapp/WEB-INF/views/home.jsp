<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
	   var f1 =  function(){
		   console.log("f1", this); //window
	   }
	   var f2 =  ()=> {
		   console.log("f2", this);//window
	   }
	   
	   var f3 = function(){
		    
		   return {
			   title : "f3 title",
			   go : function(){ 
				   console.log("f3", this.title);
			   }
		   };
	   }
	   var obj = { name:"f4의 name"};
	   var f4 = ()=>{
		   console.log(this.name);
	   }
	   f4 = f4.bind(obj);
	   f4();
	   
	    
	   
	</script> 
</head>
<body>
 
<h1>
	Hello world!  ***로그인한 사람: ${sessionScope.user}
</h1>

<P>  The time on the server is ${serverTime}. </P>
<p>나의 이름은 ${myname }</p>
<p>나의 이름은 ${myname2 }</p>
 <div id="here">
 
 </div>
<ul>
<li><a href="/myapp/hello/my1">MyController의 my1로 연결하기</a></li>
<li><a href="${path}/hello/my2">MyController의 my2로 연결하기</a></li>
<li>
   <form action="${path}/hello/my2" method="get">
      <input type="submit" value="Get전송">
   </form>
</li>
<li>
   <form action="${path}/hello/my3" method="post">
      <input type="submit" value="Post전송">
   </form>
</li>
<li>
   <form action="${path}/hello/param.do" method="get">
      <input type="text" name="userid" value="hi"/><br>
      <input type="text" name="userpass" value="1234"/><br>
      <!-- <input type="text" name="email" value="zzilre@naver.com"/><br> -->
      <input type="submit" value="Paramer전송">
   </form>
</li>

<li>
   <form action="${path}/hello/param3.do" method="get">
      <input type="text" name="userid" value="hi"/><br>
      <input type="number" name="userpass" value="1234"/><br>
      <input type="text" name="email" value="zzilre@naver.com"/><br>
      <input type="date" name="birthday" value="2022-06-22">
      <input type="submit" value="Paramer전송해서 받기 ">
   </form>
</li>

<li>
   <form action="${path}/hello/param6.do" method="get">
      <input type="text" name="model" value="SM7"/><br>
      <input type="number" name="price" value="4000"/><br>
      <input type="text" name="color" value="black"/><br>
      <input type="text" name="email" value="zzilre@naver.com"/><br>
      <input type="date" name="birthday" value="2022-06-22">
      <input type="submit" value="DTO로받기">
   </form>
</li>
</ul>

</body>
</html>
