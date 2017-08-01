package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 82402 on 2016/11/14.
 */

@Repository

public class CommentDao extends BaseDao{
    /**
     * 评论
     * @param comment
     * @return
     */
    public int addComment(Comment comment){
        String sql = " insert into sns_comment(" +
                "comment_id,news_id,reply_comment_id,content,create_time,create_user_id,status" +
                ")value(?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?)";
        return this.executeUpdate(sql,new Object[]{
                comment.getCommentId(),
                comment.getNewsId(),
                comment.getReplyCommentId(),
                comment.getContent(),
                this.getSysdate(),
                comment.getCreateUserId(),
                CoreConstant.Status.NORMAL
        });
    }

    /**
     * 回复
     * @param comment
     * @return
     */
    public int addReply(Comment comment){
        String sql = " insert into sns_comment(" +
                "comment_id,news_id,reply_comment_id,content,create_time,create_user_id,status" +
                ")value(?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?)";
        return this.executeUpdate(sql,new Object[]{
                comment.getCommentId(),
                comment.getNewsId(),
                comment.getReplyCommentId(),
                comment.getContent(),
                this.getSysdate(),
                comment.getCreateUserId(),
                CoreConstant.Status.NORMAL
        });
    }

    /**
     * 删除自己评论
     * @param commentId,createUserId
     * @return
     */
    public int delComment(String commentId){
        String sql = "update sns_comment set status =? where comment_id =?";
        return this.executeUpdate(sql,new Object[]{
                CoreConstant.Status.DELETE,
                commentId
        });
    }

    /**
     * 动态所有者删除底下评论
     * @param commentId,createUserId
     * @return
    public int delCommentByNewsOwn(String commentId,String createUserId){
        String sql = " delete from sns_comment where sns_comment.comment_id = ?" +
                "and sns_news.news_id = sns_comment.news_id " +
                "and sns_news.create_user_id = ?";
    }*/

    /**
     * 展示评论
     * @param commentId
     * @return
     */
    public List<Comment> queryForBeanListCommentByCommentId(String commentId){
        String sql = "select " +
                " comment_id," +
                " news_id," +
                " reply_comment_id," +
                " content," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " create_user_id " +
                " from sns_comment where comment_id=?";
        List<Comment> commentList=this.queryForBeanList(sql,new Object[]{commentId},Comment.class);
        return commentList;
    }
}
