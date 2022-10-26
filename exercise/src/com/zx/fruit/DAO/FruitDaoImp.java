package com.zx.fruit.DAO;

import com.zx.fruit.fruitexception.FruitException;
import com.zx.fruit.pojo.Fruit;
import com.zx.myssm.Utils.Connections;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName FruitDaoImp
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:17
 * @Version 1.0
 */
public class FruitDaoImp implements FruitDao {
    QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        try {
            BeanListHandler<Fruit> beanListHandler = new BeanListHandler<>(Fruit.class);
            String sql = "select fid,fname,price as fprice,fcount,remark from fruit where fname like ? or remark like ? limit ?,10 ";

            List<Fruit> fruitList = queryRunner.query(Connections.getConn(), sql, beanListHandler, "%" + keyword + "%", "%" + keyword + "%", (pageNo - 1) * 10);

            return fruitList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO出错");
        }
    }

    @Override
    public Fruit getFruit(Integer fid) {
        try {
            String sql = "select fid,fname,price as fprice,fcount,remark from fruit where fid =?";
            BeanHandler<Fruit> beanHandler = new BeanHandler<>(Fruit.class);

            Fruit fruit = queryRunner.query(Connections.getConn(), sql, beanHandler, fid);

            return fruit;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO出错");
        }
    }

    @Override
    public Long fruitCount(String keyword) {
        try {
            String sql = "select count(*) from fruit where fname like ? or remark like ?";
            ScalarHandler scalarHandler = new ScalarHandler<>();

            Object countObj = queryRunner.query(Connections.getConn(), sql, scalarHandler, "%" + keyword + "%", "%" + keyword + "%");

            if (null != countObj) {
                return  (Long) countObj;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO出错");
        }
        return null;
    }

    @Override
    public void addFruit(Fruit fruit) {

        try {
            String sql = "insert into fruit values(0,?,?,?,?)";

            int update = queryRunner.update(Connections.getConn(), sql, fruit.getFname(), fruit.getFprice(), fruit.getFcount(), fruit.getRemark());
            System.out.println(update);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO出错");
        }
    }

    @Override
    public void Update(Fruit fruit) {

        try {
            String sql = "update fruit set fname = ?,price = ?,fcount = ?,remark = ? where fid = ?";
            int update = queryRunner.update(Connections.getConn(), sql, fruit.getFname(), fruit.getFprice(), fruit.getFcount(), fruit.getRemark(), fruit.getFid());
            System.out.println(update);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO出错");
        }
    }

    @Override
    public void del(Integer fid) {

        try {
            String sql = "delete from fruit where fid = ?";

            queryRunner.update(Connections.getConn(),sql,fid);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FruitException("DAO出错");
        }
    }
}
