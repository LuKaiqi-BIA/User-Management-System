package com.bjsxt.dao;

import com.bjsxt.pojo.Users;

public interface UserLoginDao {
    public Users selectUsersByUsernameAndUserPwd(String username, String userPwd);
}
