function initSwiper(optional) {
  const buttons = document.querySelectorAll('.recommend-button .custom-btn');
  let btnVal;

  let swiper = new Swiper('.swiper', {
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

    buttons.forEach(button => {
      if (button.disabled) {
          btnVal = button.value;
      }
    });
    selectRate(btnVal, swiper);

    buttons.forEach(button => {
      button.onclick = function(ev) {
        btnVal = ev.target.value;
        
        buttons.forEach(btn => {
          if(btn === ev.target) {
              btn.disabled = true;
          } else {
              btn.disabled = false;
          }
      })

      selectRate(btnVal, swiper);
    }
  })
}

function selectRate(btnVal, swiper) {
  $.ajax({
    url: "topten.sl",
    type: "post",
    data: {btnValue: btnVal},
    success: function(res) {
      console.log(res.qList)

      swiper.removeAllSlides();

      for(let list of res.qList) {
        let swiperSlide = "<div class='swiper-slide' onclick=location.href='" + res.contextPath + "/click.sl?quiz_number=" + list.quiz_number + "&page=1' style='cursor: pointer'>" + 
        "<div class='thumbnail'><img src=" + res.contextPath + "/" + list.thumbnail + "></div>" + 
        "<div class='title'>" + list.quiz_number + ". " + list.quiz_title + "</div>" + 
        "</div>"

        swiper.appendSlide(swiperSlide);
      }
      swiper.update();

      // swiper.loopDestroy();
      // swiper.loopCreate();
      
      swiper.slideToLoop(0); 
    },
  });
}
