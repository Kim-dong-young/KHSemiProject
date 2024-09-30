function init(pageName, optional){

    switch(pageName){
        case "communityMainPage":
            initBoard(optional) // tabNo 전달
            initSide()
            break;
        case "communityViewPage":
            initView(optional) // tabNo 전달0
            initSide()
            break;
        case "communityWritePage":
            initSide()
            break;
        case "personalPage":
            initLoad(optional) // memberNo 전달
            break;
        case "mainPage":
            initSwiper()
			break;
        case "searchMainPage":
            tagSearchAjax()
            break;
    }
}