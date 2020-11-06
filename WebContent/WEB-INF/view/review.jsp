<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
<title>muIzik</title>
<link rel="stylesheet" type="text/css" href="../css/layout.css"/>
<style type="text/css">
	table{
		margin:30px 50px 20px 50px;	
		text-align:left;
	}
	.albumInfo{ border:solid 3px lightgray; }
	.comment{
		table-layout:fixed; 
		background-color: #fad4e1
	}
	td{
		vertical-align:top;
		padding:10px;
	}
	.reviewContent{
		height:110px;
		text-overflow:ellipsis;
		overflow:hidden;
	}
	
	img{ width:200px; height:200px }
	.writeComment{width:100px;}
	.readComment{
		margin-top:10px;
		padding:5px;
		background-color:white;
		border: 0;
	}
	.readComment:hover{background-color:whiteSmoke;}
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
		<div class="content">
		    <!---------------앨범 정보---------------->
			<table class="albumInfo">
				<tr>
				<td><img src = "${pageContext.request.contextPath}/albumImage/${review.album.image}"/></td>
				<td style="width:100%;">
					<font size="5"><b>${review.album.title}</b></font><br>
					${review.album.releaseYear}<br>
					<b>${review.album.artist}</b>
				</td>
				</tr>
			</table>
			<u:isLogin>
				<button class="writeComment" onclick="location.href='write.do?album=${review.album.number}'">리뷰 작성</button>
			</u:isLogin>
			
			<!---------------앨범 코멘트---------------->
			<br><br>
			<c:if test="${review.hasNoComments()}">게시글이 없습니다.</c:if>

			<c:forEach var="comment" items="${review.content}">
				<table class="comment">
					<tr>
						<td style="width:100%"><b>${comment.writer.name}</b></td>
						<td style="text-align:right;width:100px"><font color="red">♥${comment.likes}</font></td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="reviewContent"><u:pre value='${comment.content}'/></div>
							<div style="text-align:right">
								<button class="readComment" onclick="location.href='read.do?no=${comment.number}'">전체 보기</button>
							</div>
						</td>
					</tr>
				</table>
			</c:forEach>
			
			<!---------------페이지 탐색---------------->
			<c:if test="${review.hasComments()}">
			<tr>
				<td colspan="4">
					<c:if test="${review.startPage > 5}">
						<a href="list.do?album=${review.album.number}&pageNo=${review.startPage - 5}">[이전]</a>
					</c:if>
					<c:forEach var="pNo" begin="${review.startPage}" end="${review.endPage}">
						<c:if test="${review.currentPage == pNo}">
							[${pNo}]
						</c:if>
						<c:if test="${review.currentPage != pNo}">
							<a href="list.do?album=${review.album.number}&pageNo=${pNo}">[${pNo}]</a>
						</c:if>
					</c:forEach>
					<c:if test="${review.endPage < review.totalPages}">
						<a href="list.do?album=${review.album.number}&pageNo=${review.startPage + 5}">[다음]</a>
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