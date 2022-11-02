package com.zx.qqzone.DAO.Imp;

import com.zx.myssm.Utils.Connections;
import com.zx.qqzone.DAO.BasicUserDAO;
import com.zx.qqzone.DAO.queryRunner.QueryRunnerUtils;
import com.zx.qqzone.pojo.BasicUser;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName BasicUserDAOImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/27 9:47
 * @Version 1.0
 */
public class BasicUserDAOImp implements BasicUserDAO {
    QueryRunner queryRunner = QueryRunnerUtils.getQueryRunner();

    @Override
    public BasicUser getBaseUser(String loginID, String pwd) {
        try {
            BeanHandler<BasicUser> beanHandler = new BeanHandler<>(BasicUser.class);
            //根据用户名获得 用户id 昵称 头像
            String sql = "select * from t_user_basic where loginId = ? and pwd = ?";
            //执行sql
            //返回用户
            return queryRunner.query(Connections.getConnections(), sql, beanHandler, loginID, pwd);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public List<BasicUser> getFriendList(BasicUser basicUser) {

        try {
            BeanListHandler<BasicUser> beanListHandler = new BeanListHandler<>(BasicUser.class);

            //根据用户 获取所有好友列表
            String sql = "select * from t_user_basic where id   = any(select fid from t_friend where uid = ?);";

            return queryRunner.query(Connections.getConnections(), sql, beanListHandler, basicUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BasicUser getBasicUser(Integer id) {

        try {
            BeanHandler<BasicUser> beanHandler = new BeanHandler<>(BasicUser.class);

            String sql = "select * from t_user_basic where id = ?";

            return queryRunner.query(Connections.getConnections(), sql, beanHandler, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
