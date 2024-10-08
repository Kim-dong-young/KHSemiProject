document.addEventListener('DOMContentLoaded', function() {
	const questionList = document.querySelector('.question-list');
	const addQuestionBtn = document.querySelector('.add-question-btn');
	const deleteQuestionBtn = document.querySelector('.delete-question-btn');
	const quizLayout = document.querySelector('.quiz-layout');
	let currentPage = 1;
	let totalPages = 1;

	// 페이지 수 업데이트 함수 (현재 페이지와 총 페이지 표시)
	function updatePageCount() {
		const progressElements = document.querySelectorAll('.progress');
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
                <input type="file" id="file-${totalPages}">
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
				<button class="home-btn"
					onclick="window.location.href='<%=contextPath%>/main.me'">
					<img src="<%=contextPath%>/static/img/homebtn.png" width="130px"
						height="45px">
				</button>
				<button class="create-btn" onclick="saveQuiz()" onclick="window.location.href='<%=contextPath%>/main.me'">질문 생성하기</button>
			</div>
			
        `;
		quizLayout.appendChild(newPage);

		// 새로 추가된 페이지로 이동
		goToPage(totalPages);
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
			goToPage(totalPages);
		}
	}

	// 질문 리스트의 항목을 클릭하면 해당 페이지로 이동
	questionList.addEventListener('click', function(event) {
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

	// 퀴즈 데이터를 서버에 저장하는 함수
	function saveQuiz() {
		const formData = new FormData();

		for (let i = 1; i <= totalPages; i++) {
			formData.append(`question_${i}`, document.getElementById(`question-${i}`).value);
			formData.append(`hint_${i}`, document.getElementById(`hint-${i}`).value);
			formData.append(`answer_${i}`, document.getElementById(`answer-${i}`).value);
			const fileInput = document.getElementById(`file-${i}`);
			if (fileInput.files.length > 0) {
				formData.append(`file_${i}`, fileInput.files[0]);
			}
			const selectedTime = document.querySelector(`input[name="time-${i}"]:checked`);
			if (selectedTime) {
				formData.append(`time_${i}`, selectedTime.value);
			}
		}

		// Ajax 요청으로 데이터를 서버에 전송
		const xhr = new XMLHttpRequest();
		xhr.open('POST', '/your-context-path/problemsController', true); // 실제 경로로 수정
		xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest"); // AJAX 요청임을 명시

		xhr.onload = function() {
			if (xhr.status === 200) {
				const response = JSON.parse(xhr.responseText);
				if (response.status === "success") {
					alert('퀴즈가 성공적으로 저장되었습니다.');
				} else {
					alert('퀴즈 저장 중 오류가 발생했습니다.');
				}
			} else {
				alert('퀴즈 저장 중 오류가 발생했습니다.');
			}
		};

		xhr.send(formData);
	}

	// '질문 생성하기' 버튼 클릭 이벤트
	document.querySelector('.create-btn').addEventListener('click', function(event) {
		event.preventDefault(); // 폼 제출을 막고 Ajax로 처리
		saveQuiz();
	});

	// 첫 번째 페이지로 이동
	updatePageCount();
	goToPage(1);
});
