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
                <form action="${pageContext.request.contextPath}/profile.me" method="post" enctype="multipart/form-data">
                    <img id="profileImage" src="${pageContext.request.contextPath}/static/img/guest-icon.png" alt="프로필 이미지" class="profile-image" onclick="document.getElementById('imageUpload').click();">             
                    <div class="profile-details">
                        <input type="file" id="imageUpload" name="memberImage" action=".jpg, .png" style="display: none;" onchange="previewImage(event)">
                        <input type="hidden" name="memberId" value="${loginMember.memberId }">
                        <label for="memberNickName">닉네임:</label>
                        <input type="text" id="memberNickName" name="memberNickName" placeholder="닉네임">
                        <label for="Introduce">자기소개:</label>
                        <textarea id="Introduce" name="Introduce" rows="4" placeholder="자기소개를 입력하세요..."></textarea>
                    </div>
                    <button type="submit">저장</button>
                </form>
                <script>
                   function previewImage(event) {
                    const image = document.getElementById('profileImage');
                    const file = event.target.files[0];  
                        if (file) {
                            const reader = new FileReader();
                            reader.onload = function(e) {
                                image.src = e.target.result; // 선택된 파일을 이미지로 미리보기
                            };
                            reader.readAsDataURL(file); // 파일을 데이터 URL로 변환
                        }
                    }
                </script>
            </section>
        </fieldset>
        </main>
    </div>
</body>
</html>