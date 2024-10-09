document.addEventListener('DOMContentLoaded', function () {
    const questionList = document.querySelector('.question-list');
    const addQuestionBtn = document.querySelector('.add-question-btn');
    const deleteQuestionBtn = document.querySelector('.delete-question-btn');
    const quizLayout = document.querySelector('.quiz-layout');
    let currentPage = 1;
    let totalPages = 1;
    const maxQuestions = 5; // 최대 질문 수

    // 페이지 수 업데이트 함수 (현재 페이지와 총 페이지 표시)
    function updatePageCount() {
        const progressElements = document.querySelectorAll('.progress');
        progressElements.forEach(el => {
            el.textContent = `${currentPage}/${totalPages}`;
        });
    }

    // 새로운 질문 페이지를 추가하는 함수
    function addNewQuestion() {
        if (totalPages >= maxQuestions) {
            alert("더 이상 질문을 추가할 수 없습니다. 최대 질문 수는 5개입니다.");
            return;
        }

        totalPages++; // 총 페이지 수 증가

        // 왼쪽 질문 항목 추가
        const newQuestionItem = document.createElement('div');
        newQuestionItem.classList.add('question-item');
        newQuestionItem.innerHTML = `
            <span class="question-number">${totalPages}.</span>
            <div class="image-placeholder"></div>
        `;
        questionList.insertBefore(newQuestionItem, addQuestionBtn.parentNode); // 질문 추가 버튼 위에 새 질문 추가

        // 새로운 페이지도 추가
        const newPage = document.createElement('div');
        newPage.classList.add('quiz-content');
        newPage.style.display = 'none'; // 기본적으로 숨김
        newPage.innerHTML = `
            <div class="question-type">
                <label>문제 유형</label>
                <div class="progress">${totalPages}/${totalPages}</div>
                <div class="type-btns">
                    <button class="type-btn" disabled>객관식</button>
                    <button class="type-btn" disabled>주관식</button>
                    <button class="type-btn" disabled>O / X</button>
                </div>
            </div>

            <div class="question-input">
                <label for="question-${totalPages}">질문 내용:</label>
                <input type="text" id="question-${totalPages}" name="question-${totalPages}" placeholder="질문을 입력하세요">
            </div>

            <div>
                <input type="file" name="file-${totalPages}" id="file-${totalPages}" accept="image/*">
            </div>
            <div class="media">
                <img id="preview-${totalPages}" src="" alt="미리보기 이미지" style="display:none; max-width:100%; height:auto;">
            </div>

            <div class="time-limit">
                <label>제한시간</label>
                <input type="radio" name="time-${totalPages}" value="15"> 15초
                <input type="radio" name="time-${totalPages}" value="30"> 30초
                <input type="radio" name="time-${totalPages}" value="45"> 45초
            </div>

            <div class="hint-answer">
                <label for="hint-${totalPages}">힌트:</label>
                <input type="text" id="hint-${totalPages}" name="hint-${totalPages}" placeholder="없을 경우 '.'을 입력해 주세요">
                
                <label for="answer-${totalPages}">정답:</label>
                <input type="text" id="answer-${totalPages}" name="answer-${totalPages}" placeholder="정답을 입력해 주세요">
            </div>
            <div class="buttons">
                <button class="home-btn">
                    <a href="../main.me"> 
                        <img src="../static/img/homebtn.png" width="130px" height="45px">
                    </a>
                </button>
                <button class="create-btn" onclick="saveQuiz()">질문 생성하기</button>
            </div>
        `;
        quizLayout.appendChild(newPage);

        // 새로 추가된 페이지로 이동
        goToPage(totalPages);

        // 파일 입력의 change 이벤트 리스너 추가
        const fileInput = newPage.querySelector(`#file-${totalPages}`);
        const previewImage = newPage.querySelector(`#preview-${totalPages}`);
        
        fileInput.addEventListener('change', function(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    previewImage.src = e.target.result; // 미리보기 이미지 경로 설정
                    previewImage.style.display = 'block'; // 미리보기 이미지 표시
                };
                reader.readAsDataURL(file); // 파일을 데이터 URL로 읽기
            } else {
                previewImage.src = ""; // 파일이 선택되지 않았을 때 미리보기 이미지 초기화
                previewImage.style.display = 'none'; // 미리보기 이미지 숨김
            }
        });

        // 첫 번째 페이지의 파일 입력 이벤트 리스너 추가
        if (totalPages === 2) {
            const firstFileInput = document.querySelector(`#file-1`);
            const firstPreviewImage = document.querySelector(`#preview-1`);

            firstFileInput.addEventListener('change', function(event) {
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function(e) {
                        firstPreviewImage.src = e.target.result; // 미리보기 이미지 경로 설정
                        firstPreviewImage.style.display = 'block'; // 미리보기 이미지 표시
                    };
                    reader.readAsDataURL(file); // 파일을 데이터 URL로 읽기
                } else {
                    firstPreviewImage.src = ""; // 파일이 선택되지 않았을 때 미리보기 이미지 초기화
                    firstPreviewImage.style.display = 'none'; // 미리보기 이미지 숨김
                }
            });
        }
    }

    // 페이지 이동 함수
    function goToPage(pageNumber) {
        const allPages = document.querySelectorAll('.quiz-content');
        allPages.forEach(page => {
            page.style.display = 'none';
        });
        const targetPage = allPages[pageNumber - 1];
        if (targetPage) {
            targetPage.style.display = 'block';
            currentPage = pageNumber;
            updatePageCount();
        }
    }

    // 질문 삭제 기능
    function deleteLastQuestion() {
        if (totalPages > 1) {
            questionList.removeChild(questionList.lastElementChild.previousElementSibling);
            quizLayout.removeChild(quizLayout.lastElementChild);
            totalPages--;
            goToPage(totalPages); // 현재 페이지를 마지막 페이지로 변경
        }
    }

    // 질문 리스트의 항목을 클릭하면 해당 페이지로 이동
    questionList.addEventListener('click', function (event) {
        const clickedItem = event.target.closest('.question-item');
        if (clickedItem) {
            const clickedPageNumber = Array.from(questionList.children).indexOf(clickedItem) + 1;
            goToPage(clickedPageNumber); // 페이지 이동 함수 호출
        }
    });

    // '질문 추가' 버튼 클릭 이벤트
    addQuestionBtn.addEventListener('click', addNewQuestion);

    // '삭제하기' 버튼 클릭 이벤트
    deleteQuestionBtn.addEventListener('click', deleteLastQuestion);

    // 첫 번째 페이지로 이동
    updatePageCount();
    goToPage(1);

    // 첫 페이지의 파일 입력 이벤트 리스너 추가
    const initialFileInput = document.querySelector(`#file-1`);
    const initialPreviewImage = document.querySelector(`#preview-1`);
    if (initialFileInput) {
        initialFileInput.addEventListener('change', function(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    initialPreviewImage.src = e.target.result; // 미리보기 이미지 경로 설정
                    initialPreviewImage.style.display = 'block'; // 미리보기 이미지 표시
                };
                reader.readAsDataURL(file); // 파일을 데이터 URL로 읽기
            } else {
                initialPreviewImage.src = ""; // 파일이 선택되지 않았을 때 미리보기 이미지 초기화
                initialPreviewImage.style.display = 'none'; // 미리보기 이미지 숨김
            }
        });
    }
});
