<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>muIzik</title>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<style type="text/css">
	table{	
		text-align:left;
		margin:auto;
	}
	td{padding:10px;}
	
</style>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="title"><a href="index.do" id="title_text">muIzik</a></div>
		<div id="loginform">
			<u:isLogin>
				<button class="login" onclick="location.href='logout.do'">logout</button>
			</u:isLogin>
			<u:notLogin>
				<button class="login" onclick="location.href='login.do'">login</button>
			</u:notLogin>
		</div>
	</div>
	<div id="main">
		<div class="sidebar"></div>
		<div class="content">
			<p style="font-size:20px; font-color:gray;text-align:left;"><b>&nbsp;&nbsp;앨범 업로드</b></p><br>
			<form action="albumUpload.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td>아티스트명:</td> 
					<td><input type="text" name="artist" value="${addreq.artist}"></td>
				<tr>
				<tr>
					<td>앨범 타이틀:</td>
					<td><input type="text" name="title" value="${addreq.title}" ></td>
				<tr>
					<td>발매년도:</td> 
					<td><input type="text" name="releaseYear" value="${addreq.releaseYear}"></td>
				</tr>
				<tr>
					<td>커버 이미지:</td> 
					<td><input type="file" name="coverImage"></td>
       			<tr>
       				<td colspan="2" style="text-align:center"><input type="submit" value="확인"></td>
       			</tr>
       			</table>
    		</form>
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