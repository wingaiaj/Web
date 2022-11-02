package com.zx.qqzone.service;

import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;

/**
 * @ClassName HostReplyServiceImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/31 9:47
 * @Version 1.0
 */
public interface HostReplyService {

    HostReply getHostReplyById(Reply reply);

    HostReply getHostReplyById(Integer replyId);

     void addHostReply(HostReply hostReply);

    void delHostReply(Integer ReplyId);
}
