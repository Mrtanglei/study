package com.shiro.realm;

import com.shiro.dao.UserDao;
import com.shiro.domain.UserBean;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义realm
 */
public class CustomerRealm extends AuthorizingRealm {

    final static String SALT = "sdfsadsfdasfer";
    private final Logger logger = LoggerFactory.getLogger(CustomerRealm.class);
    @Autowired
    private UserDao userDao;

    /**
     * 权限、角色
     *
     * @param principals
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("PrimaryPrincipal=" + principals.getPrimaryPrincipal());
        System.out.println("RealmNames=" + principals.getRealmNames().toString());
        String userName = (String) principals.getPrimaryPrincipal();

        Set<String> roels = getRoles(userName);

        Set<String> permissions = getPermissions(userName);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roels);
        info.setStringPermissions(permissions);
        return info;
    }

    private Set<String> getPermissions(String userName) {
        logger.info("从数据库中获取权限数据");
        Set<String> permissions = new HashSet<String>();
        permissions.add("delete");
        permissions.add("add");
        return permissions;
    }

    private Set<String> getRoles(String userName) {
        logger.info("从数据库中获取角色数据");
        List<String> roles = userDao.getRolesByUserName(userName);
        Set<String> set = new HashSet<String>(roles);
        return set;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("Principal=" + token.getPrincipal());
        System.out.println("Credentials=" + token.getCredentials().toString());
        String userName = (String) token.getPrincipal();
        String password = getUserPassword(userName);
        if (password == null)
            return null;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, this.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(SALT));
        return info;
    }

    /**
     * 模拟数据库查询账户密码
     *
     * @param userName
     * @return
     */
    private String getUserPassword(String userName) {
        if (userName != null) {
            UserBean user = userDao.getUserByName(userName);
            if (user != null)
                return user.getPassword();
        }
        return null;
    }
}
