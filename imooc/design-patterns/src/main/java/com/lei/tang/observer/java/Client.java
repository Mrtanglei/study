package com.lei.tang.observer.java;

import java.util.Observer;

/**
 * @author tanglei
 * @date 18/9/5 下午1:15
 */
public class Client {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        Observer girl = new ConcreteObserver("小妹");

        Observer mum = new ConcreteObserver("老妈子");

        subject.addObserver(girl);
        subject.addObserver(mum);

        subject.setContent("气温30℃");
    }
}
