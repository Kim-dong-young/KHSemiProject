function initBoard(tno){
    const blist = document.querySelectorAll(".board-tab button");
    const tab = tno !== null ? document.querySelector("#t"+tno) : document.querySelector("#t") ;

    tab.style.backgroundColor = "#FF9139"
}