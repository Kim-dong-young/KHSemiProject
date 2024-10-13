function initLoad(optional) {
    const showSelectOpt = document.querySelector('select[name="showOpt"]') // 노출할 퀴즈 조건 select
    const recomBtns = document.querySelectorAll('.recommend-button .custom-btn') // 정렬 조건 button
    const searchBar = document.querySelector('.search .search-bar') // 검색창
    const searchBtn = document.querySelector('.search .search-button') // 검색버튼
    const searchSelectOpt = document.querySelector('select[name=searchOpt]') // 검색 조건 select

    let showSelectVal = showSelectOpt.value; // 노출 select 옵션 초기값 
    let btnVal;
    let searchVal = '';
    let searchSelectVal = searchSelectOpt.value; // 검색조건 select 옵션 초기값

    // 초기 button 옵션 값
    recomBtns.forEach(button => {
        if (button.disabled) {
            btnVal = button.value;
        }
    });

    listLoad(optional, showSelectVal, btnVal, searchVal, searchSelectVal);

    showSelectOpt.onchange = function(ev) {
        showSelectVal = ev.target.value;

        listLoad(optional, showSelectVal, btnVal, searchVal, searchSelectVal);
        
    }

    recomBtns.forEach(button => {
        button.onclick = function(ev) {
            btnVal = ev.target.value; // 클릭된 버튼의 값을 btnVal에 저장

            recomBtns.forEach(btn => {
                if(btn === ev.target) {
                    btn.disabled = true;
                } else {
                    btn.disabled = false;
                }
            })
            
            listLoad(optional, showSelectVal, btnVal, searchVal, searchSelectVal);
        }
    });


    searchBtn.onclick = function() {
        searchVal = searchBar.value;

        listLoad(optional, showSelectVal, btnVal, searchVal, searchSelectVal);
    }

    searchBar.onkeypress = function(ev) {
        if(ev.key === 'Enter' || ev.keyCode === 13) {
            searchVal = searchBar.value;

            listLoad(optional, showSelectVal, btnVal, searchVal, searchSelectVal);
        }
    }

    searchSelectOpt.onchange = function(ev) {
        searchSelectVal = ev.target.value
    }
}

function performSearch() {
    searchVal = searchBar.value;

    listLoad(optional, showSelectVal, btnVal, searchVal, searchSelectVal);
}

function listLoad(optional, showSelectVal, btnVal, searchVal, searchSelectVal) {
    let searchData;

    if(searchVal.trim().length > 0) {
        searchData = {
            memberNo: optional,
            selName: showSelectVal,
            btnName: btnVal,
            searchVal: searchVal,
            serSelName: searchSelectVal
        }
    } else {
        searchData = {
            memberNo: optional,
            selName: showSelectVal,
            btnName: btnVal,
        }
    }

    $.ajax({
        url: "personal.sl",
        type: "post",
        data: searchData,
        success: function(res) {
            // 퀴즈 데이터를 담을 HTML을 동적으로 생성
            const interestList = document.querySelector('.interestList');
            
            interestList.innerHTML = "";
            
            if(res.qList.length > 0) {
                for(let myQuiz of res.qList) {
                    
                    interestList.innerHTML += "<div class='quizbox' onclick=location.href='" + res.contextPath + "/click.sl?quiz_number=" + myQuiz.quiz_number + "&page=3'>" +
                                                "<div class='thumbnail'><img src=" + res.contextPath + "/" + myQuiz.thumbnail + "></div>" + 
                                                "<div class='title'>" + myQuiz.quiz_number + ". " + myQuiz.quiz_title + "</div>" + 
                                                "<div>"
                }
            } else {
                interestList.innerHTML = "<div class='not-found-box'>조회 결과가 없습니다.</div>"
            }
        },
        error: function() {
            const interestList = document.querySelector('.interestList');

            interestList.innerHTML += "<div>조회실패</div>"
        }
    })
}