package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ZengJieHang on 2016/11/15.
 */
@Repository
public class AdminDao extends BaseDao
{
    /**
     * 查找举报列表
     * @return
     */
    public List<Report> findReportList()
    {
        String sql="select " +
                " report_id," +
                " report_type," +
                " report_desc," +
                " create_user_id," +
                " date_format(sns_report.create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " target_type," +
                " target_id," +
                " read_status," +
                " user_name as createUserName" +
                " from sns_report,sns_user " +
                " where sns_report.create_user_id=sns_user.user_id" +
                " order by sns_report.create_time";

        List<Report> reports=(List<Report>)this.queryForBeanList(sql,Report.class);

        return reports;
    }

    /**
     * 查找用户列表
     * @return
     */
    public List<User> findUserList()
    {
        String sql = "select " +
                " user_id," +
                " user_name," +
                " password," +
                " email," +
                " mobile," +
                " nickname," +
                " avatar," +
                " is_artist," +
                " signature," +
                " is_locked," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_user";

        List<User> userList= (List<User>) this.queryForBeanList(
                sql, User.class);

        return userList;
    }

    /**
     * 封号
     * @param userId
     * @return
     */
    public int lockUser(String userId)
    {
        String sql="update sns_user set is_locked=? where user_Id=?";

        return this.executeUpdate(sql,new Object[]{CoreConstant.UserAccountStatus.LOCKED,userId});
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

    /**
     * 根据敏感词判断敏感词是否存在
     * @param key
     * @return
     */
    public Map isSensitiveKeyExist(String key)
    {
        String sql="select count(*) as number" +
                " from sns_sensitive_word" +
                " where word_name=?";
        return this.queryForMap(sql,new Object[]{key});
    }

    /**
     * 根据敏感词id判断敏感词是否存在
     * @param key
     * @return
     */
    public Map isSensitiveIdExist(String key)
    {
        String sql="select count(*) as number" +
                " from sns_sensitive_word" +
                " where word_id=?";

        return this.queryForMap(sql,new Object[]{key});
    }

    /**
     * 添加敏感词
     * @param sensitiveKey
     * @return
     */
    public int addKey(SensitiveKey sensitiveKey)
    {
        String sql="insert into sns_sensitive_word " +
                " values(?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";

        return this.executeUpdate(sql,new Object[]{
                sensitiveKey.getWordId(),
                sensitiveKey.getWordName(),
                this.getSysdate()
        });
    }

    /**
     * 删除敏感词
     * @param key
     * @return
     */
    public int removeKey(String key)
    {
        String sql="delete from sns_sensitive_word" +
                " where word_id=?";

        return this.executeUpdate(sql,new Object[]{key});
    }

    /**
     * 根据用户id查找用户
     * @param userId
     * @return
     */
    public User findUserByUserId(String userId)
    {
        String sql="select " +
                " user_id," +
                " user_name," +
                " password," +
                " email," +
                " mobile," +
                " nickname," +
                " avatar," +
                " is_artist," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_user where user_id=?";
        return (User)this.queryForBean(sql,new Object[]{userId},User.class);
    }

    /**
     * 根据作品集id查找作品集
     * @param collectionId
     * @return
     */
    public Collection findCollectionByCollectionId(String collectionId)
    {
        String sql="select " +
                " collection_id," +
                " collection_name," +
                " show_scope," +
                " description," +
                " status," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " cover " +
                " from sns_collection where collection_id=?";
        return (Collection)this.queryForBean(sql,new Object[]{collectionId},Collection.class);
    }

    /**
     * 根据艺术圈id查找艺术圈
     * @param circleId
     * @return
     */
    public Circle findCircleByCircleId(String circleId)
    {
        String sql="select " +
                " circle_id," +
                " circle_name," +
                " status," +
                " show_scope," +
                " apply_strategy," +
                " description," +
                " avatar," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_circle where circle_id=?";
        return (Circle)this.queryForBean(sql,new Object[]{circleId},Circle.class);
    }

    /**
     * 根据动态id查找动态
     * @param newsId
     * @return
     */
    public News findNewsByNewsId(String newsId)
    {
        String sql = "select " +
                " sns_news.news_id," +
                " sns_news.show_scope," +
                " sns_news.content," +
                " sns_news.like_count," +
                " sns_news.comment_count," +
                " sns_news.status," +
                " date_format(sns_news.create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " sns_news.create_user_id," +
                " sns_user.nickname,"+
                " sns_user.avatar" +
                " from sns_news,sns_user " +
                " where sns_news.news_id=? and sns_news.create_user_id=sns_user.user_id";

        News news=(News)this.queryForBean(sql,new Object[]{newsId},News.class);

        return news;
    }

    /**
     * 查找动态的图片
     * @param newsId
     * @return
     */
    public List<Image> findNewsImageByNewsId(String newsId)
    {
        String sql= " select" +
                " pic_id," +
                " news_id, " +
                " pic_index," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_news_image where news_id = ? order by pic_index";
       return  (List<Image>)this.queryForBeanList(sql,new Object[]{newsId},Image.class);
    }

    /**
     * 根据评论id查找评论
     * @param commentId
     * @return
     */
    public Comment findCommentByCommentId(String commentId)
    {
        String sql="select sns_comment.comment_id," +
                " sns_comment.news_id," +
                " sns_comment.reply_comment_id," +
                " sns_comment.content," +
                " date_format(sns_comment.create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " sns_comment.create_user_id," +
                " sns_user.nickname as createNickname," +
                " sns_user.avatar as createAvatar" +
                " from sns_comment,sns_user" +
                " where comment_id=? and sns_comment.create_user_id=sns_user.user_id";
        return (Comment)this.queryForBean(sql,new Object[]{commentId},Comment.class);
    }

    /**
     * 根据举报的id查找举报
     * @param reportId
     * @return
     */
    public Report findReportByReportId(String reportId)
    {
        String sql="select " +
                " report_id," +
                " report_type," +
                " report_desc," +
                " create_user_id," +
                " date_format(sns_report.create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " target_type," +
                " target_id," +
                " read_status," +
                " report_reply," +
                " user_name as createUserName" +
                " from sns_report,sns_user " +
                " where sns_report.create_user_id=sns_user.user_id" +
                " and report_id=?";

        Report report=(Report)this.queryForBean(sql,new Object[]{reportId},Report.class);

        return report;
    }

    /**
     *处理举报
     * @param reportId
     * @return
     */
    public int haveDealReport(String reportId)
    {
        String sql="update sns_report set read_status=?" +
                " where report_id=? ";

        return this.executeUpdate(sql,new Object[]{CoreConstant.ReportReadStatus.HAS_DEAL,reportId});
    }

    /**
     * 删除动态、作品集、艺术圈、或者评论
     * @param sql
     * @param targetId
     * @return
     */
    public int deleteNewsOrCollectionOrCircleOrComment(String sql,String targetId)
    {
        return this.executeUpdate(sql,new Object[]{CoreConstant.Status.DELETE,targetId});
    }

    public int replyReport(String reportId,String reportReply)
    {
        String sql="update sns_report set report_reply=? where report_id=?";
        return this.executeUpdate(sql,new Object[]{reportReply,reportId});
    }

    public Map adminLogin(String account,String password)
    {
        String sql="select count(*) as number " +
                " from sns_admin" +
                " where admin_id=? " +
                " and admin_password=?";

        return this.queryForMap(sql,new Object[]{account,password});
    }
}
