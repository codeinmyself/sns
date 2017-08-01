<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 登录" description="登录" page_tab="login" sidebar_about="show">
<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
            <li><a href="${baseUrl!}/">Puckart</a></li>
            <li class="active">登录</li>
        </ol>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" style="margin-top: 20px;">
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">邮箱</label>
                <div class="col-sm-8">
                    <input type="email" class="form-control" id="email" placeholder="邮箱(必填)">
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="password" placeholder="密码(必填)">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-8">
                    <span id="loginMsg"></span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-8">
                    <a onclick="login()" id="login_btn" class="btn btn-raised btn-default">登录</a>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function login() {
        $("#login_btn").attr("disabled", true);
        $.ajax({
            url: "${baseUrl!}/submitLogin",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                email: $("#email").val(),
                password: $("#password").val()
            },
            success: function (data) {
                alert(data.code);
                if (data.code == '200') {
                    location.href = "${baseUrl!}/";
                } else {
                    $("#loginMsg").css("color", "red").html(data.msg);
                    $("#login_btn").attr("disabled", false);
                }
            },

        });
    }
</script>
</@html>