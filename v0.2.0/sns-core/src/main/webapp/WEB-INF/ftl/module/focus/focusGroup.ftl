<#include "../../common/_layout.ftl"/>
<@html title="Puckart > 我的关注" description="关注" page_tab="focus" sidebar_about="show">
<div id="right">
    <div id="follow-nav">
        <ul>
            <li ><a href="${baseUrl!}/focus/focus">所有用户</a></li>
            <li style="border-bottom: #93247a solid thick;"><a href="${baseUrl!}/focusGroup/group">我的分组</a></li>
            <li id="newgroup">
                <a class="edit">
                 <img  onclick="addNewGroup()" src="${staticUrl!}/img/icon/newgroup.png" width="20px" height="20px">
                </a>
            </li>
        </ul>

    </div>
    <div id="follow-groups">
        <ul id="nav">
            <#if snsFocusGroupList?exists>
                <#list snsFocusGroupList as snsFocusGroup>
                    <li>
                        <div id="group-name" onclick="DoMenu('ChildMenu${snsFocusGroup_index}')">${snsFocusGroup.groupName}(${snsFocusGroup.focusNumber})</div>
                        <img class="edit" onclick="searchGroupName('${snsFocusGroup.groupName}','${snsFocusGroup.groupId}')" src="${staticUrl!}/img/icon/edit.png">
                        <img onclick="deleteGroup('${snsFocusGroup.groupId}')" src="${staticUrl!}/img/icon/delete.png">
                        <ul id="ChildMenu${snsFocusGroup_index}" class="collapsed">
                            <li>
                                <#if snsFocusList?exists>
                                    <#list snsFocusList as snsFocus>
                                        <#if snsFocus.groupId?exists>
                                           <#if snsFocus.groupId==snsFocusGroup.groupId>
                                                <div id="usercard-long">
                                                    <a href="${baseUrl!}/other/otherNewsShow?createUserId=${snsFocus.focusUserId}">
                                                        <img src="${uploadUrl!}${snsFocus.userAvatar}">
                                                    </a>
                                                    <div id="usercard-long-content">
                                                        <a href="${baseUrl!}/other/otherNewsShow?createUserId=${snsFocus.focusUserId}">
                                                        <p id="follow-name">${snsFocus.userNickname}</p>
                                                        </a>
                                                        <#if snsFocus.userSignature?exists>
                                                            <p id="follow-intro">简介：${snsFocus.userSignature}</p>
                                                        <#else >
                                                            <p id="follow-intro">简介：空</p>
                                                        </#if>
                                                    </div>
                                                    <select onchange="removeFocusToOtherGroup(this.value,'${snsFocus.focusId}')">
                                                        <option >移至</option>
                                                        <#list snsFocusGroupList as snsFocusGroupOption>
                                                            <#if snsFocusGroupOption.groupName!=snsFocusGroup.groupName>
                                                                <option value="${snsFocusGroupOption.groupId}">
                                                                    ${snsFocusGroupOption.groupName}
                                                                </option>
                                                            </#if>
                                                        </#list>
                                                    </select>
                                                    <button onclick="cancelFocus('${snsFocus.focusId}')">取消关注</button>
                                                </div>
                                            </#if>
                                        </#if>
                                    </#list>
                                </#if>
                            </li>
                        </ul>
                     </li>
                </#list>
            </#if>
            <li>
                <div onclick="DoMenu('ChildMenu')">未分组(${untitledFocusNumber})</div>
                <ul id="ChildMenu" class="collapsed">
                    <li>
                        <#if snsFocusList?exists>
                            <#list snsFocusList as snsFocus>
                                <#if !snsFocus.groupId?exists>
                                    <div id="usercard-long">

                                        <a href="${baseUrl!}/other/otherNewsShow?createUserId=${snsFocus.focusUserId}">
                                            <img src="${uploadUrl!}${snsFocus.userAvatar}">
                                        </a>
                                        <div id="usercard-long-content">
                                            <a href="${baseUrl!}/other/otherNewsShow?createUserId=${snsFocus.focusUserId}">
                                            <p id="follow-name">${snsFocus.userNickname}</p>
                                            </a>
                                            <#if snsFocus.userSignature?exists>
                                                <p id="follow-intro">简介：${snsFocus.userSignature}</p>
                                            <#else >
                                                <p id="follow-intro">简介：空</p>
                                            </#if>
                                        </div>
                                        <select onchange="removeFocusToOtherGroup(this.value,'${snsFocus.focusId}')">
                                            <option >移至</option>
                                            <#if snsFocusGroupList?exists>
                                                <#list snsFocusGroupList as snsFocusGroupOption>
                                                    <option value="${snsFocusGroupOption.groupId}">
                                                    ${snsFocusGroupOption.groupName}
                                                    </option>
                                                </#list>
                                            </#if>
                                        </select>
                                        <button onclick="cancelFocus('${snsFocus.focusId}')">取消关注</button>
                                    </div>
                                </#if>
                            </#list>
                        </#if>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="modal-add">
        <div class="modal-add-body" style="width: 450px">

            <div class="modal-add-header">
                <div class="modal-add-title">
                    <span>分组信息</span>
                </div>
            </div>
            <div class="modal-add-content">
                <table id="edit"><form>
                    <tr>
                        <td width="" id="label">分组名称</td>
                        <td width=""><input id="add_groupName" type="text" "/>
                            <div id="returnErrorAdd">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" colspan="2">
                            <br/>
                            <input id="submit_btn" type="submit" value="创建" onClick="createNewGroup()">
                            <input class="add-close" id="cancel_btn" type="submit" value="取消">
                        </td>
                    </tr>
                </form>
                </table>
            </div>
        </div>
    </div>
    <div class="modal fade">
        <div class="modal-body" style="width: 450px">

            <div class="modal-header">
                <div class="modal-title">
                    <span>分组信息</span>
                </div>
            </div>
            <div class="modal-content">
                <table id="edit"><form>
                    <input type="hidden" id="updateGroupId">
                    <tr>
                        <td width="" id="label">分组名称</td>
                        <td width=""><input id="groupName" type="text" />
                            <div id="returnError" >
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" colspan="2">
                            <br/>
                            <input id="update_btn" type="submit" value="保存" onClick="updateGroupName()">
                            <input class="close" id="cancel_btn" type="submit" value="取消">
                        </td>
                    </tr></form>
                </table>
            </div>
        </div>
    </div>
    <div class="modal-backup"></div>
