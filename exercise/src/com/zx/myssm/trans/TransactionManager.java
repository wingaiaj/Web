package com.zx.myssm.trans;

import com.zx.myssm.Utils.Connections;

import java.sql.SQLException;

/**
 * @ClassName TransactionManager
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:22
 * @Version 1.0
 */
public class TransactionManager {

    public static void TransactionBegin(){
        try {
            Connections.getConn().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void TransactionCommit(){
        try {
            Connections.getConn().commit();
            Connections.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void TransactionRollback(){
        try {
            Connections.getConn().rollback();
            Connections.getConn().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
