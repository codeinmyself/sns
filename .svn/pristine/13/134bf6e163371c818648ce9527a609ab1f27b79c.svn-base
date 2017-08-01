package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Circle;
import com.puckteam.sns.interfaces.core.vo.CircleQueryCourier;
import com.puckteam.sns.interfaces.core.vo.News;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by asus1 on 2016/10/15.
 */
@Repository
public class CircleDao extends BaseDao {
    /**
     * 动态集合表信息提交
     * @param circle
     * @return
     */
    public void circleInformationInsert(Circle circle){
        String sql = "insert into sns_circle(" +
                "circle_id,circle_name,show_scope,description,apply_strategy,status,create_time,create_user_id,avatar" +
                ") values (?,?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?)";
        this.executeUpdate(sql, new Object[]{
                circle.getCircleId(),
                circle.getCircleName(),
                circle.getShowScope(),
                circle.getDescription(),
                circle.getApplyStrategy(),
                circle.getStatus(),
                this.getSysdate(),
                circle.getCreateUserId(),
                circle.getAvatar()
                });
    }

    /**
     * 提取表单于前台展示
     */
    public List<Circle>  queryForBeanListCircle() {
        String sql = "select " +
                " circle_id," +
                " circle_name," +
                " show_scope," +
                " description," +
                " apply_strategy," +
                " status," +
                " avatar," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime, " +
                " create_user_id"+
                " from sns_circle ";
        List<Circle> circleList=this.queryForBeanList(sql,null,Circle.class);

        return circleList;
    }

    /**
     * 艺术圈删除
     * @param circleId
     */
    public void deleteCircleByCircleId(String circleId){
        String sql = "update sns_circle set status =? where circle_id =?";
        this.executeUpdate(sql,new Object[]{CoreConstant.Status.DELETE,circleId});
    }

    /**
     * 艺术圈编辑
     * @param circle
     */
    public void editCircleByCircleId(Circle circle){
        if(circle.getAvatar()==null){
            String sql = "update sns_circle set "+
                    " circle_name=?,"+
                    " description=?,"+
                    " show_scope=?,"+
                    " apply_strategy=? "+
                    " where circle_id=?";
            this.executeUpdate(sql,new Object[]{
                    circle.getCircleName(),
                    circle.getDescription(),
                    circle.getShowScope(),
                    circle.getApplyStrategy(),
                    circle.getCircleId()});
        }
        else{
            String sql = "update sns_circle set "+
                    " circle_name=?,"+
                    " description=?,"+
                    " show_scope=?,"+
                    " avatar=?,"+
                    " apply_strategy=? "+
                    " where circle_id=?";
            this.executeUpdate(sql,new Object[]{
                    circle.getCircleName(),
                    circle.getDescription(),
                    circle.getShowScope(),
                    circle.getAvatar(),
                    circle.getApplyStrategy(),
                    circle.getCircleId()});

        }

    }


    private String getSelectSql(){
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("select ")
                .append("   A.circle_id as circleId,")
                .append("   A.create_user_id as createUserId,")
                .append("   A.circle_name as circleName,")
                .append("   A.show_scope as showScope,")
                .append("   A.description as description,")
                .append("   A.avatar as avatar,")
                .append("   A.apply_strategy as applyStrategy, ")
                .append("   date_format(A.create_time,'%Y-%m-%d %H:%i:%s') as createTime ");

        return sqlBuilder.toString();
    }


     /**
     * 艺术圈昵称
     * @param queryCourier
     */
    public List<Circle>  searchCircleByPartName(CircleQueryCourier queryCourier) {
/*        StringBuilder sqlBuilder = new StringBuilder(getSelectSql());

        sqlBuilder.append("   where status = '1' ");
        StringBuilder paramBuilder = new StringBuilder();*/
        List<String> paramList = new ArrayList<String>();

        if(!StringUtils.isEmpty(queryCourier.getPartName())){

            String partName = queryCourier.getPartName().trim().replaceAll("/", "//")
                    .replaceAll("%", "/%")
                    .replace("_", "/_");

            paramList.add("%" + partName + "%");
            paramList.add("%" + partName + "%");
        }
        paramList.add(CoreConstant.Status.NORMAL);
        paramList.add(queryCourier.getUserId());

        String sql =
                getSelectSql()+
                " ,if(isnull(B.member_id),0,1)" +
                " as isJoinIn from (select * from sns_circle where (circle_name like ? escape '/' or description like ? escape '/')and status= ? ) " +
                " A left join (select circle_id,member_id " +
                " from sns_member where user_id=?)B on A.circle_id=B.circle_id" +
                " order by createTime desc";
        List<Circle> circleList=this.queryForBeanList(sql,paramList.toArray(),Circle.class);
        return circleList;
    }
    /**
     * 按circleId获取circle
     * @param circleId
     * @return
     */
    public Circle findCircleByCircleId(String circleId) {
        String sql = "select " +
                " circle_id," +
                " circle_name," +
                " status," +
                " show_scope," +
                " apply_strategy," +
                " description," +
                " avatar," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_circle where circle_id=? and status='1'";

        Circle circle = (Circle) this.queryForBean(
                sql, new Object[]{circleId}, Circle.class);

        return circle;
    }
    /**
     * 按circleName获取circle
     * @param circleName
     * @return
     */
    public Circle findCircleByCircleName(String circleName) {
        String sql = "select " +
                " circle_id," +
                " circle_name," +
                " status," +
                " avatar," +
                " show_scope," +
                " apply_strategy," +
                " description," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_circle where circle_name=?";

        Circle circle = (Circle) this.queryForBean(
                sql, new Object[]{circleName}, Circle.class);
        return circle;
    }

