package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Report;
import org.springframework.stereotype.Repository;

/**
 * Created by ZengJieHang on 2016/11/15.
 */
@Repository
public class ReportDao extends BaseDao
{
    public int insertReportItem(Report report)
    {
        String sql=" insert into sns_report" +
                " (report_type,report_desc,create_user_id,create_time,target_type,target_id,read_status)" +
                " values(?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?,?)";

        return this.executeUpdate(sql,new Object[]{
                report.getReportType(),
                report.getReportDesc(),
                report.getCreateUserId(),
                this.getSysdate(),
                report.getTargetType(),
                report.getTargetId(),
                CoreConstant.ReportReadStatus.NOT_DEAL
        });
    }
}
