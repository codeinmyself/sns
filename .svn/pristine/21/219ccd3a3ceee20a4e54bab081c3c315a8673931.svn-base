<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>用户列表</title>
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
        <li><a style="background:#0099CC">用户管理</a>  </li>
        <li><a href="${baseUrl!}/admin/report">举报信息</a></li>
        <li><a href="${baseUrl!}/admin/keys">敏感词处理</a></li>
    </ul>
</div>
<div id="right">
    <div id="content">用户管理
        <div id="content-head-operate">
            <div id="content-head-operate-left">
                <select style="height:30px;" onchange="changeUser(this.value)"><option  value="all">所有用户</option>
                    <option value="artist">艺术家</option>
                    <option value="user">普通用户</option>
                </select>
                <button onclick="lockUsers()">封号</button></div>
        </div>

        <table id="content-table" width="100%" cellpadding="8px">
            <tr>
                <th width="3%"><input id="allUsers" name="allUsers" type="checkbox" onclick="selectAll()" /></th>
                <th width="20%">用户名</th>
                <th width="25%">用户昵称</th>
                <th width="30%">用户简介</th>
                <th width="10%">分类</th>
                <th width="10%">状态</th>
            </tr>
            <#if userList?exists>
                <#list userList as user>
                    <tr>
                        <td><input name="checkBoxs" type="checkbox" value="${user.userId}"/></td>

                        <td width="20%">${user.userName}</td>
                        <td width="25%">
                            <#if user.nickname?exists>${user.nickname}
                            </#if>
                        </td>
                        <td width="30%" >
                            <#if user.signature?exists>${user.signature}
                             </#if></td>
                        <td width="10%">
                            <#if user.isArtist=="1">艺术家
                            <#else >普通用户
                            </#if>
                        </td>
                        <td width="10%">
                            <#if user.isLocked=="0">正常
                            <#else >被封号
                            </#if>
                        </td>
                    </tr>
                </#list>
            </#if>
        </table>
        <table id="content-table-artist" width="100%" cellpadding="8px"style="display: none">
            <tr>
                <th width="3%"><input id="allUsers" name="allUsers"  type="checkbox" onclick="selectAll()" /></th>
                <th width="20%">用户名</th>
                <th width="25%">用户昵称</th>
                <th width="30%">用户简介</th>
                <th width="10%">分类</th>
                <th width="10%">状态</th>
            </tr>
        <#if userList?exists>
            <#list userList as user>
                <#if user.isArtist=="1">
                    <tr>
                        <td><input name="checkBoxs" type="checkbox" value="${user.userId}"/></td>
                        <td width="20%">${user.userName}</td>
                        <td width="25%">
                            <#if user.nickname?exists>${user.nickname}
                                </#if>
                        </td>
                        <td width="30%" >
                            <#if user.signature?exists>${user.signature}
                                 </#if></td>
                        <td width="10%">艺术家</td>
                        <td width="10%">
                            <#if user.isLocked=="0">正常
                            <#else >被封号
                            </#if>
                        </td>
                    </tr>
                </#if >
            </#list>
        </#if>
        </table>
        <table id="content-table-user" width="100%" cellpadding="8px" style="display: none">
            <tr>
                <th width="3%"><input id="allUsers" name="allUsers"  type="checkbox" onclick="selectAll()" /></th>
                <th width="20%">用户名</th>
                <th width="25%">用户昵称</th>
                <th width="30%">用户简介</th>
                <th width="10%">分类</th>
                <th width="10%">状态</th>
            </tr>
        <#if userList?exists>
            <#list userList as user>
                <#if user.isArtist=="0">
                    <tr>
                        <td><input name="checkBoxs" type="checkbox" value="${user.userId}"/></td>

                        <td width="20%">${user.userName}</td>
                        <td width="25%">
                            <#if user.nickname?exists>${user.nickname}
                                </#if>
                        </td>
                        <td width="30%" >
                            <#if user.signature?exists>${user.signature}</#if>
                        </td>
                        <td width="10%">普通用户</td>
                        <td width="10%">
                            <#if user.isLocked=="0">正常
                            <#else >被封号
                            </#if>
                        </td>
                    </tr>
                </#if >
            </#list>
        </#if>
        </table>

    </div>
</div>
</body>
<script>
    function changeUser(type)
    {
        var checkBoxs=document.getElementsByName("allUsers");
        allUncheck(checkBoxs);
        checkBoxs=document.getElementsByName("checkBoxs");
        allUncheck(checkBoxs);
        if(type=="all")
        {
            $("#content-table").css("display", "table");
            $("#content-table-artist").css("display", "none");
            $("#content-table-user").css("display", "none");
        }
        else if(type=="user")
        {
            $("#content-table").css("display", "none");
            $("#content-table-user").css("display", "table");
            $("#content-table-artist").css("display", "none");
        }
        else
        {
            $("#content-table").css("display", "none");
            $("#content-table-user").css("display", "none");
            $("#content-table-artist").css("display", "table");
        }
    }
    function lockUsers()
    {
        var arr = [];
        $("input[name='checkBoxs']:checked").each(function () {
            arr.push(this.value);
        });
        if(arr=="")
        {
            alert("未选择用户!");
        }
        else
        {
            if (window.confirm("确定将勾选的用户封号吗?") == true) {
                $.ajax({
                    url: "${baseUrl!}/admin/lock",
                    async: false,
                    cache: false,
                    traditional: true,
                    type: 'post',
                    dataType: "json",
                    data: {
                        userId: arr
                    },
                    success: function (data) {
                        if (data.code == '200') {
                            alert("封号成功!");
                            window.location.href = "${baseUrl!}/admin/users";
                        }
                        else {
                            window.alert(data.message);
                        }
                    },
                });
            }
        }
    }
</script>
</html>
