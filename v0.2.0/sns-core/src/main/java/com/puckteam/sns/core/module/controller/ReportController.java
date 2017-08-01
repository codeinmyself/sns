package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.IReportService;
import com.puckteam.sns.interfaces.core.vo.Report;
import com.puckteam.sns.interfaces.core.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by ZengJieHang on 2016/11/15.
 */
@Controller
@RequestMapping(value = "/report")
public class ReportController extends BaseController
{
    @Autowired
    @Qualifier("reportService")
    private IReportService reportService;

    @Autowired
    private UserUtil userUtil;

    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult addReport(HttpServletRequest request, HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }
        String reportType=request.getParameter("reportType");
        String reportDesc=request.getParameter("reportDesc");
        String targetType=request.getParameter("targetType");
        String targetId=request.getParameter("targetId");

        Report report=new Report();
        report.setReportType(reportType);
        report.setReportDesc(reportDesc);
        report.setTargetId(targetId);
        report.setTargetType(targetType);
        report.setCreateUserId(currentUser.getUserId());
        if(this.reportService.addReport(report)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库出错");
        }
    }
}
