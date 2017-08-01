<#macro sidebar    sidebar_circle_info sidebar_member_info sidebar_user_info ="" sidebar_newtopic="" sidebar_about="" >
    <#if Session.user?? && sidebar_about=="show" >
    <div id="userMessage">
        <div>
            <img src="${uploadUrl!}${Session.user.avatar!}">
            <p>${Session.user.nickname!}</p>
            <table id="userMessageContent">
                <tr>
                    <td><a href="${baseUrl!}/focus/focus">${Session.user.focusNumber!}</a></td>
                    <td><a href="${baseUrl!}/focus/haveyou">${Session.user.fansNumber!}</a></td>
                    <td><a href="${baseUrl!}/user/findNews">${Session.user.newsNumber!}</a></td>
                </tr>
                <tr>
                    <td><a href="${baseUrl!}/focus/focus">关注</a></td>
                    <td><a href="${baseUrl!}/focus/haveyou">粉丝</a></td>
                    <td><a href="${baseUrl!}/user/findNews">动态</a></td>
                </tr>
            </table>
        </div>
    </div>
    <div id="loginSide-nav">
        <nav>
            <ul>
                <li>
                    <img src="${staticUrl!}/img/icon/home.png">
                    <a href="${baseUrl!}/">首页</a>
                    <div class="triangle"></div>
                </li>
                <li>
                    <img src="${staticUrl!}/img/icon/hot.png">
                    <a href="${baseUrl!}/user/findNews">我的动态</a>
                    <div class="triangle"></div>
                </li>
<#--                <li>
                    <img src="${staticUrl!}/img/icon/paint.png">
                    <a href="#">我的赞</a>
                    <div class="triangle"></div>
                </li>-->
                <li>
                    <img src="${staticUrl!}/img/icon/carve.png">
                    <a href="${baseUrl!}/collection/index">我的作品集</a>
                    <div class="triangle"></div>
                </li>
                <div class="side-border"></div>
                <li id="flip">
                    <img src="${staticUrl!}/img/icon/write.png">
                    <a href="#">艺术圈</a>
                    <div class="triangle"></div>
                </li>
                <li class="panel">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${baseUrl!}/circle/circleJoin">我加入的</a>

                </li>
                <li class="panel">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${baseUrl!}/circle/circleQuery">我发起的</a>
                </li>
                <li class="panel">
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${baseUrl!}/circle/circleExplore">探索艺术圈</a>
                </li>
                <li>
                    <img src="${staticUrl!}/img/icon/stuff.png">
                    <a href="${baseUrl!}/notice/show">与我相关</a>
                    <#if Session.user.noticeNumber!="0">
                    <span class="noticeNumber">${Session.user.noticeNumber!}</span>
                    </#if>
                    <div class="triangle"></div>
                </li>
            </ul>
        </nav>
    </div>
    <#elseif Session.user?? && sidebar_about=="circleshow" >
    <div id="userMessage">
        <div>
            <#if sidebar_circle_info.avatar?exists>
                <img src="${uploadUrl!}${sidebar_circle_info.avatar}">
            <#else>
                <img src="${uploadUrl!}/user/default_avatar.png">
            </#if>
            <p>艺术圈名：${sidebar_circle_info.circleName}</p>
            <table id="circleContent">
                <tr>
                    <td><a href="${baseUrl!}/circle/circleActivity?circleId=${circle.circleId}&circleName=${circle.circleName}">${sidebar_circle_info.newsNumber}</a></td>
                    <#if member ?exists >
                        <td><a href="${baseUrl!}/circle/memberManage?circleId=${circle.circleId}">${sidebar_circle_info.memberNumber}</a></td>
                    <#else>
                        <td  style="color:black">${sidebar_circle_info.memberNumber}</td>
                    </#if>

                </tr>
                <tr>
                    <td><a href="${baseUrl!}/circle/circleActivity?circleId=${circle.circleId}&circleName=${circle.circleName}">动态数量</a></td>
        <#if member ?exists >
                    <td><a href="${baseUrl!}/circle/memberManage?circleId=${circle.circleId}">成员数量</a></td>
        <#else>
                    <td  style="color:black">成员数量</td>
        </#if>
                </tr>
            </table>
        </div>
    </div>
    <div id="loginSide-nav">
        <nav>
            <ul>
                <li>
                    <img src="${staticUrl!}/img/icon/paint.png">
                    <a href="${baseUrl!}/circle/circleActivity?circleId=${circle.circleId}&circleName=${circle.circleName}">艺术圈首页</a>
                    <div class="triangle"></div>
                </li>
                <#if member ?exists >
                    <#if member.memberType=="01"||member.memberType="02">
                        <li>
                            <img src="${staticUrl!}/img/icon/home.png">
                            <a href="${baseUrl!}/circle/circleMessage?circleId=${circle.circleId}">基本信息</a>
                            <div class="triangle"></div>
                        </li>
                        <li>
                            <img src="${staticUrl!}/img/icon/hot.png">
                            <a href="${baseUrl!}/circle/memberManage?circleId=${circle.circleId}">成员信息</a>
                            <div class="triangle"></div>
                        </li>
                    <#else>
                        <li>
                            <img src="${staticUrl!}/img/icon/hot.png">
                            <a href="${baseUrl!}/circle/memberManage?circleId=${circle.circleId}">成员信息</a>
                            <div class="triangle"></div>
                        </li>
                    </#if>
                    <li>
                        <img src="${staticUrl!}/img/icon/stuff.png">
                        <a href="#">与我相关</a>
                        <div class="triangle"></div>
                    </li>
                <#else>
                <#--</#if>-->
                </#if>
            </ul>
        </nav>
    </div>
    <#elseif sidebar_about=="show" >
    <div id="side-nav">
        <nav>
            <ul>
                <li>
                    <img src="${staticUrl!}/img/icon/home.png">
                    <a href="#">首页</a>
                </li>
                <li>
                    <img src="${staticUrl!}/img/icon/hot.png">
                    <a href="#">推荐</a>
                </li>
                <li>
                    <img src="${staticUrl!}/img/icon/paint.png">
                    <a href="#">图画</a>
                </li>
                <li>
                    <img src="${staticUrl!}/img/icon/carve.png">
                    <a href="#">雕塑</a>
                </li>
                <div class="side-border"></div>
                <li>
                    <img src="${staticUrl!}/img/icon/write.png">
                    <a href="#">书法</a>
                </li>
                <li>
                    <img src="${staticUrl!}/img/icon/stuff.png">
                    <a href="#">器具</a>
                </li>
            </ul>
        </nav>
    </div>
    </#if>

</#macro>