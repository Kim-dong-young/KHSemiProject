// 썸네일 이미지 미리 보기 함수
document.getElementById('thumbnail').addEventListener('change', function(event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onload = function() {
        const preview = document.getElementById('thumbnail-preview');
        preview.innerHTML = `<img src="${reader.result}" alt="Thumbnail" style="width:100%; height:100%;">`;
    };

    if (file) {
        reader.readAsDataURL(file);
    }
});

<<<<<<< HEAD
// 카테고리 선택 시 태그 입력 처리
const categorySelect = document.getElementById('category');
const tagInput = document.getElementById('tag');

categorySelect.addEventListener('change', function() {
    const selectedValue = categorySelect.value;

    if (selectedValue === '8') { 
        tagInput.disabled = false; 
        tagInput.value = ''; 
    } else {
        tagInput.disabled = true; 
        switch (selectedValue) {
            case '1': tagInput.value = '유머'; break;
            case '2': tagInput.value = '예술/문학'; break;
            case '3': tagInput.value = '세계'; break;
            case '4': tagInput.value = '역사'; break;
            case '5': tagInput.value = '언어'; break;
            case '6': tagInput.value = '과학/자연'; break;
            case '7': tagInput.value = '스포츠'; break;
            default: tagInput.value = ''; break;
        }
    }
});


window.addEventListener('load', function() {
    const selectedValue = categorySelect.value;
    if (selectedValue !== '8') { 
        tagInput.disabled = true;
    }
});
=======

>>>>>>> 34fc244c587f6a00306b34f3ac83286458c168c8
