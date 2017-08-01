package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.Comment;
import com.puckteam.sns.interfaces.core.vo.News;
import com.puckteam.sns.interfaces.core.vo.NewsRelaCircleCollection;

import java.util.List;

/**
 * Created by 82402 on 2016/11/3.
 */
public interface INewsService {
    /**
     * 动态信息添加
     * @param  news
     * @return
     */
    public int addNews(News news);

    /**
     * 动态信息删除
     * @param  newsId
     * @return
     */
    public int delNews(String newsId);

    /**
     * 动态信息更新
     * @param  news
     * @return
     */
    public void updNews(News news);

    /**
     * 提取自身，关注，公开动态信息展示
     * @param  createUserId
     * @return
     */
    public List<News> queryForpublicNewsListByCreateUserId(String createUserId);

    /**
     * 提取公开动态信息展示
     * @param  showScope
     * @return
     */
    public List<News> queryForNewsListByShowScope(String showScope);

    /**
     * 推荐动态信息展示
     * @return
     */
    public List<News> queryForNewsListForRecommend();

    /**
     * 根据id查找动态
     * @param newsId
     * @return
     */
    public List<News> queryForNewsListByNewsId(String newsId);

    /**
     * 根据id查找动态
     * @param newsId
     * @return
     */
    public News  queryForNewsByNewsId(String newsId);

    /**
     * 根据用户id查找动态
     * @param userId
     * @return
     */
    public List<News>  queryForBeanListNewsListByUserId(String userId);

    /**
     * 提取他人动态信息展示
     * @param  createUserId,userId
     * @return
     */
    public List<News>  queryForBeanListOtherNewsListByCreateUserId(String createUserId, String userId);


    /**
     * 点赞次数增加
     * @param  newsId
     * @return
     */
    public int likeCountPlus(String newsId);

    /**
     * 点赞次数减少
     * @param  newsId
     * @return
     */
    public int likeCountMinus(String newsId);

    /**
     * 评论次数增加
     * @param  newsId
     * @return
     */
    public int commentCountPlus(String newsId);

    /**
     * 评论次数减少
     * @param  newsId
     * @return
     */
    public int commentCountMinus(String newsId);

    /**
     * 评论者展示
     *  @param
     *  @return
     */
    public List<Comment> queryForCommentByNewsId(String newsId, String replyCommentId);

    /**
     * 全部评论展示
     *  @param
     *  @return
     */
    public List<Comment> queryForAllCommentByNewsId(String newsId);

    /**
     * 动态加入作品集/艺术圈
     * @param news
     * @param circle
     * @param collection
     * @return
     */
    public boolean addNewsRelaCircleCollection(News news, String[] circle, String[] collection);


    /**
     * 从作品集/艺术圈中删除动态
     * @param newsRelaCircleCollection
     * @return
     */
    public int deleteNewsRelaCircleCollection(NewsRelaCircleCollection newsRelaCircleCollection);


    /**
     * 检查动态中是否存在敏感词汇，不存在返回true,存在返回false
     * @param news
     * @return
     */
    public boolean checkNewsSensitiveKeyInContent(News news);
}
