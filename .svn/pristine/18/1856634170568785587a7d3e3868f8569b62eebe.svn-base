<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 作品集信息" description="个人信息" page_tab="collection_information" sidebar_about="show">
<h4>Puckart > 作品集创建</h4>
<div class="container">
    <div class="row">
        <div class="col-xs-3">
            <ul class="nav nac-tabs nav-stacked" data-spy="affix" data-offset-top="125">

                <form>
                    <h6>作品集名称：</h6>
                    <input type="text" id="collection_name">
                </form>
                <form>
                    <h6>作品集简介：</h6>
                    <input type="text" id="description">
                </form>
                <form>
                    <h6>选择公开范围：</h6>
                    公开：
                    <input type="radio" checked="checked" name="show_scope" value="01" />
                    <br><br>
                    仅限用户关注者：
                    <input type="radio" name="show_scope" value="02" />
                    <br><br>
                    仅限自己：
                    <input type="radio" name="show_scope" value="03" />
                </form>
                <div class="form-group">
                    <div class=""col-sm-offset-2 col-sm-8">
                    <a onclick="submit()" id="submit_btn" class="btn btn-raised btn-default">创建</a>
                </div>
        </div>

        </ul>
    </div>
</div>

</div>
<script>
    function submit(){
        $("#submit_btn").attr("disabled",true);
        $.ajax({
            url:"${baseUrl!}/collectionAdd",
            async:false,
            cache:false,
            type:'post',
            dataType:"json",
            data:{
                collectionName:$("#collection_name").val(),
                description:$("#description").val(),
                showScope:$("input[name='show_scope']:checked").val()
            },
            success:function(data){
                if(data.code=='200') {
                    alert("创建成功!");
                    location.href = "${baseUrl!}/collectionIndex";
                }else if(data.code=='202'){
                    location.href = "${loginUrl!}";
                }else{
                    $("#submit_btn").attr("disabled",false);
                }
            },
        });
    }
</script>

</@html>