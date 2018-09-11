package com.lei.tang.observer.weather;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tanglei
 * @date 18/9/4 下午2:49
 *
 * 目标对象，提供删除和注册观察者
 */
public class WeatherSubject {

    private List<Observer> observers = new ArrayList<>();

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    /**
     * 通知所有的观察者对象
     */
    protected void notifyObserver(){
        if(observers != null && observers.size() > 0){
            observers.forEach(observer -> {
                observer.update(this);
            });
        }
    }
}
