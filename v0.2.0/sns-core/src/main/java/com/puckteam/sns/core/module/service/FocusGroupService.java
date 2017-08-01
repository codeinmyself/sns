package com.puckteam.sns.core.module.service;

import com.puckteam.sns.base.util.StringClass;
import com.puckteam.sns.core.module.dao.FocusGroupDao;
import com.puckteam.sns.interfaces.core.service.IFocusGroupService;
import com.puckteam.sns.interfaces.core.vo.SnsFocus;
import com.puckteam.sns.interfaces.core.vo.SnsFocusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by ZengJieHang on 2016/10/25.
 */
@Service("focusGroupService")
@Transactional
public class FocusGroupService implements IFocusGroupService
{
    @Autowired
    private FocusGroupDao snsFocusGroupDao;

    //通过用户Id查找分组
    public List<SnsFocusGroup> findSnsFocusGroupListByUserId(String userId)
    {
       List<SnsFocusGroup> snsFocusGroupList=this.snsFocusGroupDao.findSnsFocusGroupListByUserId(userId);
        if(snsFocusGroupList!=null&&snsFocusGroupList.size()>0)
        {
            for (int i = 0; i < snsFocusGroupList.size(); ++i) {
                Map map = this.snsFocusGroupDao.findFocusNumberByGroupId(snsFocusGroupList.get(i));
                if (map != null) {
                    snsFocusGroupList.get(i).setFocusNumber(StringClass.getString(map.get("count(focus_id)")));
                }
            }
        }
        return snsFocusGroupList;
    }

    //创建分组
    public int createNewGroup(SnsFocusGroup snsFocusGroup)
    {
        return this.snsFocusGroupDao.insertNewGroup(snsFocusGroup);
    }

    //修改分组
    public  int modifyGroup(String groupId,String groupName)
    {
        return this.snsFocusGroupDao.updateGroup(groupId,groupName);
    }

    //删除分组
    public int deleteGroup(String groupId)
    {
        return this.snsFocusGroupDao.deleteGroup(groupId);
    }

    //判断用户的分组名称是否存在
    public boolean isGroupNameExist(String userId,String groupName)
    {
        Map map= this.snsFocusGroupDao.isGroupNameExist(userId,groupName);
        if(Integer.parseInt(map.get("number").toString())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //判断用户的分组Id是否存在
    public boolean isGroupIdExist(String userId,String groupId)
    {
        Map map =this.snsFocusGroupDao.isGroupIdExist(userId,groupId);
        if(Integer.parseInt(map.get("number").toString())>0)
        {
            return true;
        }
        else
        {
            return  false;
        }
    }

}
