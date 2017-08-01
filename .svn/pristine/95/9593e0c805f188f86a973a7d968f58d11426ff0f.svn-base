<#macro sidebar sidebar_user_info = "" sidebar_newtopic="" sidebar_about="" >
<#if sidebar_user_info == "show">
    <div class="panel panel-default">
        <#if Session.user??>
            <div class="panel-heading">
                <span class="glyphicon glyphicon-user"></span>
                <b>个人信息</b>
            </div>
            <div class="panel-body">
                <div class="media">
                    <div class="media-left">
                        <a href="${staticUrl!}/user/${Session.user.userId!}" style="text-decoration: none;">
                            <img src="${staticUrl!}${Session.user.avatar!}" title="${Session.user.nickname!}" class="avatar">
                        </a>
                    </div>
                    <div class="media-body">
                        <div class="media-heading">
                            <a href="${baseUrl!}/user/${Session.user.userId!}">${Session.user.nickname!}</a>
                        </div>
                    </div>
                </div>
            </div>
        <#else>
            <div class="panel-body">
                <h5>puckart社区</h5>
                <p>在这里，您可以提问，回答，分享，诉说，这是个属于puckart艺术交流的社区，欢迎您的加入！</p>
            </div>
        </#if>
    </div>
</#if>
<#if sidebar_newtopic == "show">
    <#if Session.user??>
    <div class="panel panel-default">
        <div class="panel-body">
            <a href="${baseUrl!}/topic/add" class="btn btn-raised btn-default ">&nbsp;发布话题&nbsp;</a>
        </div>
    </div>
    </#if>
</#if>
<#if sidebar_about == "show">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="glyphicon glyphicon-paperclip"></span>
            <b>关于puckart社区</b>
        </div>
        <div class="panel-body">
            <p>在这里你可以：</p>
            <p><span class="dgray">• </span>向别人提出你遇到的问题</p>
            <p><span class="dgray">• </span>帮助遇到问题的人</p>
            <p><span class="dgray">• </span>分享心爱的艺术品</p>
            <p><span class="dgray">• </span>分享艺术品鉴赏知识</p>
            <p><span class="dgray">• </span>提供改进建议</p>
        </div>
    </div>
</#if>
</#macro>