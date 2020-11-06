<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>muIzik:회원가입</title>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<style type="text/css">
	table{
		margin-top:25px;
		margin-left:100px;
	}
	td{
		text-align:left;
		margin:0;
		padding:20px;
		border-bottom:1px solid gray;
	}
	.input{width:500px;}
	
	.joinButton{
		width:200px;
		height:50px;
		margin:50px;
		border-radius:10px;
		border:0;
	}
	.joinButton:hover{color:white; background-color:skyblue;}
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
	
	<form action="join.do" method="post">
	<table>
	<tr>
	<td colspan="2"><p style="font-size:20px; font-color:gray;text-align:left;"><b>회원가입</b></p></td>
	</tr>

	<tr>
		<td><font color="red">*</font>아이디(16자 이하)</td>
		<td class="input">
			<input type="text" name="id" value="${param.id}">&nbsp;
			<c:if test="${errors.id}"><font color="red">ID를 입력하세요</font></c:if>
			<c:if test="${errors.idLen}"><font color="red">ID를 입력하세요</font></c:if>
			<c:if test="${errors.duplicateId}"><font color="red">이미 사용중인 아이디입니다.</font></c:if>
		</td>
	</tr>
	<tr>
		<td><font color="red">*</font>닉네임(10자 이하)</td> 
		<td class="input">
			<input type="text" name="name" value="${param.name}">&nbsp;
			<c:if test="${errors.name}"><font color="red">이름을 입력하세요 </font></c:if>
		</td>
	</tr>
	<tr>
		<td><font color="red">*</font>e-mail</td>
		<td class="input">
			<input type="text" name="email" value="${param.email}">&nbsp;
			<c:if test="${errors.email}"><font color="red">이메일을 입력하세요</font> </c:if>
		</td>
	</tr>
	<tr>
		<td><font color="red">*</font>비밀번호(16자 이하)</td> 
		<td class="input">
			<input type="password" name="password">&nbsp;
			<c:if test="${errors.password}"><font color="red">암호를 입력하세요 </font></c:if>
		</td>
	<tr>
	<tr>
		<td><font color="red">*</font>비밀번호 확인</td>
		<td class="input">
			 <input type="password" name="confirmPassword">&nbsp;
			<c:if test="${errors.confirmPassword}"><font color="red">확인을 입력하세요 </font></c:if>
			<c:if test="${errors.notMatch}"><font color="red">암호와 확인이 일치하지 않습니다.</font></c:if>
		</td>
	</tr>
	</table>
		<input class="joinButton" type="submit" value="가입하기">
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