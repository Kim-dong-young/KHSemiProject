function initLoad(optional) {
    $.ajax({
        url: "createlatest.sl",    
        type: "post",        
        data: {
            memberNo: optional
        },
        success: function(res) {
            console.log(res.qList);
            // 퀴즈 데이터를 담을 HTML을 동적으로 생성
            const interestList = document.querySelector('.interestList');
            
            for(let myQuiz of res.qList) {
                interestList.innerHTML += "<div class='quizbox' onclick=location.href='" + res.contextPath + "/click.sl?quiz_number=" + myQuiz.quiz_number + "&page=3'>" +
                                            "<div class='thumbnail'><img src=" + myQuiz.thumbnail + "></div>" + 
                                            "<div class='title'>" + myQuiz.quiz_number + ". " + myQuiz.quiz_title + "</div>" + 
                                            "<div>"
            }
        },
        error: function() {
            const interestList = document.querySelector('.interestList');

            interestList.innerHTML += "<div>조회실패</div>"
        }
    });
}

function search() {

}