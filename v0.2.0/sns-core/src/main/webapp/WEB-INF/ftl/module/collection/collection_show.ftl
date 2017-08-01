<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 作品集展示" description="作品集展示" page_tab="collection_information" sidebar_about="show">


<div id="right">
    <div style="padding-left: 20px;">
        <div id="loginMainshow">
            <#if collectionId?exists>
                <#if newsList?exists>
                    <#list newsList as news>
                        <div id="loginMainshowNo1">
                            <div class="outer">
                                <a href="${baseUrl}/news/find ?createUserId=${news.createUserId}" class="headPic"><img src="${uploadUrl!}${news.avatar}"></a>
                                <div class="main-show-message">
                                    <a href="${baseUrl}/news/find ?createUserId=${news.createUserId}">${news.nickname}</a>
                                    <span>${news.createTime}</span>
                                    <span>浏览(${news.clickCount})</span>
                                </div>
                                <#if Session.user.userId==news.createUserId>
                                    <ul class="slideButton">
                                        <li><button onclick="deleteNewsInCollection('${news.newsId}','${collectionId}')">删除</button></li>
                                    </ul>
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
    <#--                                    <li>
                                            <img src="${staticUrl!}/img/icon/send.png">
                                            <a href="#" class="resendBtn">转发</a>
                                        </li>-->
                                        <li>
                                            <img src="${staticUrl!}/img/icon/comment.png">
                                            <a href="${baseUrl!}/news/comment?newsId=${news.newsId}">评论(${news.commentCount})</a>
                                        </li>
                                        <li>
                                            <img src="${staticUrl!}/img/icon/praise.png">
                                            <a href="#" onclick="addLike('${news.newsId}','${collectionId}')" id="addLike_btn">点赞(<span id="addLike_count">${news.likeCount}</span>)</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </#if>
        </div>
    </div>
</div>
<script>
    function deleteNewsInCollection(newsId,collectionId)
    {
        if(window.confirm("确定删除该动态吗?")) {
            $.ajax({
                url: "${baseUrl!}/news/deleteNewsInConnection",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    newsId: newsId,
                    collectionId: collectionId
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("从作品集移除成功！");
                        location.href = "${baseUrl!}/collection/show?collectionId=" + collectionId;
                    }
                    else {
                        alert(data.message);
                    }
                },
            });
        }
    }

    function addLike(id,collectionId){
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
                    location.href = "${baseUrl!}/collection/show?collectionId=" + collectionId;
                }
                else{
                    $("#addLike_btn").attr("disabled",false);
                }
            },
        });
    }
</script>
</@html>