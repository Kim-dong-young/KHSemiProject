function initSwiper() {
  const swiper = new Swiper('.swiper', {
      // Optional parameters
      slidesPerView: 6,
      spaceBetween: 10,
      loop: true,

      // Navigation arrows
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    }); 
}

function turn(name) {
  let buttons = document.querySelectorAll('.recommend-button .custom-btn');

  for(let i = 0; i < buttons.length; i++) {
    if (buttons[i].name === name) {
      buttons[i].disabled = true;  // 클릭된 버튼 비활성화
    } else {
        buttons[i].disabled = false; // 나머지 버튼 활성화
    }
  }
}