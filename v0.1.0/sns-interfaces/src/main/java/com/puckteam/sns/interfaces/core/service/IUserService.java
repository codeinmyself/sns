package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.User;

/**
 * Created by PoemWhite on 16/9/12.
 */
public interface IUserService {

    /**
     * 按userId获取用户
     * @param userId
     * @return
     */
    public User findUserByUserId(String userId);
}
