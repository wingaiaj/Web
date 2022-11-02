package com.zx.qqzone.DAO.Imp;

import com.zx.myssm.Utils.Connections;
import com.zx.qqzone.DAO.TopicDAO;
import com.zx.qqzone.DAO.queryRunner.QueryRunnerUtils;
import com.zx.qqzone.pojo.BasicUser;
import com.zx.qqzone.pojo.Topic;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName TopicDAOImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 15:17
 * @Version 1.0
 */
public class TopicDAOImp implements TopicDAO {
    QueryRunner queryRunner = QueryRunnerUtils.getQueryRunner();

    @Override
    public List<Topic> getTopicList(BasicUser basicUser) {
        try {
            BeanListHandler<Topic> beanListHandler = new BeanListHandler<>(Topic.class);
            String sql = "select * from t_topic where author = ?";

            return queryRunner.query(Connections.getConnections(), sql, beanListHandler, basicUser.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addTopic(Topic topic) {
        try {
            String sql = "insert into t_topic values(0,?,?,?,?)";
            queryRunner.update(Connections.getConnections(), sql, topic.getTitle(), topic.getContent(), topic.getTopicDate(), topic.getAuthor());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delTopic(Integer topicId) {

        try {
            String sql = "delete from t_topic where id= ? ";
            queryRunner.update(Connections.getConnections(), sql, topicId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Topic getTopic(Integer id) {
        try {
            BeanHandler<Topic> beanHandler = new BeanHandler(Topic.class);

            String sql = "select * from t_topic where id = ?";
            return queryRunner.query(Connections.getConnections(), sql, beanHandler, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
