package com.lei.tang.observer.java;

import java.util.Observable;

/**
 * @author tanglei
 * @date 18/9/5 下午1:08
 */
public class ConcreteSubject extends Observable {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        //实现java自带的观察者模式，setChanged方法必须调用
        this.setChanged();
        //拉模型
        this.notifyObservers(content);

        //推模型
        //        this.notifyObservers();
    }
}
