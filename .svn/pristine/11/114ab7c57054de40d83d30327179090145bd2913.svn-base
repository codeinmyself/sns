package com.puckteam.sns.core.module.service;


import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.interfaces.core.service.ICollectionService;

import com.puckteam.sns.interfaces.core.vo.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;


/**
 * Created by 82402 on 2016/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-hikaricp.xml","/spring-mvc.xml"})
public class CollectionServiceTest {
    @Autowired
    @Qualifier("collectionService")
    private ICollectionService collectionService;

    /**
     * 作品集创建测试
     */
    @Test
    public void collectionAddTest()
    {
        Collection collection = new Collection();
        collection.setCollectionId(UUID.randomUUID().toString().replace("-", ""));
        collection.setCollectionName("aaa");
        collection.setShowScope("01");
        collection.setDescription("ha");
        collection.setStatus(CoreConstant.Status.NORMAL);
        collectionService.addCollection(collection);
        System.out.println(collection.getCollectionId());
        System.out.println(collection.getCollectionName());
        System.out.println(collection.getShowScope());
        System.out.println(collection.getDescription());
        System.out.println(collection.getStatus());
    }

    /**
     * 作品集删除测试
     */
    @Test
    public void collectionDeleteTest(){
        Collection collection = new Collection();
        collection.setCollectionId("1e311c862ddf44b791de09cb322eb4ef");
        collectionService.delCollectionByCollectionId(collection.getCollectionId());
    }

    /**
     * 作品集修改测试
     */
    @Test
    public void collectionReviseTest(){
        Collection collection = new Collection();
        collection.setCollectionId("1e311c862ddf44b791de09cb322eb4ef");
        collection.setCollectionName("6");
        collection.setDescription("6");
        collection.setShowScope("03");
        collectionService.updCollection(collection);
        System.out.println(collection.getCollectionName());
        System.out.println(collection.getShowScope());
        System.out.println(collection.getDescription());
    }


}
