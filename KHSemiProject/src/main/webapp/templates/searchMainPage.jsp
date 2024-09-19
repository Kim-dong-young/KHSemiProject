<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel="stylesheet" href="../static/css/searchMainPage.css">

</head>
<body>
    <%@ include file="common/menu.jsp" %>
    <div class="content">
        <div class="wrapper">
            <div class="category-div">
                <div>
                    <img src="../static/img/searchMainPage/Happy.png" alt="">
                    <p>유머</p>
                </div>
                <div>
                    <img src="../static/img/searchMainPage/Paint Palette.png" alt="">
                    <p>예술/문학</p>
                </div>
                <div>
                    <img src="../static/img/searchMainPage/Geography.png" alt="">
                    <p>세계</p>
                </div>
                <div>
                    <img src="../static/img/searchMainPage/Colosseum.png" alt="">
                    <p>역사</p>
                </div>
                <div>
                    <img src="../static/img/searchMainPage/Language.png" alt="">
                    <p>언어</p>
                </div>
                <div>
                    <img src="../static/img/searchMainPage/Test Tube.png" alt="">
                    <p>과학/자연</p>
                </div>
                <div>
                    <img src="../static/img/searchMainPage/Game Soccer.png" alt="">
                    <p>스포츠</p>
                </div>
                <div>
                    <img src="../static/img/searchMainPage/Question Mark.png" alt="">
                    <p>기타</p>
                </div>
            </div>
            <br>
            <div class="search-div">
                <div id="search-div-area">
                    <select name="search-menu" id="search-select">
                        <option value="title">제목</option>
                        <option value="maker">제작자</option>
                    </select>
                    <input type="text" id="search-text">
                    <input type="image" src="../static/img/icon.png" id="search-submit">
                </div>
            </div>
            <br>
            <div class="buttons">
                <div id="array-buttons">
                    <button>조회수</button>
                    <button>최신순</button>
                    <button>평점순</button>
                </div>
                <div id="tag-popup-button">
                    <button type="button" class="btn btn-primary" data-bs-toggle="popover" title="Popover Header" data-bs-content="Some content inside the popover">Toggle popover</button>
                </div>
                
            </div>
            <br>
            <div class="contents">

                <section class="video-flex">
                    <div class="video-priview">
                        <div class="thumbnail-row">
                            <img class="thumbnail" src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                        </div>
                        <div class="video-info-grid">
                            <div class="video-info">
                                <p class="video-title">
                                    인물 이름 맞추기
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="video-priview">
                        <div class="thumbnail-row">
                            <img class="thumbnail" src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                        </div>
                        <div class="video-info-grid">
                            <div class="video-info">
                                <p class="video-title">
                                    인물 이름 맞추기
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="video-priview">
                        <div class="thumbnail-row">
                            <img class="thumbnail" src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                        </div>
                        <div class="video-info-grid">
                            <div class="video-info">
                                <p class="video-title">
                                    인물 이름 맞추기
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="video-priview">
                        <div class="thumbnail-row">
                            <img class="thumbnail" src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                        </div>
                        <div class="video-info-grid">
                            <div class="video-info">
                                <p class="video-title">
                                    인물 이름 맞추기
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="video-priview">
                        <div class="thumbnail-row">
                            <img class="thumbnail" src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                        </div>
                        <div class="video-info-grid">
                            <div class="video-info">
                                <p class="video-title">
                                    인물 이름 맞추기
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="video-priview">
                        <div class="thumbnail-row">
                            <img class="thumbnail" src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                        </div>
                        <div class="video-info-grid">
                            <div class="video-info">
                                <p class="video-title">
                                    인물 이름 맞추기
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="video-priview">
                        <div class="thumbnail-row">
                            <img class="thumbnail" src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                        </div>
                        <div class="video-info-grid">
                            <div class="video-info">
                                <p class="video-title">
                                    인물 이름 맞추기
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="video-priview">
                        <div class="thumbnail-row">
                            <img class="thumbnail" src="../static/img/searchMainPage/alwaysjone_teacher_photo 1.png" alt="">
                        </div>
                        <div class="video-info-grid">
                            <div class="video-info">
                                <p class="video-title">
                                    인물 이름 맞추기
                                </p>
                            </div>
                        </div>
                    </div>
                </section>
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





        </div>














    </div>
    
        
</body>
</html>