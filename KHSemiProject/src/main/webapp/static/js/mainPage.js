function initMain(optional) {
  selectPopBoard();
  setInterval(selectPopBoard, 3000);
  
  if(optional) {
    setLevel(optional);
    selectDailyQuest()
  }

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

function selectPopBoard(){
  $.ajax({
      url:"poplist.bo",
      method :"post",
      success: function(res){
          let str = ""
          for(let board of res){
              str += (
                  "<tr>"+
                  "<td class='tab' data-tab-no=\'"+ board.communityTabNo +"\' onclick=\"location.href='community?cpage=1&tno=" + board.communityTabNo +"\'\">" + board.communityTab + "</td>" +
                  "<td class='title' onclick=\"location.href='board?cpage=1&no="+ board.communityNo 
                                                              +"&comment=1&tno="+ board.communityTabNo +"\'\">" + board.communityTitle + "</td>" +
                  "<td class='author'>" + board.memberId + "</td>" +
                  "<td class='comment-num'>["+ board.commentCount + "]<img src='static/img/comment-icon.png'></td>" +
                  "</tr>"
              )
          }
          /* i < 5 조건 하드코딩 나중에 고치자 */
          for (let i = res.length; i < 5; i++) {
              str += (
                  "<tr>" +
                  "<td>&nbsp;</td>" +
                  "<td>&nbsp;</td>" +
                  "<td>&nbsp;</td>" +
                  "<td>&nbsp;</td>" +
                  "</tr>"
              );
          }

          const popList = document.querySelector(".popular-community > table");
          if(str === ""){
              str += "현재 조회된 인기글이 없습니다."
          }
          popList.innerHTML = str;

          const categoryList = document.querySelectorAll(".popular-community td.tab");

          for(let category of categoryList){
              switch(category.getAttribute("data-tab-no")) {
                  case "1":
                      category.style.color = "#FF4040";
                      break;
                  case "2":
                      category.style.color = "#55C62C";
                      break;
                  case "3":
                      category.style.color = "#407BFF";
                      break;
                  case "4":
                      category.style.color = "#FF4DED";
                      break;
                  default :
                      category.style.color = "black";
                      break;
              }
          }
      },
      error : function(){
          console.log("인기글 조회 실패");
      }
  })
}


function selectDailyQuest(){
  $.ajax({
    url: "dailyQuest.me",
    type: "post",
    success: function(res) {
      const questDiv = document.querySelector(".quest-list-row");
      let str = "";
      console.log(res)
      console.log("응답 길이:", res.length);

      for(let quest of res) {
        str += (
          "<div>" +
              "<div class='quest-content'>"+ quest.questContent+"</div>" +
               "<div class='quest-achieving-condition'>" +
                  "<span>" + (quest.questSuccess > 0 ? "1" : "0") + "</span>" +
                  "<span> / </span>" +
                  "<span class='completion-condition'>" + 1 + "</span>" +
              "</div>" +
              "<div class='submit-btn'>" +
                  "<button "+ (quest.questSuccess === 2 ? "disabled" : "") +" onclick=\"location.href='checkQuest?qno=" + quest.questNo + "'\">"+ (quest.questSuccess === 2 ? "완료함" : "달성하기") +"</button>" +
              "</div>" +
          "</div>"
        )
      }

      questDiv.innerHTML = str;
    },
    error : function() {
      console.log("일일퀘스트 조회 실패");
    }
  });
}

function setLevel(optional) {
    const levelValue = document.getElementById('level')
    const expValue = document.getElementById('exp-value')
    const expBarValue = document.getElementById('current-user-exp-bar')
  
    let op = Number(optional) //optional을 number타입으로 변경
  
    let maxExp = 1000;
    let level = Math.floor(op / maxExp);
    let exp = op % maxExp;
    let expGage = (exp / maxExp) * 100;
  
    if(op < maxExp) {
      levelValue.innerText = 1;
    } else {
      levelValue.innerText = level + 1;
    }
    
    if(exp > 0) {
      expValue.innerText = exp;
    } else {
      expValue.innerText = 0;
    }
  
    expBarValue.style.width = expGage + '%'
}