package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.interfaces.core.service.IAdminService;
import com.puckteam.sns.interfaces.core.service.INoticeService;
import com.puckteam.sns.interfaces.core.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by ZengJieHang on 2016/11/15.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController
{
    @Autowired
    @Qualifier("adminService")
    private IAdminService adminService;

    @Autowired
    @Qualifier("noticeService")
    private INoticeService noticeService;

    /**
     * 跳转到管理员登录页面
     * @param request
     * @return
     */
    @RequestMapping(value = "")
    public String turnToAdminLogin(HttpServletRequest request)
    {
        return "module/admin/login.ftl";
    }

    /**
     * 跳转到举报主页
     * @param request
     * @return
     */
    @RequestMapping(value = "/report")
    public String turnToReportPage(HttpServletRequest request)
    {
        List<Report> reportList=this.adminService.findReportList();
        request.setAttribute("reportList",reportList);
        return "module/admin/report.ftl";
    }

    /**
     * 跳转到用户列表页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/users")
    public String turnToUserPage(HttpServletRequest request)
    {
        List<User>userList=this.adminService.findUserList();
        request.setAttribute("userList",userList);
        return "module/admin/users.ftl";
    }

    /**
     * 跳转到关键字页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/keys")
    public String turnToKeyPage(HttpServletRequest request)
    {
        List<SensitiveKey>sensitiveKeyList=this.adminService.findSensitiveKeys();
        request.setAttribute("sensitiveKeyList",sensitiveKeyList);
        return "module/admin/key.ftl";
    }

    /**
     * 添加敏感词
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addKey")
    @ResponseBody
    public JsonResult addKey(HttpServletRequest request,HttpServletResponse response)
    {
        String key = request.getParameter("key");
        if (key == null || key.equals(""))
        {
            return renderJsonError("敏感词不能为空");
        }
        else if(this.adminService.isSensitiveKeyExist(key))
        {
            return renderJsonError("敏感词已存在!");
        }

        SensitiveKey sensitiveKey=new SensitiveKey();
        sensitiveKey.setWordName(key);
        sensitiveKey.setWordId(UUID.randomUUID().toString().replace("-", ""));
        if(this.adminService.addKey(sensitiveKey)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库失败!");
        }
    }

    /**
     * 删除敏感词
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/removeKey")
    @ResponseBody
    public JsonResult removeKey(HttpServletRequest request,HttpServletResponse response)
    {
        String key = request.getParameter("key");
        if (key == null || key.equals(""))
        {
            return renderJsonError("敏感词不能为空");
        }
        else if(!this.adminService.isSensitiveIdExist(key))
        {
            return renderJsonError("敏感词不存在!");
        }

        if(this.adminService.removeKey(key)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("删除数据库失败!");
        }
    }

    /**
     * 管理员登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public JsonResult adminLogin(HttpServletRequest request, HttpServletResponse response)
    {
        String account=request.getParameter("account");
        String password=request.getParameter("password");

        if(this.adminService.adminLogin(account,password))
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("管理员账号或密码不正确!");
        }
    }

    /**
     * 封号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/lock")
    @ResponseBody
    public JsonResult lockUsers(HttpServletRequest request, HttpServletResponse response)
    {
        String userId[]=request.getParameterValues("userId");
          for(int i=0;i<userId.length;++i)
        {
            if(this.adminService.lockUser(userId[i])<=0)
            {
                return renderJsonError("修改数据库出错!");
            }
        }
        return renderJsonSucc(null);
    }

    /**
     * 查看举报的详细信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/reportDetail",method = RequestMethod.GET)
    public String turnToReportDetail(HttpServletRequest request)
    {
        String targetType=request.getParameter("targetType");
        String targetId=request.getParameter("targetId");
        String reportId=request.getParameter("reportId");

        if(targetType.equals(CoreConstant.ReportTargetType.USER_REPORT))
        {
            request.setAttribute("reportUser",this.adminService.findUserByUserId(targetId));
        }
        else if(targetType.equals(CoreConstant.ReportTargetType.NEWS_REPORT))
        {
            request.setAttribute("news",this.adminService.findNewsByNewsId(targetId));
        }
        else if(targetType.equals(CoreConstant.ReportTargetType.CIRCLE_REPORT))
        {
            request.setAttribute("circle",this.adminService.findCircleByCircleId(targetId));
        }
        else if(targetType.equals(CoreConstant.ReportTargetType.COLLECTION_REPORT))
        {
            request.setAttribute("collection",this.adminService.findCollectionByCollectionId(targetId));
        }
        else if(targetType.equals(CoreConstant.ReportTargetType.COMMENT_REPORT))
        {
            Comment comment=this.adminService.findCommentByCommentId(targetId);
            request.setAttribute("comment",comment);
        }
        request.setAttribute("report",this.adminService.findReportByReportId(reportId));

        return "module/admin/detail.ftl";
    }

    /**
     * 在详细举报页对用户封号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detailLock")
    @ResponseBody
    public JsonResult lockUsersByDetailPage(HttpServletRequest request, HttpServletResponse response)
    {
        String userId=request.getParameter("userId");
        String reportId=request.getParameter("reportId");

        if(this.adminService.lockUser(userId)<=0)
        {
            return renderJsonError("修改数据库出错!");
        }
        else
        {
            if(this.adminService.haveDealReport(reportId)>0)
            {
                return renderJsonSucc(null);
            }
            else
            {
                return renderJsonError("修改数据库出错!");
            }
        }
    }


    /**
     * 忽略举报
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detailIgnore")
    @ResponseBody
    public JsonResult ignoreReport(HttpServletRequest request, HttpServletResponse response)
    {
        String reportId=request.getParameter("reportId");

        if(this.adminService.ignoreReport(reportId)>0) {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("修改数据库出错!");
        }
    }

    /**
     * 在详细举报页删除作品集、动态、艺术圈、评论
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detailDelete")
    @ResponseBody
    public JsonResult deleteContent(HttpServletRequest request,HttpServletResponse response)
    {
        String targetType=request.getParameter("targetType");
        String targetId=request.getParameter("targetId");
        String reportId=request.getParameter("reportId");

        if(targetType.equals(CoreConstant.ReportTargetType.COLLECTION_REPORT)||
                targetType.equals(CoreConstant.ReportTargetType.CIRCLE_REPORT)||
                targetType.equals(CoreConstant.ReportTargetType.NEWS_REPORT)||
                targetType.equals(CoreConstant.ReportTargetType.COMMENT_REPORT))
        {
            if(this.adminService.deleteNewsOrCollectionOrCircleOrComment(targetType,targetId)<=0)
            {
                return renderJsonError("修改数据库出错!");
            }
            else
            {
                if(targetType.equals(CoreConstant.ReportTargetType.COLLECTION_REPORT))
                {
                    Collection collection=this.adminService.findCollectionByCollectionId(targetId);
                    if(!this.noticeService.collectionDeleteByAdminNotice(collection))
                    {
                        return renderJsonError("插入数据库出错!");
                    }
                }
                else if(targetType.equals(CoreConstant.ReportTargetType.CIRCLE_REPORT))
                {
                    Circle circle=this.adminService.findCircleByCircleId(targetId);
                    if(!this.noticeService.circleDeleteByAdminNotice(circle))
                    {
                        return renderJsonError("插入数据库出错!");
                    }
                }
                else if(targetType.equals(CoreConstant.ReportTargetType.NEWS_REPORT))
                {
                    News news=this.adminService.findNewsByNewsId(targetId);
                    if(!this.noticeService.newsDeleteByAdminNotice(news))
                    {
                        return renderJsonError("插入数据库出错!");
                    }
                }
                else if(targetType.equals(CoreConstant.ReportTargetType.COMMENT_REPORT))
                {
                    Comment comment=this.adminService.findCommentByCommentId(targetId);
                    if(!this.noticeService.commentDeleteByAdminNotice(comment))
                    {
                        return renderJsonError("插入数据库出错!");
                    }
                }
            }
        }

        if(this.adminService.haveDealReport(reportId)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("修改数据库出错!");
        }
    }

    /**
     * 在详细举报页回复举报人
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/detailReply")
    @ResponseBody
    public JsonResult replyReport(HttpServletRequest request,HttpServletResponse response)
    {
        String reportId=request.getParameter("reportId");
        String reportReply=request.getParameter("replyText");

        if(this.adminService.replyReport(reportId,reportReply)>0)
        {
            Report report=this.adminService.findReportByReportId(reportId);
            if(this.noticeService.reportReplyByAdminNotice(report)) {
                return renderJsonSucc(null);
            }
            else
            {
                return renderJsonError("插入数据库出错");
            }
        }
        else
        {
            return renderJsonError("插入数据库出错");
        }
    }
}