</div>

<script>
    function removeFocusToOtherGroup(groupId,focusId)
    {
        $.ajax({
            url: "${baseUrl!}/focus/group",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                focusId:focusId,
                groupId:groupId
            },
            success: function (data)
            {
                if (data.code == '200')
                {
                    window.location.href = "${baseUrl!}/focusGroup/group";
                }
                else
                {
                    alert(data.message);
                }
            },
        });
    }
    function  createNewGroup()
    {
        $.ajax({
            url: "${baseUrl!}/focusGroup/add",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                name: $("#add_groupName").val()
            },
            success: function (data)
            {
                if (data.code == '200')
                {
                    window.location.href = "${baseUrl!}/focusGroup/group";
                }
                else
                {
                    $("#returnErrorAdd").css("color", "red").html(data.message);
                }
            },
        });
    }
    function updateGroupName()
    {
        $.ajax({
            url: "${baseUrl!}/focusGroup/save",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                groupName:$("#groupName").val(),
                groupId:$("#updateGroupId").val()
            },
            success: function (data)
            {
                if (data.code == '200')
                {
                    window.location.href = "${baseUrl!}/focusGroup/group";
                }
                else
                {
                    $("#returnError").css("color", "red").html(data.message);
                }
            },
        });
    }
    function deleteGroup(groupId)
    {
        if(window.confirm("确定删除此分组（删除后分组内关注成员将转移到未分组）?"))
        {
            $.ajax({
                url: "${baseUrl!}/focusGroup/delete",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    groupId: groupId
                },
                success: function (data) {
                    if (data.code == '200') {
                        window.location.href = "${baseUrl!}/focusGroup/group";
                    }
                    else {
                        window.alert(data.message);
                    }
                },
            });
        }
    }

    function cancelFocus(focusId)
    {
        if(window.confirm("确定取消关注吗?"))
        {
            $.ajax({
                url: "${baseUrl!}/focus/cancel",
                async: false,
                cache: false,
                type: 'post',
                dataType: "json",
                data: {
                    focusId: focusId
                },
                success: function (data) {
                    if (data.code == '200') {
                        window.location.href = "${baseUrl!}/focusGroup/group";
                    }
                    else {
                        window.alert(data.message);
                    }
                },
            });
        }
    }
    function DoMenu(emid)
    {
        var obj = document.getElementById(emid);
        obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded");
    }
</script>

</@html>