<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 我的分组" description="分组" page_tab="group" sidebar_about="show">

<div class="panel panel-default">

    这是分组信息:
    <br/>
    <form action="create" method="post">
        <input id="newGroupName" name="newGroupName" type="text" placeholder="输入分组名"/>
        <input type="button" onclick="createGroup()" value="新建分组"/>
    </form>
    <br/>
    <#if snsFocusGroupList?exists>
        <#list snsFocusGroupList as snsFocusGroup>
           <input id="groupName${snsFocusGroup_index}"type="text" value="${snsFocusGroup.groupName}"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" value="修改分组" onclick="modifyGroup('${snsFocusGroup.groupId}','groupName${snsFocusGroup_index}')"/>
            <input type="button" value="删除分组" onclick="deleteGroup('${snsFocusGroup.groupId}')"/>
            <br/>
        </#list>
    </#if>

</div>

<script>
    function createGroup()
    {
        $.ajax({
            url: "${baseUrl!}/focusGroup/add",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                name: $("#newGroupName").val()
            },
            success: function (data)
            {
                if (data.code == '200')
                {
                    window.location.href = "${baseUrl!}/focusGroup/group.html";
                }
                else
                {
                    window.alert(data.message);
                }
            },

        });
    }


    function modifyGroup(groupId,groupNameTextId)
    {
        var newGroupName=document.getElementById(groupNameTextId).value;
         $.ajax({

            url: "${baseUrl!}/focusGroup/save",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                groupId:groupId,
                groupName:newGroupName
            },
            success: function (data)
            {
                if (data.code == '200')
                {
                    window.location.href = "${baseUrl!}/focusGroup/group.html";
                }
                else
                {
                    window.alert(data.message);
                }
            },

        });
    }

    function deleteGroup(groupId)
    {
        $.ajax({

            url: "${baseUrl!}/focusGroup/delete",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                groupId:groupId
            },
            success: function (data)
            {
                if (data.code == '200')
                {
                    window.location.href = "${baseUrl!}/focusGroup/group.html";
                }
                else
                {
                    window.alert(data.message);
                }
            },

        });
    }
</script>
</@html>