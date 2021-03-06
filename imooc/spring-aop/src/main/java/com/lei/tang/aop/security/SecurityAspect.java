package com.lei.tang.aop.security;

import com.lei.tang.aop.service.AuthService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    AuthService authService;

    @Pointcut("@annotation(AdminOnly)")
    public void adminOnly(){

    }

    @Before("adminOnly()")
    public void checkAdmin(){
        authService.check();
    }
}
