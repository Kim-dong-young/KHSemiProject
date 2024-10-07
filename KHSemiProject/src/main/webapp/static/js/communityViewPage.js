function initView(tno){
    const errorMsg = document.getElementById("errorMsg").value
    history.scrollRestoration = "auto";

    if(errorMsg){
        alert(errorMsg.value);
    }

    errorMsg.value=""

    const blist = document.querySelectorAll(".board-tab button");
    const tab = tno !== null ? document.querySelector("#t"+tno) : document.querySelector("#t") ;

    tab.style.backgroundColor = "#FF9139"
}

function increaseLike(boardNo){
    const like = document.querySelector(".data-info span[name=likeCount]");
    $.ajax({
        url : "like.bo",
        type : "post",
        data : {
            boardNo : boardNo
        },
        success : function(res){
            if(res > 0) {
                like.innerHTML = "좋아요: " + res;
            } else {
                alert("이미 좋아요를 눌렀습니다.");
            }
        },
        error : function(){
            alert("서버와의 통신에 실패하였습니다.");
        }
    })
}

function writeReply(cno){
    const replyform = document.querySelector("form[name=reply-form-"+cno+"]");

    if(replyform.style.display === "none"){
        replyform.style.display = "block";
    } else {
        replyform.style.display = "none";
    }
}

function submitComment(_this){
    const text =  _this.querySelector("textarea[name=commentContent]");
    text.value = text.value.replace(/(?:\r\n|\r|\n)/g, '<br>');

    if(text.value.trim() === ""){
        alert("공백 댓글은 입력하실 수 없습니다.")
        return false;
    } else{
        return true;
    }
}

function changeSubmitBtnBoard(bno, mno){ // 피신고자 커뮤니티 번호, 멤버번호 
    const reportSubmitBtn = document.getElementById('report-submit-button');
    reportSubmitBtn.setAttribute('onclick','reportBoard('+ bno +',' + mno + ')');
}

function changeSubmitBtnComment(cno, mno){ // 피신고자 댓글 번호, 멤버번호
    const reportSubmitBtn = document.getElementById('report-submit-button');
    reportSubmitBtn.setAttribute('onclick','reportComment('+ cno +',' + mno + ')');
}

function reportBoard(bno, mno){ // 피신고자 커뮤니티 번호, 멤버번호 
    const checkedButton = document.querySelector('#reportForm input[type="radio"][name="reportNumber"]:checked');
    const reportReason = document.querySelector("#reportForm textarea[name=reportReason]");

    $.ajax({
        url : "report.bo",
        type: "post",
        data : {
            communityNo : bno,
            reportedMemberNo : mno,
            reportNumber : checkedButton.value,
            reportReason : reportReason.value
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

function reportComment(cno, mno){
    const checkedButton = document.querySelector('#reportForm input[type="radio"][name="reportNumber"]:checked');
    const reportReason = document.querySelector("#reportForm textarea[name=reportReason]");


    $.ajax({
        url : "report.co",
        type: "post",
        data : {
            commentNo : cno,
            reportedMemberNo : mno,
            reportNumber : checkedButton.value,
            reportReason : reportReason.value
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