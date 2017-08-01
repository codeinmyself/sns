package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.core.module.service.FocusGroupService;
import com.puckteam.sns.core.support.aop.annotation.UserSecurity;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.IFocusService;
import com.puckteam.sns.interfaces.core.vo.SnsFocus;
import com.puckteam.sns.interfaces.core.vo.SnsFocusGroup;
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
 * Created by ZengJieHang on 2016/10/25.
 */
@Controller
@RequestMapping(value = "/focusGroup")
public class FocusGroupController extends BaseController
{
    @Autowired
    private UserUtil userUtil;

    @Autowired
    @Qualifier("focusGroupService")
    private FocusGroupService focusGroupService;

    @Autowired
    @Qualifier("focusService")
    private IFocusService focusService;

    //获取分组列表
    @RequestMapping(value = "/group")
    @UserSecurity
    public String turnToGroupPage(HttpServletRequest request)
    {
        String userId = userUtil.getCurrentUser(request).getUserId();
        //分组列表
        List<SnsFocusGroup> snsFocusGroupList=focusGroupService.findSnsFocusGroupListByUserId(userId);
        request.setAttribute("snsFocusGroupList",snsFocusGroupList);
        request.setAttribute("untitledFocusNumber",focusService.findUntitledFocusNumberByUserId(userId));

        List<SnsFocus> snsFocusList=focusService.findSnsFocusByUserId(userUtil.getCurrentUser(request).getUserId());
        request.setAttribute("snsFocusList",snsFocusList);
        return "module/focus/focusGroup.ftl";
    }

    //新建分组
    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult createNewGroup(HttpServletRequest request, HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String userId = currentUser.getUserId();
        String groupName=request.getParameter("name");//获取分组名称

        if(groupName==null||groupName.equals(""))
        {
            return renderJsonError("分组名称不能为空!");
        }
        else if(this.focusGroupService.isGroupNameExist(userId,groupName))
        {
            return renderJsonError("分组名称已经存在!");
        }

        SnsFocusGroup snsFocusGroup=new SnsFocusGroup();
        snsFocusGroup.setCreateUserId(userId);
        snsFocusGroup.setGroupName(groupName);
        snsFocusGroup.setGroupId(UUID.randomUUID().toString().replace("-", ""));

        if(focusGroupService.createNewGroup(snsFocusGroup)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("插入数据库出错");
        }
    }

    //修改分组名称
    @RequestMapping(value="/save")
    @ResponseBody
    public JsonResult modifyGroup(HttpServletRequest request,HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String userId = currentUser.getUserId();
        String groupId=request.getParameter("groupId"); //分组Id
        String groupName=request.getParameter("groupName");//修改后的分组名称

        if(groupId==null||groupId.equals("")||!focusGroupService.isGroupIdExist(userId,groupId))
        {
            return renderJsonError("分组不存在!");
        }
        else if(groupName==null||groupName.equals(""))
        {
            return renderJsonError("分组名称不能为空!");
        }
        else if(this.focusGroupService.isGroupNameExist(userId,groupName))
        {
            return renderJsonError("分组名称已经存在!");
        }

        if(focusGroupService.modifyGroup(groupId,groupName)>0)
        {
            return renderJsonSucc(null);
        }
        else
        {
            return renderJsonError("更新数据库出错");
        }
    }

    //删除分组
    @RequestMapping(value="/delete")
    @ResponseBody
    public JsonResult deleteGroupByGroupId(HttpServletRequest request,HttpServletResponse response)
    {
        User currentUser = userUtil.getCurrentUser(request);
        if(currentUser == null){
            return renderJsonUnLogin();
        }

        String userId = currentUser.getUserId();
        String userName=currentUser.getUserName();
        String groupId=request.getParameter("groupId"); //分组Id

        if(groupId==null||groupId.equals(""))
        {
            return renderJsonError("分组Id不存在!");
        }
        else if(!this.focusGroupService.isGroupIdExist(userId,groupId))
        {
            return renderJsonError("用户"+userName+"没有分组"+groupId);
        }

        if(focusService.updateFocusGroupId(groupId)>=0)
        {
            if (focusGroupService.deleteGroup(groupId) > 0) {
                return renderJsonSucc(null);
            }
            else
            {
                return renderJsonError("修改数据库出错");
            }
        }
        else
        {
            return renderJsonError("删除数据库出错");
        }
    }
}
