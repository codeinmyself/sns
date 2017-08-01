package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Circle;
import com.puckteam.sns.interfaces.core.vo.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by asus1 on 2016/11/1.
 */
@Repository
public class MemberDao extends BaseDao {
    /**
     * 动态集合表信息提交
     * @param member
     * @return
     */
    public void memberInformationInsert(Member member){
        String sql = "insert into sns_member(" +
                "member_id,circle_id,user_id,member_type,create_time" +
                ") values (?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";
        this.executeUpdate(sql, new Object[]{
                member.getMemberId(),
                member.getCircleId(),
                member.getUserId(),
                member.getMemberType(),

                this.getSysdate(),
        });
    }


    public List<Circle>queryForCircleIsIn(Member member)
    {
        String sql = "select sns_circle.* " +
                " from sns_member,sns_circle "+
                " where sns_member.user_id=? "+
                " and sns_circle.circle_id=sns_member.circle_id " +
                " and sns_circle.status=?";
        List<Circle> circleList=this.queryForBeanList(sql,new Object[]{member.getUserId(), CoreConstant.Status.NORMAL},Circle.class);

        return circleList;
    }
    /**
     * 加入的艺术圈查询
     */
    public List<Circle> queryForCircleJoin(Member member) {
        String sql = "select sns_circle.* " +
                " from sns_member,sns_circle "+
                " where sns_member.user_id=? "+
                " and sns_circle.circle_id=sns_member.circle_id "+
                " and sns_member.member_type<>'01'";
        List<Circle> joinList=this.queryForBeanList(sql,new Object[]{member.getUserId()},Circle.class);

        return joinList;
    }


    /**
     * 根据circleId和userId退出艺术圈
     * @param member
     */
    public void deleteMemberByCircleIdAndUserId(Member member){
        String sql = "delete from sns_member where circle_id =? and user_id=?";
        this.executeUpdate(sql,new Object[]{
                member.getCircleId(),
                member.getUserId()
        });
    }
    /**
     * 根据circleId和userId查询member
     * @param member
     */
    public Member queryMemberByCircleIdAndUserId(Member member){
        String sql = "select *"+
                " from sns_member"+
                " where circle_id=? "+
                " and user_id=? ";
        Member member1=(Member)this.queryForBean(sql,new Object[]{member.getCircleId(),member.getUserId()},Member.class);
        return member1;
    }
    /**
     * 根据memberId删除艺术圈成员
     * @param member
     */
    public void deleteMemberByMemberId(String member){
        String sql = "delete from sns_member where member_id=?";
        this.executeUpdate(sql,new Object[]{member});
    }

    /**
     * 成员查询返回member类的成员
     */
    public List<Member> queryForCircleMember(Member member) {
        String sql = "select sns_member.member_id as member_id,sns_member.member_type as member_type,sns_user.nickname as nickname," +
                " sns_user.avatar as avatar "+
                " from sns_member,sns_user "+
                " where sns_member.circle_id=? "+
                " and sns_member.user_id=sns_user.user_id";

        List<Member> joinUserList=this.queryForBeanList(sql,new Object[]{member.getCircleId()},Member.class);

        return joinUserList;
    }


    /**
     * 成员权限修改信息提交
     * @param member
     * @return
     */
    public void updateMemberByMemberId(Member member){
        String sql = "update sns_member set "+
                " member_type=? "+
                " where member_id=?";
        this.executeUpdate(sql,new Object[]{
                member.getMemberType(),
                member.getMemberId()});
    }

    /**
     * 通过memberId查找member
     * @param memberId
     * @return
     */
    public Member queryMemberByMemberId(String memberId)
    {
        String sql="select *"+
                " from sns_member"+
                " where member_id=?";
        return (Member)this.queryForBean(sql,new Object[]{memberId},Member.class);
    }

}



