<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.playQuiz.model.vo.Problem"%>
<%
    ArrayList<Problem> pList = (ArrayList<Problem>)request.getAttribute("pList");
    int pNum = 0;
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/Quiz Screen(Media O).css">

    <div id="div-main-problem">
        <!-- 첫 번째 문제 내용 초기화 -->
        <script>
            var pList = <%= new com.google.gson.Gson().toJson(pList) %>;
            var pNum = 0;
            var correctNum = 0;
            function renderProblem() {
                if (pNum < pList.length) {
                    var problem = pList[pNum];
                    document.getElementById("div-main-problem").innerHTML =
                        `<div id="quiz-container">
                            <div id="header">
                                <div id="title">
                                    <img src="<%=request.getContextPath()%>/" alt="">
                                </div>
                                <div id="right-header">
                                    <div class="xp-box">
                                        <span>등급<br>L V.13</span>
                                        <div>44,642/50,000 XP</div>
                                    </div>
                                    <div class="score">6/10</div>
                                </div>
                            </div>

                            <div id="timer-container">
                                <img src="<%=request.getContextPath()%>/static/img/Timer.png" alt="타이머 아이콘" class="icon">
                                <div id="progress-bar">
                                    <div>${problem.ptime}</div>
                                </div>
                            </div>

                            <div id="quiz-content">
                                <div class="question-and-answer">
                                    <div class="question">
                                        <div id="text2">${problem.problem_content}</div>
                                    </div>
                                    <div class="answer-container">
                                        <button id="home-btn">
                                            <img src="<%=request.getContextPath()%>/static/img/homebtn.png" alt="홈 버튼">
                                            <a href="<%=request.getContextPath()%>/main.me"></a>
                                        </button>
                                        <input type="text" id="answer-input" placeholder="정답 입력">
                                        <button id="submit-btn" onclick="submit_problem(${problem.problem_number}, document.getElementById("answer-input").value)">제출</button>
                                    </div>
                                </div>
                            </div>
                        </div>`;
                } else {
                    // 모든 문제를 다 풀었을 때 결과 창으로 전환
                    document.getElementById("div-main-problem").innerHTML =
                        `<h2>퀴즈 종료!</h2>
                        <p>모든 문제를 완료했습니다. 결과를 확인하세요.</p>`;
                }
            }

            function nextProblem() {
                pNum++;
                renderProblem();
            }

            function submit_problem(num, ans) {
                let submitBTN = document.getElementById("submin-btn");
                submitBTN.setAttribute('disabled',true);
                let answerArea = document.getElementById("answer-input");
                $.ajax({
                    url: "qzAnswer.pl",
                    contentType: "application/json",
                    data: ({
                        problem_num: num,
                        answer: ans
                    }),
                    success: function(res) {
                        if (res.correct) {
                            answerArea.innerText = "정답!";
                        } else {
                            answerArea.innerText = "오답! 정답은: " + res.correctAnswer;
                        }
                    },
                    error: function() {
                        console.log("정답 조회용 ajax 통신 실패");
                    }
                });
            }

            // 첫 번째 문제 렌더링
            renderProblem();
        </script>
    </div>
</body>
</html>