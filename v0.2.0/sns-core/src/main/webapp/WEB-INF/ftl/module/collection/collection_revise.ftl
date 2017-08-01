<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 作品集信息" description="个人信息" page_tab="collection_information" sidebar_about="show">
<h4>Puckart > 作品集修改 </h4>
<form name="form1" method="post" action="tem?action=temModify">
    <#list collectionList as collection>
        <li> 作品集id ：<input name="id" type="text" id="collectionId"  value="${collection.collectionId}" readonly="readonly" size="30"></li>
        <li>作品集名称：<input name="name" type="text" id="collectionName"  value="${collection.collectionName}" size="30"></li>
        <li>作品集简介：<input name="description" type="text" id="description"  value="${collection.description}" size="30"></li>
        <form>
            <h6>选择公开范围：</h6>
            <#if collection.showScope=='01'>
                公开：
                <input type="radio" checked="checked" name="show_scope" value="01" />
                <br><br>
                仅限用户关注者：
                <input type="radio" name="show_scope" value="02" />
                <br><br>
                仅限自己：
                <input type="radio"  name="show_scope" value="03" />
            </#if>
            <#if collection.showScope=='02'>
                公开：
                <input type="radio" name="show_scope" value="01" />
                <br><br>
                仅限用户关注者：
                <input type="radio" checked="checked" name="show_scope" value="02" />
                <br><br>
                仅限自己：
                <input type="radio"  name="show_scope" value="03" />
            </#if>
            <#if collection.showScope=='03'>
                公开：
                <input type="radio" name="show_scope" value="01" />
                <br><br>
                仅限用户关注者：
                <input type="radio" name="show_scope" value="02" />
                <br><br>
                仅限自己：
                <input type="radio" checked="checked" name="show_scope" value="03" />
            </#if>
        </form>
    </#list>
    <div class="form-group">
        <div class=""col-sm-offset-2 col-sm-8">
        <a onclick="save()" id="save_btn" class="btn btn-raised btn-default">保存</a>
    </div>
</form>
<script>
    function save(){
        $("#save_btn").attr("disabled",true);
        $.ajax({
            url:"${baseUrl!}/collectionInformationRevise",
            async:false,
            cache:false,
            type:'post',
            dataType:"json",
            data:{
                collectionId:$("#collectionId").val(),
                collectionName:$("#collectionName").val(),
                description:$("#description").val(),
                showScope:$("input[name='show_scope']:checked").val()
            },
            success:function(data){
                if(data.code=='200') {
                    alert("修改成功!");
                    location.href = "${baseUrl!}/collectionEdit";
                }else{
                    $("#save_btn").attr("disabled",false);
                }
            },
        });
    }
</script>
</@html>