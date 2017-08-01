package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.module.dao.ImageDao;
import com.puckteam.sns.core.module.dao.NewsDao;
import com.puckteam.sns.core.module.dao.NewsRelaCircleCollectionDao;
import com.puckteam.sns.interfaces.core.service.INewsService;
import com.puckteam.sns.interfaces.core.vo.Comment;
import com.puckteam.sns.interfaces.core.vo.News;
import com.puckteam.sns.interfaces.core.vo.NewsRelaCircleCollection;
import com.puckteam.sns.interfaces.core.vo.SensitiveKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 82402 on 2016/11/3.
 */

@Service("newsService")
@Transactional

public class NewsService implements INewsService {
    @Autowired
    private NewsDao newsDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private NewsRelaCircleCollectionDao newsRelaCircleCollectionDao;

    /**
     * 动态信息添加
     * @param news
     * @return
     */
    public int addNews(News news){
        return newsDao.addNews(news);
    }

    /**
     * 动态信息删除
     * @param newsId
     * @return
     */
    public int delNews(String newsId){
        return newsDao.delNews(newsId);
    }

    /**
     * 动态信息更新
     * @param news
     * @return
     */
    public void updNews(News news){
        newsDao.updNews(news);
    }


    /**
     * 提取自身，关注，公开动态信息展示
     * @param  createUserId,status
     * @return
     */
    public List<News> queryForpublicNewsListByCreateUserId(String createUserId){
        List<News>newsList=newsDao.queryForpublicNewsListByCreateUserId(createUserId);
        if(newsList!=null)
        {
            for(int i=0;i<newsList.size();++i)
            {
                newsList.get(i).setImageList(imageDao.queryForBeanListImageListByNewsId(newsList.get(i).getNewsId()));
            }
        }
        return newsList;
    }

    /**
     * 提取公开动态信息展示
     * @param  showScope
     * @return
     */
    public List<News> queryForNewsListByShowScope(String showScope){
        List<News> newsList=newsDao.queryForBeanListNewsListByShowScope(showScope);
        if(newsList!=null)
        {
            for(int i=0;i<newsList.size();++i)
            {
                newsList.get(i).setImageList(imageDao.queryForBeanListImageListByNewsId(newsList.get(i).getNewsId()));
            }
        }
        return newsList;
    }

    /**
     * 推荐动态信息展示
     * @return
     */
    public List<News> queryForNewsListForRecommend(){
        List<News>newsList=newsDao.queryForNewsListForRecommend();
        if(newsList!=null)
        {
            for(int i=0;i<newsList.size();++i)
            {
                newsList.get(i).setImageList(imageDao.queryForBeanListImageListByNewsId(newsList.get(i).getNewsId()));
            }
        }
        return newsList;
    }

    /**
     * 根据id查找动态
     * @param newsId
     * @return
     */
    public List<News> queryForNewsListByNewsId(String newsId){
        List<News> newsList=newsDao.queryForBeanListNewsListByNewsId(newsId);
        if(newsList!=null)
        {
            for(int i=0;i<newsList.size();++i)
            {
                newsList.get(i).setImageList(imageDao.queryForBeanListImageListByNewsId(newsList.get(i).getNewsId()));
            }
        }
        return newsList;
    }

    /**
     * 根据id查找动态
     * @param newsId
     * @return
     */
    public News  queryForNewsByNewsId(String newsId){
        News news=newsDao.queryForBeanNewsByNewsId(newsId);
        if(news!=null)
        {
            news.setImageList(imageDao.queryForBeanListImageListByNewsId(news.getNewsId()));
        }
        return news;
    }

    /**
     * 根据用户id查找动态
     * @param userId
     * @return
     */
    public List<News>  queryForBeanListNewsListByUserId(String userId){
        List<News> newsList=newsDao.queryForBeanListNewsListByUserId(userId);
        if(newsList!=null)
        {
            for(int i=0;i<newsList.size();++i)
            {
                newsList.get(i).setImageList(imageDao.queryForBeanListImageListByNewsId(newsList.get(i).getNewsId()));
            }
        }
        return newsList;
    }

