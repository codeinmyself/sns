package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.Like;

/**
 * Created by 82402 on 2016/11/14.
 */
public interface ILikeService {

    /**
     * 点赞
     * @param like
     * @return
     */
    public int addLike(Like like);

    /**
     * 取消点赞
     * @param newsId,createUserId
     * @return
     */
    public int delLike(String newsId, String createUserId);

    /**
     * 判断是否点过赞
     * @param newsId,createUserId
     * @return
     */
    public boolean ifLike(String newsId, String createUserId);

}
