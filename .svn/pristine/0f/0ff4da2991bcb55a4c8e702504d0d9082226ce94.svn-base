function model (loginBtn,closeBtn,targetList){
    this.loginBtn = loginBtn;
    this.closeBtn = closeBtn;
    this.targetList = targetList;
    this.initElements();
    this.initEvents();
}

model.prototype = {
    constructor:model,
    initElements:function () {
        this.idSaver = document.getElementById('userIdHidden');
        this.reportIdGetter=document.getElementById('reportHiddenNewsId');
    },
    initEvents:function () {
        var that = this;
        this.loginBtn.onclick=function () {
            that.targetList.style.display="block";
            that.targetId = this.value;
            if (that.idSaver){
                that.idSaver.value = that.targetId;
            }
            if(that.reportIdGetter)
            {
                that.reportIdGetter.value=this.nextSibling.nextSibling.value;
            }
            document.body.style.overflow = 'hidden';
        }
        this.closeBtn.onclick=function () {
            that.targetList.style.display="none";
            document.body.style.overflow = 'scroll';
        }
    }
}
