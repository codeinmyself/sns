package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 82402 on 2016/11/25.
 */

@Repository

public class ImageDao extends BaseDao{
    /**
     * 图片上传
     * @param image
     * @return
     */
    public int addImage(Image image){
        String sql = " insert into sns_news_image(" +
                "pic_id,news_id,pic_index,create_time" +
                ") values (?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";
        return this.executeUpdate(sql,new Object[]{
                image.getPicId(),
                image.getNewsId(),
                image.getPicIndex(),
                this.getSysdate()
        });
    }

    /**
     * 图片展示
     * @param newsId
     * @return
     */
    public List<Image> queryForBeanListImageListByNewsId(String newsId){
        String sql = "select" +
                " pic_id," +
                " news_id, " +
                " pic_index," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_news_image where news_id = ? order by pic_index";
        List<Image> imageList = this.queryForBeanList(sql,new Object[]{newsId},Image.class);
        return imageList;
    }
}
