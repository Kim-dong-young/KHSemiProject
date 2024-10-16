<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page buffer="16kb" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList, com.kh.search.model.vo.Quiz"%>
<%
	ArrayList<Quiz> list = ((ArrayList<Quiz>)request.getAttribute("qList"));
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 메인</title>

<!-- 스와이프 라이브러리-->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/swiper@8/swiper-bundle.min.js"></script>

<link rel="stylesheet" href="static/css/mainPageContent.css">
<link rel="stylesheet" href="static/css/communityBoardPage.css">

<script src="static/js/mainPage.js"></script>

</head>
<body>
    <%@ include file="common/menu.jsp" %>
    <div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <div id="content1">
            <div>이런 퀴즈는 어떤가요?~</div>
            <div class="recommend-button">
                <ul>
                    <li><button class="custom-btn" name="latest" value="Latest" disabled>최신</button></li>
                    <li><button class="custom-btn" name="inquiry" value="Inquiry">조회</button></li>
                    <li><button class="custom-btn" name="grade" value="Grade">평점</button></li>
                </ul>
            </div>
            <div class="my-swiper-box">
                <div class="swiper">
                    <div class="swiper-wrapper">
                        <!-- ajax로 swiper 채워줌 -->
                    </div>
                    <!-- Add Navigation buttons if needed -->
                    <div class="swiper-button-next custom-swiper-btn"></div>
                    <div class="swiper-button-prev custom-swiper-btn"></div>
                </div>
            </div>
        </div>
        <div id="content2">
            <div id="content2-left">
                <div class="user-status">
                	<c:choose>
                	    <c:when test="${empty loginMember}">
                		    <div class="not-login-info">로그인 후 이용 가능한 서비스입니다.</div>
                	    </c:when>
                		<c:otherwise>
		                    <div id="user-info">
		                        <div id=user-info-img><img src="<%=contextPath%>${loginMember.memberImg}" alt=""></div>
		                        <div class="user-info-exp">
		                            <div class="user-info-level">
		                                <div>
		                                    <span>LV.</span>
                                            <span id="level"></span>
		                                </div>
		                                <div> 
		                                    <span id="exp-value"></span>
		                                    <span>/</span>
		                                    <span>1000</span>
		                                </div>
		                            </div>
		                            <div id="user-exp-bar">
		                                <div id="current-user-exp-bar"></div>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="achievement-donut-rate">
		                        <div>
		                            <div class="chart-donut" data-percent="${attendance}"></div>
		                            <div class="content-rate">출석률</div>
		                        </div>
		                        <div>
		                            <div class="chart-donut"  data-percent="${played}"></div>
		                            <div class="content-rate">플레이한 퀴즈</div>
		                        </div>
		                        <div>
		                            <div class="chart-donut"  data-percent="${correct}"></div>
		                            <div class="content-rate">정답률</div>
		                        </div>
		                    </div>
                    	</c:otherwise>
                    </c:choose>
                </div>
                <div class="quest-list">
                    <div>일간</div>
                    <div class="quest-list-row">
                        <!--
                            <div class="quest-content">1. 퀴즈 1개 완료하기</div>
                            <div class="quest-achieving-condition">
                                <span>0</span>
                                <span>/</span>
                                <span class="completion-condition">1</span>
                            </div>
                            <div class="submit-btn">
                                <button>달성하기</button>
                            </div>
                        -->
                    </div>                    
                </div>
                <div class="popular-community">
                    <div>인기글</div>
                    <table>
                            
                    </table>
                </div>
            </div>
            <div id="content2-right">
                <div class="attendance-recode">
                    <div id="attendance">
                    	<%if(loginMember == null) {%>
                            <div>출석부</div>
                       		<div id="attendance-btn"><button class="btn custom-btn" disabled>출석하기</button></div>
                        <%} else {%>
                    	<form action="<%=contextPath%>/attend.me" method="post" id="attendance-notdisabled">
                    		<input type="hidden" name="MemberNo" value="<%=loginMember.getMemberNo()%>">
                    		<div>출석부</div>
	                        <div id="attendance-btn"><button class="btn custom-btn" type="submit">출석하기</button></div>	                        
                    	</form>
                    	<%}%>                
                    </div>
                    <hr>
                    <div id="serial-attendance">
                        <div>연속 출석 일수</div>
                        <%if(loginMember != null) {%>
                        <div><%=loginMember.getCheckContinueCnt() %>회</div>
                        <%} else { %>
                        <div>0회</div>
                        <%} %>
                    </div>
                    <hr>
                    <div id="total-attendance">
                        <div>총 출석 일수</div>
                        <%if(loginMember != null) {%>
                        <div>${totalAt }회</div>
                        <%} else { %>
                        <div>0회</div>
                        <%} %>
                    </div>
                    <hr>
                    <div id="solve-a-quiz">
                        <div>오늘 플레이 횟수</div>
                        <%if(loginMember != null) {%>
                            <div class="solve-a-quiz-day">${playedRecode}회</div>
                        <%} else { %>
                            <div class="solve-a-quiz-day">0회</div>
                        <%} %>
                    </div>
                </div>
                <div class="ranking"></div>
            </div>
        </div>
    </div>
</body>
</html>