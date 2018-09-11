package com.lei.tang.aop.proxy.dynamic;

import com.lei.tang.aop.proxy.RealSubject;
import com.lei.tang.aop.proxy.Subject;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        //通过Proxy代理机制得到代理类
        //Subject：需要代理的类，JdkProxySubject：静态代理机制，RealSubject：代理类的具体实现
        Subject subject = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(), new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));
        subject.save();
        subject.delete();
    }
}
