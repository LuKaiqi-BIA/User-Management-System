package com.bjsxt.dao.impl;

import com.bjsxt.commons.JdbcUtils;
import com.bjsxt.dao.UserLoginDao;
import com.bjsxt.pojo.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserLoginDaoImpl implements UserLoginDao {
    /**
     * 用户登录的数据库查询
     * @param username
     * @param userPwd
     * @return
     */
    @Override
    public Users selectUsersByUsernameAndUserPwd(String username, String userPwd) {
        Users users = null;
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where username = ? and userpwd = ? ");
            ps.setString(1, username);
            ps.setString(2, userPwd);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                users = new Users();
                users.setUsersex(resultSet.getString("usersex"));
                users.setUserpwd(resultSet.getString("userpwd"));
                users.setUsername(resultSet.getString("username"));
                users.setUserid(resultSet.getInt("userid"));
                users.setPhonenumber(resultSet.getString("phonenumber"));
                users.setQqnumber(resultSet.getString("qqnumber"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(connection);
        }
        return users;
    }
}
