<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>举报详情</title>
    <link href="${staticUrl!}/css/admin/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${staticUrl!}/js/jquery-3.1.1.min.js"></script>
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
        <li><a href="${baseUrl!}/admin/report">举报信息</a></li>
        <li><a href="${baseUrl!}/admin/keys">敏感词处理</a></li>

    </ul>
</div>
<div id="right">
    <div id="content">举报详情
        <#if report?exists>
            <table id="detail-table" width=100% cellpadding="8px;">
                <input type="hidden" id="hiddenReportStatus" value="${report.readStatus}">
                <input type="hidden" id="hiddenReportId" value="${report.reportId}">
                <input type="hidden" id="hiddenTargetType" value="${report.targetType}">
                <input type="hidden" id="hiddenTargetId" value="${report.targetId}">
                <tr>
                    <td width="10%">举报类别</td>
                    <td width="90%">
                        <#if report.reportType=="01">违法信息
                        <#elseif report.reportType=="02">露骨色情内容
                        <#elseif report.reportType=="03">垃圾内容
                        <#elseif report.reportType=="04">人身攻击
                        <#else >其他
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td>举报描述</td>
                    <td>${report.reportDesc}</td>
                </tr>
                <tr>
                    <td>举报人</td>
                    <td>${report.createUserName}</td>
                </tr>
                <tr>
                    <td>举报日期</td>
                    <td>${report.createTime}</td>
                </tr>
                <tr>
                    <td>举报内容</td>
                    <td>
                        <#if report.targetType=="01">
                            <button id="lockbtn" onclick="lock()">
                                封号
                            </button>
                        <#else >
                            <button id="deletebtn" onclick="deleteContent()">
                                删除该内容
                            </button>
                        </#if>
                        <button id="detailbtn" onclick="ignore()">忽略该举报</button>
                     </td>
                </tr>
                <#if report.targetType=="01">
                    <#if reportUser?exists>
                        <tr>
                            <td colspan="2">
                                <div id="reportuser">
                                    <img src="${uploadUrl!}${reportUser.avatar}">
                                    <p>${reportUser.nickname}</p>
                                    <p>手机号：
                                        <#if reportUser.mobile?exists>${reportUser.mobile}
                                        <#else >无
                                        </#if>
                                    </p>
                                    <p>邮箱：
                                        <#if reportUser.email?exists>${reportUser.email}
                                        <#else >无
                                        </#if>
                                    </p>
                                 </div>
                            </td>
                        </tr>
                    </#if>
                <#elseif report.targetType=="02">
                    <#if news?exists>
                        <tr>
                            <td colspan="2">
                                <div id="reportnews">
                                    <p>用户名称：${news.nickname}</p>
                                    <p>动态内容：${news.content}</p>
                                    <p>
                                        <#if news.imageList?exists>
                                            <#list news.imageList as image>
                                                <img src="${uploadUrl!}/news/${image.picId}.jpg" width="60px" height="60px"/>
                                            </#list>
                                        </#if>
                                    </p>
                                </div>
                            </td>
                        </tr>
                    </#if>
                    <#elseif report.targetType=="03">
                        <#if circle?exists>
                            <tr>
                                <td colspan="2">
                                    <div id="reportcircle">
                                        <p>艺术圈名称：${circle.circleName}</p>
                                        <p>简介：${circle.description}</p>
                                        艺术圈头像：
                                        <p>
                                            <img src="${uploadUrl!}${circle.avatar}" width="60px" height="60px">
                                        </p>
                                    </div>
                                </td>
                            </tr>
                        </#if>
                    <#elseif report.targetType=="04">
                        <#if collection?exists>
                            <tr>
                                <td colspan="2">
                                    <div>
                                        <p>作品集名称：${collection.collectionName}</p>
                                        <p>简介：${collection.description}</p>
                                        作品集头像：
                                        <p>
                                            <img src="${uploadUrl!}${collection.cover}" width="60px" height="60px">
                                        </p>
                                    </div>
                                </td>
                            </tr>
                        </#if>
                    <#elseif report.targetType=="05">
                        <#if comment?exists>
                            <tr>
                                <td colspan="2">
                                    <div>
                                        <p>评论用户：${comment.createNickname}</p>
                                        <p>评论内容：${comment.content}</p>
                                    </div>
                                </td>
                            </tr>
                        </#if>
                </#if>
                <#if report.reportReply?exists>
                    <td>回复</td>
                    <td><p><textarea id="replyTextarea"disabled="disabled" >${report.reportReply}</textarea></p><p><button id="replybtn" onclick="reply()" disabled="disabled">已回复</button></p></td>
                <#else >
                <tr>
                    <td>回复</td>
                    <td><p><textarea id="replyTextarea" ></textarea></p><p><button id="replybtn" onclick="reply()">回复</button></p></td>
                </tr>
                </#if>
                <tr><td colspan="2"></td></tr>
            </table>
        </#if>
    </div>
</div>
</body>
<script>
    $(function ()
    {
        var status=$("#hiddenReportStatus").val();
        if(status=="1")
        {
            $("#lockbtn").attr("disabled","true");
            $("#deletebtn").attr("disabled","true");
            $("#detailbtn").attr("disabled","true");
        }
    });

    function lock()
    {
        if(window.confirm("确定封号该用户?"))
        {
            $.ajax({
                url: "${baseUrl!}/admin/detailLock",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    userId: $("#hiddenTargetId").val(),
                    reportId:$("#hiddenReportId").val()
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("封号成功!");
                        $("#lockbtn").attr("disabled","true");
                        $("#detailbtn").attr("disabled","true");
                    }
                    else {
                        window.alert(data.message);
                    }
                },
            });
        }
    }

    function ignore()
    {
        if(window.confirm("确定忽略该举报?"))
        {
            $.ajax({
                url: "${baseUrl!}/admin/detailIgnore",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    reportId:$("#hiddenReportId").val()
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("忽略成功!");
                        $("#lockbtn").attr("disabled","true");
                        $("#deletebtn").attr("disabled","true");
                        $("#detailbtn").attr("disabled","true");
                    }
                    else {
                        window.alert(data.message);
                    }
                },
            });
        }
    }

    function deleteContent()
    {
        if(window.confirm("确定删除该内容?"))
        {
            $.ajax({
                url: "${baseUrl!}/admin/detailDelete",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    targetId:$("#hiddenTargetId").val(),
                    targetType:$("#hiddenTargetType").val(),
                    reportId:$("#hiddenReportId").val()
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("删除成功!");
                        $("#deletebtn").attr("disabled","true");
                        $("#detailbtn").attr("disabled","true");
                    }
                    else {
                        window.alert(data.message);
                    }
                },
            });
        }
    }

    function reply()
    {
        var text=$("#replyTextarea").val();
        if(text=="")
        {
            alert("回复内容为空!");
        }
        else
        {
            $.ajax({
                url: "${baseUrl!}/admin/detailReply",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    reportId:$("#hiddenReportId").val(),
                    replyText:text
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("回复成功!");
                        $("#replybtn").attr("disabled","true");
                    }
                    else {
                        window.alert(data.message);
                    }
                },
            });
        }
    }
</script>
</html>
