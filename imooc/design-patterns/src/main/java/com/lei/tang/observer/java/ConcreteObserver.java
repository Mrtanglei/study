package com.lei.tang.observer.java;

import java.util.Observable;
import java.util.Observer;

/**
 * @author tanglei
 * @date 18/9/5 下午1:11
 */
public class ConcreteObserver implements Observer {

    private String observerName;

    public ConcreteObserver(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(observerName+"推送的消息："+arg);
        System.out.println(observerName+"拉取的消息："+(((ConcreteSubject)o).getContent()));
    }
}
