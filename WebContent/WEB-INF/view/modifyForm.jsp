<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>muIzik</title>
<link rel="stylesheet" type="text/css" href="../css/layout.css"/>
<style type="text/css">

</style>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="title"><a href="/musicReview/index.do" id="title_text">muIzik</a></div>
	</div>
	<div id="main">
		<div class="sidebar"></div>
		<div class="content">
			<div style="text-align:left; margin-left:30px; margin-top:15px;">
				<font size="5"><b>코멘트 수정</b></font><br><br>
				${album.artist} - ${album.title} (${album.releaseYear})
			</div>
			<form action="modify.do?no=${modReq.commentNumber}" method="post">
				<div style="margin:10px 30px 10px 30px">
					<textarea name="content" rows="20" style="width:100%">${modReq.content}</textarea>
				</div>
				<input type="submit" value="확인">
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