<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 申请管理" sidebar_circle_info=circle sidebar_member_info=memberI description="申请管理" page_tab="apply_manage" sidebar_about="circleshow" >
<div id="circle-content">
    <div class="artCircle-rightbar">
        <div class="notice-bar">
            <a href="${baseUrl!}/circle/circleApplyManage?circleId=${circle.circleId}">
                <div class="noticebt-active">通知</div>
            </a>
            <a href="${baseUrl!}/circle/memberManage?circleId=${circle.circleId}">
                <div class="noticebt">成员</div>
            </a>
        </div>
        <input type="text" name="firstname" placeholder="搜索成员" class="search">
        <!--动态加载内容-->
        <#if memberApplyList?exists>
            <#list memberApplyList as apply>
                <div class="artCircle-myjoin">
                    <div class="artCircle-myjoin-content1">
                        <#if apply.avatar?exists>
                            <img src="${uploadUrl!}${apply.avatar}" class="artCircle-myjoin-img">
                        <#else >
                            <img src="${staticUrl!}/img/user/default_avatar.png" class="artCircle-myjoin-img">
                        </#if>
                    </div>
                    <div class="artCircle-myjoin-content2">
                        <div class="artCircle-myjoin-h1">${apply.nickname}</div>
                        <div class="artCircle-myjoin-h2">${apply.applyTime} &nbsp;&nbsp;&nbsp;&nbsp; 申请信息：${apply.applyDesc}</div>
                    </div>
                    <#if apply.applyStatus=="01">
                        <button class="mybutton1" onclick="submit('${apply.circleId}','${apply.applyUserId}','02')">同意
                        </button>
                        <div class="artCircle-myjoin-h3">
                            <a onclick="ignore('${apply.circleId}','${apply.applyUserId}','03')">忽略</a>
                        </div>
                    <#elseif apply.applyStatus=="02">
                        <button class="mybutton1">已同意</button>
                    <#else>
                        <button class="mybutton1">已忽略</button>
                    </#if>
                </div>
            </#list>
        <#else>
            <p>没有申请消息！</p>
        </#if>
    </div>
</div>
<script>
    function submit(circleId, applyUserId, applyStatus) {
        $("#submit_btn").attr("disabled", true);
        alert("确定同意此申请？");
        $.ajax({
            url: "${baseUrl!}/circle/applyDeal",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                circleId: circleId,
                applyUserId: applyUserId,
                applyStatus: applyStatus
            },
            success: function (data) {
                if (data.code == '200') {
                    alert("审核成功!");
                    window.location.reload();
                } else {
                    $("#submit_btn").attr("disabled", false);
                }
            }

        });

    }

    function ignore(circleId, applyUserId, applyStatus) {
        $("#submit_btn").attr("disabled", true);
        alert("确定拒绝此申请？");
        $.ajax({
            url: "${baseUrl!}/circle/applyDeal",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                circleId: circleId,
                applyUserId: applyUserId,
                applyStatus: applyStatus
            },
            success: function (data) {
                if (data.code == '200') {
                    alert("已拒绝!");
                    window.location.reload();
                } else {
                    $("#submit_btn").attr("disabled", false);
                }
            }

        });

    }

</script>
</@html>
