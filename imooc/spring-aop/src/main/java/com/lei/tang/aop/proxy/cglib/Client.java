package com.lei.tang.aop.proxy.cglib;


import com.lei.tang.aop.proxy.RealSubject;
import com.lei.tang.aop.proxy.Subject;
import org.springframework.cglib.proxy.Enhancer;

public class Client {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        Subject subject = (Subject) enhancer.create();
        subject.save();
    }
}
