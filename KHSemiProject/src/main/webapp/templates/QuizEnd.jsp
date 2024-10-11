<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.playQuiz.model.vo.Problem, java.lang.Math"%>
<%


%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    
</head>
<body>
    
    <script>
        console.log(${param.pList})
        console.log(${param.correctNum})
        console.log(${param.quizNumber})
        console.log(${loginMember.memberNo})
        console.log(${param.listLen})
    </script>
    <div style="display: none;">
        <%@ include file="common/menu.jsp" %>
    </div>
    
    <link rel="stylesheet" href="<%=contextPath%>/static/css/Result.css">
    <div class="container">
        <div class="box">
            <p>정답률: <span class="highlight" id="percent_correct">80%</span> <span class="highlight">${param.correctNum}/${param.listLen}</span></p>
        </div>
        <script>
            document.getElementById("percent_correct").innerText = Math.round((${param.correctNum} / ${param.listLen}) * 100) + "%";
        </script>
        <% if(loginMember != null){ %>
            <% 
                // 총 누적 경험치 값을 가져옵니다.
                int exp = (Integer) request.getAttribute("exp");
        
                // 각 레벨에서 필요한 최대 경험치
                int maxExpForLevel = 1000;
        
                // 현재 레벨 계산 (레벨 번호를 1부터 시작)
                int currentLevel = exp / maxExpForLevel + 1;
        
                // 현재 레벨에서의 경험치
                int expInCurrentLevel = exp % maxExpForLevel;
        
                // 진행률 계산 (레벨업 시 진행 바를 100%로 표시)
                int progressPercentage = (expInCurrentLevel == 0 && exp != 0) ? 100 : expInCurrentLevel * 100 / maxExpForLevel;
            %>
            <div class="level-box">
                <p id="playerLevel">
                    LV. <%= currentLevel %>
                </p>
                <div class="progress-bar">
                    <div class="progress-fill" style="width: <%= progressPercentage %>%"></div>
                </div>
                <p class="progress-text">
                    경험치: <%= expInCurrentLevel %> / <%= maxExpForLevel %>
                </p>
            </div>
        <% } %>
        

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
            <button class="button" id="starconfirm" value="${param.quizNumber}">별점주기</button>
            <input type="hidden" id="memberNoInfo" value="${loginMember.memberNo}">
        </div>

        <div class="bottom-buttons">
            <% if(loginMember != null){ %>
            <button class="report-btn" data-bs-toggle="modal" data-bs-target="#report-board-modal">신고하기</button>
            <% } %>
            <button class="button" onclick="location.href='<%=contextPath%>/main.me'">
                홈으로
            </button>
            <form action="<%= contextPath %>/main.pl" method="POST">
                <input type="hidden" name="memberNumber" value="${loginMember.memberNo}">
                <input type="hidden" name="quizNumber" value="${param.quizNumber}">
                <button class="button" type="submit" id="playenter">
                    다시하기
                </button>
            </form>
        </div>
    </div>


    <!-- 신고 Modal -->
    <div class="modal fade" id="report-board-modal">
        <div class="modal-dialog">
          <div class="modal-content">
      
            <!-- Modal Header -->
            <div class="modal-header">
              <img src="<%=contextPath%>/static/img/flag-icon.png">
              <h4 class="modal-title">신고하기</h4>
              <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>
      
            <!-- Modal body -->
            <form id="reportForm">
                <div class="modal-body">
                    <div class="report-choose-area">
                        <label>
                            <input type="radio" name="reportNumber" value="1" checked>
                            <span class="custom-check"></span>
                            홍보/도배 글입니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="2">
                            <span class="custom-check"></span>
                            음란물을 포함하고 있습니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="3">
                            <span class="custom-check"></span>
                            불법적인 내용입니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="4">
                            <span class="custom-check"></span>
                            욕설이 포함되어있습니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="5">
                            <span class="custom-check"></span>
                            혐오발언이 포함되어있습니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="6">
                            <span class="custom-check"></span>
                            사칭 글입니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="7">
                            <span class="custom-check"></span>
                            괴롭힘 및 따돌림이 포함되었습니다.
                        </label><br>
                    
                        <label>
                            <input type="radio" name="reportNumber" value="8">
                            <span class="custom-check"></span>
                            기타
                        </label><br>
                    </div>

                    <textarea name="reportReason" wrap="hard" placeholder="자세한 사유를 설명해주세요."></textarea>
                </div>
        
                <!-- Modal footer -->
                <div class="modal-footer">
                <button type="button" id="report-submit-button" onclick="reportBoard(${param.quizNumber}, ${loginMember.memberNo});" class="btn btn-danger">제출하기</button>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                </div>
            </form> 
          </div>
        </div>
      </div>


    <script src="<%=contextPath%>/static/js/Result.js"></script>
</body>
</html>