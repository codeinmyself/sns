<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 评论" description="评论" page_tab="newsComment" sidebar_about="show">
<div id="right">
    <div id="inform">
        <div>
            <a href="${baseUrl!}/notice/show" class="informTab">未读通知</a>
            <a href="javascript:void(0)" class="informTabActive">已读通知</a>
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
                            ${notice.noticeContent}
                        </td>
                        <td>
                            ${notice.createTime}
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        <#else ><div style="text-align: center">暂无新通知!</div>
        </#if>
    </div>

</div>
</@html>