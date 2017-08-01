package com.puckteam.sns.test;

import com.puckteam.sns.core.module.dao.TestDao;
import com.puckteam.sns.interfaces.core.vo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by PoemWhite on 2016/11/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
        ({"/spring-hikaricp.xml","/spring-mvc.xml"})
public class TestBatch {

    @Autowired
    private TestDao testDao;


    @Test
    public void test01(){
        testDao.addBatchTest();
    }

}
