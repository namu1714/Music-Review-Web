<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>muIzik</title>
<script>
	function deleteLink(comment){
		var result = confirm("정말 삭제하시겠습니까?");
		if(result){
			location.href="delete.do?no="+comment;
		}
	}
</script>

<link rel="stylesheet" type="text/css" href="../css/layout.css" />
<style type="text/css">
	.content{padding:30px;}
	table{
		table-layout: fixed;
		border-collapse:collapse;
		width:100%;
		background-color:#fad4e1
	}
	td{
		border:2px solid #cc7a97;
		padding:5px;
		margin:0;
	}
	.info{ width:150px; font-weight:bold}
	.reviewContent{
		box-sizing: border-box;
		border:2px solid #cc7a97;
		padding:15px; 
		height:400px;
		background-color:#fff2f7
	}
	.mod{
		float:right;
		margin:10px 0px 0px 10px;
	}
</style>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="title"><a href="/musicReview/index.do" id="title_text">muIzik</a></div>
		<div id="loginform">
			<u:isLogin>
				<button class="login" onclick="location.href='../logout.do'">logout</button>
			</u:isLogin>
			<u:notLogin>
				<button class="login" onclick="location.href='../login.do'">login</button>
			</u:notLogin>
		</div>
	</div>
	<div id="main">
		<div class="sidebar"></div>
		<div class="content" style="text-align:left">
			<b>${album.artist} - ${album.title} (${album.releaseYear})</b>
			<button style="float:right" onclick="location.href='list.do?album=${album.number}&pageNo=1'">목록</button><br>
			<hr style="border:solid 1px gray; background-color:lightgray"><br>
			
			<table>
				<tr>
					<td class="info">작성자</td>
					<td>${comment.writer.name}</td>
					<td class="info">작성일자</td>
					<td>${regDate}</td>
				</tr>
				<tr>
					<td class="info">like</td>
					<td><font color="red">♥${comment.likes}</font></td>
					<td class="info">수정일자</td>
					<td>${modifiedDate}</td>
				</tr>
			</table>
			<div class="reviewContent">
				<u:pre value='${comment.content}'/>
			</div>
			<c:if test="${user.id == comment.writer.id}">
				<button class="mod" onclick="deleteLink(${comment.number});">삭제</button>
				<button class="mod" onclick="location.href='modify.do?no=${comment.number}'">수정</button>
				
			</c:if>
		</div>
		<div class="sidebar"></div>
	</div>
	<div id="footer">
		<div id="copyright">
		namu1714@naver.com<br>
		2020 muIzik (c) All Rights Reserved
		</div>
	</div>
</div>
</body>
</html>