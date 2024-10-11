function checkPwd(){
    const pwd = document.querySelector("#enroll-form input[name=memberPwd]").value
    const pwdCheck = document.querySelector("#enroll-form input[name=memberPwdCheck]").value

    if(pwd !== pwdCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
}

function idInput() {
    const idInput = document.querySelector("#enroll-form input[name='memberId']");
        let eventFlag;

        idInput.onkeyup = function(ev){ //키가 입력될때마다 호출
            clearTimeout(eventFlag); // 아직 실행되지 않은 ajax요청을 취소
            
            const str = ev.target.value;
            if(str.trim().length >= 5){ 
                    eventFlag = setTimeout(function(){ // 1초뒤에 값을 확인해서 ajax요청
                    // ajax요청
                    $.ajax({
                        url: "idCheck.me",
                        data: {checkId: str},
                        success : function(result){
                            const checkResult = document.querySelector("#checkResult");

                            if(result === "NNNNN"){
                                checkResult.style.color = "red";
                                checkResult.innerText = "이미 사용중인 아이디입니다.";

                                document.querySelector("#enrollForm button[type='submit']").disabled = true;
                            } else {
                                checkResult.style.color = "green";
                                checkResult.innerText = "사용가능한 아이디입니다.";

                                document.querySelector("#enrollForm button[type='submit']").disabled = false;
                            }
                        }, error : function(){
                            console.log("아이디 중복체크 ajax 실패")
                        }
                    })
                }, 1000)
            } else {
                document.querySelector("#enrollForm button[type='submit']").disabled = true;
                document.querySelector("#checkResult").style.display = "none";
            }
        }
}