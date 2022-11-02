package com.zx.qqzone.service;

import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.Topic;

import java.util.List;

/**
 * @ClassName TopicService
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 17:09
 * @Version 1.0
 */
public interface TopicService {

    //根据用户获取所有日志
    List<Topic> getTopics(BasicUser basicUser);

    //根据id获取当前日志

    Topic getTopic(Integer id);

    //根据当前用户添加一条日志
    void addTopic(Topic topic);

    void delTopic(Integer id);

}
