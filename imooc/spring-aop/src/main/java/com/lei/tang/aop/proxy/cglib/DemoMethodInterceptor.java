package com.lei.tang.aop.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

@Slf4j
public class DemoMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        log.info("before");
        Object result = null;
        try {
            result = methodProxy.invokeSuper(obj, args);
        }catch (Exception e){
            log.info("get exception ",e);
            throw e;
        }finally {
            log.info("after");
        }
        return result;
    }
}
