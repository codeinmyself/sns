<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 评论" description="评论" page_tab="newsComment" sidebar_about="show">
    <div id="right">
        <div id="inform">
            <div>
                <a href="javascript:void(0)" class="informTabActive">未读通知</a>
                <a href="${baseUrl!}/notice/showHasRead" class="informTab">已读通知</a>
            </div>
        <#if noticeList?exists>
            <table>
                <thead>
                <th>通知</th>
                <th>时间</th>
                </thead>
                <tbody>
                <#list noticeList as notice>
                    <tr>
                        <td>
                            <#if notice.noticeType=="01"||notice.noticeType=="02"||notice.noticeType=="03"
                            ||notice.noticeType=="04"||notice.noticeType=="05"||notice.noticeType=="06"||
                            notice.noticeType=="08"||notice.noticeType=="09"||
                            notice.noticeType=="10"||notice.noticeType=="11">
                            <a href="${baseUrl!}/notice/read?noticeId=${notice.noticeId}">
                                ${notice.noticeContent}
                            </a>
                            <#else >${notice.noticeContent}
                            </#if>
                        </td>
                        <td>
                            <#if notice.noticeType=="01"||notice.noticeType=="02"||notice.noticeType=="03"
                            ||notice.noticeType=="04"||notice.noticeType=="05"||notice.noticeType=="06"||
                            notice.noticeType=="08"||notice.noticeType=="09"||
                            notice.noticeType=="10"||notice.noticeType=="11">
                            <a href="${baseUrl!}/notice/read?noticeId=${notice.noticeId}">
                                ${notice.createTime}
                            </a>
                            <#else >${notice.createTime}
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        <#else ><div style="text-align: center">暂无新通知!</div>
        </#if>
        </div>

    </div>
<script>
    function deleteNotice(noticeId)
    {
        if(window.confirm("您确定删除此通知吗?")) {
            $.ajax({
                url: "${baseUrl!}/notice/delete",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    noticeId: noticeId
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("删除成功！");
                        location.href="${baseUrl!}/notice/show";
                    }
                    else {
                        alert(data.message);
                    }
                }
            });
        }
    }
</script>
</@html>