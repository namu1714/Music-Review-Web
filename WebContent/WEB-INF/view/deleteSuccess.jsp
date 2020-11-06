<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>muIzik</title>
<link rel="stylesheet" type="text/css" href="../css/layout.css" />
<style type="text/css">
	.deleteSuccess{
		width:400px; height:250px;
		margin-top:50px;
		display: inline-block;
	}
	#homeButton{
		width:90px; height:40px;
		margin:50px 15px 15px 15px;
		border:0;
	}
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
			<div class="deleteSuccess">
				<p><br>삭제가 성공적으로 완료되었습니다.<br>
				<button id="homeButton" onclick="location.href='list.do?album=${comment.album}&pageNo=1'">목록</button>
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