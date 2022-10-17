package com.zx.mySpring.Utils;

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
 * @Date 2022/10/15 19:17
 * @Version 1.0
 */
public class Connections {
   private static Connection connection = null ;
     private  static DataSource dataSource = null;

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
        public static Connection getConnection() {

            if (connection == null) {
                try {
                    connection = dataSource.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
                return connection;
        }

}
