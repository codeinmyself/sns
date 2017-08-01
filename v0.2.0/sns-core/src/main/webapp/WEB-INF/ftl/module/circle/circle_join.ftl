<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 我的艺术圈" description="艺术圈信息" page_tab="circle_owner" sidebar_about="show">
<div class="artCircle-rightbar">
    <div class="notice-bar">
        <div class="noticebt-active">我加入的</div>
    </div>
    <!--动态加载内容-->
    <#if joinList?exists>
        <#list joinList  as join >
    <div class="artCircle-myjoin">
        <div class="artCircle-myjoin-content1">
            <#if join.avatar??>
            <img src="${uploadUrl!}${join.avatar}" class="artCircle-myjoin-img">
                <#else>
                    <img src="${staticUrl!}/img/user/dafault_avatar.png" class="artCircle-myjoin-img">
            </#if>
        </div>
        <div class="artCircle-myjoin-content2">
            <div class="artCircle-myjoin-h1">${join.circleName}</div>
            <div class="artCircle-myjoin-h2">1分钟前</div>
            <div class="artCircle-myjoin-h2">简介：${join.description}</div>
        </div>
        <button class="mybutton1"><a href="${baseUrl!}/circle/circleActivity?circleId=${join.circleId}&circleName=${join.circleName}">点击查看</a></button>
        <a onclick="exit('${join.circleId}')"  class="artCircle-myjoin-h3">退出艺术圈</a>
    </div>
        </#list>
    </#if>
</div>

<script>
    function exit(circleId){
        if(confirm('确定要退出此艺术圈吗？')) {
            $("#del_btn").attr("disabled",true);
            $.ajax({
                url:"${baseUrl!}/circle/circleOut",
                async:false,
                cache:false,
                type:'post',
                dataType:"json",
                data:{
                    circleId:circleId
                },
                success:function(data){
                    if(data.code=='200') {
                        alert("退出成功!");
                        location.href="${baseUrl}/circle/circleJoin";
                    }else{
                        $("#del_btn").attr("disabled",false);
                    }
                }

            });
        }
    }


</script>
</@html>
