function newsModal (loginBtn,closeBtn,targetList){
    this.loginBtn = loginBtn;
    this.closeBtn = closeBtn;
    this.targetList = targetList;
    this.initElements();
    this.initEvents();
}

newsModal.prototype = {
    constructor:newsModal,
    initElements:function () {
        this.publishPic = document.getElementById('picIds');
        this.publishContent = document.getElementById('newsContent');
    },
    initEvents:function () {
        var that = this;
        this.loginBtn.onclick=function () {
            if (that.publishPic.value==""&&that.publishContent.value=="")
            {
                return;
            }
            that.targetList.style.display="block";
            document.body.style.overflow = 'hidden';
        }
        this.closeBtn.onclick=function () {
            that.targetList.style.display="none";
            document.body.style.overflow = 'scroll';
        }
    }
}
