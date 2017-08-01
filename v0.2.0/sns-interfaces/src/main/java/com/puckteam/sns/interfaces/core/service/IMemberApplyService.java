package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.MemberApply;

import java.util.List;

/**
 * Created by asus1 on 2016/11/1.
 */
public interface IMemberApplyService {
    /**
     * 申请信息提交
     * @param memberApply
     * @return
     */
    public void memberApplyInformationInsert(MemberApply memberApply);

    /**
     * 申请信息查询
     * @param memberApply
     * @return
     */
    public List<MemberApply> queryForMemberApplyInformation(MemberApply memberApply);

    /**
     * 审核申请
     * @param memberApply
     * @return
     */
    public void checkApplyInformationInsert(MemberApply memberApply);
}
