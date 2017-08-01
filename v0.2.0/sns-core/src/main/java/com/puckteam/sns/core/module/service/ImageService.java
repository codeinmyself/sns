package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.module.dao.ImageDao;
import com.puckteam.sns.interfaces.core.service.IImageService;
import com.puckteam.sns.interfaces.core.vo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 82402 on 2016/11/25.
 */

@Service("imageService")
@Transactional

public class ImageService implements IImageService {
    @Autowired
    private ImageDao imageDao;

    /**
     * 图片上传
     * @param image
     * @return
     */
    public int addImage(Image image){
        return imageDao.addImage(image);
    }

    /**
     * 图片展示
     * @param newsId
     * @return
     */
    public List<Image> queryForImageListByNewsId(String newsId){
        return imageDao.queryForBeanListImageListByNewsId(newsId);
    }
}
