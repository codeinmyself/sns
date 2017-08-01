<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 艺术圈个人信息" description="个人信息" page_tab="circle_information" sidebar_about="show" >

<div class="artCircle-rightbar">

    <div class="notice-bar">
        <div class="noticebt-active">发起艺术圈</div>
    </div>
    <!--动态加载内容-->
    <div class="artCircle-rightbar-edit">
        <div style="float:left;">

            用户名：
            <br>
            <br>
            <input type="text" id="circleName" placeholder="艺术圈名称" class="inputblank">
            <br>
            <br>

            简介：

            <br>
            <br>
            <textarea id="description" class="text">输入简介</textarea>
            <br>
            <br>
            公开范围：
            <br>
            <br>
            <select id="showScope" class="selectblank">
                <option value="01" selected="selected">对所有人公开</option>
                <option value="02">仅限艺术圈成员</option>
            </select>
            <br>
            <br>
            申请策略：
            <br>
            <br>
            <select id="applyStrategy" class="selectblank">
                <option value="01" selected="selected">无需申请加入</option>
                <option value="02">需要申请加入</option>
            </select>
            <br>
            <br>
            艺术圈形象：
            <br>
            <br>

            <input type="file"  id="uploadImage" name="uploadImage" value="上传图片"  style="display: block" class="inputblank" onclick="new imgPreview($('#circlePreviewImg')[0],$('#uploadImage')[0]);"/>
            <br>
            <div id="returnErrorAdd">
            </div>
            <br>
            <div style="display:inline-block">
                <button class="mybutton2" onclick="submitButton()" id="save_revise">提交</button>
            </div>
            <div style="display:inline-block">
                <button class="mybutton1"><a href="${baseUrl!}/circle/circleQuery">取消</button>
            </div>


        </div>
        <div class="artCircle-rightbar-short-imgtitle">
            <img id="circlePreviewImg" src="${staticUrl!}/img/user/default_avatar.png">
        </div>

    </div>
</div>
</div>


<script>
    function submitButton()
    {
        if(confirm("确定填写完毕？")) {
            var circleName = $("#circleName").val();
            var description = $("#description").val();
            var showScope = $("#showScope option:selected").val();
            var applyStrategy = $("#applyStrategy option:selected").val();
            var uploadImage = $("#uploadImage").val();
            if (circleName == "" || description == "" || showScope == "" || applyStrategy == "") {
                $("#returnErrorAdd").css("color", "red").html("必要信息不能为空!");
            }
            else if (uploadImage == "") {
                $("#returnErrorAdd").css("color", "red").html("艺术圈形象不能为空!");
            }
            else {
                var ext = ".gif,.jpg,.png,.jpeg";
                var hz = uploadImage.substring(uploadImage.lastIndexOf("."), uploadImage.length);
                if (ext.indexOf(hz.toLowerCase()) == -1) {
                    $("#returnErrorAdd").css("color", "red").html("图片格式不正确!");
                }
                else {
                    $("#returnErrorAdd").css("color", "red").html("");
                    $.ajaxFileUpload({
                        url: "${baseUrl!}/circle/circleSubmit",
                        secureuri: false,
                        fileElementId: 'uploadImage',
                        dataType: 'json',
                        type: 'post',
                        data: {
                            circleName: $("#circleName").val(),
                            description: $("#description").val(),
                            showScope: $("#showScope option:selected").val(),
                            applyStrategy: $("#applyStrategy option:selected").val()
                        },
                        success: function (data) {
                            if (data.code == '200') {
                                alert("创建成功!");
                                location.href = "${baseUrl!}/circle/circleQuery";
                            }
                            else {
                                $("#returnErrorAdd").css("color", "red").html(data.message);
                            }
                        },
                    });
                }
            }
        }
    }

</script>

</@html>