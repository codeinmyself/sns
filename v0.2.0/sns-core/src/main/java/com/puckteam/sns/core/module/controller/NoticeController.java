package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.core.support.aop.annotation.UserSecurity;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.INoticeService;
import com.puckteam.sns.interfaces.core.vo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZengJieHang on 2016/11/14.
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController extends BaseController
{
    @Autowired
    private UserUtil userUtil;

    @Autowired
    private INoticeService noticeService;

    /**
     * 跳转到通知页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/show")
    @UserSecurity
    public String turnToNoticePage(HttpServletRequest request)
    {
        String userId = userUtil.getCurrentUser(request).getUserId();
        request.setAttribute("noticeList",noticeService.findNoticeByReceiveUserId(userId));
        return "module/notice/notice.ftl";
    }

    /**
     * 跳转到通知页面(已经阅读的通知)
     * @param request
     * @return
     */
    @RequestMapping(value = "/showHasRead")
    @UserSecurity
    public String turnToNoticeHaveReadPage(HttpServletRequest request)
    {
        String userId = userUtil.getCurrentUser(request).getUserId();
        request.setAttribute("noticeList",noticeService.findNoticeHaveReadByReceiveUserId(userId));
        return "module/notice/noticeHaveRead.ftl";
    }

    /**
     * 根据通知的类型跳转到相应页面
     * @param request
     * @param reponse
     * @return
     */
    @RequestMapping(value = "/read")
    @UserSecurity
    public String haveReadNotice(HttpServletRequest request,HttpServletResponse reponse)
    {
        String userId=userUtil.getCurrentUser(request).getUserId();
        String noticeId=request.getParameter("noticeId");


        if(noticeId==null||noticeId==""||!noticeService.isNoticeIdExists(userId,noticeId))
        {
            return "error.ftl";
        }

        Notice notice=this.noticeService.findNoticeByNoticeId(noticeId);
        if(notice!=null)
        {
            return this.noticeService.turnToTargetPageByNotice(notice);
        }
        else
        {
            return "error.ftl";
        }

    }

    /*@RequestMapping(value="/delete")
    @ResponseBody
    public JsonResult deleteNotice(HttpServletRequest request,HttpServletResponse response)
    {
        String noticeId=request.getParameter("noticeId");

        if(this.noticeService.haveReadNotice(noticeId)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("更新数据库失败!");
        }
    }*/

}
