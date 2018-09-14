package com.why.jiekou;

import com.why.shiti.User;
import com.why.shiti.UserResponse;

import java.sql.SQLException;

public interface UserInter {
    String test();
    String sayHello(String name);

    UserResponse zhuce(User user) throws ClassNotFoundException, SQLException;
}
