package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.module.dao.NoticeDao;
import com.puckteam.sns.interfaces.core.service.INoticeService;
import com.puckteam.sns.interfaces.core.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ZengJieHang on 2016/11/14.
 */
@Service("noticeService")
@Transactional
public class NoticeService implements INoticeService
{
    @Autowired
    private NoticeDao noticeDao;

    public List<Notice> findNoticeByReceiveUserId(String userId)
    {
        List<Notice> noticeList= this.noticeDao.selectNoticeListByReceiveUserId(userId);

        if(noticeList!=null&&noticeList.size()>0)
        {
            for(int i=0;i<noticeList.size();++i)
            {
                this.noticeDao.updateNoticeStatueWhileSelecting(noticeList.get(i));
            }
        }

        return noticeList;
    }

    /***
     * 通过userId查找已经阅读的通知
     * @param userId
     * @return
     */
    public List<Notice> findNoticeHaveReadByReceiveUserId(String userId)
    {
        return this.noticeDao.findNoticeHaveReadByReceiveUserId(userId);
    }

   /* public int haveReadNotice(String noticeId)
    {
        return this.noticeDao.updateReadStatusByNoticeId(noticeId);
    }*/

    public boolean isNoticeIdExists(String userId,String noticeId)
    {
        return this.noticeDao.isNoticeIdExists(userId,noticeId);
    }

    public boolean applyToCircleNotice(MemberApply memberApply)
    {
        if(memberApply==null)
        {
            return false;
        }

        String circleName=this.noticeDao.getCircleNameByCircleId(memberApply.getCircleId());
        String nickName=this.noticeDao.getUserNickNameByUserId(memberApply.getApplyUserId());
        String noticeContent="用户"+nickName+"申请加入艺术圈"+circleName;
        String circleAdminList[]=this.noticeDao.getCircleAdminByCircleId(memberApply.getCircleId());

        for(int i=0;i<circleAdminList.length;++i)
        {
            if(this.insertNotice(noticeContent,circleAdminList[i], CoreConstant.NoticeType.APPLY_TO_CIRCLE,memberApply.getCircleId())<=0)
            {
                return false;
            }
        }
        return true;
    }

