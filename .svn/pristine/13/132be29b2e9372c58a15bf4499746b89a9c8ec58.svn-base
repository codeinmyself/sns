package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Comment;
import com.puckteam.sns.interfaces.core.vo.News;
import com.puckteam.sns.interfaces.core.vo.SensitiveKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 82402 on 2016/11/3.
 */

@Repository

public class NewsDao extends BaseDao {

    /**
     * 动态信息添加
     * @param news
     * @return
     */
    public int addNews(News news){
            String sql = "insert into sns_news(" +
                    "news_id,show_scope,content,like_count,comment_count,status,create_time,create_user_id" +
                    ") values (?,?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?)";
            return this.executeUpdate(sql, new Object[]{
                    news.getNewsId(),
                    news.getShowScope(),
                    news.getContent(),
                    news.getLikeCount(),
                    news.getCommentCount(),
                    news.getStatus(),
                    this.getSysdate(),
                    news.getCreateUserId()});
    }

    /**
     * 动态信息删除
     * @param newsId
     * @return
     */
    public int delNews(String newsId){
        String sql = "update sns_news set status =? where news_id =?";
        return this.executeUpdate(sql,new Object[]{CoreConstant.Status.DELETE,newsId});
    }

    /**
     * 动态更新
     * @param news
     * @return
     */
    public int updNews(News news){
        String sql = "update sns_news set content=? where news_id=?";
        return this.executeUpdate(sql,new Object[]{news.getContent(),news.getNewsId()});
    }


    /**
     * 提取自身，关注，公开动态信息展示
     * @param  createUserId
     * @return
     */
    public List<News>  queryForpublicNewsListByCreateUserId(String createUserId){
        String sql = "select " +
                " sns_news.news_id," +
                " sns_news.show_scope," +
                " sns_news.content," +
                " sns_news.like_count," +
                " sns_news.comment_count," +
                " sns_news.click_count," +
                " sns_news.status," +
                " sns_news.create_user_id," +
                " date_format(sns_news.create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " sns_user.nickname,"+
                " sns_user.avatar" +
                " from sns_news,sns_user " +
                " where sns_news.news_id in (select A.news_id from sns_news A where A.status=? and A.show_scope='01'" +
                " or A.create_user_id=? and A.status=? " +
                " or (A.show_scope='02'and A.status=? and A.create_user_id in (select sns_focus.focus_user_id from sns_focus where sns_focus.user_id=?)))" +
                " and sns_news.create_user_id=sns_user.user_id" +
                " order by sns_news.create_time desc";
        List<News> newsList=this.queryForBeanList(sql,new Object[]{CoreConstant.Status.NORMAL,createUserId,CoreConstant.Status.NORMAL,CoreConstant.Status.NORMAL,createUserId},News.class);
        return newsList;
    }

    /**
     * 提取公开动态信息展示
     * @param  showScope
     * @return
     */
    public List<News>  queryForBeanListNewsListByShowScope(String showScope){
        String sql = "select " +
                " sns_news.*," +
                " sns_user.nickname,"+
                " sns_user.avatar" +
                " from sns_news,sns_user " +
                " where sns_news.show_scope=? and sns_news.status=? and sns_news.create_user_id=sns_user.user_id" +
                " order by sns_news.create_time desc";
        List<News> newsList=this.queryForBeanList(sql,new Object[]{showScope,CoreConstant.Status.NORMAL},News.class);
        return newsList;
    }

    /**
     * 推荐动态信息展示
     * @return
     */
    public List<News> queryForNewsListForRecommend(){
        String sql = "select " +
                " sns_news.*," +
                " sns_user.nickname,"+
                " sns_user.avatar" +
                " from sns_news,sns_user " +
                " where sns_news.show_scope=? and sns_news.status=? and sns_news.create_user_id=sns_user.user_id" +
                " order by sns_news.click_count desc limit 5";
        List<News> newsList=this.queryForBeanList(sql,new Object[]{01,CoreConstant.Status.NORMAL},News.class);
        return newsList;
    }

    /**
     * 根据id查找动态
     * @param newsId
     * @return
     */
    public List<News>  queryForBeanListNewsListByNewsId(String newsId){
        String sql = "select " +
                " sns_news.*," +
                " sns_user.nickname,"+
                " sns_user.avatar" +
                " from sns_news,sns_user " +
                " where sns_news.news_id=? and sns_news.status=? and sns_news.create_user_id=sns_user.user_id" +
                " order by sns_news.create_time desc";
        List<News> newsList=this.queryForBeanList(sql,new Object[]{newsId,CoreConstant.Status.NORMAL},News.class);
        return newsList;
    }

    /**
     * 根据id查找动态
     * @param newsId
     * @return
     */
    public News queryForBeanNewsByNewsId(String newsId){
        String sql = "select " +
                " sns_news.*," +
                " sns_user.nickname,"+
                " sns_user.avatar" +
                " from sns_news,sns_user " +
                " where sns_news.news_id=? and sns_news.status=? and sns_news.create_user_id=sns_user.user_id";
        News news=(News)this.queryForBean(sql,new Object[]{newsId,CoreConstant.Status.NORMAL},News.class);
        return news;
    }

