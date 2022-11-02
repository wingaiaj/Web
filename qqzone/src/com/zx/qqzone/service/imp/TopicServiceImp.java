package com.zx.qqzone.service.imp;

import com.zx.qqzone.DAO.BasicUserDAO;
import com.zx.qqzone.DAO.Imp.BasicUserDAOImp;
import com.zx.qqzone.DAO.ReplyDAO;
import com.zx.qqzone.DAO.TopicDAO;
import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;
import com.zx.qqzone.pojo.Topic;
import com.zx.qqzone.service.BasicUserService;
import com.zx.qqzone.service.ReplyService;
import com.zx.qqzone.service.TopicService;

import java.util.List;

/**
 * @ClassName TopicServiceImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 17:12
 * @Version 1.0
 */
public class TopicServiceImp implements TopicService {

    TopicDAO topicDAO = null;
    ReplyService replyService = null;
    BasicUserService basicUserService = null;

    @Override
    public List<Topic> getTopics(BasicUser basicUser) {
        return topicDAO.getTopicList(basicUser);
    }

    //此service方法中 设置topic的值 并把所有回复和主人回复查询
    @Override
    public Topic getTopic(Integer topicId) {
        Topic topic = topicDAO.getTopic(topicId);
        String topicHeadImg = basicUserService.getTopicOrReplyHeadImg(topic.getAuthor());
        String topicOrReplyNickName = basicUserService.getTopicOrReplyNickName(topic.getAuthor());
        List<Reply> replyList = replyService.getReplyList(topicId);
        topic.setReplyList(replyList);
        topic.setHeadImg(topicHeadImg);
        topic.setNickName(topicOrReplyNickName);

        return topic;
    }

    @Override
    public void addTopic(Topic topic) {
        topicDAO.addTopic(topic);
    }

    @Override
    public void delTopic(Integer topicId) {
        //根据当前日志id 获取当前日志所有回复
        List<Reply> replyList = replyService.getReplyList(topicId);
        //删除所有 回复 和 主人回复
        for (Reply reply : replyList) {
            replyService.delReply(reply.getId());
        }

        topicDAO.delTopic(topicId);
    }
}
