function init(tno){
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