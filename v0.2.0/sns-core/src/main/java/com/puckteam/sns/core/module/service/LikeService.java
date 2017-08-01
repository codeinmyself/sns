package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.module.dao.LikeDao;
import com.puckteam.sns.interfaces.core.service.ILikeService;
import com.puckteam.sns.interfaces.core.vo.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 82402 on 2016/11/14.
 */
@Service("likeService")
@Transactional

public class LikeService implements ILikeService {

    @Autowired
    private LikeDao likeDao;

    /**
     * 点赞
     * @param like
     * @return
     */
    public int addLike(Like like){
        return likeDao.addLike(like);
    }

    /**
     * 取消点赞
     * @param newsId,createUserId
     * @return
     */
    public int delLike(String newsId,String createUserId){
        return likeDao.delLike(newsId,createUserId);
    }

    /**
     * 判断是否点过赞
     * @param newsId,createUserId
     * @return
     */
    public boolean ifLike(String newsId,String createUserId){
        return likeDao.ifLike(newsId,createUserId);
    }
}
