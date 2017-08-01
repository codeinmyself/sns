<#macro header page_tab="">
<nav class="navbar navbar-default"  style="color:rgb(51, 47, 56)">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="color:#fff;font-family: &#39;comic sans ms&#39;;" href="${baseUrl!}/">puckart</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <span class="hidden-xs hidden-sm">
                <form class="navbar-form navbar-left" id="search_form" role="search" method="get" action="${baseUrl!}/">
                    <div class="form-group has-feedback">
                        <input type="text" name="q" class="form-control" style="width: 240px;" value="${q!}"
                               placeholder="回车搜索" onkeypress="enterSearch(event)">
                        <a href="javascript:search();">
                            <span class="glyphicon glyphicon-search " aria-hidden="true" style="line-height: 28px;"></span>
                        </a>
                    </div>
                </form>
                <script type="text/javascript">
                    function enterSearch(e) {
                        var e = e || window.event;
                        if(e.keyCode == 13) {
                            search();
                        }
                    }
                    function search() {

                        if($.trim($("input[name='q']").val()) != "") {
                            $("#search_form").submit();
                        }

                    }
                </script>
            </span>
            <ul class="nav navbar-nav navbar-right">
                <#if Session.user??>
                    <li <#if page_tab == 'notice'> class="active" </#if> >
                        <a href="${baseUrl!}/user/notice">通知 <span class="badge" id="badge"></span></a></li>
                    <li class="dropdown">
                        <a href="${baseUrl!}/user/${Session.user.userId!}" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown">
                            <img src="${staticUrl!}${Session.user.avatar!}" width="20" style="border-radius: 20px;"/>
                        ${Session.user.nickname!}
                            <span class="caret"></span>
                        </a>
                        <span class="dropdown-arrow"></span>
                        <ul class="dropdown-menu">
                            <li><a href="${baseUrl!}/user/${Session.user.userId!}"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;我的</a></li>
                            <li><a href="${baseUrl!}/message"><span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;私信</a></li>
                            <li><a href="${baseUrl!}/user/setting"><span class="glyphicon glyphicon-cog"></span>&nbsp;&nbsp;设置</a></li>
                            <li><a href="${baseUrl!}/logout"><span class="glyphicon glyphicon-off"></span>&nbsp;&nbsp;退出</a></li>
                        </ul>
                    </li>
                <#else>
                    <li <#if page_tab == 'login'> class="active" </#if>><a href="${baseUrl!}/login.html">登录</a></li>
                </#if>
            </ul>
        </div>
    </div>
</nav>
</#macro>