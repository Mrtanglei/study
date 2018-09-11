package com.shiro.dao;

import com.shiro.domain.UserBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public UserBean getUserByName(String userName) {
        String sql = "select * from test_users where username = ?";
        List<UserBean> list = jdbcTemplate.query(sql, new Object[]{userName}, new RowMapper<UserBean>() {
            public UserBean mapRow(ResultSet resultSet, int i) throws SQLException {
                UserBean bean = new UserBean();
                bean.setUsername(resultSet.getString("username"));
                bean.setPassword(resultSet.getString("password"));
                return bean;
            }
        });
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    public List<String> getRolesByUserName(String userName) {
        String sql = "select * from test_user_roles where username = ?";
        return jdbcTemplate.query(sql, new Object[]{userName}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });

    }
}