    /**
     * 提取他人动态信息展示
     * @param  createUserId,userId
     * @return
     */
    public List<News>  queryForBeanListOtherNewsListByCreateUserId(String createUserId,String userId){
        List<News> newsList=newsDao.queryForBeanListOtherNewsListByCreateUserId(createUserId,userId);
        if(newsList!=null)
        {
            for(int i=0;i<newsList.size();++i)
            {
                newsList.get(i).setImageList(imageDao.queryForBeanListImageListByNewsId(newsList.get(i).getNewsId()));
            }
        }
        return newsList;
    }

    /**
     * 评论者展示
     *  @param
     *  @return
     */
    public List<Comment> queryForCommentByNewsId(String newsId,String replyCommentId){
        return newsDao.queryForBeanListCommentListByNewsId(newsId,replyCommentId);
    }

    /**
     * 全部评论展示
     *  @param
     *  @return
     */
    public List<Comment> queryForAllCommentByNewsId(String newsId){
        List<Comment> comments=this.newsDao.queryForAllCommentByNewsId(newsId);
        if(comments!=null&&comments.size()>0)
        {
            for(int i=0;i<comments.size();++i)
            {
                Comment comment=comments.get(i);
                if(comment.getReplyUserId()!=null)
                {
                    Map map = this.newsDao.queryReplyNicknameByReplyUserId(comment);
                    comment.setReplyNickname(map.get("replyNickname").toString());
                }
            }
        }
        return comments;
    }

    /**
     * 点赞次数增加
     * @param  newsId
     * @return
     */
    public int likeCountPlus(String newsId){
        return newsDao.likeCountPlus(newsId);
    }

    /**
     * 点赞次数减少
     * @param  newsId
     * @return
     */
    public int likeCountMinus(String newsId){
        return newsDao.likeCountMinus(newsId);
    }

    /**
     * 评论次数增加
     * @param  newsId
     * @return
     */
    public int commentCountPlus(String newsId){
        return newsDao.commentCountPlus(newsId);
    }

    /**
     * 评论次数减少
     * @param  newsId
     * @return
     */
    public int commentCountMinus(String newsId){
        return newsDao.commentCountMinus(newsId);
    }

    public boolean addNewsRelaCircleCollection(News news,String []circle,String []collection)
    {
        if(circle!=null&&circle.length>0)
        {
            for(int i=0;i<circle.length;++i)
            {
                NewsRelaCircleCollection newsRelaCircleCollection = new NewsRelaCircleCollection();
                newsRelaCircleCollection.setNewsId(news.getNewsId());
                newsRelaCircleCollection.setRelaId(UUID.randomUUID().toString().replace("-",""));
                newsRelaCircleCollection.setTargetId(circle[i]);
                newsRelaCircleCollection.setRelaType(CoreConstant.NewsAssociateCircleCollectionType.cicleNews);
                if(this.newsRelaCircleCollectionDao.addAssociate(newsRelaCircleCollection)<=0)
                {
                    return false;
                }
            }
        }
        if(collection!=null&&collection.length>0)
        {
            for(int i=0;i<collection.length;++i)
            {
                NewsRelaCircleCollection newsRelaCircleCollection = new NewsRelaCircleCollection();
                newsRelaCircleCollection.setNewsId(news.getNewsId());
                newsRelaCircleCollection.setRelaId(UUID.randomUUID().toString().replace("-",""));
                newsRelaCircleCollection.setTargetId(collection[i]);
                newsRelaCircleCollection.setRelaType(CoreConstant.NewsAssociateCircleCollectionType.collectionNews);
                if(this.newsRelaCircleCollectionDao.addAssociate(newsRelaCircleCollection)<=0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 从作品集/艺术圈中删除动态
     * @param newsRelaCircleCollection
     * @return
     */
    public int deleteNewsRelaCircleCollection(NewsRelaCircleCollection newsRelaCircleCollection)
    {
        return this.newsRelaCircleCollectionDao.delAssociate(newsRelaCircleCollection);
    }

    /**
     * 检查动态中是否存在敏感词汇，不存在返回true,存在返回false
     * @param news
     * @return
     */
    public boolean checkNewsSensitiveKeyInContent(News news)
    {
        List<SensitiveKey> sensitiveKeyList=this.newsDao.findSensitiveKeys();

        if(sensitiveKeyList!=null&&sensitiveKeyList.size()>0)
        {
            for(int i=0;i<sensitiveKeyList.size();++i)
            {
                if (news.getContent().contains(sensitiveKeyList.get(i).getWordName()))
                    return false;
            }
        }
        return true;
    }
}
