package com.zx.qqzone.controller;

import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.Topic;
import com.zx.qqzone.service.BasicUserService;
import com.zx.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 16:52
 * @Version 1.0
 */
public class UserController {
    BasicUserService basicUserService = null;
    TopicService topicService = null;

    public String login(String loginId, String pwd, HttpSession session) {
        //验证登录
        BasicUser user = basicUserService.login(loginId, pwd);
        //获取好友列表
        List<BasicUser> friends = basicUserService.getFriends(user);
        //获取日志
        List<Topic> topics = topicService.getTopics(user);

        user.setFriendList(friends);
        user.setTopicList(topics);
        session.setAttribute("user", user);
        session.setAttribute("friend", user);


        return "index";

    }

    public String friend(Integer id, HttpSession session) {
        //根据id获取用户
        BasicUser friend = basicUserService.getBasicUser(id);
        //根据用户获取所有日志
        List<Topic> topics = topicService.getTopics(friend);
        //将所有日志设置到用户
        friend.setTopicList(topics);

        session.setAttribute("friend",friend);

        return "index";
    }
    public String RedirectPage(Integer id, HttpSession session) {
        //根据id获取用户
        BasicUser friend = basicUserService.getBasicUser(id);
        //根据用户获取所有日志
        List<Topic> topics = topicService.getTopics(friend);
        //将所有日志设置到用户
        friend.setTopicList(topics);

        session.setAttribute("friend",friend);

        return "frames/main";
    }



}
