<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.playQuiz.model.vo.Problem"%>
<%
    ArrayList<Problem> pList = (ArrayList<Problem>)request.getAttribute("pList");
        String contextPath2 = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>퀴즈팡</title>
    <link rel="stylesheet" href="<%=contextPath2%>/static/css/QuizScreen(Media O).css">
</head>
<body>
<div style="display: none;" >
    <%@ include file="common/menu.jsp" %>
</div>
<script>
    console.log(${loginMember.memberNo})
</script>
<div id="quiz-container">
    <div id="header">
        <div id="title">
            <img src="<%=contextPath%>/static/img/logo.png" alt="퀴즈 로고">
        </div>
        <div id="right-header">
            
        </div>
    </div>

    <div id="timer-container">
        <img src="<%=contextPath%>/static/img/Timer.png" alt="타이머 아이콘" class="icon">
        <div id="progress-bar">
            <div id="progress"></div>
        </div>
        <div id="timer-value">30</div>          
    </div>

    <div id="quiz-content">
        <div class="Media-area">
            <div id="text1">미디어 영역</div>
        </div>
        <div class="question-and-answer">
            <div class="question">
                <div id="text2">질문 내용</div>
            </div>
            <div class="hint">
                <button id="hintButton" onclick="showHint()" style="display: true;">힌트</button>
                <div id="hintText" style="display: none;">힌트 내용</div>
            </div>
            <div class="answer-container">
                <input type="text" id="answer-input" placeholder="정답 입력">
                <button id="submit-btn" onclick="submit_problem()">제출</button>
            </div>
        </div>
    </div>

    <button id="home-btn">
        <img src="<%=contextPath%>/static/img/homebtn.png" alt="홈 버튼" onclick="location.href=' <%=contextPath%>/main.me'">
    </button>
</div>
<script>
    var pList = <%= new com.google.gson.Gson().toJson(pList) %>;
    var pNum = 0;
    var correctNum = 0;
    let interval;


    function renderProblem(){
        if(pNum < pList.length) {
            var problem = pList[pNum];
            document.getElementById("text2").innerText = problem.problem_content;

            
            $.ajax({
                url: "pMedia.pl",
                contentType: "application/json",
                type: "GET",
                data: {
                    num: problem.problem_media_kind,
                    pNum: problem.problem_number
                },
                success: function(res){
                    console.log(res);
                    if (res != null) {
                        document.querySelector(".Media-area").setAttribute('style', "display: true;");
                        document.querySelector(".Media-area").innerHTML = `<img src="<%=contextPath%>/` + res + `" alt="">`;
                    } else {
                        document.querySelector(".Media-area").setAttribute('style', "display: none;");
                    }
                },
                error: function(){
                    console.log("시각콘텐츠 조회용 ajax 통신 실패")
                }
            })
            if(interval !== undefined){
                clearInterval(interval);
                console.log(interval);
            }
            startTimer(problem.Ptime);
            if(problem.problem_hint != null){
                document.querySelector(".hint").setAttribute('style', "display: true;");
                document.querySelector("#hintButton").setAttribute('style', "display: true;");
                document.querySelector("#hintText").setAttribute('style', "display: none;");
                document.querySelector("#hintText").innerText = problem.problem_hint;
            } else {
                document.querySelector(".hint").setAttribute('style', "display: none;");
            }
        } else {
            console.log("응애");
            postResult();
        }
    }

    function postResult() {
        var form = document.createElement("form");
        form.setAttribute("method", "post");
        form.setAttribute("action", "<%=contextPath%>/pqEnd.pl");
        
        var correctNumInput = document.createElement("input");
        correctNumInput.setAttribute("type", "hidden");
        correctNumInput.setAttribute("name", "correctNum");
        correctNumInput.setAttribute("value", correctNum);
        form.appendChild(correctNumInput);

        var quizNumberInput = document.createElement("input");
        quizNumberInput.setAttribute("type", "hidden");
        quizNumberInput.setAttribute("name", "quizNumber");
        quizNumberInput.setAttribute("value", ${param.quizNumber});
        form.appendChild(quizNumberInput);

        var memberNoInput = document.createElement("input");
        memberNoInput.setAttribute("type", "hidden");
        memberNoInput.setAttribute("name", "memberNo");
        memberNoInput.setAttribute("value", "${loginMember.memberNo}");
        form.appendChild(memberNoInput);

        var listLenInput = document.createElement("input");
        listLenInput.setAttribute("type", "hidden");
        listLenInput.setAttribute("name", "listLen");
        listLenInput.setAttribute("value", "${pList.size()}");
        form.appendChild(listLenInput);

        document.body.appendChild(form);
        form.submit();
    }


  

    function nextProblem(){
        pNum++;
        document.querySelector(".answer-container").innerHTML = `<input type="text" id="answer-input" placeholder="정답 입력">
                <button id="submit-btn" onclick="submit_problem()">제출</button>`
        renderProblem();
    }

    

    function submit_problem() {
        var problem = pList[pNum];
        let num = problem.problem_number
        clearInterval(interval);
        let submitBTN = document.getElementById("submit-btn");
        submitBTN.setAttribute('disabled', true);
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
                console.log(ans)
                let value = ""
                if (res.correct) {
                    value = "정답!";
                    correctNum++;
                    $.ajax({
                        url : 'checkQuest.pl',
                        error : function(){
                            console.log("퀘스트 AJAX 실패")
                        }
                    })
                } else {    
                    value = "오답! 정답은: " + res.correctAnswer;
                }
                console.log(document.getElementById("answer-input").value)
                document.querySelector("#submit-btn").setAttribute('style', "display: none;");
                document.querySelector(".answer-container").innerHTML += `<button id="submit-btn" onclick="nextProblem()">다음</button>`
                document.querySelector("#answer-input").value = value;
            },
            error: function() {
                console.log("정답 조회용 ajax 통신 실패");
            }
        });
    }

    function showHint(){
        document.querySelector("#hintButton").setAttribute('style', "display: none;");
        document.querySelector("#hintText").setAttribute('style', "display: true;");
    }





    function startTimer(duration) {
        const progressBar = document.getElementById('progress');
        const timerValue = document.getElementById('timer-value');
        let remainingTime = duration;

        progressBar.style.width = '100%'; 
        timerValue.textContent = remainingTime; 

        interval = setInterval(() => {
            if (remainingTime > 0) {
                remainingTime--; 
                const percentage = (remainingTime / duration) * 100;
                progressBar.style.width = percentage + `%`; 

                timerValue.textContent = remainingTime; 
                console.log(`남은 시간: ${remainingTime}, 진행 바 너비: ${progressBar.style.width}`);
            }

            if (remainingTime <= 0) {
                clearInterval(interval);
                console.log('시간이 다됐습니다!');
                var problem = pList[pNum];
                let num = problem.problem_number
                let submitBTN = document.getElementById("submit-btn");
                submitBTN.setAttribute('disabled', true);
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
                        console.log(document.getElementById("answer-input").value)
                        document.querySelector("#submit-btn").setAttribute('style', "display: none;");
                        document.querySelector(".answer-container").innerHTML += `<button id="submit-btn" onclick="nextProblem()">다음</button>`
                        document.querySelector("#answer-input").value = "시간초과! 정답은: " + res.correctAnswer;
                    },
                    error: function() {
                        console.log("정답 조회용 ajax 통신 실패");
                    }
                });
            }
        }, 1000);
    } 
</script>
</body>
</html>