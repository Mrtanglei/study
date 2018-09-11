package com.lei.tang.observer.weather;

/**
 * @author tanglei
 * @date 18/9/4 下午2:50
 *
 * 具体的目标对象，负责把有关状态存入到相应的观察者对象中
 */
public class ConcreteWeatherSubject extends WeatherSubject {

    private String weatherContent;

    public String getWeatherContent() {
        return weatherContent;
    }

    public void setWeatherContent(String weatherContent) {
        this.weatherContent = weatherContent;
        this.notifyObserver();
    }
}
