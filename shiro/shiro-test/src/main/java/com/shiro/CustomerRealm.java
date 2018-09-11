package com.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 自定义realm
 */
public class CustomerRealm extends AuthorizingRealm {

    final static String SALT = "sdfsadsfdasfer";
    Map<String, String> users = new HashMap<String, String>(16);

    {
        users.put("mazi", "e97159e4d990f4dbcbec1201a9b30992");
        users.put("wanger", "be882a3d09c67ba663d8a69b569a0317");

        super.setName("customerRealm");
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("1234567", SALT);
        System.out.println(md5Hash);
    }

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
        Set<String> permissions = new HashSet<String>();
        permissions.add("delete");
        permissions.add("add");
        return permissions;
    }

    private Set<String> getRoles(String userName) {
        Set<String> roles = new HashSet<String>();
        roles.add("user");
        return roles;
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
            return users.get(userName);
        }
        return null;
    }
}
