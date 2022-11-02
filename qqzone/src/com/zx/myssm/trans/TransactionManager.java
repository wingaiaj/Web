package com.zx.myssm.trans;

import com.zx.myssm.Utils.Connections;

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
        System.out.println(Connections.getConnections());
        Connections.getConnections().setAutoCommit(false);
        System.out.println("事务开启...");
    }

    public static void commit() throws SQLException {
        System.out.println(Connections.getConnections());
        Connections.getConnections().commit();
        System.out.println("事务提交...");
        Connections.closeConn();
    }

    public static void rollback() throws SQLException {
        System.out.println(Connections.getConnections());
        System.out.println("事务回滚...");
        Connections.getConnections().rollback();
        Connections.closeConn();

    }
}
