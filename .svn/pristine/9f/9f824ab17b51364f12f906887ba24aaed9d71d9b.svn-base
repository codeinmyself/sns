package com.puckteam.sns.core.support.exception;

import com.puckteam.sns.base.exception.SnsBaseRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PoemWhite on 16/9/12.
 */
public class SnsExceptionHandler implements HandlerExceptionResolver {

    static Logger logger = LogManager.getLogger();

    public ModelAndView resolveException(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));

        String errorStack = writer.getBuffer().toString();
        logger.error(errorStack);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", e);

        // 根据不同错误转向不同页面
        if(e instanceof SnsBaseRuntimeException) {
            return new ModelAndView("error.jsp", model);
        }

        return new ModelAndView("error.ftl", model);
    }
}
