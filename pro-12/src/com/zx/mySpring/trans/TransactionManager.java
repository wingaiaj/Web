package com.zx.mySpring.trans;

import com.zx.mySpring.Utils.Connections;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName TransactionManager
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/21 21:00
 * @Version 1.0
 */
public class TransactionManager {


    public static void beginTrans() throws SQLException {
        System.out.println(Connections.getConnections()+"事务开启");
        Connections.getConnections().setAutoCommit(false);
        System.out.println("事务开启...");
    }

    public static void commit() throws SQLException {
        System.out.println(Connections.getConnections()+"事务提交");
        Connections.getConnections().commit();
        System.out.println("事务提交...");
        Connections.closeConn();
    }

    public static void rollback() throws SQLException {
        System.out.println(Connections.getConnections()+"事务回滚");
        System.out.println("事务回滚...");
        Connections.getConnections().rollback();
        Connections.closeConn();

    }
}
