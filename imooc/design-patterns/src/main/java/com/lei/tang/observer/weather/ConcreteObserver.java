package com.lei.tang.observer.weather;

/**
 * @author tanglei
 * @date 18/9/4 下午2:54
 *
 * 具体的观察者对象，实现更新的方法，使自己的状态与目标状态一样
 */
public class ConcreteObserver implements Observer {

    private String weatherContent;

    private String observerName;

    private String remindThing;

    public ConcreteObserver( String observerName, String remindThing) {
        this.observerName = observerName;
        this.remindThing = remindThing;
    }

    @Override
    public void update(WeatherSubject weatherSubject) {
        weatherContent = ((ConcreteWeatherSubject) weatherSubject).getWeatherContent();
        System.out.println(observerName + "收到了天气，" + weatherContent+"，"+remindThing);
    }
}
