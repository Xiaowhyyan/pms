package com.why.sql;

import java.sql.*;

public class JdbcConnection{

    /***
     *  get connection 获取链接对象
     * @return
     */
    public static Connection getConnection() throws SQLException {
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名pms
        String url = "jdbc:mysql://127.0.0.1:3306/pms";
        // MySQL配置时的用户名
        String user1 = "root";
        // MySQL配置时的密码
        String password1 = "111111";
        Connection conn =null;
        // 加载驱动程序
        try {
            Class.forName(driver);
            // 连续数据库
             conn = DriverManager.getConnection(url, user1, password1);
            if (!conn.isClosed()) { //if 不加{} 没有写else就可以这样写
                System.out.println("Succeeded connecting to the Database!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

}
