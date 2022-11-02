package com.zx.qqzone.service;

import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;
import com.zx.qqzone.pojo.Topic;

import java.sql.Date;
import java.util.List;

/**
 * @ClassName ReplyService
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/29 16:31
 * @Version 1.0
 */
public interface ReplyService {
    //获取日志列表所有回复
    List<Reply> getReplyList(Integer topicId);

    void addReply(String content, Date replyDate, Integer author, Integer topicId);

    Reply getReplyById(Integer replyId);

    void delReply(Integer replyId);

}

