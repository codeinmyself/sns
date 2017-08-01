package com.puckteam.sns.core.support.util;

import com.puckteam.sns.core.constant.CoreConfig;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.interfaces.core.service.IUserService;
import com.puckteam.sns.interfaces.core.vo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by PoemWhite on 2016/10/23.
 */
@Component
public class UserUtil {

    static Logger logger = LogManager.getLogger();

    @Autowired
    @Qualifier("userService")
    public IUserService userService;

    public User getCurrentUser(HttpServletRequest request){

        if(CoreConfig.getInstance().isDevelopMode()){
            User user = userService.findUserByName("hongjiacan");
            userService.setUserExtInfo(user);
            request.getSession(true).setAttribute(CoreConstant.USER_SESSION, user);
            return user;
        }

        //先从session取
        User user = (User)request.getSession().getAttribute(CoreConstant.USER_SESSION);

        if(user != null
                || CoreConfig.getInstance().isDevelopMode()){

            if(logger.isDebugEnabled()){
                logger.debug(user);
            }
            return user;
        }

        //非开发模式
        //再从cookie取
        Cookie[] cookies = request.getCookies();

        if(cookies!=null && cookies.length>0){

            String username = "";
            for(Cookie cookie : cookies) {
                if (CoreConstant.USER_COOKIE.equals(cookie.getName())) {
                    try {
                        username = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        break;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(!StringUtils.isEmpty(username)){

                user = userService.findUserByName(username);

                if (user == null){
                    //新用户默认生成一个
                    user = userService.generateNewPkaUser(username);
                    if(logger.isDebugEnabled()){
                        logger.debug("generateNewPkaUser:"+user.toString());
                    }
                }
                userService.setUserExtInfo(user);
                if(logger.isDebugEnabled()){
                    logger.debug("sessionUser:"+user.toString());
                }
                request.getSession(true).setAttribute(CoreConstant.USER_SESSION, user);

                return user;
            }

        }

        return null;
    }
}
