package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class CustomerRealmTest {

    @Test
    public void testSimpleAccountRealm() {

        CustomerRealm realm = new CustomerRealm();

        //加密方式
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("md5");
        //设置加密次数
        matcher.setHashIterations(1);
        realm.setCredentialsMatcher(matcher);

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager(realm);

        UsernamePasswordToken token = new UsernamePasswordToken("wanger", "1234567");

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        subject.checkRole("user");
        subject.checkPermission("delete");
    }
}
