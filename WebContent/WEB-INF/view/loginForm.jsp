<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>muIzik:로그인</title>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<style type="text/css">
	.loginBlock{
		width:400px; height:280px;
		border: 1px solid lightgray;
		margin-top:100px;
		display: inline-block;
	}
	.loginBlock form{
		margin-top:70px;
	}
	#loginButton,#joinButton{
		width:90px; height:50px;
		margin:10px;
		border-radius:10px;
		border:0;
	}
	#loginButton:hover {color:white; background-color:salmon;}
	#joinButton:hover{color:white; background-color:skyblue;}
</style>	
</head>

<body>
<div id="wrapper">

<div id="header">
	<div id="title"><a href="index.do" id="title_text">muIzik</a></div>
</div>

<div id="main">
	<div class="sidebar"></div>
	<div class="content">
		<div class="loginBlock">
		<form action="login.do" method="post"> 
			<p>아이디: <input type="text" name="id" value="${param.id}"><br></p>
			<p>비밀번호: <input type="password" name="password"><br></p>
		<input id="loginButton" type="submit" value="로그인">
		<button id="joinButton" onclick="location.href='join.do'">회원가입</button>
		</form>
		</div>
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

<c:if test="${errors.idOrPwNotMatch || errors.id || errors.password}">
	<script>
		alert("일치하는 회원정보를 찾을 수 없습니다");
	</script>
</c:if>

</body>
</html>