package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.NewsRelaCircleCollection;
import org.springframework.stereotype.Repository;

/**
 * Created by 82402 on 2016/11/8.
 */
@Repository
public class NewsRelaCircleCollectionDao extends BaseDao {
    /**
     * 动态作品集关联添加
     *
     * @param collectionAssociateNews
     * @return
     */
    public int addAssociate(NewsRelaCircleCollection collectionAssociateNews) {
        String sql = "insert into sns_news_rela(" +
                "news_id,rela_type,target_id,create_time" +
                ") values (?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";
        return this.executeUpdate(sql, new Object[]{
                collectionAssociateNews.getNewsId(),
                collectionAssociateNews.getRelaType(),
                collectionAssociateNews.getTargetId(),
                this.getSysdate()
                });
    }

    /**
     * 动态作品集关联删除
     *
     * @param newsRelaCircleCollection
     * @return
     */
    public int delAssociate(NewsRelaCircleCollection newsRelaCircleCollection) {
        String sql = "delete from sns_news_rela " +
                "where news_id=? and rela_type=? and target_id=?";
        return  this.executeUpdate(sql,
                new Object[]{
                        newsRelaCircleCollection.getNewsId(),
                        newsRelaCircleCollection.getRelaType(),
                        newsRelaCircleCollection.getTargetId()
                });
    }
}
