<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 我的艺术圈" sidebar_circle_info=circle sidebar_member_info=memberMe description="艺术圈信息" page_tab="circle_owner" sidebar_about="circleshow">

    <div class="artCircle-rightbar">
        <div class="notice-bar">
            <#if memberMe ?exists>
            <#if memberMe.memberType=="01"||memberMe.memberType=="02">
                <a href="${baseUrl!}/circle/circleApplyManage?circleId=${circle.circleId}">
                    <div class="noticebt">通知</div>
                </a>
            </#if>
            </#if>
            <a href="${baseUrl!}/circle/memberManage?circleId=${circle.circleId}">
                <div class="noticebt-active">成员</div>
            </a>
        </div>
        <input type="text" name="firstname" placeholder="搜索成员" class="search">
        <!--动态加载内容-->
        <#if memberList?exists>
            <#list memberList as member>
                <div class="artCircle-myjoin">
                    <div class="artCircle-myjoin-content1">
                        <#if member.avatar??>
                            <img src="${uploadUrl!}${member.avatar}">
                        <#else >
                            <img src="${staticUrl!}/img/user/default_avatar.png">
                        </#if>

                    </div>
                    <div class="artCircle-myjoin-content2">
                        <div class="artCircle-myjoin-h1">${member.nickName}</div>
                    </div>
                    <#if memberMe ?exists>
                    <#if memberMe.memberType=="01">
                        <#if member.memberType=="03">
                            <button class="mybutton2" onclick="revise('${member.memberId}','02')">
                                设为管理员
                            </button>
                        <#elseif member.memberType=="02">
                            <button class="mybutton1" onclick="revise('${member.memberId}','03')">
                                取消管理员
                            </button>
                        <#else>
                            <button class="mybutton2">
                                艺术圈圈主
                            </button>
                        </#if>
                    <#if member.memberType=="03"||member.memberType=="02">
                        <div class="artCircle-myjoin-h3" onclick="del('${member.memberId}')">删除该成员</div>
                    </#if>
                    <#elseif memberMe.memberType=="02">
                        <#if member.memberType=="03">
                            <button class="mybutton2" onclick="revise('${member.memberId}','02')">
                                设为管理员
                            </button>
                        <#elseif member.memberType=="02">
                            <button class="mybutton1">
                                管理员
                            </button>
                        <#else>
                            <button class="mybutton2">
                                艺术圈圈主
                            </button>
                        </#if>
                        <#if member.memberType=="03">
                        <div class="artCircle-myjoin-h3" onclick="del('${member.memberId}')">删除该成员</div>
                        </#if>
                    <#else>
                        <#if member.memberType=="03">
                            <button class="mybutton1">成员</button>
                        <#elseif member.memberType=="02">
                            <button class="mybutton2">管理员</button>
                        <#else>
                            <button class="mybutton2">艺术圈圈主</button>
                        </#if>
                    </#if>
                    </#if>
                </div>
            </#list>
        <#else>
            <p>没有成员！</p>
        </#if>
    </div>

<script>

    function revise(memberId,  memberType) {
        if (confirm('确定要修改此权限？')) {
            $("#revise_btn").attr("disabled", true);
            $.ajax({
                url: "${baseUrl!}/circle/submitMemberRevise",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    memberId: memberId,
                    memberType: memberType

                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("修改成功！");
                        window.location.reload();
                    } else {
                        $("#revise_btn").attr("disabled", false);
                    }
                },
            });
        }
    }
    function del(memberId) {
        if (confirm('确定要删除此成员吗？')) {
            $("#del_btn").attr("disabled", true);
            $.ajax({
                url: "${baseUrl!}/circle/deleteMember",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    memberId: memberId,
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("删除成功!");
                        location.href = "${baseUrl}/circle/memberManage?circleId=${circle.circleId}";
                    } else {
                        $("#del_btn").attr("disabled", false);
                    }
                }

            });
        }
    }


</script>
</@html>
