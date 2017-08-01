package com.puckteam.sns.core.module.controller;


import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.base.util.MD5;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.*;
import com.puckteam.sns.interfaces.core.vo.Circle;
import com.puckteam.sns.interfaces.core.vo.Member;
import com.puckteam.sns.interfaces.core.vo.News;
import com.puckteam.sns.interfaces.core.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;


/**
 * Created by PoemWhite on 16/9/9.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @Autowired
    @Qualifier("userService")
    public IUserService userService;

    @Autowired
    @Qualifier("newsService")
    private INewsService newsService;

    @Autowired
    @Qualifier("collectionService")
    private ICollectionService collectionService;

    @Autowired
    @Qualifier("memberService")
    private IMemberService memberService;

    @Autowired
    private UserUtil userUtil;

    /**
     * 首页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/")
    public String index(HttpServletRequest request) {

        User currentUser = userUtil.getCurrentUser(request);

        if (currentUser==null)
        {
            List<News> newsList = newsService.queryForNewsListForRecommend();
            request.setAttribute("newsList", newsList);
        }
        else
        {
            String userId = currentUser.getUserId();
            List<News> newsList = newsService.queryForpublicNewsListByCreateUserId(userId);
            request.setAttribute("newsList",newsList);
            request.setAttribute("collectionList",this.collectionService.findCollectionListByCreateUserId(userId));
            Member member=new Member();
            member.setUserId(userId);
            List<Circle> circleList =this.memberService.queryForCircleIsIn(member);
            request.setAttribute("circleList",circleList);
        }
        request.setAttribute("topCollectionList",this.collectionService.queryTopFiveCollectionList());
        return "index.ftl";

    }


    /**
     * 注册页
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "module/register/register.ftl";
    }

    /**
     * 检测用户名存不存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "checkUserName", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult checkUserName(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");

        if (userService.findUserByName(userName)!=null) {
            return renderJsonError("用户名已存在!");
        } else {
            return renderJsonSucc(null);
        }
    }

    @RequestMapping(value = "checkPhone", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult checkPhone(HttpServletRequest request, HttpServletResponse response) {

        String phone = request.getParameter("phone");

        if (userService.findUserByMobile(phone)!=null) {
            return renderJsonError("手机号已被注册!");
        } else {
            return renderJsonSucc(null);
        }
    }

    @RequestMapping(value = "checkEmail", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult checkEmail(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        if (userService.findUserByEmail(email)!=null) {
            return renderJsonError("邮箱号已被注册!");
        } else {
            return renderJsonSucc(null);
        }
    }


    /**
     * 登录提交
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "submitLogin", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult submitLogin(HttpServletRequest request, HttpServletResponse response) {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
            return renderJsonError("账号/密码都不能为空");
        } else {

            User qryUser = null;

            //正则表达式过滤
            String emailRegex = CoreConstant.REGEX.EMAIL;//邮箱
            String phoneRegex = CoreConstant.REGEX.PHONE;//手机

            if(name.matches(emailRegex)){
                qryUser = userService.findUserByEmail(name);

            }else if(name.matches(phoneRegex)){
                qryUser = userService.findUserByMobile(name);

            }else{
                qryUser = userService.findUserByName(name);
            }

            if(qryUser == null){
                return renderJsonError("该用户不存在");
            }

            String md5Pwd = MD5.getHashString(password);
            if(!md5Pwd.equals(qryUser.getPassword())){
                return renderJsonError("账号/密码错误");

            }else if("1".equals(qryUser.getIsLocked())) {

                return renderJsonError("该账号已被封禁，禁止登录！");
            }
            setExternalMessageAndSession(request, response, qryUser);
            return renderJsonSucc(null);
        }
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute(CoreConstant.USER_SESSION);

        Cookie cookie = new Cookie(CoreConstant.USER_COOKIE, null);
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return "redirect:/";
    }

    /**
     * 邮箱注册提交
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "emailRegister", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult email_Register(HttpServletRequest request, HttpServletResponse response) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String isArtist = request.getParameter("isArtist");
        User user = new User();
        user.setAvatar("/user/default_avatar.png");
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(MD5.getHashString(password));
        user.setNickname(nickname);
        user.setIsArtist(isArtist);
        user.setMobile(mobile);
        userService.addUser(user);
        return renderJsonSucc(null);
    }


    /**
     * 手机号注册提交
     * @param request
     * @return
     */
    @RequestMapping(value = "mobileRegister",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult mobile_Register(HttpServletRequest request, HttpServletResponse response){

        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
        String nickname=request.getParameter("nickname");
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String isArtist=request.getParameter("isArtist");
        User user=new User();
        user.setAvatar("/user/default_avatar.png");
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserName(name);
        user.setMobile(mobile);
        user.setPassword(MD5.getHashString(password));
        user.setNickname(nickname);
        user.setEmail(email);
        user.setIsArtist(isArtist);
        userService.addUser(user);
        return renderJsonSucc(null);
        }

    private void setExternalMessageAndSession(HttpServletRequest request,HttpServletResponse response,User user) {

        user=userService.setUserExtInfo(user);
        request.getSession(true).setAttribute(CoreConstant.USER_SESSION, user);
        String username = user.getUserName();
        try {
            username =URLEncoder.encode(username, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(CoreConstant.USER_COOKIE,username);
        response.addCookie(cookie);

    }
}
