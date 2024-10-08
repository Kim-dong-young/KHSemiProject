function initWrite(){
    /* 
        https://ko.javascript.info/selection-range
        https://dev.to/_moehab/documentexeccommand-alternative-5a55
        https://jungpaeng.tistory.com/86
        https://stackoverflow.com/questions/4479151/javascript-how-to-un-surroundcontents-range
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

        const isAllowedContainer = userSelection.baseNode.parentElement?.closest?.('#editor');

        // 선택된 텍스트가 없거나, 에디터 밖이면 종료
        if( userSelection.rangeCount < 1 || !isAllowedContainer ) return;
        
        const selectedTextRange = userSelection.getRangeAt(0); // 드래그한 부분의 range 객체 반환
        const parentElement = userSelection.anchorNode?.parentElement;  // 드래그한 부분을 감싸는 태그 선택
        // nodeType = 1 은 해당 부모요소가 <> 태그요소임을 의미
        // ?. = 옵셔널 체이닝은 존재하지 않을 수 있는 프로퍼티 또는 메서드를 안전하게 호출할 수 있도록 도와줍니다.
        // 태그요소이고, 중첩된 태그가 아닐때 parentElement를 할당한다. 하나라도 만족하지 않으면 중간에 undefined 반환
        const selectedElem = parentElement?.nodeType == 1 && parentElement?.children.length < 2 && parentElement;
        
        // 이미 해당 스타일 태그로 감싸져 있다면, 스타일 제거
        if(selectedElem.tagName === 'B') {
            selectedElem.replaceWith(...selectedElem.childNodes);
        } 
        // 스타일을 적용
        else {
            const newElem = document.createElement('b');
            selectedTextRange.surroundContents(newElem);
            userSelection.removeAllRanges();
            userSelection.addRange(range);
            selectedTextRange.collapse(); // 스타일 적용 후 커서를 텍스트 뒤로 이동
        }
        
    });

    fontItalic.addEventListener('click', function(){
        const userSelection = window.getSelection();

        const isAllowedContainer = userSelection.baseNode.parentElement?.closest?.('#editor');

        // 선택된 텍스트가 없거나, 에디터 밖이면 종료
        if( userSelection.rangeCount < 1 || !isAllowedContainer ) return;
        
        const selectedTextRange = userSelection.getRangeAt(0); // 드래그한 부분의 range 객체 반환
        const parentElement = userSelection.anchorNode?.parentElement;  // 드래그한 부분을 감싸는 최상단 태그 선택
        // nodeType = 1 은 해당 부모요소가 <> 태그요소임을 의미
        // ?. = 옵셔널 체이닝은 존재하지 않을 수 있는 프로퍼티 또는 메서드를 안전하게 호출할 수 있도록 도와줍니다.
        // 태그요소이고, 중첩된 태그가 아닐때 parentElement를 할당한다. 하나라도 만족하지 않으면 중간에 undefined 반환
        const selectedElem = parentElement?.nodeType == 1 && parentElement?.children.length < 2 && parentElement;
        
        // 이미 해당 스타일 태그로 감싸져 있다면, 스타일 제거
        if(selectedElem.tagName === 'I') {
            selectedElem.replaceWith(...selectedElem.childNodes);
        } 
        // 스타일을 적용
        else {
            const newElem = document.createElement('i');
            selectedTextRange.surroundContents(newElem);
            userSelection.removeAllRanges();
            userSelection.addRange(range);
            selectedTextRange.collapse(); // 스타일 적용 후 커서를 텍스트 뒤로 이동
        }
    });

    fontUnderlined.addEventListener('click', function(){
        const userSelection = window.getSelection();

        const isAllowedContainer = userSelection.baseNode.parentElement?.closest?.('#editor');

        // 선택된 텍스트가 없거나, 에디터 밖이면 종료
        if( userSelection.rangeCount < 1 || !isAllowedContainer ) return;
        
        const selectedTextRange = userSelection.getRangeAt(0); // 드래그한 부분의 range 객체 반환
        const parentElement = userSelection.anchorNode?.parentElement;  // 드래그한 부분을 감싸는 태그 선택
        // nodeType = 1 은 해당 부모요소가 <> 태그요소임을 의미
        // ?. = 옵셔널 체이닝은 존재하지 않을 수 있는 프로퍼티 또는 메서드를 안전하게 호출할 수 있도록 도와줍니다.
        // 태그요소이고, 중첩된 태그가 아닐때 parentElement를 할당한다. 하나라도 만족하지 않으면 중간에 undefined 반환
        const selectedElem = parentElement?.nodeType == 1 && parentElement?.children.length < 2 && parentElement;
        
        // 이미 해당 스타일 태그로 감싸져 있다면, 스타일 제거
        if(selectedElem.tagName === 'U') {
            selectedElem.replaceWith(...selectedElem.childNodes);
        } 
        // 스타일을 적용
        else {
            const newElem = document.createElement('u');
            selectedTextRange.surroundContents(newElem);
            userSelection.removeAllRanges();
            userSelection.addRange(range);
            selectedTextRange.collapse(); // 스타일 적용 후 커서를 텍스트 뒤로 이동
        }
    });

    fontStrikeThrough.addEventListener('click', function(){
        const userSelection = window.getSelection();

        const isAllowedContainer = userSelection.baseNode.parentElement?.closest?.('#editor');

        // 선택된 텍스트가 없거나, 에디터 밖이면 종료
        if( userSelection.rangeCount < 1 || !isAllowedContainer ) return;
        
        const selectedTextRange = userSelection.getRangeAt(0); // 드래그한 부분의 range 객체 반환
        const parentElement = userSelection.anchorNode?.parentElement;  // 드래그한 부분을 감싸는 태그 선택
        // nodeType = 1 은 해당 부모요소가 <> 태그요소임을 의미
        // ?. = 옵셔널 체이닝은 존재하지 않을 수 있는 프로퍼티 또는 메서드를 안전하게 호출할 수 있도록 도와줍니다.
        // 태그요소이고, 중첩된 태그가 아닐때 parentElement를 할당한다. 하나라도 만족하지 않으면 중간에 undefined 반환
        const selectedElem = parentElement?.nodeType == 1 && parentElement?.children.length < 2 && parentElement;
        
        // 이미 해당 스타일 태그로 감싸져 있다면, 스타일 제거
        if(selectedElem.tagName === 'S') {
            selectedElem.replaceWith(...selectedElem.childNodes);
        } 
        // 스타일을 적용
        else {
            const newElem = document.createElement('s');
            selectedTextRange.surroundContents(newElem);
            userSelection.removeAllRanges();
            userSelection.addRange(range);
            selectedTextRange.collapse(); // 스타일 적용 후 커서를 텍스트 뒤로 이동
        }
    });

    fontSize.addEventListener('click', function(){
        const userSelection = window.getSelection();

        const isAllowedContainer = userSelection.baseNode.parentElement?.closest?.('#editor');

        // 선택된 텍스트가 없거나, 에디터 밖이면 종료
        if( userSelection.rangeCount < 1 || !isAllowedContainer ) return;
        
        const selectedTextRange = userSelection.getRangeAt(0); // 드래그한 부분의 range 객체 반환
        const parentElement = userSelection.anchorNode?.parentElement;  // 드래그한 부분을 감싸는 태그 선택
        // nodeType = 1 은 해당 부모요소가 <> 태그요소임을 의미
        // ?. = 옵셔널 체이닝은 존재하지 않을 수 있는 프로퍼티 또는 메서드를 안전하게 호출할 수 있도록 도와줍니다.
        // 태그요소이고, 중첩된 태그가 아닐때 parentElement를 할당한다. 하나라도 만족하지 않으면 중간에 undefined 반환
        const selectedElem = parentElement?.nodeType == 1 && parentElement?.children.length < 2 && parentElement;
        
        // 이미 해당 스타일 태그로 감싸져 있다면, 스타일 제거
        if(selectedElem.tagName === 'SPAN') {
            selectedElem.replaceWith(...selectedElem.childNodes);
        } 
        // 스타일을 적용
        else {
            const newElem = document.createElement('span');
            newElem.style.fontSize = '24px';

            selectedTextRange.surroundContents(newElem);
            userSelection.removeAllRanges();
            userSelection.addRange(range);
            selectedTextRange.collapse(); // 스타일 적용 후 커서를 텍스트 뒤로 이동
        }
    });

    fontColor.addEventListener('click', function(){
        const userSelection = window.getSelection();

        const isAllowedContainer = userSelection.baseNode.parentElement?.closest?.('#editor');

        // 선택된 텍스트가 없거나, 에디터 밖이면 종료
        if( userSelection.rangeCount < 1 || !isAllowedContainer ) return;
        
        const selectedTextRange = userSelection.getRangeAt(0); // 드래그한 부분의 range 객체 반환
        const parentElement = userSelection.anchorNode?.parentElement;  // 드래그한 부분을 감싸는 태그 선택
        // nodeType = 1 은 해당 부모요소가 <> 태그요소임을 의미
        // ?. = 옵셔널 체이닝은 존재하지 않을 수 있는 프로퍼티 또는 메서드를 안전하게 호출할 수 있도록 도와줍니다.
        // 태그요소이고, 중첩된 태그가 아닐때 parentElement를 할당한다. 하나라도 만족하지 않으면 중간에 undefined 반환
        const selectedElem = parentElement?.nodeType == 1 && parentElement?.children.length < 2 && parentElement;
        
        // 이미 해당 스타일 태그로 감싸져 있다면, 스타일 제거
        if(selectedElem.tagName === 'SPAN') {
            selectedElem.replaceWith(...selectedElem.childNodes);
        } 
        // 스타일을 적용
        else {
            const newElem = document.createElement('span');
            newElem.style.color = document.getElementById('color-picker').value;

            selectedTextRange.surroundContents(newElem);
            userSelection.removeAllRanges();
            userSelection.addRange(range);
            selectedTextRange.collapse(); // 스타일 적용 후 커서를 텍스트 뒤로 이동
        }
    });

    fontfill.addEventListener('click', function(){
        const userSelection = window.getSelection();

        const isAllowedContainer = userSelection.baseNode.parentElement?.closest?.('#editor');

        // 선택된 텍스트가 없거나, 에디터 밖이면 종료
        if( userSelection.rangeCount < 1 || !isAllowedContainer ) return;
        
        const selectedTextRange = userSelection.getRangeAt(0); // 드래그한 부분의 range 객체 반환
        const parentElement = userSelection.anchorNode?.parentElement;  // 드래그한 부분을 감싸는 태그 선택
        // nodeType = 1 은 해당 부모요소가 <> 태그요소임을 의미
        // ?. = 옵셔널 체이닝은 존재하지 않을 수 있는 프로퍼티 또는 메서드를 안전하게 호출할 수 있도록 도와줍니다.
        // 태그요소이고, 중첩된 태그가 아닐때 parentElement를 할당한다. 하나라도 만족하지 않으면 중간에 undefined 반환
        const selectedElem = parentElement?.nodeType == 1 && parentElement?.children.length < 2 && parentElement;
        
        // 이미 해당 스타일 태그로 감싸져 있다면, 스타일 제거
        if(selectedElem.tagName === 'SPAN') {
            selectedElem.replaceWith(...selectedElem.childNodes);
        } 
        // 스타일을 적용
        else {
            const newElem = document.createElement('span');
            newElem.style.backgroundColor = document.getElementById('color-picker').value;

            selectedTextRange.surroundContents(newElem);
            userSelection.removeAllRanges();
            userSelection.addRange(range);
            selectedTextRange.collapse(); // 스타일 적용 후 커서를 텍스트 뒤로 이동
        }
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
    const editor = _this.querySelector("#editor");
    const text =  _this.querySelector("textarea[name=content]");
    text.value = editor.innerHTML.replace(/(?:\r\n|\r|\n)/g, '<br>'); /* 엔터입력 처리 */

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