function formCheck(password1,password2,tele,email,db,type) {
    this.password1 = password1;
    this.password2 = password2;
    this.tele = tele;
    this.email = email;
    if(db) {
        this.db = db;
        this.type = type;
    }
    this.initElements();
    this.initEvents();
}
formCheck.prototype={
    constructor:formCheck,

    initElements:function () {
        this.passwordTell = document.getElementById('isPasswordCorrect');
        this.emailTell = document.getElementById('isPhoneEmailExist');
        this.phoneTell = document.getElementById('isPhoneUserExist');
    },

    initEvents:function () {
        var that = this;
        this.email.onblur=function () {
            if (!this||!that.emailTell){
                return false;
            }
            var filter = /(^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z0-9_-]+$)|(^$)/;
            if (filter.test(this.value)){
                that.emailTell.innerHTML="";

                if(that.db)
                {
                    if(that.email.value!="") {
                        that.sendRequestEmail(that.email.value, that.type);
                    }
                }
                return true;
            }
            else{
                that.emailTell.innerHTML="邮箱格式错误";
                return false;
            }

        };

        this.password1.onblur = this.password2.onblur=function () {
            if (!this||!that.password1||!that.passwordTell){
                throw new Error('there is no enough password!');
                return false;
            }
            if (this.value==that.password1.value){
                that.passwordTell.innerHTML="";
                return true;
            }
            else{
                that.passwordTell.innerHTML="两次密码不一致";
                return false;
            }
        };
        this.tele.onblur=function () {
            if (!this||this.phoneTell){
                return false;
            }
            var filter = /^1(3|4|5|7|8)\d{9}$/;
            if (filter.test(this.value)||this.value==''){
                that.phoneTell.innerHTML="";
                if(that.db)
                {
                    if(that.tele.value!="") {
                        that.sendRequestPhone(that.tele.value, that.type);
                    }
                }
                return;
            }
            else{
                that.phoneTell.innerHTML="手机号码格式不正确";
                return false;
            }

        }
    },

    sendRequestPhone:function (phone,type)
    {
        checkPhone(phone,type);
    },

    sendRequestEmail:function(email,type)
    {
        checkEmail(email,type);
    }
}