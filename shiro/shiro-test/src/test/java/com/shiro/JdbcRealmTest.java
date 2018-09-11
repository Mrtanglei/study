package com.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        dataSource.setUrl("jdbc:mysql://127.0.1:3306/shiro?useUnicode=true&characterEncoding=utf8");
    }

    /**
     * 默认用户、角色、权限校验
     */
    @Test
    public void testJdbcRealm() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //加载权限开关，默认为false
        jdbcRealm.setPermissionsLookupEnabled(true);

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager(jdbcRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        System.out.println("isAuthenticated：" + subject.isAuthenticated());

        //验证角色
        subject.checkRole("admin");
        //验证权限
        subject.checkPermission("user:delete");
    }

    /**
     * 自定义表校验
     */
    @Test
    public void testJdbcRealmForSql() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);

        //自定义账户查询
        jdbcRealm.setAuthenticationQuery("select password from test_users where username = ?");

        //自定义角色查询
        jdbcRealm.setUserRolesQuery("select role_name from test_user_roles where username = ?");

        //自定义权限查询
        jdbcRealm.setPermissionsQuery("select permission from test_roles_permissions where role_name = ?");


        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager(jdbcRealm);


        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();


        UsernamePasswordToken token = new UsernamePasswordToken("xiaoming", "123456");
        subject.login(token);

        System.out.println("isAuthenticated：" + subject.isAuthenticated());

        subject.checkRole("admin");
        subject.checkPermission("user:update");
    }
}
