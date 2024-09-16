<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<link rel="stylesheet" href="../static/css/communityViewPage.css">

</head>
<body>
	<%@ include file="common/menu.jsp" %>
	
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
		<p>자유 게시판</p>

        <div class="wrapper">
			<div class="board">
                <div class="board-content">
					<div class="board-title">
                        <span>잡담</span>
                        <span>탕수육 부먹 vs 찍먹 뭐가 더 낳나요?</span>
                        <button>삭제</button>
					</div>

                    <div class="board-info">
                        <img src="../static/img/test.png">
                        <span>민트초코가좋아</span>
                        <span>작성일: 2024-09-06 12:15</span>
                        <span>조회수: 346</span>
                        <span>좋아요: 2</span>
                        <span>댓글: 3</span>
                    </div>

                    <div class="board-main">
                        <img src="../static/img/test.png">
                        <span>
                            <br>부먹 VS 찍먹<br>
                            댓글 ㄱㄱ
                        </span>
                    </div>

                    <div class="board-option">
                        <button>좋아요</button>
                        <button>싫어요</button>
                    </div>
                </div>

                <div class="board-comment">
                    <div class="comment">
                        <div class="comment-left">
                            <img src="../static/img/test.png">
                        </div>

                        <div class="comment-right">
                            <div class="user-info">
                                <span>아이디</span>
                                <span>레벨</span>
                            </div>
                            <div class="comment-content">
                                <span>댓글 내용 입력</span>
                            </div>
                            <div class="comment-option">
                                <button>답글</button>
                                <button>신고</button>
                                <button>삭제</button>
                            </div>
                        </div>
                    </div>

                    <div class="comment-page">
                        <button>&lt;&lt;</button>
                        <button>&lt;</button>
                        <button style="background-color: #FF9139;">1</button>
                        <button>2</button>
                        <button>3</button>
                        <button>4</button>
                        <button>5</button>
                        <button>6</button>
                        <button>7</button>
                        <button>8</button>
                        <button>9</button>
                        <button>&gt;</button>
                        <button>&gt;&gt;</button>
                    </div>
                </div>

                <div class="comment-write">
                    <textarea></textarea>
                    <button>작성</button>
                </div>
                
			</div>

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
</body>
</html>