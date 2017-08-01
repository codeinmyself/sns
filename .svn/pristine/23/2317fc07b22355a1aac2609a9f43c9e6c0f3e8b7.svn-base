package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.*;

import java.util.List;

/**
 * Created by ZengJieHang on 2016/11/14.
 */
public interface INoticeService
{
    /**
     * 通过接受的用户id查找通知
     * @param userId
     * @return
     */
    List<Notice> findNoticeByReceiveUserId(String userId);

    /**
     * 通过接受的用户id查找已经阅读的通知
     * @param userId
     * @return
     */
    List<Notice> findNoticeHaveReadByReceiveUserId(String userId);

        /*
    int haveReadNotice(String noticeId);*/

    /**
     * 根据通知id判断通知是否存在
     * @param userId
     * @param noticeId
     * @return
     */
    boolean isNoticeIdExists(String userId, String noticeId);

    /**
     * 申请去艺术圈通知
     * @param memberApply
     * @return
     */
    boolean applyToCircleNotice(MemberApply memberApply);

    /**
     * 艺术圈申请结果通知
     * @param memberApply
     * @return
     */
    boolean circleApplyResultNotice(MemberApply memberApply);

    /**
     * 新成员加入艺术圈通知
     * @param member
     * @return
     */
    boolean addToCircleNotice(Member member);

    /**
     * 成员退出艺术圈通知
     * @param member
     * @return
     */
    boolean exitCircleNotice(Member member);

    /**
     * 升级为艺术圈管理员通知
     * @param member
     * @return
     */
    boolean promoteToCircleAdminNotice(Member member);

    /**
     * 移除艺术圈管理员通知
     * @param member
     * @return
     */
    boolean removeCircleAdminNotice(Member member);

    /**
     * 移除艺术圈成员通知
     * @param member
     * @return
     */
    boolean removeCircleMemberNotice(Member member);

    /**
     * 删除艺术圈动态通知
     * @return
     */
    boolean deleteCircleNewsNotice(NewsRelaCircleCollection newsRelaCircleCollection, News news);

    /**
     * 关注通知
     * @param snsFocus
     * @return
     */
    boolean focusByOthersNotice(SnsFocus snsFocus);

    /**
     * 动态被评论通知
     * @return
     */
    boolean commentByOthersNotice(Comment comment);

    /**
     * 评论被回复通知
     * @return
     */
    boolean replyByOtherNotice(Comment comment);

    /**
     * 添加新通知
     * @param noticeContent
     * @param receiveUserId
     * @param noticeType
     * @param targetId
     * @return
     */
    int insertNotice(String noticeContent, String receiveUserId, String noticeType, String targetId);

    /**
     * 通过通知id查找通知
     * @param noticeId
     * @return
     */
    Notice findNoticeByNoticeId(String noticeId);

    /**
     * 根据通知信息跳转到相应的连接
     * @param notice
     * @return
     */
    String turnToTargetPageByNotice(Notice notice);


    /**
     * 举报被回复通知
     * @param report
     * @return
     */
    boolean reportReplyByAdminNotice(Report report);

    /**
     * 作品集被管理员删除通知
     * @param collection
     * @return
     */
    boolean collectionDeleteByAdminNotice(Collection collection);

    /**
     * 艺术圈被管理员删除通知
     * @param circle
     * @return
     */
    boolean circleDeleteByAdminNotice(Circle circle);

    /**
     * 动态被管理员删除通知
     * @param news
     * @return
     */
    boolean newsDeleteByAdminNotice(News news);

    /**
     * 评论被管理员删除通知
     * @param comment
     * @return
     */
    boolean commentDeleteByAdminNotice(Comment comment);
}
