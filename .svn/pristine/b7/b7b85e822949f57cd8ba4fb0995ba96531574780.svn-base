<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 邮箱注册" description="注册" page_tab="email_register" sidebar_about="show">
<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
            <li><a href="${baseUrl!}/">Puckart</a></li>
            <li class="active">邮箱注册</li>
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
                <label for="email" class="col-sm-2 control-label">真实姓名</label>
                <div class="col-sm-8">
                    <input type="name" class="form-control" id="name" placeholder="姓名(必填)">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">昵称</label>
                <div class="col-sm-8">
                    <input type="nickname" class="form-control" id="nickname" placeholder="昵称(必填)">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-8">
                    <span id="registerMsg"></span>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-8">
                    <a onclick="register()" id="register_btn" class="btn btn-raised btn-default">注册</a>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    function register() {
        $("#register_btn").attr("disabled", true);
        $.ajax({
            url: "${baseUrl!}/EmailRegister",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                email: $("#email").val(),
                password: $("#password").val(),
                nickname: $("#nickname").val(),
                name: $("#name").val()
            },
            success: function (data) {
                if (data.code == '200') {
                    alert("成功注册！");
                    location.href = "${baseUrl!}/";
                } else {
                    $("#registerMsg").css("color", "red").html(data.msg);
                    $("#register_btn").attr("disabled", false);
                }
            },

        });
    }
</script>
</@html>