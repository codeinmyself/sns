package com.puckteam.sns.interfaces.core.vo;

/**
 * Created by PoemWhite on 16/9/12.
 */
public class User {

    private String userId;
    private String userName;
    private String password;
    private String email;
    private String mobile;
    private String nickname;
    private String avatar;
    private String isArtist;
    private String isLocked;
    private String createTime;
    private String focusNumber;
    private String fansNumber;
    private String newsNumber;
    private String noticeNumber;
    private String signature;

    public String getNoticeNumber() {
        return noticeNumber;
    }

    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIsArtist() {
        return isArtist;
    }

    public void setIsArtist(String isArtist) {
        this.isArtist = isArtist;
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

    public String getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(String fansNumber) {
        this.fansNumber = fansNumber;
    }

    public String getNewsNumber() {
        return newsNumber;
    }

    public void setNewsNumber(String newsNumber) {
        this.newsNumber = newsNumber;
    }
    public void setSignature(String signature){this.signature=signature;}
    public String getSignature(){return signature;}

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isArtist='" + isArtist + '\'' +
                ", isLocked='" + isLocked + '\'' +
                ", createTime='" + createTime + '\'' +
                ", focusNumber='" + focusNumber + '\'' +
                ", fansNumber='" + fansNumber + '\'' +
                ", newsNumber='" + newsNumber + '\'' +
                ", noticeNumber='" + noticeNumber + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
