package com.puckteam.sns.core.module.service;

import com.puckteam.sns.core.module.dao.UserDao;
import com.puckteam.sns.interfaces.core.service.IUserService;
import com.puckteam.sns.interfaces.core.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by PoemWhite on 16/9/12.
 */
@Service("userService")
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    /**
     * 按userId获取用户
     * @param userId
     * @return
     */
    public User findUserByUserId(String userId){
        return userDao.findUserByUserId(userId);
    }
}
