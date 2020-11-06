<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<%@ page import = "review.model.Album" %>
<%@ page import = "review.service.AlbumPage" %>

<!DOCTYPE html>
<html>
<head>
<title>muIzik</title>
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<style type="text/css">
	#albumlist{
		table-layout:fixed;
		margin:auto
	}
	img{
		width:200px; height:200px;
	}
	td{
		width:200px; height:230px;
		padding:15px;
	}
	.onealbum{
		width:200px;
		overflow: hidden;
  		text-overflow: ellipsis;
  		white-space:nowrap;
	}
	.addAlbum{ 
		position:absolute; right:250px; top:50px; margin:20px;
	}
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
			<form action="index.do" method="GET">
			<input type="text" name="search" value="${inputText}" style="width:450px">
			<input type="submit" value="검색">
			</form>
			<u:isLogin>
				<button class="addAlbum" onclick="location.href='albumUpload.do'">앨범 등록</button>
			</u:isLogin>
			<br>
			
			<!---------------앨범 이미지 정렬---------------->
			<table id="albumlist">
			<% 
			AlbumPage albumPage = (AlbumPage)request.getAttribute("albumPage");
			int albumCount = albumPage.getContent().size();
			for(int i=0;i<3;i++){ %>
			<tr>
			<% 
				for(int j=0;j<3;j++){ 
					int index = i * 3 + j;
					if(index < albumCount){
						Album album = albumPage.getContent().get(index);
						request.setAttribute("album", album);
			%>
						<td><div class="onealbum">
						<img src="${pageContext.request.contextPath}/albumImage/${album.image}"><br>
						<a href = "review/list.do?album=${album.number}&pageNo=1" style="color:black">
						<b>${album.title}</b><br>
						${album.artist}
						</a>
						</div></td>
			<%		} else {	%>
						<td></td>
			<% 
					}
				} 
			%>
			</tr>
			<% } %>
			</table>
			
			<!---------------페이지 탐색---------------->
			<c:if test="${albumPage.hasAlbums()}">
			<tr>
				<td colspan="4">
					<c:if test="${albumPage.startPage > 5}">
						<a href="index.do?search=${search}&pageNo=${albumPage.startPage - 5}">[이전]</a>
					</c:if>
					<c:forEach var="pNo" begin="${albumPage.startPage}" end="${albumPage.endPage}">
						<c:if test="${albumPage.currentPage == pNo}">
							[${pNo}]
						</c:if>
						<c:if test="${albumPage.currentPage != pNo}">
							<a href="index.do?search=${search}&pageNo=${pNo}">[${pNo}]</a>
						</c:if>
					</c:forEach>
					<c:if test="${albumPage.endPage < albumPage.totalPages}">
						<a href="index.do?search=${search}&pageNo=${albumPage.startPage + 5}">[다음]</a>
					</c:if>
				</td>
			</tr>
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