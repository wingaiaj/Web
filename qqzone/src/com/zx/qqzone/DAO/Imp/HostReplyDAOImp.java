package com.zx.qqzone.DAO.Imp;

import com.zx.myssm.Utils.Connections;
import com.zx.qqzone.DAO.HostReplyDAO;
import com.zx.qqzone.DAO.queryRunner.QueryRunnerUtils;
import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * @ClassName HostReplyDAOImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/31 9:43
 * @Version 1.0
 */
public class HostReplyDAOImp implements HostReplyDAO {

    QueryRunner queryRunner = QueryRunnerUtils.getQueryRunner();

    @Override
    public HostReply getHostReplyById(Reply reply) {
        try {

            BeanHandler<HostReply> beanHandler = new BeanHandler<>(HostReply.class);

            String sql = "select * from t_host_reply where reply =?";

            return queryRunner.query(Connections.getConnections(), sql, beanHandler, reply.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HostReply getHostReplyById(Integer replyId) {
        try {

            BeanHandler<HostReply> beanHandler = new BeanHandler<>(HostReply.class);

            String sql = "select * from t_host_reply where reply =?";

            return queryRunner.query(Connections.getConnections(), sql, beanHandler, replyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addHostReply(HostReply hostReply) {
        try {
            String sql = "insert into t_host_reply values(0,?,?,?,?)";
            queryRunner.update(Connections.getConnections(), sql, hostReply.getContent(), hostReply.getHostReplyDate(), hostReply.getAuthor(), hostReply.getReply());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delHostReply(Integer ReplyId) {
        try {
            String sql = "delete from t_host_Reply where reply = ?";
            queryRunner.update(Connections.getConnections(), sql, ReplyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


