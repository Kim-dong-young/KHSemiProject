<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.playQuiz.model.vo.Problem"%>
<%
    ArrayList<Problem> pList = (ArrayList<Problem>)request.getAttribute("pList");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>퀴즈팡</title>
    
</head>
<body>
    
<div style="display: none;">
    <%@ include file="common/menu.jsp" %>
</div>
    <div id="div-main-problem">
        <!-- 첫 번째 문제 내용 초기화 -->
         
        <script>
            var pList = <%= new com.google.gson.Gson().toJson(pList) %>;
            var pNum = 0;
            var correctNum = 0;
            function renderProblem() {
                if (pNum < pList.length) {
                    console.log(pList);
                    var problem = pList[pNum];
                    document.getElementById("div-main-problem").innerHTML =
                        `<link rel="stylesheet" href="<%=contextPath%>/static/css/QuizScreen(Media X).css">
                        <div id="quiz-container">
                            <div id="header">
                                <div id="title">
                                    <img src="<%=contextPath%>/static/img/QuizLogo.png" alt="퀴즈 로고">
                                </div>
                                <div id="right-header">
                                    
                                </div>
                            </div>

                            <div id="timer-container">
                                <img src="<%=contextPath%>/static/img/Timer.png" alt="타이머 아이콘" class="icon">
                                <div id="progress-bar">
                                    <div id="progress"></div>
                                </div>
                                <div id="timer-value">` + problem.Ptime + `</div>          
                            </div>

                            <div id="quiz-content">
                                <div class="Media-area">
                                    <div id="text1"></div>
                                </div>
                                <div class="question-and-answer">
                                    <div class="question">
                                        <div id="text2">` + problem.problem_content + `</div>
                                    </div>
                                    <div class="answer-container">
                                        <input type="text" id="answer-input" placeholder="정답 입력">
                                        <button id="submit-btn" onclick="submit_problem('` + problem.problem_number + `')">제출</button>
                                    </div>
                                </div>
                            </div>

                            <button id="home-btn">
                                <img src="<%=contextPath%>/static/img/homebtn.png" alt="홈 버튼">
                            </button>
                        </div>`;
                } else {
                    // 모든 문제를 다 풀었을 때 결과 창으로 전환
                    document.getElementById("div-main-problem").innerHTML =
                        `<link rel="stylesheet" href="<%=contextPath%>/static/css/Result.css">
                        <div class="container">
                            <div class="box">
                                <p>당신은 상위 <span class="highlight">?</span>% 입니다.</p>
                                <p>당신의 정답률: <span class="highlight">80%</span> <span class="highlight">16/20</span></p>
                                <p>평균 정답률: <span class="highlight">?</span>%</p>
                            </div>
                            
                            <div class="level-box">
                                <p>LV.66</p>
                                <div class="progress-bar">
                                    <div class="progress-fill"></div>
                                </div>
                            </div>

                            <div class="rating-box">
                                <p>별점</p>
                                <div class="stars">
                                    <span class="star" data-value="1">★</span>
                                    <span class="star" data-value="2">★</span>
                                    <span class="star" data-value="3">★</span>
                                    <span class="star" data-value="4">★</span>
                                    <span class="star" data-value="5">★</span>
                                </div>
                                <p id="rating-value">별점을 통해 문제를 평가해주세요.</p>
                            </div>

                            <div class="buttons">
                                <button class="button">질문하기</button>
                                <button class="button">댓글보기</button>
                            </div>

                            <div class="bottom-buttons">
                                <button class="report-btn">신고하기</button>
                                <button class="button" onclick="location.href=' <%=contextPath%>/main.me'">
                                    <a></a>
                                    홈으로
                                </button>
                                <button class="button">다시하기</button>
                            </div>
                        </div>`;
                }
                mediaInner(problem.problem_media_kind, problem.problem_number);
            }

            function mediaInner(num, pNum){
                inn = document.querySelector("#text1").innerHTML;
                if(num == 1 || num == 2){
                    $.ajax({
                        url: "pMedia.pl",
                        contentType: "application/json",
                        type: "GET",
                        data: {
                            num: num,
                            pNum: pNum
                        },
                        success: function(res){
                            if (num == 1) {
                                document.querySelector("#text1").innerHTML = `<img src="<%=contextPath%>/` + res + `" alt="">`;
                            } else {
                                document.querySelector("#text1").innerHTML = `<iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/SmTzdSVHvTI?si=0pmxBtOBhp6rPu2B" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>`;
                            }
                        },
                        error: function(){
                            console.log("시각콘텐츠 조회용 ajax 통신 실패")
                        }
                    })
                }
            }

            function nextProblem() {
                pNum++;
                renderProblem();
            }

            function submit_problem(num) {
                let submitBTN = document.getElementById("submit-btn");
                submitBTN.setAttribute('disabled', true);
                let answerArea = document.getElementById("answer-input");
                let ans = document.getElementById("answer-input").value;
                $.ajax({
                    url: "qzAnswer.pl",
                    contentType: "application/json",
                    type: "GET",
                    data: {
                        problem_num: num,
                        answer: ans
                    },
                    success: function(res) {
                        if (res.correct) {
                            answerArea.value = "정답!";
                            correctNum++;
                        } else {    
                            answerArea.value = "오답! 정답은: " + res.correctAnswer;
                        }
                        setTimeout(nextProblem, 3000); // 3초 후 다음 문제로 이동
                    },
                    error: function() {
                        console.log("정답 조회용 ajax 통신 실패");
                    }
                });
            }

            function addPlayCount(pnum){

            }

            // 첫 번째 문제 렌더링
            renderProblem();
        </script>
    </div>
</body>
</html>