package com.zx.mySpring.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName Connections
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/15 19:17
 * @Version 1.0
 */
public class Connections {
    private static DataSource dataSource = null;
    private static  ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        InputStream resourceAsStream = Connections.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnections() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                 connection = dataSource.getConnection();
                threadLocal.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return threadLocal.get();
    }

    public static void closeConn() {
        System.out.println("关闭连接");
        Connection connection = threadLocal.get();
        if (connection == null) {
            return;
        }
        try {
            if (!connection.isClosed()) {
                connection.close();
                threadLocal.remove();
//                threadLocal.set(null);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
