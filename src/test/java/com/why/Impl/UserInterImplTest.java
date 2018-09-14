//package com.why.Impl;
//
//import com.why.shiti.User;
//import com.why.sql.JdbcConnection;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserInterImplTest {
//
//    private static UserInterImpl userInterImpl = new UserInterImpl();
//    private static User user = new User();
//
//    Connection conn = JdbcConnection.getConnection();
//           Statement statement = conn.createStatement();
//
//    public UserInterImplTest() throws SQLException {
//    }
//
//    //@BeforeClass以下注解仅在该类启动前执行一次,
////    @BeforeClass
////    public static void  beforeClass(){
////        userInterImpl = new UserInterImpl();
////         user = new User();
//
////    }
//    //以下注解在每个@Test启动前执行一次
//    @Before
//    public void before() {
//        user.setName("why1203");
//        user.setAge(18);
//        user.setPassword("111111w");
//        user.setQrpassword("111111w");
//
//
//
////        // 驱动程序名
////        String driver = "com.mysql.jdbc.Driver";
////        // URL指向要访问的数据库名pms
////        String url = "jdbc:mysql://127.0.0.1:3306/pms";
////        // MySQL配置时的用户名
////        String user1 = "root";
////        // MySQL配置时的密码
////        String password1 = "123456";
////        Connection conn = null;
////        // 加载驱动程序
////        try {
////            Class.forName(driver);
////            // 连续数据库
////            conn = DriverManager.getConnection(url, user1, password1);
////
////            if (!conn.isClosed()) { //if 不加{} 没有写else就可以这样写
////                System.out.println("Succeeded connecting to the Database!");
////            }
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        Statement statement = null;
////        try {
////            statement = conn.createStatement();
////            String sql = "DELETE FROM USER where name = '"+user.getName()+"'";
////            boolean rs = statement.execute(sql);//执行sql语句
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//
//
//        try {
////            Connection conn = JdbcConnection.getConnection();
////            Statement statement = conn.createStatement();
//            // 1.清洗数据
//            String sql = "DELETE FROM USER where name = '"+user.getName()+"'";
//            boolean rs = statement.execute(sql);//执行sql语句
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    @Test
//    public void test_1_00_校验用户名_正常用户信息注册_校验注册成功数据库数据正常写入() {
//
//        String result = "";
//        try {
//            // 2、调用方法
//            result = userInterImpl.zhuce(user);
//            // 3、校验返回值，结果校验
//            Assert.assertEquals("success", result);
//
//
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败", false);
//        }
//
//        try {
//
//            String sql = "select * from user where name = '"+ user.getName()+"'";
//
//            ResultSet rs = statement.executeQuery(sql);//执行sql语句
//            List<User> userList = new ArrayList<User>();
//            while (rs.next()){
//                User user = new User();
//                user.setName(rs.getString("name"));
//                user.setAge(Integer.parseInt(rs.getString("age")));
//                user.setPassword(rs.getString("password"));
//                user.setQrpassword(rs.getString("qrpassword"));
//                userList.add(user);
//            }
//            Assert.assertEquals(1,userList.size());
//            Assert.assertEquals(user.getName(),userList.get(0).getName());
//            Assert.assertEquals(user.getAge(),userList.get(0).getAge());
//            Assert.assertEquals(user.getPassword(),userList.get(0).getPassword());
//            Assert.assertEquals(user.getQrpassword(),userList.get(0).getQrpassword());
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Test
//    public void test_1_01_校验用户名_用户名等于null_返回用户名不能为空(){
//        // 1、准备数据
//        //@BeforeClass
////        UserInterImpl userInterImpl = new UserInterImpl();
////        User user = new User();
//
//        user.setName(null);
//
//        String result="";
//        try {
//            // 2、调用方法
//            result=userInterImpl.zhuce(user);
//            // 3、校验返回值，结果校验
//            Assert.assertEquals("用户名不能为空！",result);
//            // 4、清洗数据 还未写 后续存储数据库需写
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败",false);
//        }
//    }
//    @Test
//    public void test_1_02_校验用户名_用户名等于空字符串_返回用户名不能为空(){
//
//        user.setName("");
//
//        String result="";
//        try {
//            result=userInterImpl.zhuce(user);
//            Assert.assertEquals("用户名不能为空！",result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败",false);
//        }
//    }
//    @Test
//    public void test_1_03_校验用户名_用户名为纯数字_返回用户名不能为纯数字(){
//
//    user.setName("111111");
//
//    String result = "";
//        try {
//            result = userInterImpl.zhuce(user);
//            Assert.assertEquals("用户名不能为纯数字",result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败",false);
//        }
//
//    }
//    @Test
//    public void test_1_04_校验用户名_用户名长度小于6_返回用户名长度(){
//
//        user.setName("why12");
//
//        String result = "";
//        try {
//            result = userInterImpl.zhuce(user);
//            Assert.assertEquals("长度需要为6-20",result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败",false);
//        }
//
//    }
//
//    @Test
//    public void test_1_05_校验用户名_用户名长度大于20_返回用户名长度(){
//
//        user.setName("why12why12why12why12why12");
//
//        String result = "";
//        try {
//            result = userInterImpl.zhuce(user);
//            Assert.assertEquals("长度需要为6-20",result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败",false);
//        }
//    }
//    @Test
//    public void test_1_06_校验用户名_用户名非汉字数字字母组合_返回用户名长度(){
//
//        user.setName("why1203@");
//
//        String result = "";
//        try {
//            result = userInterImpl.zhuce(user);
//            Assert.assertEquals("用户名含有特殊字符",result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败",false);
//        }
//    }
//    @Test
//    public void test_1_07_校验用户名_用户名重复注册_返回用户名不能重复注册(){
//
//        try {
//            String result = userInterImpl.zhuce(user);
//            // 以下是校验，验证结果
//            Assert.assertEquals("success",result);
//            String result1 = userInterImpl.zhuce(user);
//        Assert.assertEquals("用户名重复注册",result1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//    @Test
//    public void test_2_01_校验年龄_年龄小于18_返回年龄需要在18到70之间(){
//
//        user.setAge(16);
//
//        String result = "";
//        try {
//            result = userInterImpl.zhuce(user);
//            Assert.assertEquals("年龄需要在18-70之间",result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败",false);
//        }
//    }
//    @Test
//    public void test_2_02_校验年龄_年龄大于70_返回年龄需要在18到70之间() {
//
//
//        user.setAge(72);
//        String result = "";
//        try {
//            result = userInterImpl.zhuce(user);
//            Assert.assertEquals("年龄需要在18-70之间", result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败", false);
//        }
//    }
//
////   @Test  无法测试
////public void test_2_03_校验年龄_年龄不为纯数字_年龄必须为纯数字(){
////
////        user.setName("why8712w");
////        user.setAge();
////        user.setPassword("111222");
////        user.setQrpassword("111222");
////        String result = "";
////        try {
////        result = userInterImpl.zhuce(user);
////        Assert.assertEquals("年龄必须为纯数字",result);
////        } catch (ClassNotFoundException e) {
////        Assert.assertTrue("接口调用失败",false);
////        } catch (SQLException e) {
////        Assert.assertTrue("接口调用失败",false);
////        }
////
////    @Test
////    public void test_2_04_校验年龄_年龄等于空_返回年龄不能为空(){
////
////        user.setName("why871203");
////        user.setAge();
////        user.setPassword("111222");
////        user.setQrpassword("111222");
////        String result = "";
////        try {
////            result = userInterImpl.zhuce(user);
////            Assert.assertEquals("年龄需要在18-70之间", result);
////        } catch (ClassNotFoundException e) {
////            Assert.assertTrue("接口调用失败", false);
////        } catch (SQLException e) {
////            Assert.assertTrue("接口调用失败", false);
////        }
////
////    }
//
//
//    //以下测试包含了  密码不能为空 和 密码长度需要为6-18
//    @Test
//    public void test_3_01_校验密码_密码非数字字母组合_返回密码密码必须为字母和数字组合(){
//
//        user.setPassword("111222王");
//        user.setQrpassword("111222王");
//        String result = "";
//        try {
//            result = userInterImpl.zhuce(user);
//            Assert.assertEquals("密码必须为字母和数字组合，且密码长度需要为6-18", result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败", false);
//        }
//    }
////    @Test
////    public void test_3_02_校验密码_密码小于6位_返回用密码长度(){
////
////        user.setName("why8712014");
////        user.setAge(21);
////        user.setPassword("11122");
////        user.setQrpassword("11122");
////        String result = "";
////        try {
////            result = userInterImpl.zhuce(user);
////            Assert.assertEquals("密码长度需要为6-18", result);
////        } catch (ClassNotFoundException e) {
////            Assert.assertTrue("接口调用失败", false);
////        } catch (SQLException e) {
////            Assert.assertTrue("接口调用失败", false);
////        }
////    }
////    @Test
////    public void test_3_03_校验密码_密码大于18位_返回密码长度(){
////
////        user.setName("why8712034");
////        user.setAge(21);
////        user.setPassword("111222111222111222111222why");
////        user.setQrpassword("111222111222111222111222why");
////        String result = "";
////        try {
////            result = userInterImpl.zhuce(user);
////            Assert.assertEquals("密码长度需要为6-18", result);
////        } catch (ClassNotFoundException e) {
////            Assert.assertTrue("接口调用失败", false);
////        } catch (SQLException e) {
////            Assert.assertTrue("接口调用失败", false);
////        }
////    }
//    @Test
//    public void test_3_04_校验密码_密码不等于确认密码_返回密码和确认密码内容一致(){
//
//        user.setPassword("111222111222why");
//        user.setQrpassword("111222111222wh1");
//        String result = "";
//        try {
//            result = userInterImpl.zhuce(user);
//            Assert.assertEquals("密码和确认密码必须一致", result);
//        } catch (Exception e) {
//            Assert.assertTrue("接口调用失败", false);
//        }
//    }
//
//
//
//}
