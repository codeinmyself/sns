package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.*;

import java.util.List;

/**
 * Created by ZengJieHang on 2016/11/15.
 */
public interface IAdminService
{
    /**
     *查找举报列表
     * @return
     */
    List<Report> findReportList();

    /**
     *查找用户列表
     * @return
     */
    List<User> findUserList();

    /**
     *封号
     * @param userId
     * @return
     */
    int lockUser(String userId);

    /**
     * 查找所有敏感词
     * @return
     */
    List<SensitiveKey> findSensitiveKeys();

    /**
     * 根据敏感词内容判断敏感词是否存在
     * @param key
     * @return
     */
    boolean isSensitiveKeyExist(String key);

    /**
     * 根据敏感词ID判断敏感词是否存在
     * @param key
     * @return
     */
    boolean isSensitiveIdExist(String key);

    /**
     * 添加敏感词
     * @param sensitiveKey
     * @return
     */
    int addKey(SensitiveKey sensitiveKey);

    /**
     * 删除敏感词
     * @param key
     * @return
     */
    int removeKey(String key);

    /**
     * 通过用户id查找用户
     * @param userId
     * @return
     */
    User findUserByUserId(String userId);

    /**
     * 根据作品集id查找作品集
     * @param collectionId
     * @return
     */
    Collection findCollectionByCollectionId(String collectionId);

    /**
     * 根据艺术圈id查找艺术圈
     * @param circleId
     * @return
     */
    Circle findCircleByCircleId(String circleId);

    /**
     * 根据动态id查找动态
     * @param newsId
     * @return
     */
    News findNewsByNewsId(String newsId);

    /**
     * 根据评论id查找评论
     * @param commentId
     * @return
     */
    Comment findCommentByCommentId(String commentId);

    /**
     * 根据举报id查找举报
     * @param reportId
     * @return
     */
    Report findReportByReportId(String reportId);

    /**
     * 处理举报，举报设置为已处理
     * @param reportId
     * @return
     */
    int haveDealReport(String reportId);

    /**
     * 忽略举报
     * @param reportId
     * @return
     */
    int ignoreReport(String reportId);

    /**
     * 删除动态或作品集或艺术圈或者评论（将相应status设置为0）
     * @param targetType
     * @param targetId
     * @return
     */
    int deleteNewsOrCollectionOrCircleOrComment(String targetType, String targetId);


    /**
     * 回复举报
     * @param reportId
     * @param reportReply
     * @return
     */
    int replyReport(String reportId, String reportReply);

    /**
     * 管理员登陆
     * @param account
     * @param password
     * @return
     */
    boolean adminLogin(String account, String password);
}
