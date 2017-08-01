package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.CollectionAssociateNews;

/**
 * Created by 82402 on 2016/11/8.
 */
public interface ICollectionAssociateNewsService {
    /**
     * 动态作品集关联添加
     * @param collectionAssociateNews
     * @return
     */
    public void addAssociate(CollectionAssociateNews collectionAssociateNews);

    /**
     * 动态作品集关联删除
     * @param relaId
     * @return
     */
    public void delAssociate(String relaId);
}
