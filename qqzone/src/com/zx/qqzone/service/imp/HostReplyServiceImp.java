package com.zx.qqzone.service.imp;

import com.zx.qqzone.DAO.HostReplyDAO;
import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;
import com.zx.qqzone.service.HostReplyService;

/**
 * @ClassName HostReplyServiceImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/31 9:48
 * @Version 1.0
 */
public class HostReplyServiceImp implements HostReplyService {
    HostReplyDAO hostReplyDAO = null;

    @Override
    public HostReply getHostReplyById(Reply reply) {
        return hostReplyDAO.getHostReplyById(reply);
    }

    @Override
    public HostReply getHostReplyById(Integer replyId) {
        return hostReplyDAO.getHostReplyById(replyId);
    }

    @Override
    public void addHostReply(HostReply hostReply) {
        hostReplyDAO.addHostReply(hostReply);
    }

    @Override
    public void delHostReply(Integer ReplyId) {
        hostReplyDAO.delHostReply(ReplyId);
    }
}
