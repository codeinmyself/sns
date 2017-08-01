package com.puckteam.sns.core.support.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by PoemWhite on 16/9/9.
 */
public class BaseUrlInterceptor extends HandlerInterceptorAdapter {

    static Logger logger = LogManager.getLogger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        ServletInputStream is = request.getInputStream();




        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();

        String baseUrl = scheme + "://" + serverName + ":" + port + path;
        request.setAttribute("baseUrl", baseUrl);

//        String staticUrl = LfrCoreConfig.getInstance().getPackingConfig().getStaticUrl();
        request.setAttribute("staticUrl", "http://127.0.0.1:8080/sns/static");

        return true;
    }
}