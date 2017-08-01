<#macro header page_tab="">
    <header>
        <span class="header-logo">puckart</span>
        <form method="get" action="">
            <input type="text" size="40">
        </form>
        <a class="header-search">
            <img src="${staticUrl!}/img/icon/search.png">
        </a>
        <#if Session.user??>
            <span class="header-user-settings">
                <a href="${baseUrl!}/">回到首页</a>
                <a href="${baseUrl!}/user/setting">个人信息</a>
                <a href="${baseUrl!}/logout">注销</a>
            </span>
        <#else>
            <span class="header-login-register">
                <a href="/user/login.html?source=SNS">登录</a>
            </span>
        </#if>
    </header>

    <div id="login-modal">
        <div id="login-list">
            <form method="post"  autocomplete="off">
                <label>
                    <p id="label-name">用户登录</p>
                    <p id="login-list-close">x</p>
                </label>
                <input type="text" id="username" placeholder="请输入用户名/手机号/邮箱" size="24">
                <input type="password" id="password" placeholder="请输入密码" size="24">
                <div id="loginMessage"></div>
                <input type="checkbox" id="rememberMe"><span>记住我</span>
                <span><a href="${baseUrl!}/register">立即注册</a></span>
                <input id="loginButton" type="submit" value="登录">
            </form>
        </div>
    </div>
    <script>
        $(document).ready(function ()
        {
            (function (){
                var loginBtn=$('#headerLoginBtn')[0];
                if (loginBtn){
                    var closeBtn=document.getElementById('login-list-close');
                    var target = document.getElementById('login-modal');
                    new model(loginBtn,closeBtn,target);
                }
                getToCenter($('#login-list'));
                $(window).resize(function () {
                    getToCenter($('#login-list'));
                })
            }());
            (function () {
                var headerSize = document.getElementsByTagName('header')[0];
                var masterpieces=document.getElementById('masterpieces');
                var minWidth = 1070;
                new windowResize(headerSize,minWidth);
            }());
        })

        $("#loginButton").click(function(e)
        {
            $("#loginButton").attr("disabled", true);
            $.ajax({
                url: "${baseUrl!}/submitLogin",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    name: $("#username").val(),
                    password: $("#password").val()
                },
                success: function (data) {
                    if (data.code == '200')
                    {
                        location.href = "${baseUrl!}/";
                    }
                    else
                    {
                        $("#loginMessage").css("color", "red").html(data.message);
                        $("#loginButton").attr("disabled", false);
                        $("#password").val("");
                    }
                }
            });
            e.preventDefault();
        });
    </script>

</#macro>