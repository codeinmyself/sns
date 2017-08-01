package com.puckteam.sns.core.module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by PoemWhite on 2016/11/18.
 */
@Controller
@RequestMapping(value = "/demo")
public class DemoController {


    @RequestMapping(value = "imageUpload",method = RequestMethod.GET)
    public String imageUpload(HttpServletRequest request){
        return "module/demo/image_upload.ftl";
    }
}
