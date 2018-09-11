package com.lei.tang.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tanglei
 * @date 18/9/3 下午5:58
 */
public class LogHandler implements InvocationHandler {

    private Object target;

    public LogHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("汽车开始日志");
        Object invoke = method.invoke(target, args);
        System.out.println("汽车结束日志");
        return invoke;
    }
}
