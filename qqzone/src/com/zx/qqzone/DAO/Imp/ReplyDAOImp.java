package com.zx.qqzone.DAO.Imp;

import com.zx.myssm.Utils.Connections;
import com.zx.qqzone.DAO.ReplyDAO;
import com.zx.qqzone.DAO.queryRunner.QueryRunnerUtils;
import com.zx.qqzone.pojo.HostReply;
import com.zx.qqzone.pojo.Reply;
import com.zx.qqzone.pojo.Topic;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ReplyDAOImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 14:41
 * @Version 1.0
 */
public class ReplyDAOImp implements ReplyDAO {
    QueryRunner queryRunner = QueryRunnerUtils.getQueryRunner();

    @Override
    public List<Reply> getReplyList(Integer topicId) {
        try {

            BeanListHandler<Reply> beanListHandler = new BeanListHandler<>(Reply.class);

            String sql = "select * from t_reply where topic =?";


            List<Reply> replyList = queryRunner.query(Connections.getConnections(), sql, beanListHandler, topicId);
            return replyList;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addReply(Reply reply) {

        try {
            String sql = "insert into t_reply values(0,?,?,?,?) ";
            queryRunner.update(Connections.getConnections(), sql, reply.getContent(), reply.getReplyDate(), reply.getAuthor(), reply.getTopic());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delReply(Integer replyId) {
        try {
            String sql= "delete from t_reply where id = ?";

            queryRunner.update(Connections.getConnections(),sql,replyId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Reply getReplyById(Integer replyId) {
        try {
            BeanHandler<Reply> beanHandler = new BeanHandler<>(Reply.class);

            String sql = "select * from t_reply where id = ? ";

            return queryRunner.query(Connections.getConnections(), sql, beanHandler, replyId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}