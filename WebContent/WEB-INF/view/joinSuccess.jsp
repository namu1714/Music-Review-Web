<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>muIzik</title>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<style type="text/css">
	.joinSuccess{
		width:400px; height:250px;
		margin-top:50px;
		display: inline-block;
	}
	#loginButton,#homeButton{
		width:90px; height:40px;
		margin:50px 15px 15px 15px;
		border:0;
	}
	#loginButton {color:white; background-color:#FFA07A;}
	#homeButton {color:white; background-color:#FFB6C1;}
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
			<div class="joinSuccess">
				<p><br>가입이 완료되었습니다.<br>
				<button id="loginButton" onclick="location.href='login.do'">로그인</button>
				<button id="homeButton" onclick="location.href='main.do'">홈으로</button>
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
</body>
</html>