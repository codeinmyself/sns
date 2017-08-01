<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 探索艺术圈" description="探索艺术圈" page_tab="circle_explore" sidebar_about="show">

<div class="artCircle-rightbar">
    <form action="${baseUrl!}/circle/circleSearch">
        <div class="notice-bar">
            <div class="noticebt-active">艺术圈搜索</div>
        </div>
        <span><input type="text" name="keyword" placeholder="搜索艺术圈" class="search"><button class="artCircle-search-button">搜索</button></span>
        <div class="artCircle-myjoin-h3">&nbsp;&nbsp;艺术圈推荐</div>
        <#if circleList?exists>
            <#list circleList as circle>
                <div class="artCircle-myjoin">
                    <div class="artCircle-myjoin-content1">
                        <img src="${uploadUrl!}${circle.avatar}" class="artCircle-myjoin-img">
                    </div>
                    <div class="artCircle-myjoin-content2">
                        <div class="artCircle-myjoin-h1">${circle.circleName}</div>
                        <div class="artCircle-myjoin-h2">${circle.createTime}</div>
                    </div>
                    <button class="mybutton1"><a href="${baseUrl!}/circle/circleActivity?circleId=${circle.circleId}&circleName=${circle.circleName}">点击查看</a></button>
                    <#if circle.isJoinIn=="0">
                        <a onclick=submit1('${circle.circleId}')  class="artCircle-myjoin-h3">加入</a>
                    <#else >
                        <#if Session.user.userId==circle.createUserId>
                            <a class="artCircle-myjoin-h3"> 创建者</a>
                        <#else> <a class="artCircle-myjoin-h3"> 已加入</a>
                        </#if>
                    </#if>
                </div>
            </#list>
<#--        <div class="artCircle-myjoin">
            <div class="artCircle-myjoin-content1">
                <img src="${staticUrl!}/img/circle/IMG_2810.JPG" class="artCircle-myjoin-img">
            </div>
            <div class="artCircle-myjoin-content2">
                <div class="artCircle-myjoin-h1">XX艺术圈</div>
                <div class="artCircle-myjoin-h2">1分钟前</div>
            </div>
            <button class="mybutton1"><a href="#">未关注</a></button>
            <div class="artCircle-myjoin-h3">点击进入</div>
        </div>-->
        </#if>
    </form>
</div>
<script>

    function submit1(circleId){
        if(confirm('申请加入此艺术圈？')) {
            $("#del_btn").attr("disabled",true);
            $.ajax({
                url:"${baseUrl!}/circle/directApply",
                async:false,
                cache:false,
                type:'post',
                dataType:"json",
                data:{
                    circleId:circleId
                },
                success:function(data){
                    if(data.code=='200') {
                        alert("您已加入！")
                        location.href="${baseUrl!}/circle/circleJoin";
                    }else{
                        $("#del_btn").attr("disabled",false);
                    }
                }

            });
        }
    }
</script>
</@html>