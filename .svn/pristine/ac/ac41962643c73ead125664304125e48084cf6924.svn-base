<#macro html title description page_tab="" sidebar_user_info="" sidebar_newtopic="" sidebar_about="">
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <title>${title!"puckart社区"}</title>
    <meta name="description" content="${description!}">
    <meta name="keywords" content="puckart,bbs,puckartbbs,艺术品交易,论坛"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="icon" href="${staticUrl!}/favicon.ico">
    <link href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap-material-design/0.5.8/css/bootstrap-material-design.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/bootstrap-material-design/0.5.8/css/ripples.min.css" rel="stylesheet">
    <link href="${staticUrl!}/css/style.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-material-design/0.5.8/js/material.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-material-design/0.5.8/js/ripples.min.js"></script>
    <script type="text/javascript" src="${staticUrl!}/js/bootstrap-paginator.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap-hover-dropdown/2.2.1/bootstrap-hover-dropdown.min.js"></script>
    <script src="http://cdn.bootcss.com/blueimp-md5/2.1.0/js/md5.min.js"></script>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<#--页面头部信息-->
    <#include "header.ftl"/>
    <@header page_tab=page_tab/>
<div class="container">
    <div class="row">
        <div class="content-wrap">
            <div class="col-md-9">
                <#nested>
            </div>
            <div class="col-md-3 hidden-sm hidden-xs">
                <div id="jf_sidebar">
                    <#include "sidebar.ftl"/>
                    <@sidebar sidebar_user_info=sidebar_user_info
                sidebar_newtopic=sidebar_newtopic
                sidebar_about=sidebar_about />
                </div>
            </div>
        </div>
    </div>
</div>
<#--footer-->
    <#include "footer.ftl"/>
    <@footer/>
</body>
</html>
</#macro>