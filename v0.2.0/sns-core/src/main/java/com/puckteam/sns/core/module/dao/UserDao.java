package com.puckteam.sns.core.module.dao;

import com.puckteam.sns.base.util.StringClass;
import com.puckteam.sns.core.constant.CoreConstant;
import com.puckteam.sns.core.support.mvc.db.BaseDao;
import com.puckteam.sns.interfaces.core.vo.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by PoemWhite on 16/9/12.
 */
@Repository
public class UserDao extends BaseDao {


    public static final String USER_COLUMN_LIST =
            " user.user_id," +
            " user.user_name," +
            " user.password," +
            " user.email," +
            " user.mobile," +
            " user.nickname," +
            " user.avatar," +
            " user.is_artist," +
            " user.signature, " +
            " date_format(user.create_time,'%Y-%m-%d %H:%i:%s') createTime," +
            " user.is_locked";


    /**
     * 按userId获取用户
     * @param userId
     * @return
     */
    public User findUserByUserId(String userId)
    {
        String sql = "select " +
                USER_COLUMN_LIST +
                " from sns_user user where user_id=?";

        User bbsUser = (User) this.queryForBean(
                sql, new Object[]{userId}, User.class);

        return bbsUser;
    }


    /**
     * 按邮箱和密码查询用户
     * @param email
     * @param password
     * @return
     *
     */
    public User findUserByEmailAndPassword(String email, String password) {
        String sql = "select " +
                USER_COLUMN_LIST +
                " from sns_user user where email=? and password=?";

        User bbsUser = (User) this.queryForBean(
                sql, new Object[]{email,password}, User.class);

        return bbsUser;
    }

    /**
     * 按手机号和密码查询用户
     * @param mobile
     * @param password
     * @return
     *
     */
    public User findUserByMobileAndPassword(String mobile, String password) {
        String sql = "select " +
                USER_COLUMN_LIST +
                " from sns_user user where mobile=? and password=?";

        User bbsUser = (User) this.queryForBean(
                sql, new Object[]{mobile,password}, User.class);

        return bbsUser;
    }

    /**
     * 按userName和password获取用户
     * @param userName
     * @param password
     * @return
     */
    public User findUserByuserNameAndPassword(String userName, String password){
        String sql = "select " +
                USER_COLUMN_LIST +
                " from sns_user user where user_name=? and password=?";

        User bbsUser = (User) this.queryForBean(
                sql, new Object[]{userName,password}, User.class);

        return bbsUser;
    }


    /**
     * 按邮箱查询用户
     * @param email
     * @return
     *
     */
    public User findUserByEmail(String email)
    {
        String sql = "select " +
                USER_COLUMN_LIST +
                " from sns_user user where email=? ";

        User bbsUser = (User) this.queryForBean(
                sql, new Object[]{email}, User.class);

        return bbsUser;
    }

    /**
     * 按手机号查询用户
     * @param mobile
     * @return
     *
     */
    public User findUserByMobile(String mobile)
    {
        String sql = "select " +
                USER_COLUMN_LIST +
                " from sns_user user where mobile=? ";

        User bbsUser = (User) this.queryForBean(
                sql, new Object[]{mobile}, User.class);

        return bbsUser;
    }

    /**
     * 按用户名查询用户
     * @param name
     * @return
     *
     */
    public User findUserByName(String name)
    {
        String sql = "select " +
                USER_COLUMN_LIST +
                " from sns_user user where user_name=? ";

        User bbsUser = (User) this.queryForBean(
                sql, new Object[]{name}, User.class);

        return bbsUser;
    }

    public String findFocusNumberByUserId(String userId)
    {
        String sql="select "+
                "count(focus_id) as focus_numbers " +
                "from sns_focus "+
                "where user_id = ?";

        Map map=this.queryForMap(sql,new Object[]{userId});
        if(map!=null)
        {
            return StringClass.getString(map.get("focus_numbers"));
        }
        return "0";
    }

    public String findFansNumberByUserId(String userId)
    {
        String sql="select "+
                "count(focus_id) as fans_numbers " +
                "from sns_focus "+
                "where focus_user_id = ?";

        Map map=this.queryForMap(sql,new Object[]{userId});
        if(map!=null)
        {
            return StringClass.getString(map.get("fans_numbers"));
        }
        return "0";
    }

    public String findNewsNumberByUserId(String userId)
    {
        String sql="select "+
                "count(news_id) " +
                "from sns_news "+
                "where create_user_id = ? and status = ?";

        Map map=this.queryForMap(sql,new Object[]{userId, CoreConstant.Status.NORMAL});
        if(map!=null)
        {
            return StringClass.getString(map.get("count(news_id)"));
        }
        return "0";
    }

    public String findNoticeNumberByUserId(String userId)
    {
        String sql="select "+
                "count(notice_id) as number " +
                "from sns_notice "+
                "where receive_user_id = ? and read_status = ?";

        Map map=this.queryForMap(sql,new Object[]{userId, CoreConstant.NoticeReadStatus.NOT_READ});
        if(map!=null)
        {
            return StringClass.getString(map.get("number"));
        }
        return "0";
    }


