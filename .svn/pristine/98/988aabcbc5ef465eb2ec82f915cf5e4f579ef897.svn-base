package com.puckteam.sns.core.module.service;

import com.puckteam.sns.base.util.MD5;
import com.puckteam.sns.base.util.SequenceUtil;
import com.puckteam.sns.core.module.dao.UserDao;
import com.puckteam.sns.core.support.encrypt.Encrypt;
import com.puckteam.sns.core.support.encrypt.EncryptSHA;
import com.puckteam.sns.interfaces.core.service.IUserService;
import com.puckteam.sns.interfaces.core.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by PoemWhite on 16/9/12.
 */
@Service("userService")
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    /**
     * 按nickname获取用户
     * @param nickname
     * @return
     */
    public User findUserByNickname(String nickname){

        return userDao.findUserByNickname(nickname);
    }
    /**
     * 更新头像
     * @param user
     */
    public void updateUserAvatar(User user){
        userDao.updateUserAvatar(user);
    }
    /**
     * 更新用户基本信息
     * @param user
     */
    public void updateUser(User user){
        userDao.updateUser(user);
    }

    /**
     * 更新用户密码
     * @param user
     */
    public void updatePassword(User user){
        userDao.updatePassword(user);
    }

    /**
     * 按userId获取用户
     * @param userId
     * @return
     */
    public User findUserByUserId(String userId) {

        return userDao.findUserByUserId(userId);
    }


    /**
     * 按email获取用户
     * @param email
     * @return
     */
    public User findUserByEmail(String email)
    {
        return userDao.findUserByEmail(email);
    }

    /**
     * 按mobile获取用户
     * @param mobile
     * @return
     */
    public User findUserByMobile(String mobile)
    {
        return userDao.findUserByMobile(mobile);
    }

    /**
     * 按user_name获取用户
     * @param name
     * @return
     */
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * 设置用户关注数等信息
     * @return
     */
    public User setUserExtInfo(User user){

        if(user!=null){
            user.setFocusNumber(userDao.findFocusNumberByUserId(user.getUserId()));
            user.setFansNumber(userDao.findFansNumberByUserId(user.getUserId()));
            user.setNewsNumber(userDao.findNewsNumberByUserId(user.getUserId()));
            user.setNoticeNumber(userDao.findNoticeNumberByUserId(user.getUserId()));
        }
        return user;
    }

    /**
     * 按用户id获取信息
     */
    public List<User>  queryForOneInformationByUserId(String userId){
        return userDao.queryForBeanListOneInformationByUserId(userId);
    }

    /**
     * 生成pka用户
     * @param username
     */
    public User generateNewPkaUser(String username){

        Encrypt encrypt = EncryptSHA.getInstances();

        String defaultPwd = "666666";
//        String encryptPwd = encrypt.encrypt(defaultPwd);

        User user = new User();
        user.setAvatar("/user/default_avatar.png");
        user.setUserId(SequenceUtil.uuid2());
        user.setUserName(username);
        user.setPassword(MD5.getHashString(defaultPwd));
        user.setNickname(username);

        this.addUser(user);

        return user;
    }
}
