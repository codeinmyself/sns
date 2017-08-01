<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="${staticUrl!}/css/registerList.css">
    <script type="text/javascript" src="${staticUrl!}/js/windowResize.js"></script>
    <script type="text/javascript" src="${staticUrl!}/js/formCheck.js"></script>
    <script type="text/javascript" src="${staticUrl!}/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="${staticUrl!}/js/formCorrect.js"></script>
    <script type="text/javascript" src="${staticUrl!}/js/emailFormCheck.js"></script>
</head>
<body>
<div id="registerList">
    <label class="tabName">
        <p id="phoneRegister" class="tabNameActive">手机注册</p>
    </label>
    <label class="tabName">
        <p id="emailRegister">邮箱注册</p>
    </label>
    <form method="post" id="phoneUserList" autocomplete="off">
        <input type="text" placeholder="请输入用户名" id="phoneUserName" size="40" name="username" onblur="checkUserName(this.value,'phone')" required>
        <div id="isPhoneUserNameExist" class="note"></div>
        <input type="text" placeholder="请输入手机" id="phoneUser" size="40"  required>
        <div id="isPhoneUserExist" class="note"></div>
        <input type="password" placeholder="请输入密码" id="phonePassword" size="40" name="password" required>
        <input type="password" placeholder="请确认密码" id="phonePasswordConfirm" size="40" name="password" required>
        <div id="isPasswordCorrect" class="note"></div>
        <input type="text" placeholder="请输入邮箱(选填)" id="phoneEmail" size="40">
        <div id="isPhoneEmailExist" class="note"></div>
        <input type="text" placeholder="请输入昵称" id="phoneNickname" size="40" name="nickname" required>
        <select id="phoneisArtist">
            <option>选择是否为艺术家....</option>
            <option value="1">是</option>
            <option value="0">否</option>
        </select>
        <div id="phoneRegisterFail" class="note"></div>
        <button type="button" id="phoneRegisterButton" onclick="phoneRegister()">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
    </form>

    <form method="post" id="emailUserList" autocomplete="off">
        <input type="text" placeholder="请输入用户名" id="emailUserName" size="40" name="username" onblur="checkUserName(this.value,'email')" required>
        <div id="isEmailUserNameExist" class="note"></div>
        <input type="text" placeholder="请输入邮箱" id="emailUser"  size="40" required>
        <div id="isEmailUserExist" class="note"></div>
        <input type="password" placeholder="请输入密码" id="emailPassword" size="40" name="password" required>
        <input type="password" placeholder="请确认密码" id="emailPasswordConfirm" size="40" name="password" required>
        <div id="isEmailPasswordCorrect" class="note"></div>
        <input type="text" placeholder="请输入手机(选填)" id="emailMobile" size="40">
        <div id="isEmailMobileExist" class="note"></div>
        <input type="text" placeholder="请输入昵称" id="emailNickname" size="40" name="nickname" required>
        <select id="emailisArtist">
            <option>选择是否为艺术家....</option>
            <option value="1">是</option>
            <option value="0">否</option>
        </select>
        <div id="emailRegisterFail" class="note"></div>
        <button type="button" id="emailRegisterButton"onclick="emailRegister()">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
    </form>
