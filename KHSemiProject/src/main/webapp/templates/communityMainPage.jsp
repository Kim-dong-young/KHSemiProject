<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<link rel="stylesheet" href="../static/css/communityMainPage.css">

</head>
<body>
	<%@ include file="common/menu.jsp" %>
	
	<div class="content"> <!-- 컨텐츠 여기다가 추가 -->
		<p>자유 게시판</p>

		<div class="wrapper">
			<div class="board">
				<div class="board-tab">
					<ul>
						<li><button>전체</button></li>
						<li><button>인기글</button></li>
						<li><button>공지</button></li>
						<li><button>질문</button></li>
						<li><button>풀이</button></li>
						<li><button>잡담</button></li>
					</ul>
				</div>

				<div class="board-content">
					<div class="board-info">
						<table>
							<tr>
								<th>탭</th>
								<th>제목</th>
								<th>작성자</th>
								<th>댓글</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</table>
					</div>

					<div class="board-list">
						<table>
							<tr>
								<th>탭</th>
								<th>제목</th>
								<th>작성자</th>
								<th>댓글</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>

							<tr>
								<th>탭</th>
								<th>제목</th>
								<th>작성자</th>
								<th>댓글</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>

							<tr>
								<th>탭</th>
								<th>제목</th>
								<th>작성자</th>
								<th>댓글</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>

							<tr>
								<th>탭</th>
								<th>제목</th>
								<th>작성자</th>
								<th>댓글</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>

							<tr>
								<th>탭</th>
								<th>제목</th>
								<th>작성자</th>
								<th>댓글</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div class="board-side">

			</div>
		</div>
    </div>
</body>
</html>