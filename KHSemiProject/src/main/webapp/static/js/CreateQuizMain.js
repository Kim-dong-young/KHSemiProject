// 썸네일 이미지 미리 보기 함수
document.getElementById('thumbnail').addEventListener('change', function(event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function() {
        const preview = document.getElementById('thumbnail-preview');
        preview.innerHTML = `<img src="${reader.result}" alt="Thumbnail" style="width:100%; height:100%;">`;
    };

    if (file) {
        reader.readAsDataURL(file);
    }
});

// 퀴즈 저장 함수
function saveQuiz() {
    const title = document.getElementById('quiz-title').value;
    const description = document.getElementById('quiz-description').value;
    const category = document.getElementById('category').value;
    const tag = document.getElementById('tag').value;

    const quizData = {
        title: title,
        description: description,
        category: category,
        tag: tag
    };

    // 로컬 스토리지에 저장
    localStorage.setItem('quizData', JSON.stringify(quizData));
    alert('퀴즈가 저장되었습니다!');
}
