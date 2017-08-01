<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>敏感词处理</title>
    <link href="${staticUrl!}/css/admin/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${staticUrl!}/js/check.js"></script>
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
        <li><a href="${baseUrl!}/admin/users">用户管理</a></li>
        <li><a href="${baseUrl!}/admin/report">举报信息</a></li>
        <li><a style="background:#0099CC">敏感词处理</a></li>
    </ul>
</div>
<div id="right">
    <div id="content">敏感词处理
        <div id="content-head-operate">
            <input class="hh" id="newKey" type="text" >
            <button class="addkeyword" onclick="addKeyWord()">添加</button>
        </div>
        <div id="keyword">
            <#if sensitiveKeyList?exists>
                <#list sensitiveKeyList as sensitiveKey>
                        <div id="word" class="keyWord">${sensitiveKey.wordName}
                            <a id="x" class="keyWordX" onclick="removekey('${sensitiveKey.wordId}')"> &times;</a>
                        </div>
                </#list>
            </#if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        $(".keyWord").on("mouseover",function(){
            $(".keyWordX").css("display","block");
        });
        $(".keyWord").on("mouseout",function(){
            $(".keyWordX").css("display","none");
        });
    });

    function addKeyWord()
    {
        var key=$("#newKey").val();
        if(key=="")
        {
            alert("敏感词为空!");
        }
        else
        {
            $.ajax({
                url: "${baseUrl!}/admin/addKey",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    key: key
                },
                success: function (data) {
                    if (data.code == '200') {
                        alert("添加成功");
                        location.href = "${baseUrl!}/admin/keys";
                    }
                    else {
                        alert(data.message);
                    }
                }
            });
        }
    }

    function removekey(key)
    {
        $.ajax({
            url: "${baseUrl!}/admin/removeKey",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                key: key
            },
            success: function (data) {
                if (data.code == '200') {
                    alert("删除成功");
                    location.href = "${baseUrl!}/admin/keys";
                }
                else {
                    alert(data.message);
                }
            }
        });
    }
</script>
</body>
</html>
