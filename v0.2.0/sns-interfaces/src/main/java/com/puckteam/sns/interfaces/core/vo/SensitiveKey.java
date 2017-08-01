package com.puckteam.sns.interfaces.core.vo;

/**
 * Created by ZengJieHang on 2016/12/9.
 */
public class SensitiveKey
{
    private String wordId;
    private String wordName;
    private String createTime;

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getWordName() {
        return wordName;
    }

    public void setWordName(String wordName) {
        this.wordName = wordName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
