<#include "../../common/_layout.ftl"/>
<@html title="个人信息" description="个人信息" page_tab="UserInfo" sidebar_about="show">
<head>
    <#--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
    <#--<link href="css/collection.css" rel="stylesheet" type="text/css">-->
    <#--<link href="css/header.css" rel="stylesheet" type="text/css">-->
    <#--<link href="css/loginSideNav.css" rel="stylesheet" type="text/css">-->
    <#--<link href="css/userMessageList.css" rel="stylesheet" type="text/css">-->
    <#--<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>-->
    <#--<script type="text/javascript" src="js/formCheck.js"></script>-->
    <#--<script type="text/javascript" src="js/imgPreview.js"></script>-->
    <title>作品集</title>
</head>
<body>
    <div id="right">
        <div id="userMessageList">
            <form method="post" action="" autocomplete="off">
                <label>
                    <p>基本信息</p>
                </label>
                <input type="text" placeholder="请输入用户名" id="username" size="60" disabled value="${Session.user.userName}">
                <#if Session.user.email?exists>
                    <input type="text" placeholder="请输入邮箱" id="email" size="60" disabled value="${Session.user.email}">
                <#else>
                    <input type="text" placeholder="请输入邮箱" id="email" size="60" disabled value="无">
                </#if>
                <#if Session.user.mobile?exists>
                    <input type="text" placeholder="请输入手机" id="mobile" size="60" disabled value="${Session.user.mobile}">
                <#else>
                    <input type="text" placeholder="请输入手机" id="mobile" size="60" disabled value="无">
                </#if>
                <input type="text" placeholder="请输入昵称" id="nickname" size="60"value="${Session.user.nickname}">
                <#if Session.user.signature?exists>
                <span class="selfInfoTitle">简介:</span><textarea class="selfInfo" id="signature" >${Session.user.signature}</textarea>
                <#else>
                    <span class="selfInfoTitle">简介:</span><textarea class="selfInfo" id="signature" placeholder="暂无简介"></textarea>
                </#if>
                <div id="setMsg"></div>
                <button type="button" class="saveBtnStyle" id="saveBtn">保存</button>
            </form>
            <form method="post" action="" autocomplete="off" class="passwordLabel">
                <label>
                    <p>密码信息</p>
                </label>
                <input type="password" placeholder="请输入原密码" id="prePassword" size="60">
                <input type="password" placeholder="请输入要修改的密码" id="nowPassword1" size="60">
                <input type="password" placeholder="请确认修改的密码" id="nowPassword2" size="60">
                <div id="isPasswordCorrect" class="note"></div>
                <button type="button" class="saveBtnStyle" id="saveBtn1">保存</button>
            </form>
            <div>
            <form method="post" action="${baseUrl!}/user/uploadAvatar" id="uploadAvatar" autocomplete="off" class="headPicLabel" enctype="multipart/form-data">
                <label>
                    <p>修改头像</p>
                </label>
                <input type="file" id="avatar" name="avatar">
                <div id="imgShower">
                    <img alt="头像预览" src="${uploadUrl!}${Session.user.avatar!}">
                    <button id="viewer">浏览...</button>
                    <script type="text/javascript">
                        $(document).ready(function () {
                            $('#viewer').click(function (e) {
                                $("#avatar").click();
                                e.preventDefault();
                            });
                            new imgPreview($('#imgShower img')[0],$('#avatar')[0]);
                        });
                    </script>
                </div>
                <button  class="saveBtnStyle" type="submit">保存</button>
            </form>
                </div>
            <script>
                $(document).ready(function () {
                    var password1 = $("#nowPassword1")[0];
                    var password2 = $("#nowPassword2")[0];
                    var tele = $("#mobile")[0];
                    var email = $("#email")[0];
                    new formCheck(password1,password2,tele,email);
                });
            </script>
        </div>
    </div>
</body>
<script>
    $("#saveBtn").click(function(e)
    {
        var nickname = $("#nickname").val();
        var signature=$("#signature").val();
        if ($.trim(nickname) == "") {
            alert("昵称不能为空");
        } else {
            $.ajax({
                url: "${baseUrl!}/user/saveSetting",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    nickname: nickname,
                    signature:signature
                },
                success: function (data) {
                    if (data.code == '200') {
                        $("#setMsg").css("color", "red").html(data.message);

                    } else {
                        $("#setMsg").css("color", "red").text(data.message);
                    }
                }
            });
        }
        e.preventDefault();
    });

    $("#saveBtn1").click(function(e)
    {
        var password=$("#prePassword").val();
        var newpassword = $("#nowPassword1").val();
            $.ajax({
                url: "${baseUrl!}/user/updatePassword",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    password: password,
                    newpassword:newpassword
                },
                success: function (data) {
                    if (data.code == '200') {
                        $("#isPasswordCorrect").css("color", "red").html("修改成功!");
                    } else {
                        $("#isPasswordCorrect").css("color", "red").text(data.message);
                    }
                }
            });
        e.preventDefault();
    });


    $("#uploadAvatar").submit(function () {
        var hzs = ".gif,.jpg,.png,.jpeg";
        var avatar = $("#avatar").val();
        if(avatar) {
            var hz = avatar.substring(avatar.lastIndexOf("."), avatar.length);
            if(hzs.indexOf(hz.toLowerCase()) == -1) {
                alert("请上传正确格式的图片");
                return false;
            }
        } else {
            alert("请选择图片");
            return false;
        }
    });
</script>
</@html>