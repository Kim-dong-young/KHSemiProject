function init(){
    const errorMsg = document.getElementById("errorMsg").value
    history.scrollRestoration = "auto";

    if(errorMsg){
        alert(errorMsg.value);
    }

    errorMsg.value=""
}