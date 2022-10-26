package com.zx.myssm.Utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName Connections
 * @Description TODO
 * @Author xpower
 * @Date 2022/10/22 21:23
 * @Version 1.0
 */
public class Connections {

    private static DataSource dataSource = null;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            InputStream druidPropertiesIs = Connections.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(druidPropertiesIs);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        try {
            if (null == threadLocal.get()) {
                Connection connection = dataSource.getConnection();
                threadLocal.set(connection);
            }

            return threadLocal.get();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConn() {
        try {
            if (threadLocal.get() == null) {
                return;
            }
            if(!threadLocal.get().isClosed()){
                threadLocal.get().close();
                threadLocal.remove();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
