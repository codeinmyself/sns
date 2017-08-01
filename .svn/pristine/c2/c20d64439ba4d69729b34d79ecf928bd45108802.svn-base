<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 作品集主页" description="作品集信息" page_tab="collection_information" sidebar_about="show">


    <div id="right">
        <div id="wrap" style="padding-left: 20px;">
            <ul>
                <li >
                    <a onclick="addCollection()"><img src="${staticUrl!}/img/icon/add.jpg"></a>
                </li>
                <#if collectionList?exists>
                    <#list collectionList as collection>
                        <li>
                            <img src="${uploadUrl!}${collection.cover}">
                            <div class="text"><p>&nbsp;</p>
                                <p><a href="${baseUrl!}/collection/show?collectionId=${collection.collectionId}"><img src="${staticUrl!}/img/icon/share.png" alt="share">展示</a></p><p>&nbsp;</p>
                                <p><a onclick="collection('${collection.collectionName}','${collection.description}','${collection.showScope}','${collection.collectionId}','${uploadUrl!}${collection.cover}')"><img src="${staticUrl!}/img/icon/edit.png" alt="edit">编辑</a></p><p>&nbsp;</p>
                                <p><a onclick="deleteCollection('${collection.collectionId}')" id="delete_btn" href="#"><img src="${staticUrl!}/img/icon/delete.png" alt="delete">删除</a></p>
                            </div>
                        </li>
                    </#list>
                </#if>
            </ul>
        </div>
    </div>

    <div class="modal-add">
        <div class="modal-add-body">

            <div class="modal-add-header">
                <div class="modal-add-title">
                    <span>作品集信息</span>
                </div>
            </div>
            <div class="modal-add-content">
                    <table id="edit">
                        <tr>
                            <td id="label">作品集名称</td>
                            <td><input id="add_collection_name" name="add_collection_name" type="text"></td>
                        </tr>
                        <tr>
                            <td id="label">描述</td>
                            <td><textarea name="textarea" id="add_description" name="add_description" type="text" placeholder="简单表述作品集内容（50字之内）" maxlength="50"></textarea></td>
                        </tr>
                        <tr>
                            <td id="label" name="label">权限</td>
                            <td><input type="radio" name="add_show_scope" value="01" checked="checked">所有人可见
                                <input type="radio" name="add_show_scope" value="02">仅关注者可见
                                <input type="radio" name="add_show_scope" value="03">仅自己可见
                            </td>
                        </tr>
                        <tr>
                            <td id="label">
                                <input id="addfile" name="addfile" type="file" onclick="new imgPreview($('#add-previewImage')[0],$('#addfile')[0]);">封面
                            </td>
                            <td><img class="chosepic1" id="add-previewImage" name="add_previewImage" src="${staticUrl!}/img/icon/add.jpg" style="cursor: pointer;">
                                <div id="returnErrorAdd"></div>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" colspan="2">
                                    <input id="submit_btn" type="button" value="创建">
                                    <input class="add-close" id="cancel_btn" type="submit" value="取消" onClick="cancel()">
                                </td>
                        </tr>
                    </table>
            </div>
        </div>
    </div>
    <div class="modal fade">
        <div class="modal-body">

            <div class="modal-header">
                <div class="modal-title">
                    <span>作品集信息</span>
                </div>
            </div>
            <div class="modal-content">
                <table id="edit">
                    <input id="collection_id" type="hidden">
                    <tr>
                        <td id="label">作品集名称</td>
                        <td><input id="collection_name" type="text"></td>
                    </tr>
                    <tr>
                        <td id="label">描述</td>
                        <td><textarea name="collection_description" id="collection_description" type="text" placeholder="简单表述作品集内容（50字之内）" maxlength="50"></textarea></td>
                    </tr>
                    <tr>
                        <td id="label">权限</td>
                        <td><input type="radio" class="show_scope1" name="show_scope" value="01">所有人可见
                            <input type="radio" class="show_scope2" name="show_scope" value="02">仅关注者可见
                            <input type="radio" class="show_scope3" name="show_scope" value="03">仅自己可见
                        </td>
                    </tr>
                    <tr>
                    <td id="label">
                        <input id="updateFile" name="updateFile" type="file" onclick="new imgPreview($('#previewImage')[0],$('#updateFile')[0]);">封面
                    </td>
                    <td><img class="chosepic2" id="previewImage" src="${staticUrl!}/img/icon/add.jpg" style="cursor: pointer;">
                        <div id="returnError"></div></td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2">
                        <input id="update_btn" type="button" value="保存">
                        <input class="close" id="cancel_btn" type="submit" value="取消" onClick="cancel()">
                         </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="modal-backup"></div>

