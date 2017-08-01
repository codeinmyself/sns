<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 作品集信息" description="个人信息" page_tab="collection_information" sidebar_about="show">
<head>
</head>
<body>
<h4>Puckart > 作品集编辑 </h4>
<table width="98%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolordark="#F6B83B" bordercolorlight="#FFFFFF">
 <tr align="center" bgcolor="#e3F4F7">
     <td width="8%" bgcolor="#F9D16B">作品集名称</td>
     <td width="5%" bgcolor="#F9D16B">展示范围</td>
     <td width="10%" bgcolor="#F9D16B">简介</td>
     <td width="10%" bgcolor="#F9D16B">创建日期</td>
</tr>
<#list collectionList as collection>
    <div class="container">
        <table  class="col-xs-3">
        <tr>
            <#if collection.status=='1'>
                <td style="padding:80px;">${collection.collectionName}</td>
                <#if collection.showScope=='01'>
                    <td style="padding:45px;">公开</td>
                </#if>
                <#if collection.showScope=='02'>
                    <td style="padding:45px;">仅限用户关注者</td>
                </#if>
                <#if collection.showScope=='03'>
                    <td style="padding:45px;">仅限自己</td>
                </#if>
                <td style="padding:100px;">${collection.description}</td>
                <td style="padding:75px;">${collection.createTime}</td>
                <td align="center"><a href="${baseUrl}/collectionRevise ?tem=${collection.collectionId}">修改</a></td>
                <td style="padding:45px;">  </td>
                <td align="center"><a href="${baseUrl}/collectionDelete ?and=${collection.collectionId}">删除</a></td>
            </tr>
                <HR style="FILTER: alpha(opacity=100,finishopacity=0,style=1)" width="80%" color=#987cb9 SIZE=3>
            </#if>
        </table>
    </div>
</#list>
</table>
</@html>