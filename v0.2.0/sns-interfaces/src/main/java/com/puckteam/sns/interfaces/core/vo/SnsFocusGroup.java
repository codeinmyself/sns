package com.puckteam.sns.interfaces.core.vo;

/**
 * Created by ZengJieHang on 2016/10/13.
 */
public class SnsFocusGroup
{
    private String groupId;
    private String groupName;
    private String createUserId;
    private String createTime;
    private String focusNumber;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFocusNumber() {
        return focusNumber;
    }

    public void setFocusNumber(String focusNumber) {
        this.focusNumber = focusNumber;
    }
}
