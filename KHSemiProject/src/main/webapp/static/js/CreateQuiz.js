document.addEventListener('DOMContentLoaded', function () {
    const questionList = document.querySelector('.question-list');
    const addQuestionBtn = document.querySelector('.add-question-btn');
    const quizLayout = document.querySelector('.quiz-layout');
    let currentPage = 1;
    let totalPages = 1; // 처음에는 1페이지로 시작

    // 페이지 수 업데이트 함수 (현재 페이지와 총 페이지 표시)
    function updatePageCount() {
        const progressElements = document.querySelectorAll('.progress'); // 모든 페이지의 progress 영역을 찾아 업데이트
        progressElements.forEach(el => {
            el.textContent = `${currentPage}/${totalPages}`;
        });
    }

    // 새로운 질문 페이지를 추가하는 함수
    function addNewQuestion() {
        totalPages++; // 총 페이지 수 증가

        // 왼쪽 질문 항목 추가
        const newQuestionItem = document.createElement('div');
        newQuestionItem.classList.add('question-item');
        newQuestionItem.innerHTML = `
            <span class="question-number">${totalPages}.</span>
            <div class="image-placeholder"></div>
        `;
        questionList.insertBefore(newQuestionItem, addQuestionBtn); // 질문 추가 버튼 위에 새 질문 추가

        // 새로운 페이지도 추가 (기존 화면을 그대로 복사해서 추가)
        const newPage = document.createElement('div');
        newPage.classList.add('quiz-content');
        newPage.style.display = 'none'; // 기본적으로 숨김
        newPage.innerHTML = `
            <div class="question-type">
                <label>문제 유형</label>
                <div class="progress">${totalPages}/${totalPages}</div> <!-- 각 페이지의 현재 상태를 일단 삽입 -->
                <div class="type-btns">
                    <button class="type-btn">객관식</button>
                    <button class="type-btn">주관식</button>
                    <button class="type-btn">O / X</button>
                </div>
            </div>

            <div class="question-input">
                <label for="question-${totalPages}">질문 내용:</label>
                <input type="text" id="question-${totalPages}" placeholder="질문을 입력하세요">
            </div>
            
            <div class="media">
                <input type="file">
            </div>

            <div class="time-limit">
                <label>제한시간</label>
                <input type="radio" name="time-${totalPages}" value="15"> 15초
                <input type="radio" name="time-${totalPages}" value="30"> 30초
                <input type="radio" name="time-${totalPages}" value="45"> 45초
            </div>

            <div class="hint-answer">
                <label for="hint-${totalPages}">힌트:</label>
                <input type="text" id="hint-${totalPages}" placeholder="없을 경우 '.'을 입력해 주세요">
                
                <label for="answer-${totalPages}">정답:</label>
                <input type="text" id="answer-${totalPages}" placeholder="정답을 입력해 주세요">
            </div>

            <div class="buttons">
                <button class="home-btn">홈</button>
                <button class="create-btn">질문 생성하기</button>
            </div>
        `;
        quizLayout.appendChild(newPage); // 새로운 페이지를 퀴즈 레이아웃에 추가

        // 새로 추가된 페이지로 이동
        goToPage(totalPages);
    }

    // 페이지 이동 함수
    function goToPage(pageNumber) {
        const allPages = document.querySelectorAll('.quiz-content');
        allPages.forEach(page => {
            page.style.display = 'none'; // 모든 페이지 숨김
        });
        const targetPage = document.querySelector(`.quiz-content:nth-of-type(${pageNumber + 1})`);
        if (targetPage) {
            targetPage.style.display = 'block'; // 해당 페이지 표시
            currentPage = pageNumber; // 현재 페이지 업데이트
            updatePageCount(); // 페이지 수 업데이트
        }
    }

    // 질문 리스트의 항목을 클릭하면 해당 페이지로 이동
    questionList.addEventListener('click', function (event) {
        if (event.target.closest('.question-item')) {
            const clickedPageNumber = Array.from(questionList.children).indexOf(event.target.closest('.question-item')) + 1;
            goToPage(clickedPageNumber);
        }
    });

    // '질문 추가' 버튼 클릭 이벤트
    addQuestionBtn.addEventListener('click', function () {
        addNewQuestion();
    });

    // 처음 페이지 수 업데이트
    updatePageCount();

    // 첫 번째 페이지 표시
    goToPage(1);
});
