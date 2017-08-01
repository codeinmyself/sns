package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.base.util.StringClass;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.SnsFocusGroup;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ZengJieHang on 2016/10/25.
 */
@Repository
public class FocusGroupDao extends BaseDao
{
    //通过用户Id查找分组
    public List<SnsFocusGroup> findSnsFocusGroupListByUserId(String userId)
    {
        String sql = "select " +
                " group_id," +
                " group_name," +
                " create_user_id," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime" +
                " from sns_focus_group where create_user_id=? " +
                " order by group_id";

        List<SnsFocusGroup> snsFocusGroupList = (List<SnsFocusGroup>)this.queryForBeanList(
                sql, new Object[]{userId}, SnsFocusGroup.class);

        return snsFocusGroupList;
    }

    public Map findFocusNumberByGroupId(SnsFocusGroup snsFocusGroup)
    {
        String sql =  "select " +
                " count(focus_id) "+
                " from sns_focus "+
                " where group_id=?";

        return this.queryForMap(sql,new Object[]{snsFocusGroup.getGroupId()});
    }


    //往数据库中插入分组
    public int insertNewGroup(SnsFocusGroup snsFocusGroup)
    {
        String sql = "insert into sns_focus_group(" +
                "group_id,group_name,create_user_id,create_time" +
                ") values (?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'))";

        return this.executeUpdate(sql, new Object[]{
                snsFocusGroup.getGroupId(),
                snsFocusGroup.getGroupName(),
                snsFocusGroup.getCreateUserId(),
                this.getSysdate()}
        );
    }

    //更新数据库分组名称
    public int updateGroup(String groupId,String groupName)
    {
        String sql = "update sns_focus_group "+
                "set group_name = ? "+
                "where group_id = ?";

        return this.executeUpdate(sql, new Object[]{
                groupName,groupId}
        );
    }

    //删除数据库的某个分组
    public  int deleteGroup(String groupId)
    {
        String sql = "delete from sns_focus_group "+
                "where group_id = ?";

        return this.executeUpdate(sql, new Object[]{groupId});
    }

    //判断某个分组名称是否已存在，存在返回true，不存在返回false
    public Map isGroupNameExist(String userId,String groupName)
    {
        String sql=" select count(*) as number "+
                   " from sns_focus_group "+
                   " where create_user_id = ? and group_name =? ";

        return this.queryForMap(sql,new Object[]{userId,groupName});
    }

    //判断某个分组ID是否已存在，存在返回true，不存在返回false
    public Map isGroupIdExist(String userId,String groupId)
    {
        String sql=" select count(*) as number "+
                " from sns_focus_group "+
                " where create_user_id = ? and group_id =? ";

        return this.queryForMap(sql,new Object[]{userId,groupId});
    }
}