</div>
<script>
    $(document).ready(function ()
    {
        $("#phoneRegister").click(function ()
        {
            $("#phoneUserList").css("display","block");
            $("#emailUserList").css("display","none");
            $(this).attr("class","tabNameActive");
            $("#emailRegister").attr("class","");
        });
        $("#emailRegister").click(function ()
        {
            $("#phoneUserList").css("display","none");
            $("#emailUserList").css("display","block");
            $(this).attr("class","tabNameActive");
            $("#phoneRegister").attr("class","");
        });

        var password1 = $("#phonePassword")[0];
        var password2 = $("#phonePasswordConfirm")[0];
        var tele1 = $("#phoneUser")[0];
        var email1 = $("#phoneEmail")[0];
        new formCheck(password1,password2,tele1,email1,true,"phone");

        var password3= $("#emailPassword")[0];
        var password4 = $("#emailPasswordConfirm")[0];
        var tele2 = $("#emailMobile")[0];
        var email2 = $("#emailUser")[0];
        new emailFormCheck(password3,password4,tele2,email2,true,"email");
    });
    function checkInput(form){
        var inputs = form.getElementsByTagName('input');
        for(var i = 0;i<inputs.length;i++){
            if (inputs[i].style.backgroundColor=="rgb(255,153,102)") {
                alert('111');
                return false;
            }
        }

        return true;
    }
    function checkDiv(form){
        var divs = form.getElementsByTagName('div');
        for(var i = 0;i<divs.length-1;i++){
            if (divs[i].innerHTML!="") {
                return false;
            };
        }
        return true;
    }

    function checkEmpty(form) {
        var inputs = form.getElementsByTagName('input');
        var options = form.getElementsByTagName('option');
        for(var i = 0;i<inputs.length;i++){
            if (inputs[i].value==""&&i!=4)
            {
                return false;
            }
        }
        if (!options[2].selected&&!options[1].selected){
            return false;
        }
        return true;
    }

   function phoneRegister() {
       if(!checkEmpty($("#phoneUserList")[0])){
           $("#phoneRegisterFail").css({
               'color':'red',
               'font-size':'0.5em'
           }).html('请完整填写信息');
           return false;
       }
        if(checkDiv($("#phoneUserList")[0]))
        {
            $.ajax({
                url: "${baseUrl!}/mobileRegister",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    mobile: $("#phoneUser").val(),
                    password: $("#phonePassword").val(),
                    nickname:$("#phoneNickname").val(),
                    name:$("#phoneUserName").val(),
                    email:$("#phoneEmail").val(),
                    isArtist:$("#phoneisArtist").val()
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("成功注册！");
                        location.href = "${baseUrl!}/";
                    }
                    else {
                    }
                }
            })
        }else{
            $("#phoneRegisterFail").css({
                'color':'red',
                'font-size':'0.5em'
            }).html('请正确填写表单');
        }
    }


    function emailRegister() {
        if(!checkEmpty($("#emailUserList")[0])){
            $("#emailRegisterFail").css({
                'color':'red',
                'font-size':'0.5em'
            }).html('请完整填写信息');
            return false;
        }
            if(checkInput($("#emailUserList")[0])&&checkDiv($("#emailUserList")[0]))
            {
                $.ajax({
                    url: "${baseUrl!}/emailRegister",
                    async: false,
                    cache: false,
                    type: 'post',
                    dataType: "json",
                    data: {
                        mobile: $("#emailMobile").val(),
                        password: $("#emailPassword").val(),
                        nickname:$("#emailNickname").val(),
                        name:$("#emailUserName").val(),
                        email:$("#emailUser").val(),
                        isArtist:$("#emailisArtist").val()
                    },
                    success: function (data) {
                        if (data.code == '200') {
                            alert("成功注册！");
                            location.href = "${baseUrl!}/";
                        }
                        else {
                        }
                    }
                })
            }else{
                $("#emailRegisterFail").css({
                    'color':'red',
                    'font-size':'0.5em'
                }).html('请正确填写表单');
            }
        }




    function checkUserName(userName,type)
    {
        $.ajax({
            url: "${baseUrl!}/checkUserName",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                userName:userName
            },
            success: function (data) {
                if(data.code == '200')
                {
                    if(type=="email") {
                        $("#isEmailUserNameExist").html("");
                    }
                    else
                    {
                        $("#isPhoneUserNameExist").html("");

                    }
                }
                else
                {
                    if(type=="email") {
                        $("#isEmailUserNameExist").css({
                            'color':'red',
                            'fontSize':'0.6em',
                            'paddingLeft':'15px'
                        }).html(data.message);
                        $("#emailUser").css("margin-top","5px");
                    }
                    else {
                        $("#isPhoneUserNameExist").css({
                            'color': 'red',
                            'fontSize': '0.6em',
                            'paddingLeft':'15px'
                        }).html(data.message);
                        $("#phoneUser").css("margin-top","5px");
                    }
                }
            }
        });
    }

    function checkPhone(phone,type)
    {
        $.ajax({
            url: "${baseUrl!}/checkPhone",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                phone:phone
            },
            success: function (data)
            {
                if(data.code == '200')
                {
                    if(type=="email") {
                        $("#isEmailMobileExist").html("");
                    }
                    else
                    {
                        $("#isPhoneUserExist").html("");
                    }
                }
                else
                {
                    if(type=="email") {
                        $("#isEmailMobileExist").css({
                            'color':'red',
                            'fontSize':'0.6em',
                            'paddingLeft':'15px'
                        }).html(data.message);
                        $("#emailNickname").css("margin-top","5px");
                    }
                    else {
                        $("#isPhoneUserExist").css({
                            'color': 'red',
                            'fontSize': '0.6em',
                            'paddingLeft':'15px'
                        }).html(data.message);
                        $("#phonePassword").css("margin-top","5px");
                    }
                }
            }
        });
    }

    function checkEmail(email,type)
    {
        $.ajax({
            url: "${baseUrl!}/checkEmail",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                email:email
            },
            success: function (data)
            {
                if(data.code == '200')
                {
                    if(type=="email") {
                        $("#isEmailUserExist").html("");
                    }
                    else
                    {
                        $("#isPhoneEmailExist").html("");
                    }
                }
                else
                {
                    if(type=="email") {
                        $("#isEmailUserExist").css({
                            'color':'red',
                            'fontSize':'0.6em',
                            'paddingLeft':'15px'
                        }).html(data.message);
                        $("#emailPassword").css("margin-top","5px");
                    }
                    else {
                        $("#isPhoneEmailExist").css({
                            'color': 'red',
                            'fontSize': '0.6em',
                            'paddingLeft':'15px'
                        }).html(data.message);
                        $("#phoneNickname").css("margin-top","5px");
                    }
                }
            }
        });
    }
</script>
</body>
</html>