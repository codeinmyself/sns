package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.exception.SnsBaseRuntimeException;
import com.puckteam.sns.interfaces.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by PoemWhite on 16/9/9.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    /**
     * 首页
     * @param request
     * @return
     */
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request) {

        userService.findUserByUserId("1");

//        return "index.jsp";

        return "index.ftl";
    }

    /**
     * 登录页
     * @param request
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(HttpServletRequest request){

        return "module/login/login.ftl";
    }

}
