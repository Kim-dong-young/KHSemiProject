function errorAlert(){
    const errorMsg = document.getElementById("errorMsg").value

    if(errorMsg){
        alert(errorMsg.value);
    }

    errorMsg.value=""
}