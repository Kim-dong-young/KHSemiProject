let rating = 0;
const starCbtn = document.getElementById('starconfirm');
const memberNoinfo = document.getElementById('memberNoInfo');
const starHandlers = [];


document.addEventListener('DOMContentLoaded', () => {
    const stars = document.querySelectorAll('.star');
    const ratingValueDisplay = document.getElementById('rating-value');

    
    console.log(memberNoinfo.value)
    if(memberNoinfo.value){
        // 별 클릭 이벤트 핸들러
        stars.forEach((star, index) => {
            const handler = () => onClickStar(index);
            starHandlers.push(handler);
            star.addEventListener('click', handler);
        });

        function onClickStar(index){
            if (rating === index + 1) {
                rating = 0;
            } else {
                rating = index + 1;
            }
            updateStars(rating);
            ratingValueDisplay.textContent = rating > 0 ? `선택된 별점: ${rating}점` : '별점을 통해 문제를 평가해주세요.';
        }

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
        $.ajax({
            url: "rankCheck.pl",
            contentType: "application/json",
            type: "GET",
            data:{
                qNum: starCbtn.value,
                mNum: memberNoinfo.value
            },
            success: function(res){
                if(res){
                    updateStars(res);
                    stars.forEach((star, index) => {
                        star.removeEventListener('click', starHandlers[index]);
                    });
                    starCbtn.innerText = "이미 별점을 등록하셨습니다."
                    document.getElementById("rating-value").textContent = "";
                    starCbtn.setAttribute('disabled', true);
                }
            },
            error: function(){
                console.log("별점확인용 ajax 통신 실패")
            }
        })
    } else {
        stars.forEach((star, index) => {
            star.removeEventListener('click', starHandlers[index]);
        });
        document.getElementById('rating-value').innerText = '로그인 후 별점등록 가능합니다.';
        starCbtn.setAttribute('style', "display: none;");
    }
    starCbtn.addEventListener('click', function(){
    $.ajax({
        url: "rankConfirm.pl",
        contentType: "application/json",
        type: "GET",
        data:{
            rating: rating,
            qNum: starCbtn.value,
            mNum: memberNoinfo.value
        },
        success: function(res){
            if(res){
                alert("별점이 정상적으로 등록되었습니다.")
                stars.forEach((star, index) => {
                    star.removeEventListener('click', starHandlers[index]);
                });
                starCbtn.setAttribute('disabled', true);
            }
        },
        error: function(){
            console.log("별점주기용 ajax 통신 실패")
        }
    })
})
    
});



function reportBoard(bno, mno){ // 피신고자 커뮤니티 번호, 멤버번호 
    const checkedButton = document.querySelector('#reportForm input[type="radio"][name="reportNumber"]:checked');
    const reportReason = document.querySelector("#reportForm textarea[name=reportReason]");

    $.ajax({
        url : "report.qz",
        type: "post",
        data : {    
            quizNo : bno,                   //신고당한 퀴즈 번호
            reportedMemberNo : mno,         //신고한 사람 맴버번호
            reportNumber : checkedButton.value, //신고유형
            reportReason : reportReason.value   //신고내용
        },
        success : function(res) {
            if(res > 0) {
                alert("성공적으로 신고되었습니다.");
            } else {
                alert("이미 신고한 글입니다.");
            }
        },
        error : function() {
            console.log("신고 AJAX 실패");
        }
    })
}