package com.lei.tang.observer.template;

/**
 * @author tanglei
 * @date 18/9/4 下午2:50
 *
 * 具体的目标对象，负责把有关状态存入到相应的观察者对象中
 */
public class ConcreteSubject extends Subject {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObserver();
    }
}
