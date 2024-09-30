function initSide(){
    console.log("initSide")
    selectPopBoard();
    setInterval(selectPopBoard, 3000);
}

function selectPopBoard(){
    $.ajax({
        url:"poplist.bo",
        method :"post",
        success: function(res){
            let str = ""
            for(let board of res){
                str += (
                    "<tr>"+
                    "<td class='tab'>" + board.communityTab + "</td>" +
                    "<td class='title'>" + board.communityTitle + "</td>" +
                    "<td class='author'>" + board.memberId + "</td>" +
                    "<td class='comment-num'>["+ board.commentCount + "]<img src='static/img/comment-icon.png'></td>" +
                    "</tr>"
                )
            }

            const popList = document.querySelector(".hot-bulletin-list > table");
            if(str === ""){
                str += "현재 조회된 인기글이 없습니다."
            }
            popList.innerHTML = str;
        },
        error : function(){
            console.log("인기글 조회 실패");
        }
    })
}