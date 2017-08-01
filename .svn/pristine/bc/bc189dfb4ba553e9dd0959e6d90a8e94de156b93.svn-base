package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.MemberApply;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus1 on 2016/11/1.
 */
@Repository
public class MemberApplyDao extends BaseDao {

    /**
     * 动态集合表信息提交
     * @param memberApply
     * @return
     */
    public void memberApplyInformationInsert(MemberApply memberApply){
        String sql = "insert into sns_member_apply(" +
                "apply_id,circle_id,apply_user_id,apply_time,apply_desc,apply_status" +
                ") values (?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?)";
        this.executeUpdate(sql, new Object[]{
                memberApply.getApplyId(),
                memberApply.getCircleId(),
                memberApply.getApplyUserId(),
                this.getSysdate(),
                memberApply.getApplyDesc(),
                memberApply.getApplyStatus()
        });
    }

    /**
     * 申请信息查询
     * @param memberApply
     * @return
     */
    public List<MemberApply> queryForMemberApplyInformation(MemberApply memberApply){
        String sql = "select sns_user.nickname as nickname," +
                " sns_user.avatar as avatar, "+
                " sns_member_apply.circle_id as circle_id,sns_member_apply.apply_user_id as apply_user_id,"+
                " sns_member_apply.apply_desc,sns_member_apply.apply_status as apply_status, " +
                "date_format(sns_member_apply.apply_time,'%Y-%m-%d %H:%i:%s') as applyTime " +
                " from sns_member_apply,sns_user where sns_member_apply.circle_id=?"+
                " and sns_user.user_id=sns_member_apply.apply_user_id";
        List<MemberApply> memberApplyList=this.queryForBeanList(sql,new Object[]{memberApply.getCircleId()},MemberApply.class);
        return memberApplyList;
    }

    /**
     * 回复申请提交
     * @param memberApply
     * @return
     */
    public void checkApplyInformationInsert(MemberApply memberApply){
        String sql = "update sns_member_apply set "+
                " apply_status=?,"+
                " verify_user_id=?,"+
                " verify_time=?"+
                " where circle_id=?" +
                " and apply_user_id=?";
        this.executeUpdate(sql, new Object[]{
                memberApply.getApplyStatus(),
                memberApply.getVerifyUserId(),
                this.getSysdate(),
                memberApply.getCircleId(),
                memberApply.getApplyUserId()
        });
    }
}
