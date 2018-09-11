package com.shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 授权filter AuthorizationFilter
 * 角色filter RolesAuthorizationFilter
 * 权限filter PermissionsAuthorizationFilter
 */
public class RolesFilter extends AuthorizationFilter {

    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);
        String[] roles = (String[]) o;
        if (roles == null || roles.length == 0)
            return true;
        for (String role : roles) {
            if (subject.hasRole(role) || subject.isPermitted(role))
                return true;
        }
        return false;
    }
}
