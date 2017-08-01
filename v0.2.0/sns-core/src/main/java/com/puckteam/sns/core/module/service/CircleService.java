package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.module.dao.CircleDao;
import com.puckteam.sns.core.module.dao.ImageDao;
import com.puckteam.sns.interfaces.core.service.ICircleService;
import com.puckteam.sns.interfaces.core.vo.Circle;
import com.puckteam.sns.interfaces.core.vo.CircleQueryCourier;
import com.puckteam.sns.interfaces.core.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by asus1 on 2016/10/15.
 */
@Service("circleService")
@Transactional
public class CircleService implements ICircleService {

    @Autowired
    private CircleDao circleDao;

    @Autowired
    private ImageDao imageDao;

    /**
     * 艺术圈/作品集信息提交
     * @param circle
     * @return
     */
    public void circleInformationInsert(Circle circle){
        circleDao.circleInformationInsert(circle);
    }

    /**
     * 艺术圈/作品集信息查询
     * @return
     */
    public List<Circle> queryForCircleInformation(){
         return circleDao.queryForBeanListCircle();
    }

    /**
     * 艺术圈删除
     * @param circleId
     */
    public void deleteCircleByCircleId(String circleId){
        circleDao.deleteCircleByCircleId(circleId);
    }
    /**
     * 艺术圈编辑
     * @param circle
     */
    public void editCircleByCircleId(Circle circle){
        circleDao.editCircleByCircleId(circle);
    }

    /**
     * 艺术圈昵称模糊查询
     * @param  queryCourier;
     */
    public List<Circle> searchCircleByPartName(CircleQueryCourier queryCourier) {
        return circleDao.searchCircleByPartName(queryCourier);
    }

    /**
     * 按circleId获取circle
     * @param circleId
     * @return
     */
    public Circle findCircleByCircleId(String circleId){
        Circle circle=circleDao.findCircleByCircleId(circleId);
        circle.setNewsNumber(circleDao.findNewsNumberByCircleId(circle.getCircleId()));
        circle.setMemberNumber(circleDao.findMemberNumberByCircleId(circle.getCircleId()));
        return circle;
    }

    /**
     * 按circleName获取circle
     * @param circleName
     * @return
     */
    public Circle findCircleByCircleName(String circleName)
    {
        Circle circle= circleDao.findCircleByCircleName(circleName);

        return circle;
    }

    public List<News>queryNewsListByCircleId(String circleId)
    {
        List<News>newsList=this.circleDao.queryNewsListByCircleId(circleId);

        if(newsList!=null)
        {
            for(int i=0;i<newsList.size();++i)
            {
                newsList.get(i).setImageList(imageDao.queryForBeanListImageListByNewsId(newsList.get(i).getNewsId()));
            }
        }

        return newsList;
    }
   //推荐艺术圈
    public List<Circle> findPopularCircle(String userId)
    {
        List<Circle> circleList=this.circleDao.findPopularCircle();
        if(circleList!=null&&circleList.size()>0)
        {
            for(int i=0;i<circleList.size();++i)
            {
                Map map=this.circleDao.isJoinInCircleByMemberUserId(circleList.get(i),userId);
                circleList.get(i).setIsJoinIn(map.get("number").toString());
            }
        }

        return circleList;
    }

    public List<Circle>getCircleIsInAndNewsNotId(News news,String userId)
    {
        return this.circleDao.getCircleIsInAndNewsNotId(news,userId);
    }
}
