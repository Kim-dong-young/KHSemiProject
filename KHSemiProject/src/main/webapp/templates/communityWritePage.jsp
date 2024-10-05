<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.community.model.vo.Category" %>
<%
    ArrayList<Category> category = (ArrayList<Category>)request.getAttribute("category"); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<script src="<%=request.getContextPath()%>/static/js/communityWritePage.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/communityWritePage.css">

</head>
<body>
	<%@ include file="common/menu.jsp" %>
	
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <p>글 쓰기</p>
        <div class="wrapper">
            <div class="board">

                <form method="post" action="<%=contextPath%>/insert.bo" onsubmit="return checkEmptyTab(this);">
                    <input type="hidden" name="userNo" value="<%=loginMember.getMemberNo()%>">
                    <input type="hidden" name="tab" value="">

                    <!-- SummerNote 공부 후 추?가 할듯 
                    <div style="display:none;">
                        <input type="file" name="file1" id="file1" onchange="loadImg(this)" accept="image/*">
                        <input type="file" name="file2" id="file2" onchange="loadImg(this)" accept="image/*">
                        <input type="file" name="file3" id="file3" onchange="loadImg(this)" accept="image/*">
                        <input type="file" name="file4" id="file4" onchange="loadImg(this)" accept="image/*">
                        <input type="file" name="file5" id="file5" onchange="loadImg(this)" accept="image/*">
                    </div>
                    -->

                    <div class="board-write">
                        <div class="tab-select">
                            <span>탭 선택</span>
                            <% for(Category c : category) { %>
                                <button type="button" onclick="selectTab(this)" value="<%=c.getTabNumber()%>"><%=c.getTabName()%></button>
                            <% } %>
                        </div>

                        <div class="title-input">
                            <input type="text" name="title" placeholder="글 제목을 입력해주세요.">
                        </div>

                        <div class="content-input">
                            <div class="input-option">
                                <img src="static/img/bold-icon.png">
                                <img src="static/img/italic-icon.png">
                                <img src="static/img/underlined-icon.png">
                                <img src="static/img/strikethrough-icon.png">
                                <img src="static/img/fontsize-icon.png" style="margin-right:15px;">
                                <img src="static/img/color-icon.png">
                                <img src="static/img/fill-icon.png">
                                <img src="static/img/image-icon.png" onclick="chooseImg()">
                            </div>
                            <!-- wrap = hard : 텍스트 입력값을 textarea에 맞게 줄바꿈(\n)을 해줌, 안하면 한줄로 쭉 나옴 -->
                            <textarea name="content" wrap="hard"></textarea>
                        </div>
                    </div>

                    <div class="board-write-option">
                        <button type="submit"><img src="static/img/pen-icon-white.png">글쓰기</button>
                    </div>

                </form>
            </div>

            <div class="board-side">
                <%@ include file="communitySidePage.jsp" %>
            </div>
        </div>

    </div>
</body>
</html>