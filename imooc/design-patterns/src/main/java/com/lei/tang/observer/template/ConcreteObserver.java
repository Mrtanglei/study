package com.lei.tang.observer.template;

/**
 * @author tanglei
 * @date 18/9/4 下午2:54
 *
 * 具体的观察者对象，实现更新的方法，使自己的状态与目标状态一样
 */
public class ConcreteObserver implements Observer {

    private String status;

    @Override
    public void update(Subject subject) {
        status = ((ConcreteSubject)subject).getStatus();
    }
}
