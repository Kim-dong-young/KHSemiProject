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