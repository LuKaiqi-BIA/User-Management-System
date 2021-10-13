package com.bjsxt.dao.impl;

import com.bjsxt.commons.JdbcUtils;
import com.bjsxt.dao.UserManagerDao;
import com.bjsxt.pojo.Users;
import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserManagerDaoImpl implements UserManagerDao {
    @Override
    public void insertUser(Users users) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values(default ,?,?,?,?,? )");
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getUserpwd());
            preparedStatement.setString(3, users.getUsersex());
            preparedStatement.setString(4, users.getPhonenumber());
            preparedStatement.setString(5, users.getQqnumber());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackConnection(connection);
        } finally {
            JdbcUtils.closeConnection(connection);
        }
    }

    @Override
    public List<Users> selectUserByProperty(Users users) {
        Connection connection = null;
        List<Users> list = new ArrayList<>();
        try {
            connection = JdbcUtils.getConnection();
            String sql = this.createSQL(users);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Users user = new Users();
                user.setUsersex(resultSet.getString("usersex"));
                user.setUserid(resultSet.getInt("userid"));
                user.setUsername(resultSet.getString("username"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
                user.setUserpwd(resultSet.getString("userpwd"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(connection);
        }
        return list;
    }

    /**
     * 根据用户id查询用户
     * @param userid
     * @return
     */
    @Override
    public Users selectUserByUserId(int userid) {
        Connection connection = null;
        Users user = null;
        try {
            connection = JdbcUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where userid = ?");
            preparedStatement.setInt(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new Users();
                user.setUserid(resultSet.getInt("userid"));
                user.setPhonenumber(resultSet.getString("phonenumber"));
                user.setQqnumber(resultSet.getString("qqnumber"));
                user.setUserpwd(resultSet.getString("userpwd"));
                user.setUsername(resultSet.getString("username"));
                user.setUsersex(resultSet.getString("usersex"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(connection);
        }
        return user;
    }

    @Override
    public void updateUserById(Users users) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("update users set username=?, usersex=?, phonenumber=?, qqnumber=? where userid=?");
            preparedStatement.setString(1,users.getUsername());
            preparedStatement.setString(2,users.getUsersex());
            preparedStatement.setString(3,users.getPhonenumber());
            preparedStatement.setString(4,users.getQqnumber());
            preparedStatement.setInt(5,users.getUserid());
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackConnection(connection);
        } finally {
            JdbcUtils.closeConnection(connection);
        }
    }

    /**
     * 根据id删除用户
     * @param userId
     */
    @Override
    public void deleUserById(int userId) {
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where userid =?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            JdbcUtils.rollbackConnection(connection);
        } finally {
            JdbcUtils.closeConnection(connection);
        }
    }

    // 拼接查询的SQL语句
    private String createSQL(Users users) {
        StringBuffer stringBuffer = new StringBuffer("select * from users where 1=1");
        if (users.getUsersex() != null && users.getUsersex().length() > 0) {
            stringBuffer.append(" and usersex = '" + users.getUsersex() + "'");
        }
        if (users.getUsername() != null && users.getUsername().length() > 0) {
            stringBuffer.append(" and username = '" + users.getUsername() + "'");
        }
        if (users.getPhonenumber() != null && users.getPhonenumber().length() > 0) {
            stringBuffer.append(" and phonenumber = '" + users.getPhonenumber() + "'");
        }
        if (users.getQqnumber() != null && users.getQqnumber().length() > 0) {
            stringBuffer.append(" and qqnumber = '" + users.getQqnumber() + "'");
        }
        return stringBuffer.toString();
    }
}
