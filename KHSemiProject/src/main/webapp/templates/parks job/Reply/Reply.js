document.querySelector('.close-btn').addEventListener('click', function() {
    document.querySelector('.comment-section').style.display = 'none';
});

document.querySelectorAll('.report-btn').forEach(button => {
    button.addEventListener('click', function() {
        alert('신고가 접수되었습니다.');
    });
});

const commentInput = document.getElementById('commentInput');
const commentList = document.querySelector('.comments');

// 댓글 입력 후 Enter 키를 누르면 새로운 댓글 추가
commentInput.addEventListener('keypress', function(event) {
    if (event.key === 'Enter' && commentInput.value.trim() !== '') {
        // 새로운 댓글 생성
        const newComment = document.createElement('li');
        newComment.innerHTML = `
            <span class="user">user01</span>
            <span class="comment">${commentInput.value}</span>
        `;
        // 댓글 목록에 추가
        commentList.appendChild(newComment);

        // 입력창 초기화
        commentInput.value = '';

        // 댓글 개수 업데이트
        updateCommentCount();
    }
});

// 댓글 개수 업데이트 함수
function updateCommentCount() {
    const commentCount = document.querySelector('.header span');
    const commentItems = document.querySelectorAll('.comments li').length;
    commentCount.textContent = `댓글 ${commentItems}`;
}
