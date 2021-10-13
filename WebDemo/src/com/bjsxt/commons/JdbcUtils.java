package com.bjsxt.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JdbcUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    static{
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("db");
            driver = bundle.getString("jdbc.driver");
            url = bundle.getString("jdbc.url");
            username = bundle.getString("jdbc.username");
            password = bundle.getString("jdbc.password");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接方法
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 事务回滚
    public static void rollbackConnection(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
