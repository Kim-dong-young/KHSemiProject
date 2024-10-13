function signinit() {
    const path = document.querySelector('#enroll-form input[name="sPath"]');

    if(path){
        path.value = location.href;
    }

    idInput()
    pwdInput()
}

function checkPwd(){
    const pwd = document.querySelector("#enroll-form input[name=sMemberPwd]").value
    const pwdCheck = document.querySelector("#enroll-form input[name=sCheckMemberPwd]").value

    if(pwd !== pwdCheck) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
}

function pwdInput() {
    const pwd = document.querySelector("#enroll-form input[name=sMemberPwd]")
    const pwdCheck = document.querySelector("#enroll-form input[name=sCheckMemberPwd]")
    let eventFlag;

    pwdCheck.onkeyup = function(ev) {
        clearTimeout(eventFlag);

        const str = ev.target.value;
        if(pwd.value !== null && str !== null) {
            document.querySelector("#checkPwd").style.display = "block";

            eventFlag = setTimeout(function(){
                if(pwd.value !== str) {
                    document.querySelector("#checkPwd").innerText = "비밀번호가 일치하지 않습니다."
                    document.querySelector("#checkPwd").style.color = "red"
    
                } else {
                    document.querySelector("#checkPwd").innerText = "비밀번호가 일치합니다."
                    document.querySelector("#checkPwd").style.color = "green"
                }
            }, 500)
        } else {
            document.querySelector("#checkPwd").style.display = "none";
        }
    }

    
}

function idInput() {
    const idInput = document.querySelector("#enroll-form input[name='sMemberId']");
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
                        const checkResult = document.querySelector("#checkId");
                        checkResult.style.display = "block";

                        if(result === "NNNNN"){
                            checkResult.style.color = "red";
                            checkResult.innerText = "이미 사용중인 아이디입니다.";

                            document.querySelector(".custom-lsin-btn.signin[type='submit']").disabled = true;
                        } else {
                            checkResult.style.color = "green";
                            checkResult.innerText = "사용가능한 아이디입니다.";

                            document.querySelector(".custom-lsin-btn.signin[type='submit']").disabled = false;
                        }
                    }, error : function(){
                        console.log("아이디 중복체크 ajax 실패")
                    }
                })
            }, 1000)
        } else {
            document.querySelector(".custom-lsin-btn.signin[type='submit']").disabled = true;
            document.querySelector("#checkId").style.display = "none";
        }
    }
}