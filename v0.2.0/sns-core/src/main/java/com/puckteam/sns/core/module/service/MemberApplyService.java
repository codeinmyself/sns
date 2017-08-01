package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.module.dao.MemberApplyDao;
import com.puckteam.sns.interfaces.core.service.IMemberApplyService;
import com.puckteam.sns.interfaces.core.vo.MemberApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by asus1 on 2016/11/1.
 */
@Service("memberApplyService")
@Transactional
public class MemberApplyService implements IMemberApplyService{

    @Autowired
    private MemberApplyDao memberApplyDao;

    /**
     * 申请信息提交
     * @param memberApply
     * @return
     */
    public void memberApplyInformationInsert(MemberApply memberApply) {
        memberApplyDao.memberApplyInformationInsert(memberApply);
    }
    /**
     * 申请信息查询
     * @param memberApply
     * @return
     */
    public List<MemberApply> queryForMemberApplyInformation(MemberApply memberApply) {

        return memberApplyDao.queryForMemberApplyInformation(memberApply);
    }
    /**
     * 回复申请
     * @param memberApply
     * @return
     */
    public void checkApplyInformationInsert(MemberApply memberApply) {
        memberApplyDao.checkApplyInformationInsert(memberApply);
    }
}
