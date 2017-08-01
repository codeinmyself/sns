package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.Comment;

import java.util.List;

/**
 * Created by 82402 on 2016/11/14.
 */
public interface ICommentService {
    /**
     * 评论
     * @param comment
     * @return
     */
    public int addComment(Comment comment);

    /**
     * 回复
     * @param comment,commentId
     * @return
     */
    public int addReply(Comment comment);

    /**
     * 删除自己评论
     * @param commentId,createUserId
     * @return
     */
    public int delComment(String commentId);

    /**
     * 动态所有者删除底下评论
     * @param commentId,createUserId
     * @return
    public int delCommentByNewsOwn(String commentId,String createUserId);*/

    /**
     * 展示评论
     * @param commentId
     * @return
     */
    public List<Comment> queryForCommentByCommentId(String commentId);
}
