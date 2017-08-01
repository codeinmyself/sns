package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.module.dao.CollectionDao;
import com.puckteam.sns.core.module.dao.ImageDao;
import com.puckteam.sns.interfaces.core.service.ICollectionService;
import com.puckteam.sns.interfaces.core.vo.Collection;
import com.puckteam.sns.interfaces.core.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 82402 on 2016/10/15.
 */

@Service("collectionService")
@Transactional
public class CollectionService implements ICollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private ImageDao imageDao;

    /**
     * 作品集创建
     * @param collection
     * @return
     */
    public int addCollection(Collection collection){

        return collectionDao.addCollection(collection);
    }

    /**
     * 作品集删除
     * @param collectionId
     * @return
     */
    public int delCollectionByCollectionId(String collectionId){

        return  collectionDao.delCollectionByCollectionId(collectionId);
    }

    /**
     * 按创建用户查找作品集列表
     * @param createUserId
     * @return
     */
    public List<Collection> findCollectionListByCreateUserId(String createUserId){

        return collectionDao.findCollectionListByCreateUserId(createUserId);
    }

    /**
     * 按collectionId查找作品集
     * @param collectionId
     * @return
     */
    public Collection findCollectionByCollectionId(String collectionId){

        return collectionDao.findCollectionByCollectionId(collectionId);
    }

    /**
     * 作品集修改
     * @param collection
     * @return
     */
    public int updCollection(Collection collection){

        return collectionDao.updCollection(collection);
    }

    public int updCollectionWithoutCover(Collection collection) {

        return collectionDao.updCollectionWithoutCover(collection);
    }

    public List<News> queryNewsListByCollectionId(String collectionId) {

        List<News>newsList=this.collectionDao.queryNewsListByCollectionId(collectionId);
        if(newsList!=null) {

            for(int i=0;i<newsList.size();++i) {

                newsList.get(i).setImageList(
                        imageDao.queryForBeanListImageListByNewsId(newsList.get(i).getNewsId()));
            }
        }
        return newsList;
    }

    public List<Collection> queryTopFiveCollectionList() {

        return this.collectionDao.findTopFiveCollectionList();
    }

    /**
     * 他人作品集信息展示
     */
    public List<Collection> findOtherCollectionListByCreateUserId(String createUserId,String userId) {

        return this.collectionDao.findOtherCollectionListByCreateUserId(createUserId,userId);
    }

    /**
     * 通过动态id查找未加入作品集
     * 用来加入作品集功能
     * 作品集的权限必须低于动态
     * @param news
     * @return
     */
    public List<Collection> getCollectionByNewsToAdd(News news,String userId) {

        List<String> collectionShowScope=new ArrayList<String>();

        collectionShowScope.add(userId);
        collectionShowScope.add(CoreConstant.Status.NORMAL);

        if(news.getShowScope().equals(CoreConstant.NewsShowScope.ONLY_MYSELF)) {
            collectionShowScope.add(CoreConstant.NewsShowScope.ONLY_MYSELF);

        } else if(news.getShowScope().equals(CoreConstant.NewsShowScope.ONLY_FOCUS)) {
            collectionShowScope.add(CoreConstant.NewsShowScope.ONLY_MYSELF);
            collectionShowScope.add(CoreConstant.NewsShowScope.ONLY_FOCUS);
        }

        collectionShowScope.add(CoreConstant.NewsAssociateCircleCollectionType.collectionNews);
        collectionShowScope.add(news.getNewsId());

        return this.collectionDao.getCollectionByNewsToAdd(collectionShowScope,news);
    }
}