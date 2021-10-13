package com.bjsxt.services;

import com.bjsxt.pojo.Users;

public interface UserLoginService {
    public Users userLogin(String username, String userpwd);
}
