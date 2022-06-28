<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="listSize" value="${boardDatas.size()}"></c:set>

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
	<c:forEach items="${boardDatas}" var="board" varStatus="rowStatus">
		<tr class="${rowStatus.count%2==0?'color1':'color2'}">
			<td>${rowStatus.count}....${listSize-rowStatus.index}</td>
			<td><a href="${path}/board/boardDetail.do?boardid=${board.bno}">${board.bno}</a></td>
			<td>${board.title }</td>
			<td>${board.content }</td>
			<td>${board.writer }</td>
			<td>${board.regdate }</td>
			<td>${board.updatedate }</td>
			<td><button class="btnDel btn-primary" data-bno="${board.bno}">삭제하기</button></td>
		</tr>
	</c:forEach>
</table>