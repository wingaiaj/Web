package com.zx.qqzone.DAO;

import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.Topic;

import java.util.List;

/**
 * @ClassName BasicUserDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/26 20:55
 * @Version 1.0
 */
public interface BasicUserDAO {
    //获取用户登录信息
   BasicUser getBaseUser(String loginID,String pwd);

    //根据用户获取好友列表
    List<BasicUser> getFriendList(BasicUser basicUser);

    BasicUser getBasicUser(Integer id);

}
