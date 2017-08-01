function getToCenter(ele){
    var screenWidth = document.body.clientWidth;
    if (window.innerHeight){
        var screenHeight = window.innerHeight;
        var height = ele.height();
        if(height!=0)
        ele.css("marginTop",(screenHeight-height)/2+'px');
    }
    var width = ele.width();
    ele.css({
        "marginRight":(screenWidth-width)/2+'px'
    })
}