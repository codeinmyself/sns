package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.aop.annotation.UserSecurity;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.IFocusGroupService;
import com.puckteam.sns.interfaces.core.service.IFocusService;
import com.puckteam.sns.interfaces.core.service.INoticeService;
import com.puckteam.sns.interfaces.core.service.IUserService;
import com.puckteam.sns.interfaces.core.vo.SnsFocus;
import com.puckteam.sns.interfaces.core.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * Created by ZengJieHang on 2016/10/14.
 */
@Controller
@RequestMapping(value="/focus")
public class FocusController extends BaseController
{
    @Autowired
    private UserUtil userUtil;

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("focusService")
    private IFocusService focusService;

    @Autowired
    @Qualifier("focusGroupService")
    private IFocusGroupService focusGroupService;

    @Autowired
    @Qualifier("noticeService")
    private INoticeService noticeService;

    //跳转到关注列表页面
    @RequestMapping(value = "/focus")
    @UserSecurity
    public String turnToFocusPage(HttpServletRequest request)
    {
        //获取用户Id
        String userId = userUtil.getCurrentUser(request).getUserId();
        //获取关注列表
        List<SnsFocus>snsFocusList=focusService.findSnsFocusByUserId(userId);
        request.setAttribute("snsFocusList",snsFocusList);
        return "module/focus/focus.ftl";
    }


    //跳转到粉丝页面
    @RequestMapping(value = "/haveyou")
    @UserSecurity
    public String turnToFansPage(HttpServletRequest request)
    {
        String userId = userUtil.getCurrentUser(request).getUserId();
        //获取粉丝列表
        request.setAttribute("snsFocusList",focusService.findSnsFocusByFocusUserId(userId));
        request.setAttribute("snsFocusGroupList",focusGroupService.findSnsFocusGroupListByUserId(userId));
        return "module/focus/haveyou.ftl";
    }

    //关注其他用户
    @RequestMapping(value="/add")
    @ResponseBody
    public JsonResult focusOthers(HttpServletRequest request,HttpServletResponse response)
    {

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String focusUserId=request.getParameter("focusUserId");//被关注的用户Id
        String groupId=request.getParameter("groupId"); //分组Id

        if(focusUserId==null||focusUserId.equals("")||!this.focusService.isFocusUserIdExist(focusUserId))
        {
            return renderJsonError("关注的用户ID不存在");
        }
        else if(this.focusService.hasFocused(currentUser.getUserId(),focusUserId))
        {
            return  renderJsonError("已经关注用户"+focusUserId);
        }

        SnsFocus snsFocus = new SnsFocus();
        snsFocus.setUserId(currentUser.getUserId());
        snsFocus.setFocusId(UUID.randomUUID().toString().replace("-", ""));
        snsFocus.setFocusUserId(focusUserId);
        if(!(groupId==null||groupId.equals("")))
        {
            snsFocus.setGroupId(groupId);
        }

        if(focusService.addFocusItem(snsFocus)>0)
        {
            if(noticeService.focusByOthersNotice(snsFocus))//插入通知
            {
                currentUser=userService.setUserExtInfo(userUtil.getCurrentUser(request));
                request.getSession(true).setAttribute(CoreConstant.USER_SESSION, currentUser);
                return renderJsonSucc(null);
            }
            else
            {
                return renderJsonError("插入数据库出错！");
            }
        }
        else
        {
            return renderJsonError("插入数据库出错！");
        }
    }

    //取消关注
    @RequestMapping(value = "/cancel")
    @ResponseBody
    public JsonResult cancelFocusOthers(HttpServletRequest request,HttpServletResponse response)
    {

        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String focusId=request.getParameter("focusId");//获取取消的focusId

        if(focusId==null||focusId.equals(""))
        {
            return renderJsonError("关注ID不能为空");
        }
        else if(!focusService.isFocusIdExist(focusId))
        {
            return renderJsonError("没有关注该用户，不能取消关注");
        }

        if(focusService.cancelFocusItem(focusId)>0)
        {
            currentUser = userService.setUserExtInfo(currentUser);
            request.getSession(true).setAttribute(CoreConstant.USER_SESSION, currentUser);
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("删除数据库出错！");
        }
    }

    @RequestMapping(value = "/cancelByUserId")
    @ResponseBody
    public JsonResult cancelFocusOthersByUserId(HttpServletRequest request,HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String userId=request.getParameter("userId");//获取取消的
        String focusUserId=request.getParameter("focusUserId");

        if(focusService.cancelFocusItemByUserId(userId,focusUserId)>0)
        {
            currentUser = userService.setUserExtInfo(currentUser);
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("删除数据库出错！");
        }
    }

    //移动关注到其他分组
    @RequestMapping(value = "/group")
    @ResponseBody
    public JsonResult removeFocusItemToGroup(HttpServletRequest request,HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String userId = currentUser.getUserId();
        String focusId=request.getParameter("focusId"); //关注Id
        String groupId=request.getParameter("groupId"); //分组Id

        if(focusId==null||focusId.equals("")||!focusService.isFocusIdExist(focusId))
        {
            return renderJsonError("没有关注该用户，不能移动分组");
        }
        else if(groupId==null||groupId.equals("")||!focusService.isGroupIdExist(userId,groupId))
        {
            return renderJsonError("用户的分组不存在");
        }

        if(focusService.removeFocusItemToGroup(focusId,groupId)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("更新数据库出错！");
        }
    }
}