package com.why.constrant;


public class UserConstant {

    public static final String USERNAME_NOT_NULL_MESSAGE= "用户名不能为空！";
    public static final String USERNAME_LENGHT_LIMIT_MESSAGE = "长度需要为6-20";
    public static final String USERNAME_NOT_ALL_DIGIT_MESSAGE = "用户名不能为纯数字";
    public static final String USERNAME_CONTAINS_SPECIAL_CHARACTERS_MESSAGE ="用户名含有特殊字符";
    public static final String  USERNAME_DUPLICATE_REGISTRATION_MESSAGE = "用户名重复注册";

    public static final String AGE_LIMIT = "年龄需要在18-70之间";
    public static final String PASSWORD_LIMIT = "密码必须为字母和数字组合，且密码长度需要为6-18";
    public static final String PASSWORD_EQUALS_CONFIRMPASSWORD = "密码和确认密码必须一致";

    public static final String REGISTER_SUCCESS_MESSAGE = "用户注册成功";

    public static final String LOGIN_STATUS_CODE_SUCCESS = "00";
    public static final String LOGIN_STATUS_CODE_fAIL = "99";

}
