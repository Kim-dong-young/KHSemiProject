function initLoad(optional) {
    const selectOpt = document.querySelector('select[name="showOpt"]')
    const recomOpt = document.querySelectorAll('.recommend-button .custom-btn')

    let selectVal = selectOpt.value; // 초기 select 옵션 값 
    let btnVal;

    // 초기 button 옵션 값
    recomOpt.forEach(button => {
        if (button.disabled) {
            btnVal = button.value;
        }
    });

    selectOpt.onchange = function(ev) {
        selectVal = ev.target.value;
        console.log(selectVal, btnVal);
        listLoad(optional, selectVal, btnVal);
    }

    recomOpt.forEach(button => {
        button.onclick = function(ev) {
            btnVal = ev.target.value; // 클릭된 버튼의 값을 btnVal에 저장
            
            recomOpt.forEach(btn => {
                if(btn === ev.target) {
                    btn.disabled = true;
                } else {
                    btn.disabled = false;
                }
            })
            console.log(selectVal, btnVal); // 콘솔에 출력
            listLoad(optional, selectVal, btnVal);
        }
    });

    listLoad(optional, selectVal, btnVal);
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