package com.lei.tang.observer.weather;

/**
 * @author tanglei
 * @date 18/9/4 下午3:49
 */
public class Client {

    public static void main(String[] args) {
        WeatherSubject subject = new ConcreteWeatherSubject();

        ConcreteObserver concreteObserver1 = new ConcreteObserver("老妈子", "是一个购物的好日子，去天虹购物");

        ConcreteObserver concreteObserver2 = new ConcreteObserver("小妹子", "约会，地点街心公园，不见不散");

        subject.attach(concreteObserver1);
        subject.attach(concreteObserver2);

        ((ConcreteWeatherSubject) subject).setWeatherContent("天气晴朗，蓝天白云，气温28度");
    }
}
