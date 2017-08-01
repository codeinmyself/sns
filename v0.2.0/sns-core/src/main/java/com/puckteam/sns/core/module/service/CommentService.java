package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.module.dao.CommentDao;
import com.puckteam.sns.interfaces.core.service.ICommentService;
import com.puckteam.sns.interfaces.core.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 82402 on 2016/11/14.
 */

@Service("commentService")
@Transactional

public class CommentService implements ICommentService {
    @Autowired
    private CommentDao commentDao;

    /**
     * 评论
     * @param comment
     * @return
     */
    public int addComment(Comment comment){
        return commentDao.addComment(comment);
    }

    /**
     * 回复
     * @param commentId
     * @return
     */
    public int addReply(Comment comment){
        return commentDao.addReply(comment);
    }

    /**
     * 删除自己评论
     * @param commentId,createUserId
     * @return
     */
    public int delComment(String commentId){
        return commentDao.delComment(commentId);
    }

    /**
     * 动态所有者删除底下评论
     * @param commentId,createUserId
     * @return
    public int delCommentByNewsOwn(String commentId,String createUserId){
        return commentDao.delCommentByNewsOwn(commentId,createUserId);
    }*/

    /**
     * 展示评论
     * @param commentId
     * @return
     */
    public List<Comment> queryForCommentByCommentId(String commentId){
        return commentDao.queryForBeanListCommentByCommentId(commentId);
    }
}
