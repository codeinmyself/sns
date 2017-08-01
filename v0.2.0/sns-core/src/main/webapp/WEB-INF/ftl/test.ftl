<#include "common/_layout.ftl"/>
<@html title="Puckart > 上传图片demo" description="上传图片demo" page_tab="imageUploadDemo" sidebar_about="show">

<div style="position:relative;right:-200px">
<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
            <li><a href="${baseUrl!}/">Puckart</a></li>
            <li class="active">上传图片demo3</li>
        </ol>
    </div>
    <div class="panel-body">

        <input type="button" id="openLayerBtn" value="上传图片">
        <input type="hidden" id="picId" >
    </div>
</div>
</div>
<script type="text/javascript">

    $(function(){

        var offset = $('#openLayerBtn').offset();

        myUploader.init({
            server: '${baseUrl!}/demo/upload3',
            swf: '${staticUrl!}/js/Uploader.swf',
            picIds: 'picIds',
            fileIds: 'fileIds',
            openLayerBtn: 'openLayerBtn',
            layerOffset:{
                top:offset.top+25,
                left:offset.left
            }
        });
    });


</script>

<link  href="${staticUrl!}/layui/css/layui.css" rel="stylesheet"/>
<script src="${staticUrl!}/layui/layui.js"></script>
<link  href="${staticUrl!}/webuploader/webuploader.css" rel="stylesheet"/>
<script src="${staticUrl!}/webuploader/webuploader.js"></script>
<script src="${staticUrl!}/webuploader/myUploader.js"></script>

</@html>