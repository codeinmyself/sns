package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.module.dao.CollectionAssociateNewsDao;
import com.puckteam.sns.interfaces.core.service.ICollectionAssociateNewsService;
import com.puckteam.sns.interfaces.core.vo.CollectionAssociateNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 82402 on 2016/11/8.
 */

@Service("collectionAssociateNewsService")
@Transactional
public class CollectionAssociateNewsService implements ICollectionAssociateNewsService {
    @Autowired
    private CollectionAssociateNewsDao collectionAssociateNewsDao;

    /**
     * 动态作品集关联添加
     * @param collectionAssociateNews
     * @return
     */
    public void addAssociate(CollectionAssociateNews collectionAssociateNews){
        collectionAssociateNewsDao.addAssociate(collectionAssociateNews);
    }

    /**
     * 动态作品集关联删除
     * @param relaId
     * @return
     */
    public void delAssociate(String relaId){
        collectionAssociateNewsDao.delAssociate(relaId);
    }
}
