<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 探索艺术圈" description="艺术圈信息" page_tab="circle_explore_query" sidebar_about="show">
<div class="artCircle-rightbar">
    <form action="${baseUrl!}/circle/circleSearch">
    <div class="notice-bar">
        <div class="noticebt-active">艺术圈搜索</div>
    </div>
    <span><input type="text" name="nickname" placeholder="搜索动态" class="search"><button class="artCircle-search-button">搜索</button></span>
    </form>
    <#if circleList1?exists>
        <#list circleList1  as circle >
            <#if circle.createUserId == Session.user.userId >
            <div class="artCircle-myjoin">
                <div class="artCircle-myjoin-content1">
                    <#if circle.avatar??>
                    <img src="${uploadUrl!}${circle.avatar}" class="artCircle-myjoin-img">
                        <#else >
                            <img src="${staticUrl!}/img/user/default_avatar.png" class="artCircle-myjoin-img">
                    </#if>
                </div>
                <div class="artCircle-myjoin-content2">
                    <div class="artCircle-myjoin-h1">${circle.circleName}</div>
                    <div class="artCircle-myjoin-h2">${circle.createTime}</div>
                    <div class="artCircle-myjoin-h2">简介：${circle.description}</div>
                </div>
                <button class="mybutton1"><a href="${baseUrl!}/circle/circleActivity?circleId=${circle.circleId}&circleName=${circle.circleName}">点击查看</a></button>
            <#else>
            <div class="artCircle-myjoin">
                <div class="artCircle-myjoin-content1">
                    <#if circle.avatar??>
                    <img src="${uploadUrl!}${circle.avatar}" class="artCircle-myjoin-img">
                        <#else>
                            <img src="${staticUrl!}/img/user/default_avatar.png" class="artCircle-myjoin-img">
                    </#if>
                </div>
                <div class="artCircle-myjoin-content2">
                    <div class="artCircle-myjoin-h1">${circle.circleName}艺术圈</div>
                    <div class="artCircle-myjoin-h2">${circle.createTime}</div>
                    <div class="artCircle-myjoin-h2">简介：${circle.description}</div>
                </div>
            <#if circle.showScope=="01">
                <button class="mybutton1"><a href="${baseUrl!}/circle/circleActivity?circleId=${circle.circleId}&circleName=${circle.circleName}">点击查看</a></button>
            <#else >
                <button class="mybutton1"><a href="#">仅限成员查看</a></button>
            </#if>
                <#if circle.applyStrategy=="01">
                    <#if circle.isJoinIn=="0">
                        <a onclick=submit1('${circle.circleId}')  class="artCircle-myjoin-h3">加入</a>
                    <#else ><a class="artCircle-myjoin-h3"> 已加入</a>
                    </#if>
                <#else>
                    <#if circle.isJoinIn=="0">
                        <a href="${baseUrl!}/circle/circleApply?circleId=${circle.circleId}" class="artCircle-myjoin-h3">申请加入</a>
                    <#else ><a class="artCircle-myjoin-h3"> 已加入</a>
                    </#if>
                </#if>
            </#if>
        </div>
        </#list>
    <#else>
        <p>没有找到艺术圈!</p>
    </#if>
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
