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