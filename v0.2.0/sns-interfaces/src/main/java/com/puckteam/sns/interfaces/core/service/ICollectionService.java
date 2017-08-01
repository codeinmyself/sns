package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.Collection;
import com.puckteam.sns.interfaces.core.vo.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 82402 on 2016/10/14.
 */
public interface ICollectionService {
    /**
     * 作品集创建
     * @param collection
     * @return
     */
    public int addCollection(Collection collection);

    /**
     * 作品集删除
     * @param collectionId
     * @return
     */
    public int delCollectionByCollectionId(String collectionId);

    /**
     * 按创建用户查找作品集列表
     * @param createUserId
     * @return
     */
    public List<Collection> findCollectionListByCreateUserId(String createUserId);

    /**
     * 按collectionId查找作品集
     * @param collectionId
     * @return
     */
    public Collection findCollectionByCollectionId(String collectionId);

    /**
     * 作品集修改
     * @param collection
     * @return
     */
    public int updCollection(Collection collection);

    public int updCollectionWithoutCover(Collection collection);

    public List<News> queryNewsListByCollectionId(String collectionId);

    public List<Collection> queryTopFiveCollectionList();

    /**
     * 他人作品集信息展示
     */
    public List<Collection> findOtherCollectionListByCreateUserId(String createUserId,String userId);

    /**
     * 通过动态id查找未加入作品集
     * 用来加入作品集功能
     * 作品集的权限必须低于动态
     * @param news
     * @return
     */
    public List<Collection> getCollectionByNewsToAdd(News news,String userId);
}
