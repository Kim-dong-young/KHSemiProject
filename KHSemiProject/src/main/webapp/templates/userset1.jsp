<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저설정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/userset.css">
</head>
<body>
	<%@ include file="common/menu.jsp" %>
    <div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <nav class="navbar">
            <ul class="navbar_menu">
                <li><a href="${pageContext.request.contextPath}/templates/userset1.jsp">프로필 설정</a></li>
                <li><a href="${pageContext.request.contextPath}/templates/userset2.jsp">개인정보 설정</a></li>
                <li><a href="${pageContext.request.contextPath}/templates/userset3.jsp">내 정보 설정</a></li>
                <li><a href="${pageContext.request.contextPath}/templates/userset4.jsp">회원 탈퇴 설정</a></li>
            </ul>
        </nav>
        <main>
        <fieldset>
            <section id="profile-settings" class="content-section">
                <h2>프로필 설정</h2>
                    <img src="${pageContext.request.contextPath}/static/img/bold-icon.png" alt="프로필 이미지" class="profile-image">
                <form action="${pageContext.request.contextPath}/profile.me" method="post">
                    <div class="profile-details">
                        <input type="hidden" name="memberId" value="${loginMember.memberId }">

                        <label for="memberNickName">닉네임:</label>
                        <input type="text" id="memberNickName" name="memberNickName" placeholder="닉네임">
                        <label for="Introduce">자기소개:</label>
                        <textarea id="Introduce" name="Introduce" rows="4" placeholder="자기소개를 입력하세요..."></textarea>
                    </div>
                    <button type="submit">저장</button>
                </form>
                <script>
                    document.getElementById("edit-update-btn").onclick = function() {
                        
                    }

                </script>
            </section>
        </fieldset>
        </main>
    </div>
</body>
</html>