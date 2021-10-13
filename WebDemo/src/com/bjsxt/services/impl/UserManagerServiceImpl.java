package com.bjsxt.services.impl;

import com.bjsxt.dao.UserManagerDao;
import com.bjsxt.dao.impl.UserManagerDaoImpl;
import com.bjsxt.pojo.Users;
import com.bjsxt.services.UserManagerService;

import java.util.List;


/**
 * 用户管理业务层
 */
public class UserManagerServiceImpl implements UserManagerService {
    @Override
    public void addUser(Users users) {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        userManagerDao.insertUser(users);
    }

    @Override
    public List<Users> finderUser(Users users) {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        return userManagerDao.selectUserByProperty(users);

    }


    @Override
    public Users findUserById(int userid) {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        return userManagerDao.selectUserByUserId(userid);
    }

    @Override
    public void modifyUser(Users users) {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        userManagerDao.updateUserById(users);
    }

    @Override
    public void dropUser(int userid) {
        UserManagerDao userManagerDao = new UserManagerDaoImpl();
        userManagerDao.deleUserById(userid);
    }
}
