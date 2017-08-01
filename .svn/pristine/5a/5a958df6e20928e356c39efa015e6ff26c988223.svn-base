package com.puckteam.sns.test;

import com.puckteam.sns.core.support.util.UserUtil;
import com.puckteam.sns.interfaces.core.service.IUserService;
import com.puckteam.sns.interfaces.core.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by PoemWhite on 2016/10/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        ({"/spring-hikaricp.xml","/spring-mvc.xml"})
public class TestUser {

    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    private UserUtil userUtil;

    @Test
    public void testUserUtil(){
        HttpServletRequest request = null;
        User user = userUtil.getCurrentUser(request);
    }

    @Test
    public void test(){
        User user = userService.findUserByEmail("hjc");
        System.out.println("###"+user.getUserId());
    }



}
