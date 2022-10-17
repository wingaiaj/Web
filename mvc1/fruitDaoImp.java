package com.zx.fruit.DAO;
import com.zx.fruit.pojo.Fruit;
import com.zx.mySpring.Utils.Connections;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName fruitDaoImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/12 20:21
 * @Version 1.0
 */
public class fruitDaoImp implements fruitDao {
    QueryRunner queryRunner =new QueryRunner();
       Connection connection = Connections.getConnection();
    //获取数据库中所有水果
    @Override
    public List<Fruit> QueryList(int pageNumber, String keyword) {

        String sql = "SELECT fid,fname,price,fcount,remark FROM fruit WHERE fname LIKE ? OR remark LIKE ? LIMIT ?,6";

        BeanListHandler<Fruit> fruitBeanListHandler = new BeanListHandler<>(Fruit.class);

        List<Fruit> query = null;

        try {
            query = queryRunner.query(connection, sql, fruitBeanListHandler,"%"+keyword+"%","%"+keyword+"%",(pageNumber-1)*6);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return query;
    }

    //根据id查询单条水果数据
    @Override
    public Fruit Query( int id) {

        String sql = "select fid,fname,price,fcount,remark  from fruit where fid = ?";

        BeanHandler<Fruit> beanHandler = new BeanHandler<>(Fruit.class);

        Fruit fruit = null;

        try {
            fruit = queryRunner.query(connection, sql, beanHandler, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fruit;
    }

    @Override
    public boolean insert( Fruit fruit) {

        String sql = "insert into fruit values(0,?,?,?,?)";

        int update = 0;

        try {
            update = queryRunner.update(connection, sql,fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (update > 0) {
            return true;
        }

        return false;
    }

    @Override
    public Long count(String keyword) {

        ScalarHandler ScalarHandler = new ScalarHandler<>();

        String sql = "select count(*) from fruit where fname like ? or remark like ?";

        Object query = null;

        try {
            query = queryRunner.query(connection, sql, ScalarHandler,"%"+keyword+"%","%"+keyword+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (Long) query;
    }

    @Override
    public boolean update( Fruit fruit) {
        String sql = "update fruit set fname=?,price=?,fcount=?,remark=? where fid=?";

        int update = 0;
        try {
            update = queryRunner.update(connection, sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (update > 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean del( int id) {

        String sql = "delete from fruit where fid = ?";
        int update = 0;
        try {
            update = queryRunner.update(connection, sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(update>0){
            return true;

        }
        return false;
    }
}
