<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 我的艺术圈" description="艺术圈信息" page_tab="circle_owner" sidebar_about="show">
<script>

    $(document).ready(function () {
        $("#flip").click(function () {
            $(".panel").slideToggle("slow");
            if ($("#side-nav").height() == 288) {
                $("#side-nav").animate({ height: '370px' });
            }
            else {
                $("#side-nav").animate({ height: '288px' });
            }


        });
    });

</script>


<div class="artCircle-rightbar">
    <div class="notice-bar">
        <a href=""><div class="noticebt-active">我发起的艺术圈</div></a>
    </div>
    <!--动态加载内容-->
    <#if circleList?exists>
        <#list circleList  as circle >
            <#if circle.status=='1' && circle.createUserId == Session.user.userId>
                <div class="artCircle-myjoin">
                    <div class="artCircle-myjoin-content1">
                        <#if circle.avatar??>
                        <img src="${uploadUrl!}${circle.avatar}" class="artCircle-myjoin-img">
                            <#else>
                                <img src="${staticUrl!}/img/user/default_avatar.png" class="artCircle-myjoin-img">
                        </#if>
                    </div>
                    <div class="artCircle-myjoin-content2">
                        <div class="artCircle-myjoin-h1">${circle.circleName}</div>
                        <div class="artCircle-myjoin-h2">${circle.createTime}</div>
                        <div class="artCircle-myjoin-h2">简介：${circle.description}</div>
                    </div>
                    <button class="mybutton1"><a href="${baseUrl!}/circle/circleActivity?circleId=${circle.circleId}&circleName=${circle.circleName}">点击查看</a></button>
                    <div class="artCircle-myjoin-h3">
                        <a onclick="del('${circle.circleId}')">删除艺术圈</a>
                    </div>
                </div>
            </#if>
        </#list>
    </#if>
    <div class="artCircle-myjoin2">
        <button class="mybutton2"><a href="${baseUrl!}/circle/circleAdd">发起新的</a>
    </div>
</div>


<script>
    function del(circleId){
        if(confirm('确定要删除此艺术圈吗？')) {
            $("#del_btn").attr("disabled",true);
            $.ajax({
                url:"${baseUrl!}/circle/circleDelete",
                async:false,
                cache:false,
                type:'post',
                dataType:"json",
                data:{
                    circleId:circleId,
                },
                success:function(data){
                    if(data.code=='200') {
                        alert("删除成功!");
                        location.href="${baseUrl!}/circle/circleQuery";
                    }else{
                        $("#del_btn").attr("disabled",false);
                    }
                }

            });
        }
    }


</script>
</@html>
