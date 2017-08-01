package com.puckteam.sns.core.module.controller;

import com.puckteam.sns.base.json.JsonResult;
import com.puckteam.sns.base.util.ImageUtil;
import com.puckteam.sns.base.util.MD5;
import com.puckteam.sns.base.util.SequenceUtil;
import com.puckteam.sns.base.util.StringClass;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.aop.annotation.UserSecurity;
import com.puckteam.sns.core.support.mvc.controller.BaseController;
import com.puckteam.sns.core.support.util.ImageUploadCallback;
import com.puckteam.sns.core.support.util.ImageUploadUtil;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.ICollectionService;
import com.puckteam.sns.interfaces.core.service.IMemberService;
import com.puckteam.sns.interfaces.core.service.INewsService;
import com.puckteam.sns.interfaces.core.service.IUserService;
import com.puckteam.sns.interfaces.core.vo.Circle;
import com.puckteam.sns.interfaces.core.vo.Member;
import com.puckteam.sns.interfaces.core.vo.News;
import com.puckteam.sns.interfaces.core.vo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by yyp on 2016/10/26.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    static Logger logger = LogManager.getLogger();

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

    @Autowired
    private ImageUploadUtil imageUploadUtil;

    /**
     * 设置页面
     * @param request
     * @return
     */
    @UserSecurity
    @RequestMapping(value = "setting")
    public String setting(HttpServletRequest request){
        return "module/userInfo/userInfo.ftl";
    }

    /**
     * 自己动态
     * @param request
     * @return
     */
    @UserSecurity
    @RequestMapping(value = "findNews")
    public String findNews(HttpServletRequest request){
        String userId=userUtil.getCurrentUser(request).getUserId();
        List<News> newsList = newsService.queryForBeanListNewsListByUserId(userId);
        request.setAttribute("newsList",newsList);
        request.setAttribute("collectionList",this.collectionService.findCollectionListByCreateUserId(userId));
        Member member=new Member();
        member.setUserId(userId);
        List<Circle> circleList =this.memberService.queryForCircleIsIn(member);
        request.setAttribute("circleList",circleList);
        request.setAttribute("topCollectionList",this.collectionService.queryTopFiveCollectionList());
        return "index.ftl";
    }

    /**
     * ajax保存设置
     * @param request
     * @return
     */
    @RequestMapping(value = "saveSetting",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveSetting(HttpServletRequest request){

        User currentUser = userUtil.getCurrentUser(request);

        String nickname = StringClass.getString(request.getParameter("nickname"));
        String signature = StringClass.getString(request.getParameter("signature"));


        try {
            User user = new User();
            user.setUserId(currentUser.getUserId());
            user.setNickname(nickname);
            user.setSignature(signature);

            userService.updateUser(user);

            User newUser = userUtil.getCurrentUser(request);
            newUser=userService.setUserExtInfo(newUser);
            request.getSession(true).setAttribute(CoreConstant.USER_SESSION, newUser);
        }catch (Exception e){
            return renderJsonError("未知错误!");
        }

        return renderJsonSucc(null);
    }

    @RequestMapping(value = "updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updatePassword(HttpServletRequest request){

        User currentUser = userUtil.getCurrentUser(request);
        String password = StringClass.getString(request.getParameter("password"));
        String newpassword=StringClass.getString(request.getParameter("newpassword"));

        try {
          if(!currentUser.getPassword().equals( MD5.getHashString(password))){
             return renderJsonError("原密码错误!");
        }

            User user = new User();
            user.setUserId(currentUser.getUserId());
            user.setPassword(MD5.getHashString(newpassword));

            userService.updatePassword(user);

            User newUser = userUtil.getCurrentUser(request);
            newUser=userService.setUserExtInfo(newUser);
            request.getSession(true).setAttribute(CoreConstant.USER_SESSION, newUser);
        }catch (Exception e){
            return renderJsonError("未知错误!");
        }
        return renderJsonSucc(null);
    }

    @RequestMapping(value = "uploadAvatar",method = RequestMethod.POST)
    @UserSecurity
    public String uploadAvatar(@RequestParam("avatar") MultipartFile uploadFile, HttpServletRequest request) throws Exception {

        User currentUser = userUtil.getCurrentUser(request);

        String userId = currentUser.getUserId();

        String targetDir = CoreConstant.UploadTargetDir.USER + "/" + userId;
        String fileId = SequenceUtil.uuid2();// 文件名
        String avatar = "";

        try{

            String filename = imageUploadUtil.upload(request,uploadFile,targetDir,fileId,
                    new ImageUploadCallback() {
                        public void uploaded(String destPath) {
                            try {
                                ImageUtil.zoomImage(destPath, destPath, 100, 100);
                            }catch (Exception e){
                                logger.error("ImageUploadCallback error " + e);
                            }
                        }
                    });


            avatar = "/" + targetDir + "/" + filename;

        }catch (IOException e){
            logger.error(e);
        }

        //更新用户图片地址
        User user = new User();
        user.setUserId(userId);
        user.setAvatar(avatar);
        userService.updateUserAvatar(user);

        //更新用户会话图片地址

        User newUser = userService.setUserExtInfo(userService.findUserByUserId(userId));
        request.getSession(true).setAttribute(CoreConstant.USER_SESSION, newUser);


        return "redirect:/user/setting";

    }
}
