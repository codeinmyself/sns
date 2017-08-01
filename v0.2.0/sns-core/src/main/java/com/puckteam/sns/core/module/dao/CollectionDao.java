package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.Collection;
import com.puckteam.sns.interfaces.core.vo.News;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by 82402 on 2016/10/15.
 */
@Repository
public class CollectionDao extends BaseDao{


        public static final String COLLECTION_COLUMN_LIST =
                " collection_id," +
                " collection_name," +
                " show_scope," +
                " description," +
                " status," +
                " cover," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as create_time," +
                " create_user_id ";

    /**
     * 作品集创建
     * @param collection
     * @return
     */
    public int addCollection(Collection collection){

        String sql = "insert into sns_collection(" +
                "collection_id,collection_name,show_scope,description,status,create_time,create_user_id,cover" +
                ") values (?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?)";

        return this.executeUpdate(sql, new Object[]{
                collection.getCollectionId(),
                collection.getCollectionName(),
                collection.getShowScope(),
                collection.getDescription(),
                collection.getStatus(),
                this.getSysdate(),
                collection.getCreateUserId(),
                collection.getCover()
        });
    }
    /**
     * 按创建用户查找作品集列表
     */
    public List<Collection>  findCollectionListByCreateUserId(String createUserId) {

        String sql = "select " +
                COLLECTION_COLUMN_LIST +
                " from sns_collection " +
                " where status=? and create_user_id=?";

        return this.queryForBeanList(
                sql,new Object[]{CoreConstant.Status.NORMAL,createUserId},Collection.class);

    }

    /**
     * 按collectionId查找作品集
     */
    public Collection  findCollectionByCollectionId(String collectionId) {

        String sql = "select " +
                COLLECTION_COLUMN_LIST +
                " from sns_collection where collection_id=?";

        return (Collection) this.queryForBean(
                sql,new Object[]{collectionId},Collection.class);
    }

    /**
     * 作品集删除
     * @param collectionId
     * @return
     */
    public int delCollectionByCollectionId(String collectionId) {

        String sql = "update sns_collection set status =? where collection_id =?";

        return this.executeUpdate(
                sql,new Object[]{
                        CoreConstant.Status.DELETE,
                        collectionId
                });
    }

    /**
     * 作品集修改
     */
    public int updCollection(Collection collection) {

        String sql = " update sns_collection set " +
                " collection_name = ? ," +
                " description = ? ," +
                " show_scope = ? ," +
                " cover = ? " +
                " where collection_id = ? ";

        return this.executeUpdate(
                sql,new Object[]{
                        collection.getCollectionName(),
                        collection.getDescription(),
                        collection.getShowScope(),
                        collection.getCover(),
                        collection.getCollectionId()
                });
    }

    public int updCollectionWithoutCover(Collection collection) {

        String sql = " update sns_collection set " +
                " collection_name = ? ," +
                " description = ? ," +
                " show_scope = ? " +
                " where collection_id = ? ";

        return this.executeUpdate(sql,new Object[]{
                collection.getCollectionName(),
                collection.getDescription(),
                collection.getShowScope(),
                collection.getCollectionId()});
    }


    public List<News> queryNewsListByCollectionId(String collectionId)
    {
        String sql="select " +
                " sns_news.news_id," +
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
                "  from sns_news,sns_user,sns_news_rela" +
                " where sns_news_rela.rela_type=? " +
                "   and target_id=?" +
                "   and sns_news_rela.news_id=sns_news.news_id" +
                "   and sns_news.status=?" +
                "   and sns_news.create_user_id=sns_user.user_id"+
                " order by sns_news.create_time desc";

        return this.queryForBeanList(
                sql,
                new Object[]{
                    CoreConstant.NewsAssociateCircleCollectionType.collectionNews,
                    collectionId,
                    CoreConstant.Status.NORMAL
                },News.class);
    }

    public List<Collection> findTopFiveCollectionList(){

        String sql="select sns_collection.*, " +
                " sns_user.nickname as createNickname" +
                "  from sns_collection,sns_news,sns_news_rela,sns_user " +
                " where sns_collection.show_scope='01' " +
                "   and sns_collection.status=? " +
                "   and sns_collection.collection_id=sns_news_rela.target_id " +
                "   and sns_news_rela.rela_type=?" +
                "   and sns_news.news_id=sns_news_rela.news_id " +
                "   and sns_user.user_id=sns_collection.create_user_id" +
                " group by sns_collection.collection_id " +
                " order by sum(sns_news.comment_count) desc,sns_collection.create_time desc limit 5";

        return (List<Collection>)this.queryForBeanList(sql,new Object[]{
                CoreConstant.Status.NORMAL,CoreConstant.NewsAssociateCircleCollectionType.collectionNews}
                ,Collection.class
        );
    }

    /**
     * 他人作品集信息展示
     */
    public List<Collection>  findOtherCollectionListByCreateUserId(String createUserId,String userId) {

        String sql = "select " +
                " sns_collection.collection_id," +
                " sns_collection.collection_name," +
                " sns_collection.show_scope," +
                " sns_collection.description," +
                " sns_collection.status," +
                " date_format(sns_collection.create_time,'%Y-%m-%d %H:%i:%s') as createTime, " +
                " sns_collection.create_user_id ," +
                " sns_collection.cover " +
                "  from sns_collection " +
                " where sns_collection.create_user_id=?" +
                "   and sns_collection.collection_id in " +
                " (select A.collection_id from sns_collection A where A.status=? and A.show_scope='01'" +
                " or (A.show_scope='02' and A.create_user_id in (select sns_focus.focus_user_id from sns_focus where sns_focus.user_id=?)))" +
                " order by sns_collection.create_time desc";
        return this.queryForBeanList(
                sql,new Object[]{
                        createUserId,
                        CoreConstant.Status.NORMAL,
                        userId
                },Collection.class);

    }

   public List<Collection> getCollectionByNewsToAdd(List<String>collectionShowScope,News news)
   {
       String sql="select collection_id," +
               " collection_name" +
               " from sns_collection" +
               " where create_user_id=?" +
               " and status=?" +
               " and ";

       if(news.getShowScope().equals(CoreConstant.NewsShowScope.ONLY_MYSELF))
       {
           sql+=" (show_scope=? ) and";
       }
       else if(news.getShowScope().equals(CoreConstant.NewsShowScope.ONLY_FOCUS))
       {
           sql+=" (show_scope=? or show_scope=? ) and";
       }

       sql+=" collection_id not in(" +
               " select target_id" +
               " from sns_news_rela" +
               " where rela_type=?" +
               " and news_id=?)";

       return this.queryForBeanList(sql,collectionShowScope.toArray(),Collection.class);
   }
}

