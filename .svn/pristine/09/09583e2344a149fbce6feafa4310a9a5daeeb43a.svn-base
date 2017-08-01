<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 修改艺术圈" sidebar_circle_info=circle sidebar_member_info=memberI description="艺术圈信息" page_tab="circle_information" sidebar_about="circleshow" >

<script>

    $(document).ready(function () {
        $("#flip").click(function () {
            $(".panel").slideToggle("slow");
            if ($("#side-nav").height() == 288) {
                $("#side-nav").animate({height: '370px'});
            }
            else {
                $("#side-nav").animate({height: '288px'});
            }
        });
    });

</script>

<div class="artCircle-rightbar">
    <div class="notice-bar">
        <div class="noticebt-active">修改艺术圈</div>
    </div>

    <!--动态加载内容-->
    <div class="artCircle-rightbar-edit">
        <div style="float:left;">
            昵称：<br><br><input type="text" id="circleName" value="${circle.circleName}" class="inputblank">
            <br>
            <br>

            简介：<br><br><textarea id="description" class="text">${circle.description}</textarea>
            <br>
            <br>
            选择申请策略：<br><br>
            <select id="applyStrategy" class="selectblank">

                <#if circle.applyStrategy=='01'>
                    <option value="01" selected="selected">无需申请加入</option>
                    <option value="02">需要申请加入</option>
                <#else>
                    <option value="01">无需申请加入</option>
                    <option value="02" selected="selected">需要申请加入</option>
                </#if>
            </select>
            <br>
            <br>
            选择公开范围：<br><br>
            <select id="showScope" class="selectblank">

                <#if circle.showScope=='01'>
                    <option value="01" selected="selected">公开</option>
                    <option value="02">仅限艺术圈成员</option>
                <#else>
                    <option value="01">公开</option>
                    <option value="02" selected="selected">仅限艺术圈成员</option>
                </#if>
            </select>
            <br>
            <br>
            <input type="file" id="uploadImage" name="uploadImage" value="上传图片" style="display: block"
                   class="inputblank" onclick="new imgPreview($('#circlePreviewImg')[0],$('#uploadImage')[0]);"/>
            <br>
            <div id="returnErrorAdd">
            </div>
            <br>
            <div style="display:inline-block">
                <button onclick=save('${circle.circleId}') class="mybutton2" id="save_revise">保存</button>
            </div>
            <div style="display:inline-block">
                <button class="mybutton1"><a href="${baseUrl!}/circle/circleQuery">取消</button>
            </div>


        </div>
        <div class="artCircle-rightbar-short-imgtitle">
            <#if circle.avatar?exists>
                <img id="circlePreviewImg" src="${uploadUrl!}${circle.avatar}">
            <#else>
                <img id="circlePreviewImg" src="${staticUrl!}/img/user/default_avatar.png">
            </#if>
        </div>
    </div>
</div>

<script>
    function save(circleId) {
        var circleName = $("#circleName").val();
        var description = $("#description").val();
        var showScope = $("#showScope option:selected").val();
        var applyStrategy = $("#applyStrategy option:selected").val();
        var uploadImage = $("#uploadImage").val();
        if (circleName == "" || description == "" || showScope == "" || applyStrategy == "") {
            $("#returnErrorAdd").css("color", "red").html("必要信息不能为空!");
        }
        else {
            if (uploadImage != "") {

                var ext = ".gif,.jpg,.png,.jpeg";
                var hz = uploadImage.substring(uploadImage.lastIndexOf("."), uploadImage.length);
                if (ext.indexOf(hz.toLowerCase()) == -1) {
                    $("#returnErrorAdd").css("color", "red").html("图片格式不正确!");
                }
                else {
                    $("#returnErrorAdd").css("color", "red").html("");
                    $.ajaxFileUpload({
                        url: "${baseUrl!}/circle/circleEdit",
                        secureuri: false,
                        fileElementId: 'uploadImage',
                        dataType: 'json',
                        type: 'post',
                        data: {
                            circleId: circleId,
                            circleName: $("#circleName").val(),
                            description: $("#description").val(),
                            showScope: $("#showScope option:selected").val(),
                            applyStrategy: $("#applyStrategy option:selected").val()
                        },
                        success: function (data) {
                            if (data.code == '200') {
                                alert("修改成功!");
                                window.location.reload();
                            } else {
                                $("#save_revise").attr("disabled", false);
                            }
                        },
                    });
                }
            }
            else {
                $("#returnError").css("color", "red").html("");
                $("#save_revise").attr("disabled", true);
                $.ajax({
                    url: "${baseUrl!}/circle/circleEditWithoutUpload",
                    async: false,
                    cache: false,
                    type: 'post',
                    dataType: "json",
                    data: {
                        circleId: circleId,
                        circleName: $("#circleName").val(),
                        description: $("#description").val(),
                        showScope: $("#showScope option:selected").val(),
                        applyStrategy: $("#applyStrategy option:selected").val()
                    },
                    success: function (data) {
                        if (data.code == '200') {
                            alert("修改成功!");
                            window.location.reload();
                        } else {
                            $("#save_revise").attr("disabled", false);
                        }
                    },
                });
            }
        }

    }

    $(document).ready(function () {
        $("#flip").click(function () {
            $(".panel").slideToggle("slow");
            if ($("#side-nav").height() == 288) {
                $("#side-nav").animate({height: '370px'});
            }
            else {
                $("#side-nav").animate({height: '288px'});
            }
        });
    });
</script>
</@html>