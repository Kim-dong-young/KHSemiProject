<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
	
	<!-- bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

	<!-- jQuery -->
	<script 
        src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
        crossorigin="anonymous">
	</script>
    <script
        src="https://code.jquery.com/ui/1.14.0/jquery-ui.min.js"
        integrity="sha256-Fb0zP4jE3JHqu+IBB9YktLcSjI1Zc6J2b6gTjB0LpoM="
        crossorigin="anonymous">
	</script>

    <!-- Google font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/loginMadal.css">
	<script src="<%=request.getContextPath()%>/static/js/loginmodal.js"></script>

</head>
<body onload="signinit()">
	<!-- contextPath 로그인 modal -->
	<div class="modal" id="login-modal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">		     
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body" align="center">
					<form id="loginForm" action="<%=contextPath %>/login.me" method="POST">
						<input type="hidden" name="path" value="">
						<div class="mb-3 mt-3">
							<label for="id" class="form-label">아이디:</label>
							<input type="text" class="form-control" id="id" placeholder="Enter ID" name="memberId">
						</div>
						<div class="mb-3">
							<label for="pwd" class="form-label">비밀번호:</label>
							<input type="password" class="form-control" id="pwd" placeholder="Enter password" name="memberPwd">
						</div>
						<button type="submit" class="btn custom-lsin-btn login">로그인</button>
						<button type="button" class="btn custom-lsin-btn" data-bs-toggle="modal" data-bs-target="#signin-modal">회원가입</button>
					</form>
				</div>
			</div>
		</div>	
	</div>
		
	<!-- 회원가입 -->
	<div class="modal" id="signin-modal">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">		     
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">회원가입</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				
				<!-- Modal body -->
				<div class="modal-body enrollForm" align="center">
					<form action="<%=contextPath %>/signin.me" method="POST" id="enroll-form">
						<input type="hidden" name="origin" value="<%=request.getRequestURI()%>">
						<div class="mb-3 mt-3">
							<label for="id" class="form-label">*아이디:</label>
							<input type="text" class="form-control" id="sId" name="sMemberId" autocomplete="off" maxlength="20" required>
							<div id="checkId" style="display: none;"></div>
						</div>
						<div class="mb-3">
							<label for="pwd" class="form-label">*비밀번호:</label>
							<input type="password" class="form-control" id="sPwd" name="sMemberPwd" maxlength="20" required>
							<div id="checkPwd" style="display: none;"></div>
						</div>
						<div class="mb-3">
							<label for="pwd" class="form-label">*비밀번호 확인:</label>
							<input type="password" class="form-control" id="sCheckPwd" name="sCheckMemberPwd" maxlength="20" required>
						</div>
						<div class="mb-3">
							<label for="pwd" class="form-label">*닉네임:</label> 
							<input type="text" class="form-control" id="sNickname" name="sMemberNickname" autocomplete="off" maxlength="20" required>
						</div>
						<button type="submit" class="btn custom-lsin-btn signin" onclick="return checkPwd()">회원가입</button>
						<button type="reset" class="btn custom-lsin-btn">초기화</button>
					</form>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>