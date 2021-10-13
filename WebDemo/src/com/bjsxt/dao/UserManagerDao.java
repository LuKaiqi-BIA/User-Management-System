package com.bjsxt.dao;

import com.bjsxt.pojo.Users;

import java.util.List;

public interface UserManagerDao {
    // 完成用户添加的方法
    void insertUser(Users users);

    List<Users> selectUserByProperty(Users users);

    Users selectUserByUserId(int userid);

    void updateUserById(Users users);

    void deleUserById(int userId);
}
