<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 我的关注" description="关注" page_tab="focus" sidebar_about="show">

    <div id="right">
        <div id="follow-nav">
            <ul>
                <li style="border-bottom: #93247a solid thick;"><a href="#">所有用户</a></li>
                <li><a href="${baseUrl!}/focusGroup/group">我的分组</a></li>
            </ul>
        </div>
        <div id="follow-people">
            <#if snsFocusList?exists>
                <#list snsFocusList as snsFocus>
                    <div id="peoplecard">
                        <a href="${baseUrl!}/other/otherNewsShow?createUserId=${snsFocus.focusUserId}">
                        <img src="${uploadUrl!}${snsFocus.userAvatar}">
                        </a>
                        <a href="${baseUrl!}/other/otherNewsShow?createUserId=${snsFocus.focusUserId}">
                        <p id="follow-name">${snsFocus.userNickname}</p>
                        </a>
                        <#if snsFocus.userSignature?exists>
                            <p id="follow-intro">简介：${snsFocus.userSignature}</p>
                        <#else >
                            <p id="follow-intro">简介：空</p>
                        </#if>
                        <button onclick="cancelFocus('${snsFocus.focusId}')">取消关注</button>
                    </div>
                </#list>
            </#if>
        </div>
    </div>

<script>
    function cancelFocus(focusId)
    {
        if(window.confirm("确定取消关注吗?"))
        {
            $.ajax({
                url: "${baseUrl!}/focus/cancel",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    focusId: focusId
                },
                success: function (data) {
                    if (data.code == '200') {
                        window.location.href = "${baseUrl!}/focus/focus";
                    }
                    else {
                        window.alert(data.message);
                    }
                },
            });
        }
    }
</script>

</@html>