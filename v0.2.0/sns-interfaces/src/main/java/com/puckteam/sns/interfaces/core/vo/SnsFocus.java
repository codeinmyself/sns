package com.puckteam.sns.interfaces.core.vo;

/**
 * Created by ZengJieHang on 2016/10/11.
 */
public class SnsFocus
{
    private String focusId;
    private String userId;
    private String focusUserId;
    private String groupId;
    private String userName;
    private String userAvatar;
    private String userNickname;
    private String userSignature;
    private String createTime;
    private boolean isEachOther;

    public String getFocusId() {
        return focusId;
    }

    public void setFocusId(String focusId) {
        this.focusId = focusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFocusUserId() {
        return focusUserId;
    }

    public void setFocusUserId(String focusUserId) {
        this.focusUserId = focusUserId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserSignature() {
        return userSignature;
    }

    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isEachOther() {
        return isEachOther;
    }

    public void setEachOther(boolean eachOther) {
        isEachOther = eachOther;
    }
}
