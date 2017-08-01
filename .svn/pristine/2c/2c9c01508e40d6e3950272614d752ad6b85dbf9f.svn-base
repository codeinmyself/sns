package com.puckteam.sns.core.support.aop;

import com.puckteam.sns.core.constant.CoreConfig;
import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.vo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by PoemWhite on 16/9/12.
 */
@Aspect
@Component
public class UserSecurityAspect {

    static Logger logger = LogManager.getLogger();

    @Autowired
    private UserUtil userUtil;

    @Pointcut("@annotation(com.puckteam.sns.core.support.aop.annotation.UserSecurity)")
    public  void doUserSecurityAspect() {

    }

    @Around("doUserSecurityAspect()")
    public Object around(ProceedingJoinPoint pjp) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        User user = userUtil.getCurrentUser(request);

        if(user == null){
            try {

                if(CoreConfig.getInstance().isDevelopMode()){
                    response.sendRedirect("/");
                }else{
                    response.sendRedirect("/user/login.html");
                }

            } catch (IOException e) {
                logger.error(e);
            }
        }else {

            try {
                return pjp.proceed();
            } catch (Throwable e) {
                logger.error(e);
            }
        }

        return null;
    }
}
