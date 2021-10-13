package com.bjsxt.services.impl;

import com.bjsxt.dao.UserLoginDao;
import com.bjsxt.dao.impl.UserLoginDaoImpl;
import com.bjsxt.exceptions.UserNotFoundException;
import com.bjsxt.pojo.Users;
import com.bjsxt.services.UserLoginService;

/**
 * 用户登录业务
 */
public class UserLoginServiceImpl implements UserLoginService {
    /**
     * 用户登录
     * @param username
     * @param userpwd
     * @return
     */
    @Override
    public Users userLogin(String username, String userpwd) {
        UserLoginDao userLoginDao = new UserLoginDaoImpl();
        Users users = userLoginDao.selectUsersByUsernameAndUserPwd(username, userpwd);
        if (users == null) {
            throw new UserNotFoundException("用户名或密码有误！");
        }
        return users;
    }
}
