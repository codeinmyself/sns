<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 上传图片demo" description="上传图片demo" page_tab="imageUploadDemo" sidebar_about="show">
<div class="panel panel-default">
    <div class="panel-heading">
        <ol class="breadcrumb">
            <li><a href="${baseUrl!}/">Puckart</a></li>
            <li class="active">上传图片demo</li>
        </ol>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" style="margin-top: 20px;">

            <input type="file" multiple id="ssi-upload3"/>


        </form>
    </div>
</div>

<link href="${staticUrl!}/js/ssi-uploader/ssi-uploader.css" rel="stylesheet">
<link href="${staticUrl!}/js/ssi-uploader/ssi-uploader.js" rel="stylesheet">
<script>

</script>
</@html>