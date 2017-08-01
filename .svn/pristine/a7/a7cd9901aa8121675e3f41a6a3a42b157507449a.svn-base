<#include "../../common/_layout.ftl"/>
<@html title="Puckart " description="评论" page_tab="newsComment" sidebar_about="show1">
<div id="other-contain">
<#if userList?exists>
    <#list userList as user>
        <div id="other-head">
        <div id="other-head-info">
            <img id="other-avatar" src="${uploadUrl!}${user.avatar}">
            <div id="other-name">${user.nickname}</div>
            <#if Session.user.userId!=user.userId>
                <#if hasFocus?exists>
                    <#if hasFocus==false>
                        <button type="button" value="${user.userId}" class="newsBtn">关注</button>
                    <#else >
                        <button type="button" class="newsBtn" disabled="disabled">已关注</button>
                    </#if>
                </#if>
            </#if>
        </div>
            <div id="other-nav"><a href="" style="border-bottom:#93247a solid 5px">&nbsp;Ta的动态&nbsp;</a><a href="${baseUrl!}/other/otherCollectionShow?createUserId=${user.userId}">&nbsp;Ta的作品集&nbsp;</a></div>
        </div>
        <div id="other-body">
            <div id="other-info">
                <div id="other-info-title">个人信息</div>
                <p>昵称：${user.nickname}</p>
                <#if user.isArtist == '1'>
                    <p>身份：艺术家</p>
                </#if>
                <#if user.isArtist == '0'>
                    <p>身份：非艺术家</p>
                </#if>
            </div>
        </div>
    </#list>
</#if>

    <div id="other-content">
    <#if newsList?exists>
        <#list newsList as news>
            <div class="other-main-show-inner">
                <a href="#"><img src="${uploadUrl!}${news.avatar}"></a>
                <div class="main-show-message">
                    <p>${news.nickname}</p>
                    <span>${news.createTime}</span>
                    <span>浏览(${news.clickCount})</span>
                </div>
                <p class="main-show-user-comment" align="left">${news.content}</p>
                <div class="main-show-comment-img">
                    <#if news.imageList?exists>
                        <#list news.imageList as image>
                            <img src="${uploadUrl!}/news/${image.picId}.jpg">
                        </#list>
                    </#if>
                </div>
                <div class="main-show-comment-buttons">
                    <ul>
<#--                        <li>
                            <img src="${uploadUrl!}/send.png">
                            <a href="#">转发</a>
                        </li>-->
                        <li>
                            <img src="${staticUrl!}/img/icon/comment.png">
                            <a href="${baseUrl!}/news/comment?newsId=${news.newsId}">评论(<span id="addComment_count">${news.commentCount}</span>)</a>
                        </li>
                        <li>
                            <img src="${staticUrl!}/img/icon/praise.png">
                            <a href="#">点赞</a>
                        </li>
                    </ul>
                </div>
            </div>
        </#list>
    </#if>
    </div>
</div>


<div id="focus-modal">
    <div id="focus-list">
        <form method="post" autocomplete="off">
            <label>
                <p id="label-name">选择分组</p>
            </label>
            <input type="hidden" id="userIdHidden">
            <#if focusGroupList?exists>
                <#list focusGroupList as snsFocusGroup>
                    <input type="radio" name="focusGroup" id="group${snsFocusGroup_index}" value="${snsFocusGroup.groupId}">
                    <span>${snsFocusGroup.groupName}</span>
                </#list>
            <#else >&nbsp;&nbsp;&nbsp;&nbsp;暂无分组
            </#if>
            <br>
            <button type="button" id="saveToGroup" onclick="focusOthers()">保存</button>
            <button type="button" id="cancelToGroup">取消</button>
        </form>
    </div>
</div>
<script>
    window.onload=function () {
        var openBtn = $(".newsBtn");
        var closeBtn = document.getElementById('cancelToGroup');
        var targetList = document.getElementById('focus-modal');
        getToCenter($('#focus-list'));
        for(var i=0;i<openBtn.length;++i)
        {
            new model(openBtn[i], closeBtn, targetList);
        }
    }
    function focusOthers() {
        var groupId = "";
        var radioInput = $("input[name='focusGroup']");
        for (var i = 0; i < radioInput.length; ++i) {
            if (radioInput[i].checked) {
                groupId = radioInput[i].value;
            }
        }
        $.ajax({
            url: "${baseUrl!}/focus/add",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                focusUserId:$("#userIdHidden").val(),
                groupId:groupId
            },
            success: function (data) {
                if (data.code == '200') {
                    window.location.reload();
                    $("#newsBtn").val("已关注");
                    $("#newsBtn").css("disabled","true");
                }
                else {
                    window.alert(data.message);
                }
            },
        });
    }
</script>
</@html>

