package com.puckteam.sns.core.module.service;//package com.puckteam.sns.core.module.service;
//
//import com.puckteam.sns.base.util.MD5;
//import com.puckteam.sns.interfaces.core.service.IUserService;
//import com.puckteam.sns.interfaces.core.vo.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.UUID;
//
///**
// * Created by ZengJieHang on 2016/11/19.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//        ({"/spring-hikaricp.xml","/spring-mvc.xml"})
//public class TestUserService
//{
//    @Autowired
//    @Qualifier("userService")
//    private IUserService userService;
//
//    @Test
//    public void insert()
//    {
//        String nickName[]={"测试一","测试二","测试三","测试四","测试五","测试六","测试七","测试八","测试九","测试十",
//                "还是测试十一","还是测试十二","还是测试十三","还是测试十四","还是测试十五","还是测试十六","还是测试十七","还是测试十八","还是测试十九","还是测试二十",
//                "又是测试二十一","又是测试二十二","又是测试二十三","又是测试二十四","又是测试二十五","又是测试二十六","又是测试二十七","又是测试二十八","又是测试二十九","又是测试三十", };
//
//        for(int i=1;i<=nickName.length;++i)
//        {
//            User user = new User();
//
//            user.setAvatar("/img/default_avatar.png");
//            user.setUserId(UUID.randomUUID().toString().replace("-", ""));
//            user.setUserName(String.valueOf(i));
//            user.setEmail(String.valueOf(i));
//            user.setPassword(MD5.getHashString("123456"));
//            user.setNickname(nickName[i-1]);
//
//            userService.email_insert(user);
//        }
//    }
//}