    public List<News>queryNewsListByCircleId(String circleId)
    {
        String sql="select sns_news.news_id," +
                " sns_news.show_scope," +
                " sns_news.content," +
                " sns_news.like_count," +
                " sns_news.comment_count," +
                " sns_news.click_count," +
                " sns_news.status," +
                " date_format(sns_news.create_time,'%Y-%m-%d %H:%i:%s') as createTime," +
                " sns_news.create_user_id," +
                " sns_user.nickname as nickname," +
                " sns_user.avatar as avatar " +
                " from sns_news,sns_user,sns_news_rela" +
                " where sns_news_rela.rela_type=? and target_id=?" +
                " and sns_news_rela.news_id=sns_news.news_id" +
                " and sns_news.status=?" +
                " and sns_news.create_user_id=sns_user.user_id"+
                 " order by sns_news.create_time desc";

        List<News> newsList=this.queryForBeanList(sql,new Object[]{
                CoreConstant.NewsAssociateCircleCollectionType.cicleNews,
                circleId,
                CoreConstant.Status.NORMAL
        },News.class);
        return newsList;
    }

    public String findNewsNumberByCircleId(String circleId)
    {
        String sql="select count(sns_news_rela.rela_id) as number" +
                " from sns_news_rela" +
                " where rela_type=? and target_id=?";

        return this.queryForMap(sql,new Object[]{CoreConstant.NewsAssociateCircleCollectionType.cicleNews,circleId}).get("number").toString();
    }

    public String findMemberNumberByCircleId(String circleId)
    {
        String sql="select count(sns_member.member_id) as number" +
                " from sns_member" +
                " where circle_id=?";

        return this.queryForMap(sql,new Object[]{circleId}).get("number").toString();
    }

    //推荐艺术圈
    public List<Circle> findPopularCircle()
    {
        String sql="select " +
                " sns_circle.circle_id," +
                " sns_circle.circle_name," +
                " sns_circle.status," +
                " sns_circle.show_scope," +
                " sns_circle.apply_strategy," +
                " sns_circle.description," +
                " sns_circle.avatar," +
                " sns_circle.create_user_id," +
                " date_format(sns_circle.create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_circle,sns_member " +
                " where sns_circle.show_scope='01' " +
                " and sns_circle.status=? " +
                " and sns_circle.circle_id=sns_member.circle_id" +
                " group by sns_circle.circle_id" +
                " order by count(sns_member.member_id) desc,createTime desc" +
                " limit 5";

        return (List<Circle>)this.queryForBeanList(sql,new Object[]{CoreConstant.Status.NORMAL},Circle.class);
    }
   //判断是否加入艺术圈
    public Map isJoinInCircleByMemberUserId(Circle circle, String userId)
    {
        String sql="select count(*) as number" +
                " from sns_member " +
                " where circle_id=? and user_id=?";

        return this.queryForMap(sql,new Object[]{circle.getCircleId(),userId});
    }

    public List<Circle>getCircleIsInAndNewsNotId(News news,String userId)
    {
        String sql = "select sns_circle.circle_id," +
                " sns_circle.circle_name " +
                " from sns_member,sns_circle "+
                " where sns_member.user_id=? "+
                " and sns_circle.circle_id=sns_member.circle_id " +
                " and sns_circle.status=?" +
                " and sns_circle.circle_id not in" +
                " (select target_id" +
                " from sns_news_rela" +
                " where rela_type=?" +
                " and news_id=? )";
        List<Circle> circleList=this.queryForBeanList(sql,new Object[]{userId, CoreConstant.Status.NORMAL,CoreConstant.NewsAssociateCircleCollectionType.cicleNews,news.getNewsId()},Circle.class);

        return circleList;
    }
}