    public void addUser(User user) {

        String sql = "insert into sns_user(" +
                "user_id,user_name,password,email,mobile,nickname,avatar,signature,is_artist,create_time,is_locked " +
                ") values (?,?,?,?,?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?)";

        this.executeUpdate(sql, new Object[]{
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getMobile(),
                user.getNickname(),
                user.getAvatar(),
                user.getSignature(),
                user.getIsArtist(),
                this.getSysdate(),
                user.getIsLocked()
        });
    }

    /**
     * 邮箱注册
     * @param user
     * @return
     */
    public void email_insert(User user) {
        if (user.getMobile() != "") {
            String sql = "insert into sns_user(" +
                    "user_id,email,password,user_name,nickname,create_time,avatar,is_artist,mobile" +
                    ") values (?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?,?)";
            this.executeUpdate(sql, new Object[]{
                    user.getUserId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserName(),
                    user.getNickname(),
                    this.getSysdate(),
                    user.getAvatar(),
                    user.getIsArtist(),
                    user.getMobile()});
        }else {
            String sql = "insert into sns_user(" +
                    "user_id,email,password,user_name,nickname,create_time,avatar,is_artist" +
                    ") values (?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?)";
            this.executeUpdate(sql, new Object[]{
                    user.getUserId(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getUserName(),
                    user.getNickname(),
                    this.getSysdate(),
                    user.getAvatar(),
                    user.getIsArtist()});
        }
    }


    /**
     * 手机号注册
     * @param user
     * @return
     */
    public void mobile_insert(User user){
        if(user.getEmail()!="") {
            String sql = "insert into sns_user(" +
                    "user_id,mobile,password,user_name,nickname,create_time,avatar,is_artist,email" +
                    ") values (?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?,?)";
            this.executeUpdate(sql, new Object[]{
                    user.getUserId(),
                    user.getMobile(),
                    user.getPassword(),
                    user.getUserName(),
                    user.getNickname(),
                    this.getSysdate(),
                    user.getAvatar(),
                    user.getIsArtist(),
                    user.getEmail()
            });
        }else {
            String sql = "insert into sns_user(" +
                    "user_id,mobile,password,user_name,nickname,create_time,avatar,is_artist" +
                    ") values (?,?,?,?,?,str_to_date(?,'%Y-%m-%d %H:%i:%s'),?,?)";
            this.executeUpdate(sql, new Object[]{
                    user.getUserId(),
                    user.getMobile(),
                    user.getPassword(),
                    user.getUserName(),
                    user.getNickname(),
                    this.getSysdate(),
                    user.getAvatar(),
                    user.getIsArtist()
            });
        }
    }

    /**
     * 按nickname获取用户
     * @param nickname
     * @return
     */
    public User findUserByNickname(String nickname){

        String sql = "select " +
                " user_id,user_name,nickname,avatar,email,password,signature " +
                " from sns_user where nickname=?";

        User user = (User) this.queryForBean(
                sql, new Object[]{nickname}, User.class);

        return user;
    }
    /**
     * 按昵称查找用户组
     * @param nickname
     * @return
     */
    public List<User> findUserListByNickname(String nickname){

        String sql = "select *"+
                " from sns_user where nickname like ?";
        nickname=nickname.replaceAll("[\\p{P}\\p{Space}]+", "");
        nickname="%"+nickname+"%";

        List<User> userlist= this.queryForBeanList(sql,new Object[]{nickname},User.class);
        return userlist;
    }
    /**
     * 更新用户基本信息
     * @param user
     */
    public void updateUser(User user){
        String sql = "update sns_user set " +
                " nickname=?," +
                " signature=? " +
                " where user_id =? ";

        this.executeUpdate(sql,new Object[]{
                user.getNickname(),
                user.getSignature(),
                user.getUserId()
        });
    }

    /**
     * 更新用户密码
     * @param user
     */
    public void updatePassword(User user){
        String sql = "update sns_user set " +
                " password=?" +
                " where user_id =? ";

        this.executeUpdate(sql,new Object[]{
                user.getPassword(),
                user.getUserId()
        });
    }

    /**
     * 更新头像
     * @param user
     */
    public void updateUserAvatar(User user){
        String sql = "update sns_user set avatar =? where user_id = ?";
        this.executeUpdate(sql,new Object[]{
                user.getAvatar(),
                user.getUserId()
        });
    }

    /**
     * 按用户id获取信息
     */
    public List<User> queryForBeanListOneInformationByUserId(String userId){
        String sql = "select " +
                " user_id," +
                " user_name," +
                " nickname," +
                " avatar," +
                " is_artist," +
                " signature" +
                " from sns_user where user_id=?";
        List<User> userList = this.queryForBeanList(sql,new Object[]{userId},User.class);
        return userList;
    }


}
