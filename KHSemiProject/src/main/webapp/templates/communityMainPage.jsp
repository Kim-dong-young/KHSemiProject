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
					<div class="hot-bulletin">
						<img src="../static/img/fire-icon.png">
						<span>실시간 인기글</span>
						<div class="hot-bulletin-list">
							<table>
								<tr>
									<td class="tab">잡담</td>
									<td class="title">테스트용 제목</td>
									<td class="author">Test1</td>
									<td class="comment-num">[1]<img src="../static/img/comment-icon.png"></td>
								</tr>
	
								<tr>
									<td class="tab">잡담</td>
									<td class="title">테스트용 제목</td>
									<td class="author">Test1</td>
									<td class="comment-num">[1]<img src="../static/img/comment-icon.png"></td>
								</tr>
	
								<tr>
									<td class="tab">잡담</td>
									<td class="title">테스트용 제목</td>
									<td class="author">Test1</td>
									<td class="comment-num">[1]<img src="../static/img/comment-icon.png"></td>
								</tr>
	
								<tr>
									<td class="tab">잡담</td>
									<td class="title">테스트용 제목</td>
									<td class="author">Test1</td>
									<td class="comment-num">[1]<img src="../static/img/comment-icon.png"></td>
								</tr>
															
								<tr>
									<td class="tab">잡담</td>
									<td class="title">테스트용 제목</td>
									<td class="author">Test1</td>
									<td class="comment-num">[1]<img src="../static/img/comment-icon.png"></td>
								</tr>
							</table>
						</div>
					</div>
	
					<div class="cm-banner">
						<button><img src="../static/img/x-icon-white.png"></button>
						<img src="../static/img/logo.png">
					</div>
				</div>
			</div>

		</div>
    </div>
</body>
</html>