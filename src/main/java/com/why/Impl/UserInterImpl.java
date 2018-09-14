package com.why.Impl;

import com.why.constrant.UserConstant;
import com.why.jiekou.UserInter;
import com.why.shiti.User;
import com.why.shiti.UserResponse;
import com.why.sql.JdbcConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public  class UserInterImpl implements UserInter {

    private static final Logger log = Logger.getLogger(UserInterImpl.class);


    @Override
    public String test() {
        return "hello";
    }

    public String sayHello(String name) {
        return "Hello " + name;
        }
        //判断为纯数字
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    //判断为非汉字，数字，字母组合，即ture不为特殊字符
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{6,18}$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    public static void insertUserdata(User user,int status){


        try {
            Connection conn = JdbcConnection.getConnection();
            Statement statement = conn.createStatement();
            //先初始化失败状态是1
            user.setStatus(status);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            String sql="insert into user(Name,Age,password,qrpassword,status,createTime,updateTime) values('"+user.getName()+"',"+user.getAge()+",'"+user.getPassword()+"','"+user.getQrpassword()+"','"+user.getStatus()+"','"+user.getCreateTime()+"','"+user.getUpdateTime()+"')";
            boolean rs = statement.execute(sql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("why1203");
        user.setAge(18);
        user.setPassword("111111w");
        user.setQrpassword("111111w");
        try {
            UserResponse userResponse =new UserInterImpl().zhuce(user);
            System.out.println(userResponse);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserResponse zhuce(User user) throws ClassNotFoundException, SQLException {
        log.info("用户开始注册,user = " + user);
        if(user.getName()== null || user.getName().equals("")){
            UserInterImpl.insertUserdata(user,1);
            return new UserResponse(UserConstant.USERNAME_NOT_NULL_MESSAGE,UserConstant.LOGIN_STATUS_CODE_fAIL);
        }

        if(user.getName().length() < 6 ||user.getName().length() > 20){
            UserInterImpl.insertUserdata(user,1);
            return new UserResponse(UserConstant.USERNAME_LENGHT_LIMIT_MESSAGE,UserConstant.LOGIN_STATUS_CODE_fAIL);
        }

        if(isNumeric(user.getName())){
            UserInterImpl.insertUserdata(user,1);
            return new UserResponse(UserConstant.USERNAME_NOT_ALL_DIGIT_MESSAGE,UserConstant.LOGIN_STATUS_CODE_fAIL);
        }

        if(isSpecialChar(user.getName())){
            UserInterImpl.insertUserdata(user,1);
            return new UserResponse(UserConstant.USERNAME_CONTAINS_SPECIAL_CHARACTERS_MESSAGE,UserConstant.LOGIN_STATUS_CODE_fAIL);
        }


        if(user.getAge() < 18 ||user.getAge() > 70){
            UserInterImpl.insertUserdata(user,1);
            return new UserResponse(UserConstant.AGE_LIMIT,UserConstant.LOGIN_STATUS_CODE_fAIL);
        }

//   接口无法测试，页面可以测试
//     if(!isNumeric(String.valueOf(user.getAge()))){
//            return "年龄必须为纯数字";
//        }

//        if(String.valueOf(user.getAge())== null || String.valueOf(user.getAge()).equals("")){
//            return "年龄不能为空！";
//        }

//        if(user.getPassword()== null || user.getPassword().equals("")){
//            return "密码不能为空！";
//        }
//
//        if(user.getPassword().length() < 6 ||user.getPassword().length() > 18){
//            return "密码长度需要为6-18";
//        }

    //以下逻辑包含了  密码不能为空 和 密码长度需要为6-18
        if (!isLetterDigit(user.getPassword())){
            UserInterImpl.insertUserdata(user,1);
            return new UserResponse(UserConstant.PASSWORD_LIMIT,UserConstant.LOGIN_STATUS_CODE_fAIL);
        }


        if (!user.getPassword().equals(user.getQrpassword())){
            UserInterImpl.insertUserdata(user,1);
            return new UserResponse(UserConstant.PASSWORD_EQUALS_CONFIRMPASSWORD,UserConstant.LOGIN_STATUS_CODE_fAIL);
        }

        // 调用方法获取连接
        Connection conn = JdbcConnection.getConnection();
        // statement用来执行SQL语句
        Statement statement = conn.createStatement();
        String sqlselect = "select * from user where name = '"+ user.getName() + "'";

        //executeQuery只能用于查询，execute方法才可以执行insert，update，delete操作。
       ResultSet rs = statement.executeQuery(sqlselect);
        List<User> list = new ArrayList<User>();

       while (rs.next()){
           User user1 = new User();
           user1.setName(rs.getString("name"));
           user1.setAge(Integer.parseInt(rs.getString("age")));
           user1.setPassword(rs.getString("password"));
           user1.setQrpassword(rs.getString("qrpassword"));
           user1.setStatus(Integer.parseInt(rs.getString("status")));
           list.add(user1);

       }
        conn.close();

        //用户名不能重复注册
       if (list.size() >= 1){
           UserInterImpl.insertUserdata(user,1);
           return new UserResponse(UserConstant.USERNAME_DUPLICATE_REGISTRATION_MESSAGE,UserConstant.LOGIN_STATUS_CODE_fAIL);
       }

//        // 要执行的SQL语句 insert into user(Name,Age,password,qrpassword) values('xiao',2,'aa','aa');
//
//        String sql="insert into user(Name,Age,password,qrpassword,status) values('"+user.getName()+"',"+user.getAge()+",'"+user.getPassword()+"','"+user.getQrpassword()+"','"+user.getStatus()+"')";
//
//        statement.executeUpdate(sql);//执行sql语句

        UserInterImpl.insertUserdata(user,0);

        return new UserResponse(UserConstant.REGISTER_SUCCESS_MESSAGE,UserConstant.LOGIN_STATUS_CODE_SUCCESS);

    }

}

