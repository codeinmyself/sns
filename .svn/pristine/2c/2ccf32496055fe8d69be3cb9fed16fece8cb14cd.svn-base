package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.CollectionAssociateNews;
import org.springframework.stereotype.Repository;

/**
 * Created by 82402 on 2016/11/8.
 */
@Repository
public class CollectionAssociateNewsDao extends BaseDao {
    /**
     * 动态作品集关联添加
     *
     * @param collectionAssociateNews
     * @return
     */
    public void addAssociate(CollectionAssociateNews collectionAssociateNews) {
        String sql = "insert into sns_news_rela(" +
                "rela_id,news_id,rela_type,target_id,create_time" +
                ") values (?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";
        this.executeUpdate(sql, new Object[]{
                collectionAssociateNews.getRelaId(),
                collectionAssociateNews.getNewsId(),
                collectionAssociateNews.getRelaType(),
                collectionAssociateNews.getTargetId(),
                this.getSysdate()
                });
    }

    /**
     * 动态作品集关联删除
     *
     * @param relaId
     * @return
     */
    public void delAssociate(String relaId) {
        String sql = "delete from sns_news_rela where rela_id=?";
        this.executeUpdate(sql, new Object[]{relaId});
    }
}
