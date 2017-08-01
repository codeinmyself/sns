package com.puckteam.sns.interfaces.core.service;

import com.puckteam.sns.interfaces.core.vo.User;

import java.util.List;

/**
 * Created by PoemWhite on 16/9/12.
 */
public interface IUserService {


    /**
     * 设置用户关注数等信息
     * @return
     */
    public User setUserExtInfo(User user);

    /**
     * 按nickname获取用户
     * @param nickname
     * @return
     */
    public User findUserByNickname(String nickname);
    /**
     * 更新用户基本信息
     * @param user
     */
    public void updateUser(User user);

    /**
     * 更新用户密码
     * @param user
     */
    public void updatePassword(User user);

    /**
     * 更新头像
     * @param user
     */
    public void updateUserAvatar(User user);

    /**
     * 按userId获取用户
     * @param userId
     * @return
     */
    public User findUserByUserId(String userId);

    /**
     * 按email获取用户
     * @param email
     * @return
     */
    public User findUserByEmail(String email);

    /**
     * 按mobile获取用户
     * @param mobile
     * @return
     */
    public User findUserByMobile(String mobile);

    /**
     * 按user_name获取用户
     * @param name
     * @return
     */
    public User findUserByName(String name);

    /**
     * 按用户id获取信息
     */
    public List<User>  queryForOneInformationByUserId(String userId);

    public void addUser(User user);

    /**
     * 生成pka用户
     * @param username
     */
    public User generateNewPkaUser(String username);
}
