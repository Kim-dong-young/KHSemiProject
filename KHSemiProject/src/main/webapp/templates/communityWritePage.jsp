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

                <form method="post" enctype="multipart/form-data" action="<%=contextPath%>/insert.bo" onsubmit="return checkEmptyTab(this);">
                    <input type="hidden" name="userNo" value="<%=loginMember.getMemberNo()%>">
                    <input type="hidden" name="tab" value="">

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
                                <img id="font-bold" src="static/img/bold-icon.png">
                                <img id="font-italic" src="static/img/italic-icon.png">
                                <img id="font-underlined" src="static/img/underlined-icon.png">
                                <img id="font-strikethrough" src="static/img/strikethrough-icon.png">
                                <img id="font-size" src="static/img/fontsize-icon.png" style="margin-right:15px;">
                                <img id="font-color" src="static/img/color-icon.png">
                                <img id="font-fill" src="static/img/fill-icon.png">
                            </div>
                            <div name="content" contenteditable="true"></div>
                        </div>
                    </div>

                    <div class="board-write-option">
                        <button type="submit"><img src="static/img/pen-icon-white.png">글쓰기</button>
                    </div>
                    <!-- SummerNote 공부 후 이미지 업로드 개선예정(자유로운 위치에 이미지 놓을수 있게) -->
                    <div style="display:none;">
                        <input type="file" name="file1" id="file1" onchange="loadImg(this,1)" accept="image/*">
                        <input type="file" name="file2" id="file2" onchange="loadImg(this,2)" accept="image/*">
                        <input type="file" name="file3" id="file3" onchange="loadImg(this,3)" accept="image/*">
                        <input type="file" name="file4" id="file4" onchange="loadImg(this,4)" accept="image/*">
                        <input type="file" name="file5" id="file5" onchange="loadImg(this,5)" accept="image/*">
                    </div>

                    <div class="contain-img">
                        <span>현재 첨부된 이미지</span>
                        <div>
                            <img id="contain-img1" onclick="chooseImg(1)">
                            <img id="contain-img2" onclick="chooseImg(2)">
                            <img id="contain-img3" onclick="chooseImg(3)">
                            <img id="contain-img4" onclick="chooseImg(4)">
                            <img id="contain-img5" onclick="chooseImg(5)">
                        </div>
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