package com.zx.qqzone.controller;

import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;
import com.zx.qqzone.pojo.Topic;
import com.zx.qqzone.service.HostReplyService;
import com.zx.qqzone.service.ReplyService;
import com.zx.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ReplyController
 * @Description TODO
 * @Author xpower
 * @Date 2022/11/1 8:50
 * @Version 1.0
 */
public class ReplyController {
    ReplyService replyService = null;
    HostReplyService hostReplyService = null;
    TopicService topicService = null;

    public String addReply(String replyId, String content, HttpSession session) {
        //session作用域中获取当前topic的id
        Topic topic = (Topic) session.getAttribute("topic");
        Integer topicId = topic.getId();
        //获取当前用户的id 也就是reply 中的 author
        BasicUser user = (BasicUser) session.getAttribute("user");
        Integer userId = user.getId();
        //获取当前回复时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(new Date());
        java.sql.Date ReplyDate = java.sql.Date.valueOf(format);
        replyService.addReply(content, ReplyDate, userId, topicId);


        //客户端重定向 给topic控制器发请求
        return "Redirect:topic.do?&operate=topicDetail&id=" + topicId;
    }

    public String addHostReply(String content, Integer id, Integer ReplyId, HttpSession session) {

        //根据当前回复获取主人回复
        HostReply hostReply = hostReplyService.getHostReplyById(ReplyId);

        //获取当前回复时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(new Date());
        java.sql.Date hostReplyDate = java.sql.Date.valueOf(format);

        //如果主人回复为空则添加主人回复
        hostReply = new HostReply(content, hostReplyDate, id, ReplyId);

        //在数据库中加入这条主人回复  重新向topic发送请求  得到完整数据
        hostReplyService.addHostReply(hostReply);

        //session中获取 topic id
        Topic topic = (Topic) session.getAttribute("topic");
        Integer topicId = topic.getId();
        return "Redirect:topic.do?operate=topicDetail&id=" + topicId;
    }

    public String delReply(Integer ReplyId, HttpSession session) {
        //获取当前要删除的reply 根据replyId 获取topic 在获取topicId  用来重定向 刷新回到当前detail的页面
        Reply reply = replyService.getReplyById(ReplyId);
        //删除 操作 删除回复包括主人回复 两个方法封装在service中
        replyService.delReply(ReplyId);

        //根据replyId 获取topic 在获取topicId
        Topic topic = topicService.getTopic(reply.getTopic());
        Integer topicId = topic.getId();
        return "Redirect:topic.do?operate=topicDetail&id="+topicId;
    }

}
