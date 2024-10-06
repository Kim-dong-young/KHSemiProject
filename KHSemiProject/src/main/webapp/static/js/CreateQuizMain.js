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


