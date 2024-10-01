function initSide(){
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
                    "<td class='tab' data-tab-no=\'"+ board.communityTabNo +"\' onclick=\"location.href='community?cpage=1&tno=" + board.communityTabNo +"\'\">" + board.communityTab + "</td>" +
                    "<td class='title' onclick=\"location.href='board?cpage=1&no="+ board.communityNo 
                                                                +"&comment=1&tno="+ board.communityTabNo +"\'\">" + board.communityTitle + "</td>" +
                    "<td class='author'>" + board.memberId + "</td>" +
                    "<td class='comment-num'>["+ board.commentCount + "]<img src='static/img/comment-icon.png'></td>" +
                    "</tr>"
                )
            }
            /* i < 5 조건 하드코딩 나중에 고치자 */
            for (let i = res.length; i < 5; i++) {
                str += (
                    "<tr>" +
                    "<td>&nbsp;</td>" +
                    "<td>&nbsp;</td>" +
                    "<td>&nbsp;</td>" +
                    "<td>&nbsp;</td>" +
                    "</tr>"
                );
            }

            const popList = document.querySelector(".hot-bulletin-list > table");
            if(str === ""){
                str += "현재 조회된 인기글이 없습니다."
            }
            popList.innerHTML = str;

            const categoryList = document.querySelectorAll(".hot-bulletin-list td.tab");

            for(let category of categoryList){
                switch(category.getAttribute("data-tab-no")) {
                    case "1":
                        category.style.color = "#FF4040";
                        break;
                    case "2":
                        category.style.color = "#55C62C";
                        break;
                    case "3":
                        category.style.color = "#407BFF";
                        break;
                    case "4":
                        category.style.color = "#FF4DED";
                        break;
                    default :
                        category.style.color = "black";
                        break;
                }
            }
        },
        error : function(){
            console.log("인기글 조회 실패");
        }
    })
}