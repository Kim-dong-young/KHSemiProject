function initBoard(tno){
    const blist = document.querySelectorAll(".board-tab button");
    const tab = tno !== null ? document.querySelector("#t"+tno) : document.querySelector("#t") ;

    tab.style.backgroundColor = "#FF9139"

    const categoryList = document.querySelectorAll(".tab");
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
}   
