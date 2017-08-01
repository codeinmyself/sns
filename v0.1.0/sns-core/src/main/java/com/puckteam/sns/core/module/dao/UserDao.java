package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.User;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by PoemWhite on 16/9/12.
 */
@Repository
public class UserDao extends BaseDao {

    public List<User> test(){

        String nickname = "昵称";

        String sql = "select * from sns_user where nickname = ? ";

        List<User> userList = this.queryForBeanList(sql,new Object[]{nickname},User.class);

        return userList;
    }

    public void testInsert(){

        String sql = "insert into sns_user(user_id,user_name) values (?,?)";

        int result = this.executeUpdate(sql,new Object[]{"我是user_id","我是user_name"});


    }


    /**
     * 按userId获取用户
     * @param userId
     * @return
     */
    public User findUserByUserId(String userId){

        String sql = "select " +
                " user_id," +
                " user_name," +
                " password," +
                " email," +
                " mobile," +
                " nickname," +
                " avatar," +
                " is_artist," +
                " date_format(create_time,'%Y-%m-%d %H:%i:%s') as createTime " +
                " from sns_user where user_id=?";

        User bbsUser = (User) this.queryForBean(
                sql, new Object[]{userId}, User.class);

        return bbsUser;
    }

}
