<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/userset.css">
</head>
<body>
	<%@ include file="common/menu.jsp" %>
    <div class="content"> <!-- 컨텐츠 여기다가 추가 -->
        <nav class="navbar">
            <ul class="navbar_menu">
                <li><a href="templates/userset1.jsp">프로필 설정</a></li>
                <li><a href="templates/userset2.jsp">개인정보 설정</a></li>
                <li><a href="templates/userset3.jsp">알림 설정</a></li>
                <li><a href="templates/userset4.jsp">기타 설정</a></li>
            </ul>
        </nav>
        <main>
        <fieldset>
            <section id="profile-settings" class="content-section">
                <h2>프로필 설정</h2>
                <form>
                    <div class="profile-details">
                        <label for="nickname">닉네임:</label>
                        <input type="text" id="nickname" name="nickname" placeholder="현재 닉네임">
                        <label for="nickname">이메일:</label>
                        <input type="text" id="email" name="email" placeholder="이메일을 입력">
                        <label for="bio">자기소개:</label>
                        <textarea id="bio" name="bio" rows="4" placeholder="자기소개를 입력하세요..."></textarea>
                    </div>
                    <button type="submit">저장</button>
                </form>
            </section>
        </fieldset>
        </main>
    <script src="static/js/userset.js"></script>
    </div>
</body>
</html>