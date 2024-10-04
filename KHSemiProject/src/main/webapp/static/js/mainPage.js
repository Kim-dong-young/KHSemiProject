let swiper;

function initSwiper() {
  swiper = new Swiper('.swiper', {
      // Optional parameters
      slidesPerView: 6,
      spaceBetween: 10,
      loop: true,
      allowTouchMove: false,
      initialSlide: 0,
      // Navigation arrows
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
    }); 
}

function turn(name, path) {
  let buttons = document.querySelectorAll('.recommend-button .custom-btn');

  for(let i = 0; i < buttons.length; i++) {
    if (buttons[i].value === name) {
      buttons[i].disabled = true;  // 클릭된 버튼 비활성화
    } else {
        buttons[i].disabled = false; // 나머지 버튼 활성화
    }
  }

  selectRate(name, path)
}

function selectRate(name, path) {
  $.ajax({
    url: name + ".sl",
    type: "post",
    success: function(res) {
      swiper.removeAllSlides();

      for(let list of res) {
        let swiperSlide = "<div class='swiper-slide' onclick=location.href='" + path + "/click.sl?quiz_number=" + list.quiz_number + "&page=1' style='cursor: pointer'>" + 
        "<div class='thumbnail'><img src=" + path + "/" + list.thumbnail + "></div>" + 
        "<div class='title'>" + list.quiz_number + ". " + list.quiz_title + "</div>" + 
        "</div>"

        swiper.appendSlide(swiperSlide);
      }
      swiper.update();

      swiper.loopDestroy();
      swiper.loopCreate();
    },
  });
}
