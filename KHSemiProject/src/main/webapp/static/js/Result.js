document.addEventListener('DOMContentLoaded', () => {
    const stars = document.querySelectorAll('.star');
    const ratingValueDisplay = document.getElementById('rating-value');
    let rating = 0;

    // 별 클릭 이벤트 핸들러
    stars.forEach((star, index) => {
        star.addEventListener('click', () => {
            // 클릭한 별이 현재 선택된 별과 같으면 별점을 초기화
            if (rating === index + 1) {
                rating = 0;
            } else {
                rating = index + 1;
            }
            updateStars(rating);
            ratingValueDisplay.textContent = rating > 0 ? `선택된 별점: ${rating}점` : '별점을 통해 문제를 평가해주세요.';
        });
    });

    // 선택된 별점에 따라 별 모양 업데이트
    function updateStars(rating) {
        stars.forEach((star, index) => {
            if (index < rating) {
                star.classList.add('selected');
                star.classList.remove('unselected');
            } else {
                star.classList.add('unselected');
                star.classList.remove('selected');
            }
        });
    }
});
