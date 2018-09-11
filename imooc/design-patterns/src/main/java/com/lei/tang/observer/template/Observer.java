package com.lei.tang.observer.template;

/**
 * @author tanglei
 * @date 18/9/4 下午2:52
 *
 * 观察者接口，定义一个更新的接口给那些在目标发生改变的时候被通知的对象
 */
public interface Observer {

    void update(Subject subject);
}
