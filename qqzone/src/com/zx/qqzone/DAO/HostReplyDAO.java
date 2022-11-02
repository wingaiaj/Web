package com.zx.qqzone.DAO;

import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;

/**
 * @ClassName HostReplyDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 9:46
 * @Version 1.0
 */
public interface HostReplyDAO {
    //获取当前回复的主人回复
    HostReply getHostReplyById(Reply reply);
    HostReply getHostReplyById(Integer replyId);

     void addHostReply(HostReply hostReply);

     void delHostReply(Integer ReplyId);
}
