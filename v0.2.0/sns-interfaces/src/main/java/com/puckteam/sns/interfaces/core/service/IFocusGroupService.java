package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.SnsFocus;
import com.puckteam.sns.interfaces.core.vo.SnsFocusGroup;

import java.util.List;

/**
 * Created by ZengJieHang on 2016/10/25.
 */
public interface IFocusGroupService
{
    List<SnsFocusGroup> findSnsFocusGroupListByUserId(String userId);

    int createNewGroup(SnsFocusGroup snsFocusGroup);

    int modifyGroup(String groupId, String groupName);

    int deleteGroup(String groupId);

    boolean isGroupNameExist(String userId, String groupName);

    boolean isGroupIdExist(String userId, String groupId);


}
