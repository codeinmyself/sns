package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.module.dao.ReportDao;
import com.puckteam.sns.interfaces.core.service.IReportService;
import com.puckteam.sns.interfaces.core.vo.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZengJieHang on 2016/11/15.
 */
@Service("reportService")
@Transactional
public class ReportService implements IReportService
{
    @Autowired
    private ReportDao reportDao;

    public int addReport(Report report)//用户举报
    {
        return this.reportDao.insertReportItem(report);
    }

}
