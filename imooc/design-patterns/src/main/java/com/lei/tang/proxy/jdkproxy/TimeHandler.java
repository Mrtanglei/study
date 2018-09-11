package com.lei.tang.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tanglei
 * @date 18/9/3 下午4:59
 */
public class TimeHandler implements InvocationHandler {

    private Object target;

    public TimeHandler(Object target) {
        this.target = target;
    }

    /**
     * jdk动态代理
     * @param proxy 被代理的对象
     * @param method 被代理的方法
     * @param args 被代理的方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车开始行驶");
        Object invoke = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println("汽车停止行驶，行驶了"+(endTime-startTime)+"毫秒");
        return invoke;
    }
}
