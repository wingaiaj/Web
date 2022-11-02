package com.zx.qqzone.DAO;

import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.Reply;
import com.zx.qqzone.pojo.Topic;

import java.util.List;

/**
 * @ClassName TopicDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/26 20:58
 * @Version 1.0
 */
public interface TopicDAO {
    //根据用户获取所有日志
    List<Topic> getTopicList(BasicUser basicUser);

    //添加日志
    void addTopic(Topic topic);

    //删除日志
     void delTopic(Integer topicId);

    //获取指定日志
    Topic getTopic(Integer id);


}
