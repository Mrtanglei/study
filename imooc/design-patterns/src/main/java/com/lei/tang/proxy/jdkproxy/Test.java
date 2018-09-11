package com.lei.tang.proxy.jdkproxy;

import com.lei.tang.proxy.Car;
import com.lei.tang.proxy.MoveAble;

import java.lang.reflect.Proxy;

/**
 * @author tanglei
 * @date 18/9/3 下午5:02
 */
public class Test {

    public static void main(String[] args) {
        Car car = new Car();
        TimeHandler timeHandler = new TimeHandler(car);
//        LogHandler logHandler = new LogHandler(timeHandler);
        MoveAble moveAble = (MoveAble) Proxy.newProxyInstance(car.getClass().getClassLoader(), car.getClass().getInterfaces(), timeHandler);
        moveAble.move();

    }
}
