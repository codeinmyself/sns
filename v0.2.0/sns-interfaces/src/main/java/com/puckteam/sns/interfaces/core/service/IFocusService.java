package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.SnsFocus;

import java.util.List;

/**
 * Created by ZengJieHang on 2016/10/13.
 */
public interface IFocusService
{

    List<SnsFocus> findSnsFocusByUserId(String userId);

    List<SnsFocus> findSnsFocusByFocusUserId(String focusUserId);

    int updateFocusGroupId(String groupId);

    String findUntitledFocusNumberByUserId(String userId);

    int addFocusItem(SnsFocus snsFocus);

    int cancelFocusItem(String focusId);

    int cancelFocusItemByUserId(String userId, String focusUserId);

    int removeFocusItemToGroup(String focusId, String groupId);

    boolean isFocusUserIdExist(String userId);

    boolean isGroupIdExist(String userId, String groupId);

    boolean hasFocused(String userId, String focusUserId);

    boolean isFocusIdExist(String focusId);
}
