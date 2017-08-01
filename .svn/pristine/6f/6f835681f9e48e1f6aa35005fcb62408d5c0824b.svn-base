<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>管理员登陆</title>
    <link href="${staticUrl!}/css/admin/login.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${staticUrl!}/js/jquery-3.1.1.min.js"></script>
</head>

<body>
<div id="icon"><img src="${staticUrl!}/img/icon/logo.png" width="100px" height="100px" border="2px;" /></div>
<div id="login">
    <div id="label">用户名</div>
    <input id="inputtext" type="text" />
    <div id="label">密码</div>
    <input id="inputpassword" type="password" />
    <div id="loginMessage"></div>
    <button id="logbutton" type="button">登录</button>
</div>
</body>
<script>
    $("#logbutton").click(function(e)
    {
        var account=$("#inputtext").val();
        var password= $("#inputpassword").val();
        if(account==""||password=="")
        {
            $("#loginMessage").css("color", "red").html("请填写管理员账号或密码!");
        }
        else
        {
            $("#logbutton").attr("disabled", true);
            $.ajax({
                url: "${baseUrl!}/admin/login",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    account: account,
                    password: password
                },
                success: function (data) {
                    if (data.code == '200')
                    {
                        location.href = "${baseUrl!}/admin/report";
                    }
                    else {
                        $("#loginMessage").css("color", "red").html(data.message);
                        $("#logbutton").attr("disabled", false);
                        $("#password").val("");
                    }
                }
            });
        }
        e.preventDefault();
    });
</script>
</html>
