package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.*;
import com.puckteam.sns.interfaces.core.vo.Collection;
import com.puckteam.sns.interfaces.core.vo.News;
import com.puckteam.sns.interfaces.core.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZengJieHang on 2016/12/18.
 */
@Controller
@RequestMapping(value = "/other")
public class OtherController extends BaseController
{
    @Autowired
    @Qualifier("newsService")
    private INewsService newsService;

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("collectionService")
    private ICollectionService collectionService;

    @Autowired
    @Qualifier("focusService")
    private IFocusService focusService;

    @Autowired
    @Qualifier("focusGroupService")
    private IFocusGroupService focusGroupService;

    @Autowired
    private UserUtil userUtil;


    /**
     * 他人动态展示
     * @param request
     * @return
             */
    @RequestMapping(value = "/otherNewsShow")
    public String findNewsInformation(HttpServletRequest request){
        String createUserId = request.getParameter("createUserId");
        List<User> userList = userService.queryForOneInformationByUserId(createUserId);
        List<News> newsList ;
        request.setAttribute("userList", userList);
        if(createUserId.equals(userUtil.getCurrentUser(request).getUserId())){
            newsList = newsService.queryForBeanListNewsListByUserId(createUserId);
        }
        else {
            newsList = newsService.queryForBeanListOtherNewsListByCreateUserId(createUserId, userUtil.getCurrentUser(request).getUserId());
        }
        boolean hasFocus=this.focusService.hasFocused(userUtil.getCurrentUser(request).getUserId(),createUserId);
        request.setAttribute("hasFocus",hasFocus);
        request.setAttribute("newsList", newsList);
        request.setAttribute("focusGroupList",this.focusGroupService.findSnsFocusGroupListByUserId(userUtil.getCurrentUser(request).getUserId()));
        return "module/other/otherNewsShow.ftl";
    }

    /**
     * 他人作品集展示
     * @param request
     * @return
     */
    @RequestMapping(value = "/otherCollectionShow")
    public String findCollectionInformation(HttpServletRequest request){
        String createUserId = request.getParameter("createUserId");
        List<User> userList = userService.queryForOneInformationByUserId(createUserId);
        request.setAttribute("userList",userList);
        if(createUserId.equals(userUtil.getCurrentUser(request).getUserId())){
            List<Collection> collectionList = collectionService.findCollectionListByCreateUserId(createUserId);
            request.setAttribute("collectionList",collectionList);
        }
        else {
            List<Collection> collectionList = collectionService.findOtherCollectionListByCreateUserId(createUserId, userUtil.getCurrentUser(request).getUserId());
            request.setAttribute("collectionList", collectionList);
        }
        request.setAttribute("hasFocus",this.focusService.hasFocused(userUtil.getCurrentUser(request).getUserId(),createUserId));
        request.setAttribute("focusGroupList",this.focusGroupService.findSnsFocusGroupListByUserId(userUtil.getCurrentUser(request).getUserId()));
        return "module/other/otherCollectionShow.ftl";
    }
}
