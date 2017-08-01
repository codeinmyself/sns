package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.Circle;
import com.puckteam.sns.interfaces.core.vo.Member;
import com.puckteam.sns.interfaces.core.vo.User;

import java.util.List;

/**
 * Created by asus1 on 2016/11/1.
 */
public interface IMemberService {
    /**
     * 艺术圈信息提交
     * @param member
     * @return
     */
    public void memberInformationInsert(Member member);


    public List<Circle>queryForCircleIsIn(Member member);
    /**
     * 加入的艺术圈查询
     * @return
     */
    public List<Circle> queryForCircleJoin(Member member);

    /**
     * 退出艺术圈
     * @param member
     */
    public void deleteMemberByCircleIdAndUserId(Member member);

    /**
     * 根据circleId和userId查询member
     * @param member
     */
    public Member queryMemberByCircleIdAndUserId(Member member);
    /**
     * 根据memberId删除艺术圈成员
     * @param member
     */
    public void deleteMemberByMemberId(String member);


    /**
     * 成员查询返回member类的成员
     * @param member
     * @return
     */
    public List<Member> queryForCircleMember(Member member) ;

    /**
     * 保存成员修改权限信息
     * @param member
     * @return
     */
    public void updateMemberByMemberId(Member member);

    /**
     * 通过memberId查找member
     * @param memberId
     * @return
     */
    public Member queryMemberByMemberId(String memberId);

}
