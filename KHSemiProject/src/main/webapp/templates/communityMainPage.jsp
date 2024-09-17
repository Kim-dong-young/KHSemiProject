<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<link rel="stylesheet" href="../static/css/communityMainPage.css">

</head>
<body>
	<%@ include file="common/menu.jsp" %>
	
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
		<p>자유 게시판</p>

		<div class="wrapper">
			<div class="board">
				<%@ include file="communityBoardPage.jsp" %>
				
				<div class="board-side">
					<%@ include file="communitySidePage.jsp" %>
				</div>
			</div>

		</div>
    </div>
</body>
</html>