    public boolean circleApplyResultNotice(MemberApply memberApply)
    {
        if(memberApply==null)
        {
            return false;
        }

        String circleName=this.noticeDao.getCircleNameByCircleId(memberApply.getCircleId());

        String noticeContent = circleName + "艺术圈";
        if(memberApply.getApplyStatus().equals(CoreConstant.MemberApplyType.ADMIT))
        {
             noticeContent+="同意了您的加入!";
        }
        else if(memberApply.getApplyStatus().equals(CoreConstant.MemberApplyType.DISAGREE))
        {
            noticeContent+="不同意了您的加入!";
        }

        if(this.insertNotice(noticeContent,memberApply.getApplyUserId(), CoreConstant.NoticeType.CIRCLE_APPLY_RESULT,memberApply.getCircleId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean addToCircleNotice(Member member)
    {
        if(member==null)
        {
            return false;
        }

        String circleName=this.noticeDao.getCircleNameByCircleId(member.getCircleId());
        String applyNickName=this.noticeDao.getUserNickNameByUserId(member.getUserId());
        String noticeContent="用户"+applyNickName+"已加入艺术圈"+circleName;//需求：需不需要显示同意的管理员信息
        String circleCreator=this.noticeDao.getCircleCreatorByCircleId(member.getCircleId());

        if(this.insertNotice(noticeContent,circleCreator, CoreConstant.NoticeType.ADD_CIRCLE,member.getCircleId())>0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean exitCircleNotice(Member member)
    {
        if(member==null)
        {
            return false;
        }

        String circleName=this.noticeDao.getCircleNameByCircleId(member.getCircleId());
        String nickName=this.noticeDao.getUserNickNameByUserId(member.getUserId());
        String noticeContent="用户"+nickName+"退出艺术圈"+circleName;

        String circleCreator=this.noticeDao.getCircleCreatorByCircleId(member.getCircleId());

        if(this.insertNotice(noticeContent,circleCreator, CoreConstant.NoticeType.EXIT_CIRCLE,member.getCircleId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean promoteToCircleAdminNotice(Member member)
    {
        if(member==null)
        {
            return false;
        }

        String circleName=this.noticeDao.getCircleNameByCircleId(member.getCircleId());
        String noticeContent="您已被"+circleName+"艺术圈的创建者升级为管理员";

        if(this.insertNotice(noticeContent,member.getUserId(), CoreConstant.NoticeType.PROMOTE_TO_CIRCLE_ADMIN,member.getCircleId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeCircleAdminNotice(Member member)
    {
        if(member==null)
        {
            return false;
        }

        String circleName=this.noticeDao.getCircleNameByCircleId(member.getCircleId());
        String noticeContent="您已被"+circleName+"艺术圈的创建者降为普通成员";

        if(this.insertNotice(noticeContent,member.getUserId(), CoreConstant.NoticeType.REMOVE_CIRCLE_ADMIN,member.getCircleId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean removeCircleMemberNotice(Member member)
    {
        if(member==null)
        {
            return false;
        }

        String circleName=this.noticeDao.getCircleNameByCircleId(member.getCircleId());
        String noticeContent="您已被"+circleName+"艺术圈移除";

        if(this.insertNotice(noticeContent,member.getUserId(), CoreConstant.NoticeType.REMOVE_CIRCLE,member.getCircleId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public boolean deleteCircleNewsNotice(NewsRelaCircleCollection newsRelaCircleCollection,News news)
    {
        if(newsRelaCircleCollection==null||news==null)
        {
            return  false;
        }

        String circleName=this.noticeDao.getCircleNameByCircleId(newsRelaCircleCollection.getTargetId());
        String newsContent=this.noticeDao.getNewsContentByNewsId(newsRelaCircleCollection.getNewsId());
        String noticeContent="您在艺术圈"+circleName+"的动态"+newsContent+"被艺术圈的群主/管理员删除!";

        if(this.insertNotice(noticeContent,news.getCreateUserId(), CoreConstant.NoticeType.DELETE_CIRCLE_NEWS,newsRelaCircleCollection.getTargetId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean focusByOthersNotice(SnsFocus snsFocus)
    {
        if(snsFocus==null)
        {
            return false;
        }

        String nickName=this.noticeDao.getUserNickNameByUserId(snsFocus.getUserId());
        String noticeContent="您有新的粉丝"+nickName;

        if(this.insertNotice(noticeContent,snsFocus.getFocusUserId(), CoreConstant.NoticeType.FOCUS_BY_OTHERS,snsFocus.getUserId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 动态被别人评论通知
     * @param comment
     * @return
     */
    public boolean commentByOthersNotice(Comment comment)
    {
        if(comment==null)
        {
            return false;
        }

        String  newsCreateUserId=this.noticeDao.getUserIdByNewId(comment.getNewsId()).get("createUserId").toString();
        String nickName=this.noticeDao.getUserNickNameByUserId(comment.getCreateUserId());
        String newsContent=this.noticeDao.getNewsContentByNewsId(comment.getNewsId());
        String noticeContent="您的动态"+newsContent+"有一条来自用户"+nickName+"的新评论";

        if(this.insertNotice(noticeContent,newsCreateUserId, CoreConstant.NoticeType.COMMENT_BY_OTHERS,comment.getNewsId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 评论被别人回复通知
     * @param comment
     * @return
     */
    public boolean replyByOtherNotice(Comment comment)
    {
        if(comment==null)
        {
            return false;
        }

        String commentCreateUserId=this.noticeDao.getCreateUserIdByCommentId(comment.getReplyCommentId()).get("createUserId").toString();
        String nickName=this.noticeDao.getUserNickNameByUserId(comment.getCreateUserId());
        String commentContent=this.noticeDao.getCommentContentByCommentId(comment.getReplyCommentId());
        String noticeContent="您的评论"+commentContent+"有一条来自用户"+nickName+"的新回复";

        if(this.insertNotice(noticeContent,commentCreateUserId, CoreConstant.NoticeType.REPLY_BY_OTHER,comment.getNewsId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 举报被回复通知
     * @param report
     * @return
     */
    public boolean reportReplyByAdminNotice(Report report)
    {
        if(report ==null)
        {
            return false;
        }

        String noticeContent="您于"+report.getCreateTime()+"的举报有新回复："+report.getReportReply();

        if(this.insertNotice(noticeContent,report.getCreateUserId(), CoreConstant.NoticeType.REPORT_REPLY_BY_ADMIN,report.getTargetId())>0) {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 作品集被管理员删除通知
     * @param collection
     * @return
     */
    public boolean collectionDeleteByAdminNotice(Collection collection)
    {
        if(collection ==null)
        {
            return false;
        }

        String noticeContent="您的作品集"+collection.getCollectionName()+"因涉嫌违规而被管理员删除";

        if(this.insertNotice(noticeContent,collection.getCreateUserId(), CoreConstant.NoticeType.COLLECTION_DELETE_BY_ADMIN,collection.getCollectionId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 艺术圈被管理员删除通知
     * @param circle
     * @return
     */
    public boolean circleDeleteByAdminNotice(Circle circle)
    {
        if(circle==null)
        {
            return false;
        }

        String noticeContent="您的艺术圈"+circle.getCircleName()+"因涉嫌违规而被管理员删除";

        if(this.insertNotice(noticeContent,circle.getCreateUserId(), CoreConstant.NoticeType.CIRCLE_DELETE_BY_ADMIN,circle.getCircleId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 动态被管理员删除通知
     * @param news
     * @return
     */
    public boolean newsDeleteByAdminNotice(News news)
    {
        if(news==null)
        {
            return false;
        }

        String noticeContent="您的动态"+news.getContent()+"因涉嫌违规而被管理员删除";

        if(this.insertNotice(noticeContent,news.getCreateUserId(), CoreConstant.NoticeType.NEWS_DELETE_BY_ADMIN,news.getNewsId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 评论被管理员删除通知
     * @param comment
     * @return
     */
    public boolean commentDeleteByAdminNotice(Comment comment)
    {
        if(comment==null)
        {
            return false;
        }

        String noticeContent="您的评论"+comment.getContent()+"因涉嫌违规而被管理员删除";

        if(this.insertNotice(noticeContent,comment.getCreateUserId(), CoreConstant.NoticeType.COMMENT_DELETE_BY_ADMIN,comment.getCommentId())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    /***
     * 插入数据库
     * @param noticeContent
     * @param receiveUserId
     * @param noticeType
     * @param targetId
     * @return
     */
    public int insertNotice(String noticeContent,String receiveUserId,String noticeType,String targetId)
    {
        Notice notice=new Notice();

        notice.setNoticeContent(noticeContent);
        notice.setReceiveUserId(receiveUserId);
        notice.setNoticeType(noticeType);
        notice.setTargetId(targetId);

        return this.noticeDao.insertNotice(notice);
    }

    public Notice findNoticeByNoticeId(String noticeId)
    {
        return this.noticeDao.findNoticeByNoticeId(noticeId);
    }

    /**
     * 根据通知信息跳转到相应的连接
     * @param notice
     * @return
     */
    public String turnToTargetPageByNotice(Notice notice)
    {
        String targetPage="redirect:";

        if(notice.getNoticeType().equals(CoreConstant.NoticeType.APPLY_TO_CIRCLE))
        {
            String tempString="../circle/circleApplyManage?circleId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.CIRCLE_APPLY_RESULT))
        {
            String tempString="../circle/circleActivity?circleId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.ADD_CIRCLE))
        {
            String tempString="../circle/memberManage?circleId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.EXIT_CIRCLE))
        {
            String tempString="../circle/memberManage?circleId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.PROMOTE_TO_CIRCLE_ADMIN))
        {
            String tempString="../circle/memberManage?circleId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.REMOVE_CIRCLE_ADMIN))
        {
            String tempString="../circle/memberManage?circleId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.REMOVE_CIRCLE))
        {
            String tempString="../circle/circleActivity?circleId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.DELETE_CIRCLE_NEWS))
        {
            String tempString="../circle/circleActivity?circleId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.FOCUS_BY_OTHERS))
        {
            String tempString="../focus/haveyou";
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.COMMENT_BY_OTHERS))
        {
            String tempString="../news/comment?newsId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.COMMENT_BY_OTHERS))
        {
            String tempString="../news/comment?newsId="+notice.getTargetId();
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.REPORT_REPLY_BY_ADMIN))
        {
            targetPage+="../notice/show";
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.NEWS_DELETE_BY_ADMIN))
        {
            String tempString="../news/myself";
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.COLLECTION_DELETE_BY_ADMIN))
        {
            String tempString="../collection/index";
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.CIRCLE_DELETE_BY_ADMIN))
        {
            String tempString="../circle/circleQuery";
            targetPage+=tempString;
        }
        else if(notice.getNoticeType().equals(CoreConstant.NoticeType.COMMENT_DELETE_BY_ADMIN))
        {
            String tempString="../news/comment?newsId="+notice.getTargetId();
            targetPage+=tempString;
        }

        return targetPage;
    }

}
