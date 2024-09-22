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
                <div class="bulletin-content">
					<div class="bulletin-title">
                        <span class="board-tab">잡담</span>
                        <span>탕수육 부먹 vs 찍먹 뭐가 더 낳나요?</span>
                        <button><img src="../static/img/trash-icon.png">삭제</button>
					</div>

                    <div class="bulletin-info">
                        <div class="author-info">
                            <img src="../static/img/test.png">
                            <span>민트초코가좋아</span>
                        </div>

                        <div class="data-info">
                            <span class="after-vline">작성일: 2024-09-06 12:15</span>
                            <span class="after-vline">조회수: 346</span>
                            <span class="after-vline">좋아요: 2</span>
                            <span>댓글: 3</span>
                        </div>
                    </div>

                    <div class="bulletin-input">
                        <div class="image-input">
                            <img src="../static/img/test.png">
                        </div>
                        <div class="image-input">
                            <img src="../static/img/test2.jpg">
                        </div>
                        <p>부먹 VS 찍먹</p>
                        <p>댓글 ㄱㄱ</p>
                    </div>

                    <div class="bulletin-option">
                        <button class="like-button"><img src="../static/img/thumbup-icon.png">좋아요</button>
                        <button class="report-button"><img src="../static/img/flag-icon.png">신고</button>
                    </div>
                </div>

                <div class="bulletin-comment">
                    <div class="comment">
                        <div class="comment-left">
                            <img src="../static/img/test.png">
                        </div>

                        <div class="comment-right">
                            <div class="user-info">
                                <span>스타레일고수가될거야</span>
                                <span>Lv.35</span>
                            </div>
                            <div class="comment-content">
                                <span>탕수육은 찍먹이 근본이지</span>
                            </div>
                            <div class="comment-option">
                                <button class="after-vline">답글</button>
                                <button class="after-vline">신고</button>
                                <button>삭제</button>
                            </div>
                        </div>
                    </div>

                    <div class="comment">
                        <div class="comment-left">
                            <img src="../static/img/test.png">
                        </div>

                        <div class="comment-right">
                            <div class="user-info">
                                <span>스타레일고수가될거야</span>
                                <span>Lv.35</span>
                            </div>
                            <div class="comment-content">
                                <span>탕수육은 찍먹이 근본이지</span>
                            </div>
                            <div class="comment-option">
                                <button class="after-vline">답글</button>
                                <button class="after-vline">신고</button>
                                <button>삭제</button>
                            </div>
                        </div>
                    </div>

                    <div class="comment">
                        <div class="comment-left">
                            <img src="../static/img/test.png">
                        </div>

                        <div class="comment-right">
                            <div class="user-info">
                                <span>스타레일고수가될거야</span>
                                <span>Lv.35</span>
                            </div>
                            <div class="comment-content">
                                <span>탕수육은 찍먹이 근본이지</span>
                            </div>
                            <div class="comment-option">
                                <button class="after-vline">답글</button>
                                <button class="after-vline">신고</button>
                                <button>삭제</button>
                            </div>
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

                <div class="comment-write">
                    <textarea placeholder="댓글은 자신의 얼굴을 비추는 거울입니다."></textarea>
                    <button><img src="../static/img/comment-icon.png">작성</button>
                </div>
                
                <%@ include file="communityBoardPage.jsp" %>
                
                <div class="board-side">
                    <%@ include file="communitySidePage.jsp" %>
                </div>
			</div>
		</div>
    </div>
</body>
</html>