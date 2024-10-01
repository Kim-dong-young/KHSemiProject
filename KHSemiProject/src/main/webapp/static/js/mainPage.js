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

function turn(activeIndex) {
  // 모든 버튼을 선택
  const buttons = document.querySelectorAll('.custom-btn');

  // 모든 버튼을 순회하며 클릭된 버튼은 비활성화, 나머지 버튼은 활성화
  buttons.forEach((button, index) => {
      button.disabled = (index === activeIndex); // 클릭된 버튼만 비활성화
  });
}