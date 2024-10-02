function initLoad(optional) {
        $.ajax({
            url: "myquiz.sl",    // 서버로 보낼 URL
            type: "post",        // POST 요청
            data: {
                memberNo: optional    // 서버로 보낼 데이터
            },
            success: function(res) {
                console.log(res);  // 서버 응답 확인

                // 퀴즈 데이터를 담을 HTML을 동적으로 생성
                const interestList = document.querySelector('.interestList');  // 첫 번째 .interestList 요소 선택
                
                for(let myQuiz of res) {
                    interestList.innerHTML += "<div class='quizbox'>" +
                                                "<div class='thumnail'>썸네일입니다.</div>" + 
                                                "<div class='title'>" + myQuiz.quiz_title + "</div>" + 
                                            "<div>"
                }
            },
            error: function() {
                alert("조회 실패");
            }
        });
}
