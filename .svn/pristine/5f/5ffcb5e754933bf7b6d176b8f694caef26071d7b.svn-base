package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.Circle;
import com.puckteam.sns.interfaces.core.vo.CircleQueryCourier;
import com.puckteam.sns.interfaces.core.vo.News;

import java.util.List;


/**
 * Created by asus1 on 2016/10/15.
 */
public interface ICircleService {

    /**
     * 艺术圈信息提交
     * @param circle
     * @return
     */
    public void circleInformationInsert(Circle circle);

    /**
     * 艺术圈信息查询
     * @return
     */
    public List<Circle> queryForCircleInformation();

    /**
     * 艺术圈删除
     * @param circleId
     */
    public void deleteCircleByCircleId(String circleId);

    /**
     * 艺术圈编辑
     * @param circle
     */
    public void editCircleByCircleId(Circle circle);

    /**
     * 艺术圈探索
     * @param queryCourier;
     */
    public List<Circle> searchCircleByPartName(CircleQueryCourier queryCourier);

    /**
     * 按circleId获取circle
     * @param circleId
     * @return
     */
    public Circle findCircleByCircleId(String circleId);

    /**
     * 按circleName获取circle
     * @param circleName
     * @return
     */
    public Circle findCircleByCircleName(String circleName);

    public List<News>queryNewsListByCircleId(String circleId);

    //推荐艺术圈
    public List<Circle> findPopularCircle(String userId);


    /**
     * 查找用户加入的艺术圈
     * 和动态不在的艺术圈
     * @param news
     * @param userId
     * @return
     */
    List<Circle>getCircleIsInAndNewsNotId(News news, String userId);

}
