

function selectTab(_this){
    const blist = document.querySelectorAll(".tab-select button");
    const tabInput = document.querySelector("input[name=tab]");

    _this.style.backgroundColor = "#FF9139"
    tabInput.value = _this.value

    for(let button of blist){
        if(button.innerText !== _this.innerText){
            button.style.backgroundColor = "#D9D9D9"
        }
    }
}

function checkEmptyTab(){
    const text =  document.querySelector("textarea[name=content]");
    text.replace(/\r\n/g, '<br/>'); /* 엔터입력 처리 */

    const tabInput = document.querySelector("input[name=tab]");

    if(tabInput.value === ""){
        alert("게시글의 탭이 설정되지 않았습니다.");
        return false;
    }
    else{
        return true;
    }
}
/*

const maxFileNum = 5;
let curFileNum = 0;

function loadImg(_input){
    //_input.files[0] -> 선택된 파일이 담김
    // 파일을 읽어들이는 객체

    if(_input.files.length == 1){ // 파일이 하나 선택됬다면
        const reader = new FileReader();

        // 해당 파일을 읽어들여 해당 파일만의 url 부여
        reader.readAsDataURL(_input.files[0])
        curFileNum++;

        reader.onload = function(e){
            const content = document.querySelector("#content");

            let imgTag = document.createElement("img")
           // imgTag.src = URL.createObjectURL(_input.files[0])
            imgTag.src = e.target.result;
            imgTag.alt = "첨부이미지" + curFileNum;
            imgTag.style.display='block';
            document.querySelector("div[name=content]").appendChild(imgTag);
        }
    } else{
        curFileNum--;
    }

}


function chooseImg(){
    if(curFileNum < maxFileNum){
        const fileInput = document.querySelector("#file" + (curFileNum+1));
        fileInput.click();
    } else {
        alert("파일은 최대 5개까지 업로드 가능합니다.");
    }
}
*/