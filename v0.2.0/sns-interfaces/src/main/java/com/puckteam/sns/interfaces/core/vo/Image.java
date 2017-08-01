package com.puckteam.sns.interfaces.core.vo;

/**
 * Created by 82402 on 2016/11/23.
 */
public class Image {
    private String picId;
    private String newsId;
    private int picIndex;
    private String createTime;

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public int getPicIndex() {
        return picIndex;
    }

    public void setPicIndex(int picIndex) {
        this.picIndex = picIndex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {

        this.createTime = createTime;
    }

}