<script>
    $("#submit_btn").click(function(e)
    {
        var collectionName=$("#add_collection_name").val();
        var collectionDescription=$("#add_description").val();
        var inputFile=$("#addfile").val();
        if(collectionName==""||collectionDescription=="")
        {
            $("#returnErrorAdd").css("color","red").html("必要信息不能为空!");
        }
        else if(inputFile=="")
        {
            $("#returnErrorAdd").css("color","red").html("作品集图片不能为空!");
        }
        else
        {
            var ext = ".gif,.jpg,.png,.jpeg";
            var hz = inputFile.substring(inputFile.lastIndexOf("."), inputFile.length);
            if(ext.indexOf(hz.toLowerCase()) == -1) {
                $("#returnErrorAdd").css("color","red").html("图片格式不正确!");
            }
            else
            {
                $("#returnErrorAdd").css("color","red").html("");
                $("#submit_btn").attr("disabled", true);
                $.ajaxFileUpload({
                    url:"${baseUrl!}/collection/add",
                    secureuri:false,
                    fileElementId:'addfile',
                    dataType:'json',
                    type:'post',
                    data:
                    {
                        collectionName:collectionName,
                        description:collectionDescription,
                        showScope:$("input[name='add_show_scope']:checked").val()
                    },
                    success: function (data)
                    {
                        if (data.code == '200')
                        {
                            alert("创建成功!");
                            location.href = "${baseUrl!}/collection/index";
                        }
                        else
                        {
                            $("#returnErrorAdd").css("color", "red").html(data.message);
                            $("#submit_btn").attr("disabled", false);
                        }
                    },
                });
                e.preventDefault();
            }
        }
    });

    function cancel(){
        $("#cancel_btn").attr("disabled",true);
        location.href = "${baseUrl!}/collection/index";
    }

    $("#update_btn").click(function(e)
    {
        var collectionName=$("#collection_name").val();
        var collectionDescription=$("#collection_description").val();
        var inputFile=$("#updateFile").val();
        if(collectionName==""||collectionDescription=="")
        {
            $("#returnError").css("color","red").html("必要信息不能为空!");
        }
        else {
            if (inputFile != "") {
                var ext = ".gif,.jpg,.png,.jpeg";
                var hz = inputFile.substring(inputFile.lastIndexOf("."), inputFile.length);
                if (ext.indexOf(hz.toLowerCase()) == -1) {
                    $("#returnError").css("color", "red").html("图片格式不正确!");
                }
                else {
                    $("#returnError").css("color", "red").html("");
                    $("#update_btn").attr("disabled", true);
                    $.ajaxFileUpload({
                        url:"${baseUrl!}/collection/updateAndUpload",
                        secureuri:false,
                        fileElementId:'updateFile',
                        dataType:'json',
                        type:'post',
                        data: {
                            collectionId: $("#collection_id").val(),
                            collectionName: collectionName,
                            description: collectionDescription,
                            showScope: $("input[name='show_scope']:checked").val()
                        },
                        success: function (data)
                        {
                            if (data.code == '200') {
                                alert("修改成功!");
                                location.href = "${baseUrl!}/collection/index";
                            }
                            else if (data.code == '202'){
                                location.href = "${loginUrl!}";
                            }
                            else {
                                $("#returnError").css("color", "red").html(data.message);
                                $("#update_btn").attr("disabled", false);
                            }
                        },
                    });
                    e.preventDefault();
                }
            }
            else
            {
                $("#returnError").css("color", "red").html("");
                $("#update_btn").attr("disabled", true);
                $.ajax({
                    url: "${baseUrl!}/collection/updateWithoutUpload",
                    async: false,
                    cache: false,
                    type: 'post',
                    dataType: "json",
                    data: {
                        collectionId: $("#collection_id").val(),
                        collectionName: collectionName,
                        description: collectionDescription,
                        showScope: $("input[name='show_scope']:checked").val()
                    },
                    success: function (data) {
                        if (data.code == '200') {
                            alert("修改成功!");
                            location.href = "${baseUrl!}/collection/index";
                        }
                        else if (data.code == '202'){
                            location.href = "${loginUrl!}";
                        }
                        else {
                            $("#returnError").css("color", "red").html(data.message);
                            $("#update_btn").attr("disabled", false);
                        }
                    },
                });
                e.preventDefault();
            }
        }
    });

    function deleteCollection(id) {

        if (confirm("你确定删除吗")) {
            $("#delete_btn").attr("disabled", true);
            $.ajax({
                url: "${baseUrl!}/collection/delete",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    collectionId: id
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("删除成功!");
                        location.href = "${baseUrl!}/collection/index";
                    }
                    else if (data.code == '202'){
                        location.href = "${loginUrl!}";
                    }
                    else {
                        $("#delete_btn").attr("disabled", false);
                    }
                },
            });
        }
        else {
            location.href = "${baseUrl!}/collection/index";
        }
    }

</script>
</@html>