<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 艺术圈" sidebar_circle_info=circle sidebar_member_info=member description="艺术圈动态" page_tab="circle_activity" sidebar_about="circleshow" >
<body>
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
<div id="circle-content">
<div class="artCircle-management-search">
    <div class="notice-bar">
        <div class="noticebt-active">艺术圈首页</div>
    </div>
    <span><input type="text" name="firstname" placeholder="搜索动态" class="search"><button class="artCircle-search-button">搜索</button></span>
</div>

    <div id="loginMainshow">
        <#if newsList?exists>
            <#list newsList as news>
                <div id="loginMainshowNo1">
                    <div class="outer">
                        <a href="${baseUrl}/news/otherNewsShow?createUserId=${news.createUserId}" class="headPic"><img src="${uploadUrl!}${news.avatar}"></a>
                        <div class="main-show-message">
                            <a href="${baseUrl}/news/find ?createUserId=${news.createUserId}">${news.nickname}</a>
                            <span>${news.createTime}</span>
                            <span>浏览(${news.clickCount})</span>
                        </div>
                        <#if member?exists>
                        <#if Session.user.userId==news.createUserId||member.memberType=="01"||member.memberType=="02">
                            <ul class="slideButton">
                                <li><button onclick="deleteNewsInCircle('${news.newsId}','${news.createUserId}','${circle.circleId}')">删除</button></li>
                            </ul>
                        </#if>
                        </#if>
                        <p class="main-show-user-comment">${news.content}</p>
                        <div class="main-show-comment-img">
                            <#if news.imageList?exists>
                                <#list news.imageList as image>
                                    <img src="${uploadUrl!}/news/${image.picId}.jpg">
                                </#list>
                            </#if>
                        </div>
                        <div class="main-show-comment-buttons">
                            <ul>
                                <#--  <li>
                                    <img src="${staticUrl!}/img/icon/send.png">
                                    <a href="#" class="resendBtn">转发</a>
                                </li>-->
                                <li>
                                    <img src="${staticUrl!}/img/icon/comment.png">
                                    <a href="${baseUrl!}/news/comment?newsId=${news.newsId}">评论(${news.commentCount})</a>
                                </li>
                                <li>
                                    <img src="${staticUrl!}/img/icon/praise.png">
                                    <a href="#" onclick="addLike('${news.newsId}','${circle.circleId}')" id="addLike_btn">点赞(<span id="addLike_count">${news.likeCount}</span>)</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>
</body>
<script>
    function deleteNewsInCircle(newsId,createUserId,circleId)
    {
        if(window.confirm("确定删除该动态吗?"))
        {
        $.ajax({
            url: "${baseUrl!}/news/deleteNewsInCircle",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                newsId:newsId,
                circleId:circleId,
                createUserId:createUserId
            },
            success: function (data) {
                if (data.code == '200') {
                    alert("从艺术圈移除成功！");
                    location.href="${baseUrl!}/circle/circleActivity?circleId="+circleId;
                }
                else {
                    alert(data.message);
                }
            },
        });
        }
    }

    function addLike(id,circleId){
        $("addLike_btn").attr("disabled",true);
        $.ajax({
            url: "${baseUrl!}/news/addLike",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data:{
                newsId:id
            },
            success:function(data){
                if(data.code == '200'){
                    location.href="${baseUrl!}/circle/circleActivity?circleId="+circleId;
                }
                else{
                    $("#addLike_btn").attr("disabled",false);
                }
            },
        });
    }
</script>

</@html>
