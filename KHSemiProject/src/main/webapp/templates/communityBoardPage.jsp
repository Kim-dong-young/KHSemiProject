<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>퀴즈팡 - 커뮤니티</title>

<link rel="stylesheet" href="../static/css/communityBoardPage.css">

</head>
<body>

    <div class="board-tab">
        <ul>
            <li><button style="background-color: #FF9139;">전체</button></li>
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
                    <td class="tab">탭</td>
                    <td class="title">제목</td>
                    <td class="author">작성자</td>
                    <td class="comment-num" style="color:black;">댓글</td>
                    <td class="date">작성일</td>
                    <td class="viewcount">조회수</td>
                </tr>
            </table>
        </div>

        <div class="board-list">
            <table>
                <tr>
                    <td class="tab">잡담</td>
                    <td class="title" onclick="location.href='communityViewPage.jsp'">테스트용 제목 테스트용 제목 테스트용 제목 테스트용 제목</td>
                    <td class="author">유저명유저명유저명유저명</td>
                    <td class="comment-num">[1]<img src="../static/img/comment-icon.png"></td>
                    <td class="date">12:45</td>
                    <td class="viewcount">13</td>
                </tr>
            </table>
        </div>

        <div class="board-option">
            <div class="option1">
                <select>
                    <option>최신순</option>
                    <option>인기순</option> <!-- 좋아요 순 -->
                    <option>댓글순</option>
                </select>
            </div>

            <div class="option2">
                <button>&lt;&lt;</button>
                <button>&lt;</button>
                <button style="background-color: #FF9139;">1</button>
                <button>2</button>
                <button>3</button>
                <button>4</button>
                <button>5</button>
                <button>6</button>
                <button>7</button>
                <button>8</button>
                <button>9</button>
                <button>&gt;</button>
                <button>&gt;&gt;</button>
            </div>

            <div class="option1">
                <a href="communityWritePage.jsp"><img src="../static/img/pen-icon-white.png">글쓰기</a>
            </div>
        </div>

        <form class="search-form">
            <div class="option2">
                <input type="text">
            </div>

            <div class="option1">
                <select>
                    <option>전체</option>
                    <option>제목</option> <!-- 좋아요 순 -->
                    <option>내용</option>
                    <option>글쓴이</option>
                </select>
            </div>
            
            <div class="option1">
                <button><img src="../static/img/search-icon.png">검색</button>
            </div>
        </form>
    </div>

</div>
</body>
</html>