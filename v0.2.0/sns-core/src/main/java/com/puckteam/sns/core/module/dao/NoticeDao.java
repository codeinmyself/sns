package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Notice;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ZengJieHang on 2016/11/14.
 */
@Repository
public class NoticeDao extends BaseDao
{
    public List<Notice> selectNoticeListByReceiveUserId(String userId)
    {
        String sql="select " +
                " notice_id," +
                " notice_content," +
                " receive_user_id," +
                " notice_type," +
                " date_format(create_time,'%Y-%m-%d') as createTime" +
                " from sns_notice " +
                " where receive_user_id=? and read_status=? order by create_time desc";

        List<Notice>noticeList=(List<Notice>)this.queryForBeanList(sql,new Object[]{userId, CoreConstant.NoticeReadStatus.NOT_READ},Notice.class);

        return noticeList;
    }

    /**
     * 通过接受的用户id查找已经阅读的通知
     * @param userId
     * @return
     */
    public List<Notice> findNoticeHaveReadByReceiveUserId(String userId)
    {
        String sql="select " +
                " notice_id," +
                " notice_content," +
                " receive_user_id," +
                " notice_type," +
                " date_format(create_time,'%Y-%m-%d') as createTime" +
                " from sns_notice " +
                " where receive_user_id=? and read_status=? order by create_time desc";

        List<Notice>noticeList=(List<Notice>)this.queryForBeanList(sql,new Object[]{userId, CoreConstant.NoticeReadStatus.HAVE_READ},Notice.class);

        return noticeList;
    }


    public int insertNotice(Notice notice)
    {
        String sql="insert into sns_notice" +
                " (notice_content,read_status," +
                " receive_user_id,notice_type,target_id,create_time)" +
                " value(?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";

        return this.executeUpdate(sql,new Object[]{
                notice.getNoticeContent(),
                CoreConstant.NoticeReadStatus.NOT_READ,
                notice.getReceiveUserId(),
                notice.getNoticeType(),
                notice.getTargetId(),
                this.getSysdate()
        });
    }

    public String getCircleNameByCircleId(String circleId)
    {
        String sql="select" +
                " circle_name" +
                " from sns_circle" +
                " where circle_id=?";

        return (String)this.queryForMap(sql,new Object[]{circleId}).get("circle_name");
    }

    public String getUserNickNameByUserId(String userId)
    {
        String sql="select" +
                " nickname" +
                " from sns_user" +
                " where user_id=?";

        return (String)this.queryForMap(sql,new Object[]{userId}).get("nickname");
    }

    public String getCircleCreatorByCircleId(String circleId)
    {
        String sql="select" +
                " user_id" +
                " from sns_member" +
                " where circle_id=? and member_type=?";

        return (String)this.queryForMap(sql,new Object[]{circleId,CoreConstant.MemberType.CREATER}).get("user_id");
    }

    public String[] getCircleAdminByCircleId(String circleId)
    {
        String sql="select " +
                " user_id" +
                " from sns_member" +
                " where circle_id=?" +
                " and ( member_type=? or member_type=? )";

        List circleAdminList=this.queryForMapList(sql,new Object[]{circleId,CoreConstant.MemberType.CREATER,CoreConstant.MemberType.MANAGER});

        String []circleAdminStringList=new String[circleAdminList.size()];
        for(int i=0;i<circleAdminStringList.length;++i)
        {
            Map map=(Map)circleAdminList.get(i);
            circleAdminStringList[i]=(String)map.get("user_id");
        }
        return circleAdminStringList;
    }

    /**
     * 通过动态ID获取动态内容
     * @param newsId
     * @return
     */
    public String getNewsContentByNewsId(String newsId)
    {
        String sql="select " +
                " content " +
                " from sns_news" +
                " where news_id=?";

        return this.queryForMap(sql,new Object[]{newsId}).get("content").toString();
    }

    /**
     * 通过评论Id获取评论内容
     * @param commentId
     * @return
     */
    public String getCommentContentByCommentId(String commentId)
    {
        String sql="select " +
                " content " +
                " from sns_comment" +
                " where comment_id=?";

        return this.queryForMap(sql,new Object[]{commentId}).get("content").toString();
    }
    
    public boolean isNoticeIdExists(String userId,String noticeId)
    {
        String sql="select count(*) as numbers "+
                " from sns_notice "+
                " where receive_user_id=? and notice_id=?";

        if(Integer.parseInt(this.queryForMap(sql,new Object[]{userId,noticeId}).get("numbers").toString())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Notice findNoticeByNoticeId(String noticeId)
    {
        String sql="select " +
                " notice_id," +
                " notice_content," +
                " receive_user_id," +
                " notice_type," +
                " target_id,"+
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime" +
                " from sns_notice " +
                " where notice_id=?";

        return (Notice)this.queryForBean(sql,new Object[]{noticeId},Notice.class);
    }

    /**
     * 根据newsId查找创建者的id
     * @param newsId
     * @return
     */
    public Map getUserIdByNewId(String newsId)
    {
        String sql="select create_user_id as createUserId" +
                " from sns_news" +
                " where news_id=?";

        return this.queryForMap(sql,new Object[]{newsId});
    }

    /**
     * 根据commentId查找创建者的id
     * @param commentId
     * @return
     */
    public Map getCreateUserIdByCommentId(String commentId)
    {
        String sql="select create_user_id as createUserId" +
                " from sns_comment" +
                " where comment_id=?";

        return this.queryForMap(sql,new Object[]{commentId});
    }

    /**
     * 增加通知阅读状态
     * @param notice
     * @return
     */
    public int updateNoticeStatueWhileSelecting(Notice notice)
    {
        String sql="update sns_notice set read_status=?" +
                " where notice_id=?";

        return this.executeUpdate(sql,new Object[]{CoreConstant.NoticeReadStatus.HAVE_READ,notice.getNoticeId()});
    }
}
