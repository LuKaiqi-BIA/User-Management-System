package com.bjsxt.services;

import com.bjsxt.pojo.Users;

import java.util.List;

public interface UserManagerService {
    void addUser(Users users);

    List<Users> finderUser(Users users);

    Users findUserById(int userid);

    void modifyUser(Users users);

    void dropUser(int userid);
}
