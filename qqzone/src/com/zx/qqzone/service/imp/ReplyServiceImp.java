package com.zx.qqzone.service.imp;

import com.zx.qqzone.DAO.ReplyDAO;
import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;
import com.zx.qqzone.pojo.Topic;
import com.zx.qqzone.service.BasicUserService;
import com.zx.qqzone.service.HostReplyService;
import com.zx.qqzone.service.ReplyService;

import java.sql.Date;
import java.util.List;

/**
 * @ClassName ReplyServiceImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/29 16:31
 * @Version 1.0
 */
public class ReplyServiceImp implements ReplyService {
    ReplyDAO replyDAO = null;
    HostReplyService hostReplyService = null;
    BasicUserService basicUserService = null;

    @Override
    public List<Reply> getReplyList(Integer topicId) {
        List<Reply> replyList = replyDAO.getReplyList(topicId);
        for (Reply reply : replyList) {
            String topicOrReplyHeadImg = basicUserService.getTopicOrReplyHeadImg(reply.getAuthor());
            String topicOrReplyNickName = basicUserService.getTopicOrReplyNickName(reply.getAuthor());
            HostReply hostReply = hostReplyService.getHostReplyById(reply);
            reply.setHeadImg(topicOrReplyHeadImg);
            reply.setHostReply(hostReply);
            reply.setNickName(topicOrReplyNickName);

        }
        return replyList;
    }

    @Override
    public void addReply(String content, Date replyDate, Integer author, Integer topicId) {
            replyDAO.addReply(new Reply(content,replyDate,author,topicId));
    }

    @Override
    public Reply getReplyById(Integer replyId) {
          return replyDAO.getReplyById(replyId);
    }

    @Override
    public void delReply(Integer replyId) {
        hostReplyService.delHostReply(replyId);
        replyDAO.delReply(replyId);
    }
}
