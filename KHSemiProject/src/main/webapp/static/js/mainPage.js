let swiper;

function initSwiper() {
  swiper = new Swiper('.swiper', {
      // Optional parameters
      slidesPerView: 6,
      spaceBetween: 10,
      loop: true,
      allowTouchMove: false,
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
    if (buttons[i].value === name) {
      buttons[i].disabled = true;  // 클릭된 버튼 비활성화
    } else {
        buttons[i].disabled = false; // 나머지 버튼 활성화
    }
  }

  selectRate(name)
}

function selectRate(name) {
  $.ajax({
    url: name + ".sl",
    type: "post",
    success: function(res) {
      console.log(res);
      
      swiper.removeAllSlides();

      for(let list of res) {
        let swiperSlide = "<div class='swiper-slide'>" + 
        "<div class='thumbnail'>" + list.quiz_number + "썸네일입니다.</div>" + 
        "<div class='title'>" + list.quiz_title + "</div>" + 
        "</div>"

        swiper.appendSlide(swiperSlide);
      }
      
      swiper.update();
    },
    error: function() {
      swiperbox.innerHTML += "<div> 조회된 내용이 없습니다.</div>"
    }
  });
}