package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Like;
import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * Created by 82402 on 2016/11/14.
 */

@Repository

public class LikeDao extends BaseDao {

    /**
     * 点赞
     * @param like
     * @return
     */
    public int addLike(Like like){
        String sql = "insert into sns_like(" + "like_id,news_id,create_user_id" + ")value(?,?,?)";
        return this.executeUpdate(sql,new Object[]{
            like.getLikeId(),
            like.getNewsId(),
            like.getCreateUserId()
        });
    }



    /**
     * 取消点赞
     * @param newsId,createUserId
     * @return
     */
    public int delLike(String newsId,String createUserId){
        String sql = "delete from sns_like where news_id = ? and create_user_id = ?";
        return this.executeUpdate(sql, new Object[]{newsId,createUserId});
    }

    /**
     * 判断是否点过赞
     * @param newsId,createUserId
     * @return
     */
    public boolean ifLike(String newsId,String createUserId){
        String sql = "select count(like_id) as number from sns_like where news_id=? and create_user_id=?";
        if(Integer.parseInt(this.queryForMap(sql,new Object[]{newsId,createUserId}).get("number").toString())>0){
            return true;
        }
        else{
            return false;
        }
    }
}