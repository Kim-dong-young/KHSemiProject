function init(pageName, optional){
    const path = document.querySelector('#loginForm input[name="path"]');
    if(path){
        path.value = location.href;
    }


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
            initWrite()
            initSide()
            break;
        case "personalPage":
            initLoad(optional)
            break;
        case "mainPage":
            initMain(optional)
			break;
        case "searchMainPage":
            tagSearchAjax()
            break;
        case "searchClickPage":
            initsss()
            break;
        case "PlayQuizMain":
            renderProblem()
            break;
    }
}