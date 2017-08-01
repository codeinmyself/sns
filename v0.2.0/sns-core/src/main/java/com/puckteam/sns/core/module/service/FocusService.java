package com.puckteam.sns.core.module.service;

import com.puckteam.sns.base.util.StringClass;
import com.puckteam.sns.core.module.dao.FocusDao;
import com.puckteam.sns.interfaces.core.service.IFocusService;
import com.puckteam.sns.interfaces.core.vo.SnsFocus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by ZengJieHang on 2016/10/13.
 */
@Service("focusService")
@Transactional
public class FocusService implements IFocusService
{
    @Autowired
    private FocusDao snsFocusDao;

    //通过用户Id查找关注列表
   public  List<SnsFocus> findSnsFocusByUserId(String userId)
    {
        return this.snsFocusDao.findFocusListByUserID(userId);
    }

    //通过用户Id查找粉丝列表
    public  List<SnsFocus> findSnsFocusByFocusUserId(String focusUserId)
    {
        List<SnsFocus> snsFocusList=this.snsFocusDao.findSnsFocusByFocusUserId(focusUserId);
        if(snsFocusList!=null&&snsFocusList.size()>0)
        {
            for(int i=0;i<snsFocusList.size();++i)
            {
                Map map=this.snsFocusDao.hasFocus(snsFocusList.get(i).getFocusUserId(),snsFocusList.get(i).getUserId());
                if(Integer.parseInt(map.get("number").toString())>0)
                    snsFocusList.get(i).setEachOther(true);
                else
                    snsFocusList.get(i).setEachOther(false);
            }
        }
        return snsFocusList;
    }

    public String findUntitledFocusNumberByUserId(String userId)
    {
        Map map =this.snsFocusDao.findUntitledFocusNumberByUserId(userId);
        if (map != null) {
            return StringClass.getString(map.get("count(focus_id)"));
        }
        else
        {
            return "0";
        }
    }

    public int updateFocusGroupId(String groupId)
    {
        return this.snsFocusDao.updateFocusGroupId(groupId);
    }

    //新增关注
    public int addFocusItem(SnsFocus snsFocus)
    {
        return this.snsFocusDao.insertFocusItem(snsFocus);
    }

    //取消关注
    public int cancelFocusItem(String focusId)
    {
        return this.snsFocusDao.deleteFocusItem(focusId);
    }


    public int cancelFocusItemByUserId(String userId,String focusUserId)
    {
        return this.snsFocusDao.cancelFocusItemByUserId(userId,focusUserId);
    }
    //移动关注的分组
    public int removeFocusItemToGroup(String focusId,String groupId)
    {
        return this.snsFocusDao.updateFocusItem(focusId,groupId);
    }

    //判断被关注的用户是否存在
    public boolean isFocusUserIdExist(String userId)
    {
        Map map= this.snsFocusDao.isFocusUserIdExist(userId);

        if(Integer.parseInt(map.get("number").toString())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //判断用户Id的分组Id是否存在
    public boolean isGroupIdExist(String userId,String groupId)
    {
        Map map= this.snsFocusDao.isGroupIdExist(userId,groupId);

        if(Integer.parseInt(map.get("number").toString())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //判断该用户是否已经关注被关注用户
    public boolean hasFocused(String userId,String focusUserId)
    {
        Map map=this.snsFocusDao.hasFocus(userId,focusUserId);
        if(Integer.parseInt(map.get("number").toString())>0)
            return true;
        else
            return false;
    }

    //判断关注Id是否存在
    public boolean isFocusIdExist(String focusId)
    {
        Map map= this.snsFocusDao.isFocusIdExist(focusId);

        if(Integer.parseInt(map.get("number").toString())>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
