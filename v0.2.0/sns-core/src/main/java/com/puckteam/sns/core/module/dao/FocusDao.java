package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.SnsFocus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ZengJieHang on 2016/10/11.
 */
@Repository
public class FocusDao extends BaseDao
{
    //通过userId查找关注列表
   public  List<SnsFocus> findFocusListByUserID(String userId)
    {
        String sql = "select" +
            " sns_focus.focus_id," +
            " sns_focus.user_id," +
            " sns_focus.focus_user_id," +
            " sns_focus.group_id," +
            " date_format(sns_focus.create_time,'%Y-%m-%d %H:%i:%s') as createTime,"+
            " sns_user.user_name as userName,"+
            " sns_user.avatar as userAvatar," +
            " sns_user.nickname as userNickname," +
            " sns_user.signature as userSignature"  +
            " from sns_focus,sns_user " +
            " where sns_focus.user_id=? and sns_user.user_id = sns_focus.focus_user_id"+
            " order by sns_focus.group_id";

        List<SnsFocus> snsFocusList = (List<SnsFocus>)this.queryForBeanList(
                sql, new Object[]{userId}, SnsFocus.class);

        return snsFocusList;
    }

    //通过用户Id查找粉丝列表
    public List<SnsFocus> findSnsFocusByFocusUserId(String focusUserId)
    {
        String sql = "select" +
                " sns_focus.focus_id," +
                " sns_focus.user_id," +
                " sns_focus.focus_user_id," +
                " sns_focus.group_id," +
                " date_format(sns_focus.create_time,'%Y-%m-%d %H:%i:%s') as createTime,"+
                " sns_user.user_name as userName,"+
                " sns_user.avatar as userAvatar," +
                " sns_user.nickname as userNickname," +
                " sns_user.signature as userSignature"  +
                " from sns_focus,sns_user " +
                " where sns_focus.focus_user_id=? and sns_user.user_id = sns_focus.user_id";


        List<SnsFocus> snsFocusList = (List<SnsFocus>)this.queryForBeanList(
                sql, new Object[]{focusUserId}, SnsFocus.class);

        return snsFocusList;
    }


    public int updateFocusGroupId(String groupId)
    {
        String sql="update sns_focus set group_id=null where group_id=?";

        return this.executeUpdate(sql,new Object[]{groupId});
    }

    public Map findUntitledFocusNumberByUserId(String userId)
    {
        String sql =  "select " +
                " count(focus_id) "+
                " from sns_focus "+
                " where group_id is null and user_id=?";

        return this.queryForMap(sql, new Object[]{userId});
    }

    //往数据库中新增关注
    public int insertFocusItem(SnsFocus snsFocus)
    {
        if(snsFocus.getGroupId()!=null)
        {
            String sql = "insert into sns_focus(" +
                    "focus_id,user_id,focus_user_id,group_id,create_time" +
                    ") values (?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";

            return this.executeUpdate(sql, new Object[]{
                    snsFocus.getFocusId(),
                    snsFocus.getUserId(),
                    snsFocus.getFocusUserId(),
                    snsFocus.getGroupId(),
                    this.getSysdate()});
        }
        else
        {
            String sql = "insert into sns_focus(" +
                    "focus_id,user_id,focus_user_id,create_time" +
                    ") values (?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";

            return this.executeUpdate(sql, new Object[]{
                    snsFocus.getFocusId(),
                    snsFocus.getUserId(),
                    snsFocus.getFocusUserId(),
                    this.getSysdate()});
        }
    }

    //删除数据库中的一项关注
    public int deleteFocusItem(String focusId)
    {
        String sql = "delete from sns_focus "+
                "where focus_id = ?";

        return this.executeUpdate(sql, new Object[]{focusId});
    }

    public int cancelFocusItemByUserId(String userId,String focusUserId)
    {
        String sql="delete from sns_focus" +
                " where user_id=? and focus_user_id=?";
        return this.executeUpdate(sql,new Object[]{userId,focusUserId});
    }

    //修改数据库中关注的分组Id
    public int updateFocusItem(String focusId,String groupId)
    {
        String sql="update sns_focus "+
                " set group_id = ?" +
                " where focus_id = ?";
        return this.executeUpdate(sql,new Object[]{groupId,focusId});
    }

    //判断被关注的用户是否存在，存在返回true，不存在返回false
    public Map isFocusUserIdExist(String userId)
    {
        String sql="select count(*) as number "+
                    " from sns_user "+
                    " where user_id=?";

        return this.queryForMap(sql,new Object[]{userId});
    }

    //判断用户的分组是否存在，存在返回true，不存在返回false
    public Map isGroupIdExist(String userId,String groupId)
    {
        String sql="select count(*) as number "+
                " from sns_focus_group "+
                " where create_user_id=? and group_id=?";

        return this.queryForMap(sql,new Object[]{userId,groupId});
    }

    //判断是否已经关注，已关注返回true，未关注返回false
    public Map hasFocus(String userId,String focusUserId)
    {
        String sql="select count(*) as number "+
                    " from sns_focus "+
                    " where user_id=? and focus_user_id=?";

        return this.queryForMap(sql,new Object[]{userId,focusUserId});
    }

    //判断FocusId是否存在，存在返回true，不存在返回false
    public Map isFocusIdExist(String focusId)
    {
        String sql="select count(*) as number "+
                    " from sns_focus "+
                    " where focus_id=?";

        return this.queryForMap(sql,new Object[]{focusId});
    }
}
