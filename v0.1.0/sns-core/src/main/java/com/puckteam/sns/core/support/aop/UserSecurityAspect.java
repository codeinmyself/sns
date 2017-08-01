package com.puckteam.sns.core.support.aop;

import com.puckteam.sns.interfaces.core.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by PoemWhite on 16/9/12.
 */
@Aspect
@Component
public class UserSecurityAspect {

    static Logger logger = LogManager.getLogger();

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Pointcut("@annotation(com.puckteam.sns.core.support.aop.annotation.UserSecurity)")
    public  void doUserSecurityAspect() {

    }

    @Before("doUserSecurityAspect()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();



    }
}
