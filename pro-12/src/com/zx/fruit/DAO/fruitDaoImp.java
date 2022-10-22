package com.zx.fruit.DAO;

import com.zx.fruit.fruitexception.FruitException;
import com.zx.fruit.pojo.Fruit;
import com.zx.mySpring.Utils.Connections;
import org.apache.commons.dbutils.DbUtils;
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
    QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<Fruit> QueryList(int pageNumber, String keyword) {

        try {
            String sql = "SELECT fid,fname,price,fcount,remark FROM fruit WHERE fname LIKE ? OR remark LIKE ? LIMIT ?,6";

            BeanListHandler<Fruit> fruitBeanListHandler = new BeanListHandler<>(Fruit.class);

            List<Fruit> query = null;

            query = queryRunner.query(Connections.getConnections(), sql, fruitBeanListHandler, "%" + keyword + "%", "%" + keyword + "%", (pageNumber - 1) * 6);

            System.out.println(Connections.getConnections()+"qlist");////////////////

            return query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO有问题");
        }
    }


    @Override
    public Fruit Query(int id)  {

        try {
            String sql = "select fid,fname,price,fcount,remark  from fruit where fid = ?";

            BeanHandler<Fruit> beanHandler = new BeanHandler<>(Fruit.class);

            Fruit fruit = null;


            fruit = queryRunner.query(Connections.getConnections(), sql, beanHandler, id);

            return fruit;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO有问题");
        }
    }

    @Override
    public boolean insert(Fruit fruit) {

        try {
            String sql = "insert into fruit values(0,?,?,?,?)";

            int update = 0;


            update = queryRunner.update(Connections.getConnections(), sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark());


            if (update > 0) {
                return true;
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO有问题");
        }
    }

    @Override
    public Long count(String keyword) {

        try {
            ScalarHandler ScalarHandler = new ScalarHandler<>();

            String sql = "select count(*) from fruit where fname like ? or remark like ?";

            Object query = null;

            query = queryRunner.query(Connections.getConnections(), sql, ScalarHandler, "%" + keyword + "%", "%" + keyword + "%");

            System.out.println(Connections.getConnections()+"count");////////////////

            return (Long) query;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO有问题");
        }
    }

    @Override
    public boolean update(Fruit fruit){
        try {
            String sql = "updat fruit set fname=?,price=?,fcount=?,remark=? where fid=?";

            int update = 0;

            update = queryRunner.update(Connections.getConnections(), sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());

            if (update > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
           throw new FruitException("DAO有问题");
        }

        return false;
    }

    @Override
    public boolean del(int id) {

        try {
            String sql = "delete from fruit where fid = ?";
            int update = 0;

            update = queryRunner.update(Connections.getConnections(), sql, id);


            if (update > 0) {
                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO有问题");
        }
        return false;
    }
}
