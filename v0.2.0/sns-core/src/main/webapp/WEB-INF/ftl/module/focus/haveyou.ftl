<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 我的关注" description="关注" page_tab="focus" sidebar_about="show">

<div id="right">
    <div id="follow-nav">
        <ul>
            <li style="border-bottom: #93247a solid thick;"><a href="#">我的粉丝</a></li>
        </ul>
    </div>
    <div id="follow-people">
        <#if snsFocusList?exists>
            <#list snsFocusList as snsFocus>
                <div id="peoplecard">
                    <a href="${baseUrl!}/other/otherNewsShow?createUserId=${snsFocus.userId}"><img src="${uploadUrl!}${snsFocus.userAvatar}">
                    <p id="follow-name">${snsFocus.userNickname}</p></a>
                    <#if snsFocus.userSignature?exists>
                        <p id="follow-intro">简介：${snsFocus.userSignature}</p>
                    <#else >
                        <p id="follow-intro">简介：空</p>
                    </#if>
                    <#if snsFocus.isEachOther?exists>
                        <#if snsFocus.eachOther==true>
                            <button onclick="cancelFocus('${snsFocus.userId}','${snsFocus.focusUserId}')">取消关注</button>
                        <#else >
                            <button value="${snsFocus.userId}" class="focusBtn">关注</button>
                        </#if>
                    </#if>
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
            <#if snsFocusGroupList?exists>
                <#list snsFocusGroupList as snsFocusGroup>
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
    function cancelFocus(focusUserId,userId)
    {
        if(window.confirm("确定取消关注吗?"))
        {
            $.ajax({
                url: "${baseUrl!}/focus/cancelByUserId",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    userId: userId,
                    focusUserId:focusUserId
                },
                success: function (data) {
                    if (data.code == '200') {
                        window.location.href = "${baseUrl!}/focus/haveyou";
                    }
                    else {
                        window.alert(data.message);
                    }
                },
            });
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
                    window.location.href = "${baseUrl!}/focus/haveyou";
                }
                else {
                    window.alert(data.message);
                }
            },
        });
    }
    window.onload=function () {
        var openBtn = $(".focusBtn");
        var closeBtn = document.getElementById('cancelToGroup');
        var targetList = document.getElementById('focus-modal');
        getToCenter($('#focus-list'));
        for(var i=0;i<openBtn.length;++i)
        {
            new model(openBtn[i], closeBtn, targetList);
        }
    }
</script>
</@html>