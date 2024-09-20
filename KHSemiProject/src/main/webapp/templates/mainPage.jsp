<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../static/css/mainPageContent.css">
<link rel="stylesheet" href="../static/css/communityBoardPage.css">

<script src="../static/js/mainPage.js"></script>
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
                    <div id="user-info">
                        <div id=user-info-img><img src="../static/img/test.png" alt=""></div>
                        <div class="user-info-exp">
                            <div class="user-info-level">
                                <div>
                                    <span>LV.</span>
                                    <span id="level">456</span>
                                </div>
                                <div>
                                    <span>1</span>
                                    <span>/</span>
                                    <span>2</span>
                                </div>
                            </div>
                            <div id="user-exp-bar">
                                <div id="current-user-exp-bar"></div>
                            </div>
                        </div>
                    </div>
                    <div class="achievement-donut-rate">
                        <div>
                            <div class="donut-chart" data-persent="70">
                                <span class="donut-text"></span>
                            </div>
                            <div class="content-rate">업적 달성률</div>
                        </div>
                        <div>
                            <div class="donut-chart" data-persent="50">
                                <span class="donut-text"></span>
                            </div>
                            <div class="content-rate">플레이한 퀴즈</div>
                        </div>
                        <div>
                            <div class="donut-chart" data-persent="80">
                                <span class="donut-text"></span>
                            </div>
                            <div class="content-rate">정답률</div>
                        </div>
                    </div>
                </div>
                <div class="quest-list">
                    <div>일간</div>
                    <div class="quest-list-row">
                        <div>
                            <div class="quest-content">1. 퀴즈 1개 완료하기</div>
                            <div class="quest-achieving-condition">
                                <span>0</span>
                                <span>/</span>
                                <span class="completion-condition">1</span>
                            </div>
                            <div class="submit-btn">
                                <button>달성하기</button>
                            </div>
                        </div>
                        <div>
                            <div class="quest-content">1. 퀴즈 1개 완료하기</div>
                            <div class="quest-achieving-condition">
                                <span>0</span>
                                <span>/</span>
                                <span class="completion-condition">1</span>
                            </div>
                            <div class="submit-btn">
                                <button>달성하기</button>
                            </div>
                        </div>
                        <div>
                            <div class="quest-content">1. 퀴즈 1개 완료하기</div>
                            <div class="quest-achieving-condition">
                                <span>0</span>
                                <span>/</span>
                                <span class="completion-condition">1</span>
                            </div>
                            <div class="submit-btn">
                                <button>달성하기</button>
                            </div>
                        </div>
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
            <div id="content2-right">
                <div class="attendance-recode">
                    <div id="attendance">
                        <div>출석</div>
                        <div id="attendance-btn"><button>출석하기</button></div>
                    </div>
                    <hr>
                    <div id="serial-attendance-day">
                        <div>일간 출석 일수</div>
                        <div>30일</div>
                    </div>
                    <hr>
                    <div id="serial-attendance-week">
                        <div>주간 출석 일수</div>
                        <div>4주</div>
                    </div>
                    <hr>
                    <div id="solve-a-quiz">
                        <div>최근 플레이 횟수</div>
                        <div class="solve-a-quiz-day">
                            <div class="solve-a-quiz-day-bar">
                                <div class="solve-a-quiz-bar"><div></div></div>
                                <div class="solve-a-quiz-bar"><div></div></div>
                                <div class="solve-a-quiz-bar"><div></div></div>
                                <div class="solve-a-quiz-bar"><div></div></div>
                                <div class="solve-a-quiz-bar"><div></div></div>
                                <div class="solve-a-quiz-bar"><div></div></div>
                                <div class="solve-a-quiz-bar"><div></div></div>
                            </div>
                            <hr>
                            <div class="solve-a-quiz-day-of-week">
                                <div>일</div>
                                <div>월</div>
                                <div>화</div>
                                <div>수</div>
                                <div>목</div>
                                <div>금</div>
                                <div>토</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ranking"></div>
            </div>
        </div>
    </div>
</body>
</html>