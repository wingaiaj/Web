package com.zx.qqzone.service;

import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.Topic;

import java.util.List;

/**
 * @ClassName BasicUserService
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 16:54
 * @Version 1.0
 */
public interface BasicUserService {
    //获取登录用户
    BasicUser login(String user, String pwd);

    //获取用户好友
    List<BasicUser> getFriends(BasicUser basicUser);

    //获取
    BasicUser getBasicUser(Integer id);

    //根据日志或者回复的作者id获取当前日志作者的头像

    String getTopicOrReplyHeadImg(Integer id);

    //同上 昵称
    String getTopicOrReplyNickName(Integer id);


}
