let btnVal
let selectVal

function initLoad(optional) {
    selectVal = document.querySelector('select[name="showOpt"]').value
    const recomOpt = document.querySelectorAll('.recommend-button .custom-btn')
    
    recomOpt.forEach(button => {
        if(button.disabled) {
            btnVal = button.value
        }
    })

    listLoad(optional, selectVal, btnVal);
}

function ChangebtnVal(optional, name) {
    const recomOpt = document.querySelectorAll('.recommend-button .custom-btn')

    recomOpt.forEach(button => {
        if(button.name === name) {
            btnVal = button.value;
            button.disabled = true;
        } else {
            button.disabled = false;
        }
    })

    listLoad(optional, selectVal, btnVal);
}

function ChangeSelVal(optional) {
    selectVal = document.querySelector('select[name="showOpt"]').value

    listLoad(optional, selectVal, btnVal)
}

function listLoad(optional, selectVal, btnVal) {
    $.ajax({
        url: "personal.sl",    
        type: "post",        
        data: {
            memberNo: optional,
            selName: selectVal,
            btnName: btnVal
        },
        success: function(res) {
            console.log(res.qList);
            // 퀴즈 데이터를 담을 HTML을 동적으로 생성
            const interestList = document.querySelector('.interestList');
            
            interestList.innerHTML = ""
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