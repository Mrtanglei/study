package com.lei.tang.aop.proxy.dynamic;

import com.lei.tang.aop.proxy.RealSubject;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class JdkProxySubject implements InvocationHandler {

    RealSubject realSubject;

    public JdkProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("before");
        Object result = null;
        try {
            result = method.invoke(realSubject,args);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw e;
        }finally {
            log.info("after");
        }
        return result;
    }
}
