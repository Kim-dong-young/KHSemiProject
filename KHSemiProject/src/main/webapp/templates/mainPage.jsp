<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../static/css/mainPageContent.css">
<link rel="stylesheet" href="../static/css/communityBoardPage.css">
</head>
<body>
    <%@ include file="common/menu.jsp" %>
    <div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <div id="content1">
            <div>이런 퀴즈는 어떤가요?~</div>
            <div class="recommend-button">
                <ul>
                    <li><button>최신</button></li>
                    <li><button>조회</button></li>
                    <li><button>평점</button></li>
                    <li><button>장르</button></li>
                </ul>
            </div>
            <div>
                <div class="quiz-list">
                    <ul>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                        <li class="quiz-box">
                            <div class="thumbnail">썸네일</div>
                            <div class="title">제목</div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="content2">
            <div id="content2-left">
                <div class="user-status">
                    <div class="user-profile-info">
                        <div id="user-profile-img"><img src="../static/img/test.png" alt=""></div>
                        <div class="user-level-info">
                            <div id="user-level-value">12345</div>
                            <div class="user-exp-info">
                                <span id="current-value-info">1</span>
                                <span>/</span>
                                <span id="total-value-info">2</span>
                            </div>
                            <div id="exp-gage-bar"></div>
                        </div>
                    </div>
                    <div class="rate-gage">
                        <div id="achievement-rate">12</div>
                        <div id="played-quiz">12</div>
                        <div id="correct-answer-rate">12</div>
                    </div>
                </div>
                <div class="quest-list">
                    <div>일간</div>
                    <div class="daily-quest-list" id="first-quest">
                        <div>1.퀴즈 한개 풀기</div>
                        <div>
                            <span>0</span>
                            <span>/</span>
                            <span>1</span>
                        </div>
                        <div class="success-btn"><button>달성하기</button></div>
                    </div>
                    <div class="daily-quest-list" id="second-quest">
                        <div>1.퀴즈 한개 풀기</div>
                        <div>
                            <span>0</span>
                            <span>/</span>
                            <span>1</span>
                        </div>
                        <div class="success-btn"><button>달성하기</button></div>
                    </div>
                    <div class="daily-quest-list" id="third-quest">
                        <div>1.퀴즈 한개 풀기</div>
                        <div>
                            <span>0</span>
                            <span>/</span>
                            <span>1</span>
                        </div>
                        <div class="success-btn"><button>달성하기</button></div>
                    </div>
                </div>
                <div class="popular-community">
                    <div>인기글</div>
                    <table>
                        <tr>
                            <td class="tab">잡담</td>
                            <td class="title">테스트용 제목</td>
                            <td class="author">Test1</td>
                            <td class="comment-num">[1]<img src="../static/img/comment-icon.png"></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="content2-right">
                <div></div>
                <div></div>
            </div>
        </div>
    </div>
</body>
</html>