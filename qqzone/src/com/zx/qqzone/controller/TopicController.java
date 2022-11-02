package com.zx.qqzone.controller;

import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.Topic;
import com.zx.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName TopicController
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/29 16:14
 * @Version 1.0
 */
public class TopicController {
    TopicService topicService = null;

    public String topicDetail(Integer id, HttpSession session) {

        //根据id获取当前日志 包括回复 主人回复
        Topic topic = topicService.getTopic(id);

        session.setAttribute("topic", topic);

        return "frames/detail";

    }

    public String addTopic(Integer id,String title,String content, HttpSession session) {
        //获取当前user的id
        //获取当前回复时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(new Date());
        java.sql.Date TopicDate = java.sql.Date.valueOf(format);
        //调用service方法 添加一篇日志
        topicService.addTopic(new Topic(title,content,TopicDate,id));

        return "Redirect:user.do?operate=RedirectPage&id=" + id;
    }

    public String delTopic(Integer id,HttpSession session){
        //根据当前topicId 删除当前topic
        topicService.delTopic(id);
        //获取当前用户id 根据当前用户id 重定向
        BasicUser user = (BasicUser) session.getAttribute("user");
        Integer userId = user.getId();
        //客户端重定向
        return "Redirect:user.do?operate=RedirectPage&id=" + userId;
    }
}
