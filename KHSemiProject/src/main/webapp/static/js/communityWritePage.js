function initWrite(){
    /* 
        https://ko.javascript.info/selection-range
        https://dev.to/_moehab/documentexeccommand-alternative-5a55
    */
    const content = document.querySelector('.content-input div[name=content]');
    const fontBold = document.getElementById('font-bold');
    const fontItalic = document.getElementById('font-italic');
    const fontUnderlined = document.getElementById('font-underlined');
    const fontStrikeThrough = document.getElementById('font-strikethrough');
    const fontSize = document.getElementById('font-size');
    const fontColor = document.getElementById('font-color');
    const fontfill = document.getElementById('font-fill');

    fontBold.addEventListener('click', function(){
        const userSelection = window.getSelection();
        const selectedTextRange = userSelection.getRangeAt(0);

        let node = document.createElement('b');
        selectedTextRange.surroundContents(node);
        
    });

    fontItalic.addEventListener('click', function(){
        const userSelection = window.getSelection();
        const selectedTextRange = userSelection.getRangeAt(0);

        let node = document.createElement('i');
        selectedTextRange.surroundContents(node);
    });

    fontUnderlined.addEventListener('click', function(){
        const userSelection = window.getSelection();
        const selectedTextRange = userSelection.getRangeAt(0);

        let node = document.createElement('u');
        selectedTextRange.surroundContents(node);
    });

    fontStrikeThrough.addEventListener('click', function(){
        const userSelection = window.getSelection();
        const selectedTextRange = userSelection.getRangeAt(0);

        let node = document.createElement('s');
        selectedTextRange.surroundContents(node);
    });

    fontSize.addEventListener('click', function(){
        const userSelection = window.getSelection();
        const selectedTextRange = userSelection.getRangeAt(0);

        let node = document.createElement('b');
        selectedTextRange.surroundContents(node);
    });

    fontColor.addEventListener('click', function(){
        const userSelection = window.getSelection();
        const selectedTextRange = userSelection.getRangeAt(0);

        let node = document.createElement('p');
        selectedTextRange.surroundContents(node);
    });

    fontfill.addEventListener('click', function(){
        const userSelection = window.getSelection();
        const selectedTextRange = userSelection.getRangeAt(0);

        let node = document.createElement('p');
        selectedTextRange.surroundContents(node);
    });
}

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

function checkEmptyTab(_this){
    const text =  _this.querySelector("textarea[name=content]");
    text.value = text.value.replace(/(?:\r\n|\r|\n)/g, '<br>'); /* 엔터입력 처리 */

    const tabInput = document.querySelector("input[name=tab]");

    if(tabInput.value === ""){
        alert("게시글의 탭이 설정되지 않았습니다.");
        return false;
    }
    else{
        return true;
    }
}

function loadImg(_input, num){
    //_input.files[0] -> 선택된 파일이 담김
    // 파일을 읽어들이는 객체

    if(_input.files.length == 1){ // 파일이 하나 선택됬다면
        const reader = new FileReader();

        // 해당 파일을 읽어들여 해당 파일만의 url 부여
        reader.readAsDataURL(_input.files[0])

        // 파일 읽어들이기가 완료됬다면 실행되는 함수
        reader.onload = function(e){
            switch(num){
                case 1:
                    document.getElementById("contain-img1").src = e.target.result;
                    break;
                case 2:
                    document.getElementById("contain-img2").src = e.target.result;
                    break;
                case 3:
                    document.getElementById("contain-img3").src = e.target.result;
                    break;
                case 4:
                    document.getElementById("contain-img4").src = e.target.result;
                    break;
                case 5:
                    document.getElementById("contain-img5").src = e.target.result;
                    break;
            }
        }
    } else{
        switch(num){
            case 1:
                document.getElementById("contain-img1").src = null;
                break;
            case 2:
                document.getElementById("contain-img2").src = null;
                break;
            case 3:
                document.getElementById("contain-img3").src = null;
                break;
            case 4:
                document.getElementById("contain-img4").src = null;
                break;
            case 5:
                document.getElementById("contain-img5").src = null;
                break;
        }
    }

}


function chooseImg(num){
    const fileInput = document.querySelector("#file" + num);
    fileInput.click();
}

/*
function loadImg(_input){
    //_input.files[0] -> 선택된 파일이 담김
    // 파일을 읽어들이는 객체

    if(_input.files.length == 1){ // 파일이 하나 선택됬다면
        const reader = new FileReader();

        // 해당 파일을 읽어들여 해당 파일만의 url 부여
        reader.readAsDataURL(_input.files[0])
        curFileNum++;

        // 파일 읽어들이기가 완료됬다면 실행되는 함수
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
*/