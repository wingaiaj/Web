package com.zx.qqzone.service.imp;

import com.zx.qqzone.DAO.BasicUserDAO;
import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.Topic;
import com.zx.qqzone.service.BasicUserService;

import java.util.List;

/**
 * @ClassName BasicUserServiceImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 17:03
 * @Version 1.0
 */
public class BasicUserServiceImp implements BasicUserService {
    BasicUserDAO basicUserDAO = null;

    @Override
    public BasicUser login(String user, String pwd) {
        return basicUserDAO.getBaseUser(user, pwd);
    }

    @Override
    public List<BasicUser> getFriends(BasicUser basicUser) {
        return basicUserDAO.getFriendList(basicUser);

    }

    @Override
    public BasicUser getBasicUser(Integer id) {
       return basicUserDAO.getBasicUser(id);
    }

    @Override
    public String getTopicOrReplyHeadImg(Integer id) {

        BasicUser basicUser= getBasicUser(id);

        return basicUser.getHeadImg();
    }
    @Override
    public String getTopicOrReplyNickName(Integer id) {

        BasicUser basicUser= getBasicUser(id);

        return basicUser.getNickName();
    }


}
