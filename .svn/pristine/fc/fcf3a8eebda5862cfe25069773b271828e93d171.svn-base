<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>管理员主页</title>
    <link href="${staticUrl!}/css/admin/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${staticUrl!}/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="${staticUrl!}/js/check.js"></script>
</head>

<body>
<div id="header">
    <table width="100%">
        <tr><td width="88%">PUCKART后台管理</td>
            <td width="12%">您好，Adimin &nbsp;&nbsp;&nbsp;<a href="${baseUrl!}/admin/">退出</a></td>
        </tr></table>
</div>
<div id="left">
    <ul id="nav">
        <li><a href="${baseUrl!}/admin/users">用户管理</a>  </li>
        <li><a style="background:#0099CC">举报信息</a></li>
        <li><a href="${baseUrl!}/admin/keys">敏感词处理</a></li>
    </ul>
</div>
<div id="right">
    <div id="content">
        <div id="content-head-operate">
            <select style="height:30px;" onchange="changeReport(this.value)">
                <option value="all">所有举报信息</option>
                <option value="not">未处理</option>
                <option value="has">已处理</option>
            </select>
        </div>

        <table id="content-table-all" width="100%" cellpadding="8px" style="table-layout:fixed">
            <tr>
                <th width="15%">举报类型</th>
                <th width="35%">举报描述</th>
                <th width="10%">举报种类</th>
                <th width="15%">举报人</th>
                <th width="15%">日期</th>
                <th width="10%">状态</th>
            </tr>
            <#if reportList?exists>
                <#list reportList as report>
                    <tr onclick="reportDetail('${report.targetType}','${report.targetId}','${report.reportId}')">
                        <td width="15%">
                            <#if report.reportType=="01">违法信息
                            <#elseif report.reportType=="02">露骨色情内容
                            <#elseif report.reportType=="03">垃圾内容
                            <#elseif report.reportType=="04">人身攻击
                            <#else >其他
                            </#if>
                        </td>
                        <td width="35%" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis;width:200px;">${report.reportDesc}</td>
                        <td width="10%">
                            <#if report.targetType=="01">用户
                            <#elseif report.targetType=="02">动态
                            <#elseif report.targetType=="03">艺术圈
                            <#elseif report.targetType=="04">作品集
                            <#elseif report.targetType=="05">评论
                            <#else >其他
                            </#if>
                        </td>
                        <td width="15%">${report.createUserName}</td>
                        <td width="15%">${report.createTime}</td>
                        <td width="10%">
                            <#if report.readStatus=="0">未处理
                            <#else >已处理
                            </#if>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
        <table id="content-table-has"  width="100%" cellpadding="8px" style="display: none;table-layout:fixed">
            <tr>
                <th width="15%">举报类型</th>
                <th width="35%">举报描述</th>
                <th width="10%">举报种类</th>
                <th width="15%">举报人</th>
                <th width="15%">日期</th>
                <th width="10%">状态</th>
            </tr>
        <#if reportList?exists>
            <#list reportList as report>
                <#if report.readStatus=="1">
                    <tr>
                        <td width="15%">
                            <#if report.reportType=="01">违法信息
                            <#elseif report.reportType=="02">露骨色情内容
                            <#elseif report.reportType=="03">垃圾内容
                            <#elseif report.reportType=="04">人身攻击
                            <#else >其他
                            </#if>
                        </td>
                        <td width="35%">${report.reportDesc}</td>
                        <td width="10%">
                            <#if report.targetType=="01">用户
                            <#elseif report.targetType=="02">动态
                            <#elseif report.targetType=="03">艺术圈
                            <#elseif report.targetType=="04">作品集
                            <#elseif report.targetType=="05">评论
                            <#else >其他
                            </#if>
                        </td>
                        <td width="15%">${report.createUserName}</td>
                        <td width="15%">${report.createTime}</td>
                        <td width="10%">
                           已处理
                        </td>
                    </tr>
                </#if>
            </#list>
        </#if>
        </table>
        <table id="content-table-not" width="100%" cellpadding="8px" style="display: none;table-layout:fixed">
            <tr>
                <th width="15%">举报类型</th>
                <th width="35%">举报描述</th>
                <th width="10%">举报种类</th>
                <th width="15%">举报人</th>
                <th width="15%">日期</th>
                <th width="10%">状态</th>
            </tr>
        <#if reportList?exists>
            <#list reportList as report>
                <#if report.readStatus=="0">
                    <tr>
                        <td width="15%">
                            <#if report.reportType=="01">违法信息
                            <#elseif report.reportType=="02">露骨色情内容
                            <#elseif report.reportType=="03">垃圾内容
                            <#elseif report.reportType=="04">人身攻击
                            <#else >其他
                            </#if>
                        </td>
                        <td width="35%">${report.reportDesc}</td>
                        <td width="10%">
                            <#if report.targetType=="01">用户
                            <#elseif report.targetType=="02">动态
                            <#elseif report.targetType=="03">艺术圈
                            <#elseif report.targetType=="04">作品集
                            <#elseif report.targetType=="05">评论
                            <#else >其他
                            </#if>
                        </td>
                        <td width="15%">${report.createUserName}</td>
                        <td width="15%">${report.createTime}</td>
                        <td width="10%">
                            未处理
                        </td>
                    </tr>
                </#if>
            </#list>
        </#if>
        </table>
    </div>
</div>
</body>
<script>
    function changeReport(type)
    {
        if(type=="all")
        {
            $("#content-table-all").css("display", "table");
            $("#content-table-has").css("display", "none");
            $("#content-table-not").css("display", "none");
        }
        else if(type=="has")
        {
            $("#content-table-all").css("display", "none");
            $("#content-table-has").css("display", "table");
            $("#content-table-not").css("display", "none");
        }
        else
        {
            $("#content-table-all").css("display", "none");
            $("#content-table-has").css("display", "none");
            $("#content-table-not").css("display", "table");
        }
    }
    function reportDetail(targetType,targetId,reportId)
    {
        location.href = "${baseUrl!}/admin/reportDetail?targetType="+targetType+"&targetId="+targetId+"&reportId="+reportId;
    }
</script>
</html>
