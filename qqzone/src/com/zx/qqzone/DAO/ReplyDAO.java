package com.zx.qqzone.DAO;

import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;
import com.zx.qqzone.pojo.Topic;

import java.util.List;

/**
 * @ClassName ReplyDAO
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 9:13
 * @Version 1.0
 */
public interface ReplyDAO {
    //根据日志获取所有回复
    List<Reply> getReplyList(Integer topicId);

    //添加回复
    void addReply(Reply reply);

    //删除回复
    void delReply(Integer replyId);

    //根据回复id获取当前回复
    Reply getReplyById(Integer replyId);

}