    /**
     * 根据用户id查找动态
     * @param userId
     * @return
     */
    public List<News>  queryForBeanListNewsListByUserId(String userId){
        String sql = "select " +
                " sns_news.news_id," +
                " sns_news.show_scope," +
                " sns_news.content," +
                " sns_news.like_count," +
                " sns_news.comment_count," +
                " sns_news.click_count," +
                " sns_news.status," +
                " sns_news.create_user_id," +
                " date_format(sns_news.create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " sns_user.nickname,"+
                " sns_user.avatar" +
                " from sns_news,sns_user " +
                " where sns_news.create_user_id=? and sns_news.status=? and sns_news.create_user_id=sns_user.user_id" +
                " order by sns_news.create_time desc";
        List<News> newsList=this.queryForBeanList(sql,new Object[]{userId,CoreConstant.Status.NORMAL},News.class);
        return newsList;
    }


    /**
     * 提取他人动态信息展示
     * @param  createUserId,userId
     * @return
     */
    public List<News>  queryForBeanListOtherNewsListByCreateUserId(String createUserId,String userId){
        String sql = " select " +
                " sns_news.news_id, " +
                " sns_news.show_scope," +
                " sns_news.content, " +
                " sns_news.like_count, " +
                " sns_news.comment_count," +
                " sns_news.status," +
                " sns_news.create_user_id," +
                " date_format(sns_news.create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " sns_user.nickname," +
                " sns_user.avatar " +
                " from sns_news,sns_user" +
                " where sns_news.create_user_id=? and sns_news.news_id in" +
                " (select A.news_id from sns_news A where A.status=? " +
                " and A.show_scope='01'" +
                " or (A.show_scope='02' and A.create_user_id in (select sns_focus.focus_user_id from sns_focus where sns_focus.user_id=?)))" +
                " and sns_news.create_user_id=sns_user.user_id " +
                " order by sns_news.create_time desc";
        List<News> newsList = this.queryForBeanList(sql,new Object[]{createUserId,CoreConstant.Status.NORMAL,userId},News.class);
        return newsList;
    }

    /**
     * 点赞次数增加
     * @param  newsId
     * @return
     */
    public int likeCountPlus(String newsId){
        String sql = "update sns_news set like_count=like_count+1,click_count=click_count+1 where news_id=?";
        return this.executeUpdate(sql,new Object[]{newsId});
    }

    /**
     * 点赞次数减少
     * @param  newsId
     * @return
     */
    public int likeCountMinus(String newsId){
        String sql = "update sns_news set like_count=like_count-1 where news_id=?";
        return this.executeUpdate(sql,new Object[]{newsId});
    }

    /**
     * 评论次数增加
     * @param  newsId
     * @return
     */
    public int commentCountPlus(String newsId){
        String sql = "update sns_news set comment_count=comment_count+1,click_count=click_count+1 where news_id=?";
        return this.executeUpdate(sql,new Object[]{newsId});
    }

    /**
     * 评论次数减少
     * @param  newsId
     * @return
     */
    public int commentCountMinus(String newsId){
        String sql = "update sns_news set comment_count=comment_count-1 where news_id=?";
        return this.executeUpdate(sql,new Object[]{newsId});
    }

    /**
     * 评论者展示
     *  @param
     *  @return
     */
    public List<Comment> queryForBeanListCommentListByNewsId(String newsId,String replyCommentId){
        String sql = " select " +
                " sns_comment.*," +
                " sns_user.nickname," +
                " sns_user.avatar " +
                " from sns_comment,sns_user " +
                " where sns_comment.news_id = ? and reply_comment_id = ? and sns_comment.create_user_id = sns_user.user_id";
        List<Comment> commentList=this.queryForBeanList(sql,new Object[]{newsId,replyCommentId},Comment.class);
        return commentList;
    }

    /**
     * 全部评论展示
     *  @param
     *  @return
     */
    public List<Comment> queryForAllCommentByNewsId(String newsId){
        String sql = "set max_sp_recursion_depth=255;";
        this.executeUpdate(sql,null);
        sql="call showCommentList(?)";
        List<Comment> commentList=this.queryForBeanList(sql,new Object[]{newsId},Comment.class);
        return commentList;
    }

    public Map queryReplyNicknameByReplyUserId(Comment comment)
    {
        String sql="select nickname as replyNickname from sns_user where user_id=?";
        return this.queryForMap(sql,new Object[]{comment.getReplyUserId()});
    }

    /**
     * 查找敏感词列表
     * @return
     */
    public List<SensitiveKey> findSensitiveKeys()
    {
        String sql="select " +
                " word_id," +
                " word_name," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime" +
                " from sns_sensitive_word";

        return (List<SensitiveKey>)this.queryForBeanList(sql,SensitiveKey.class);
    }
}
