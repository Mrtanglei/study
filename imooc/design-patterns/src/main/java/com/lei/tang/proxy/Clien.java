package com.lei.tang.proxy;

/**
 * @author tanglei
 * @date 18/9/3 下午4:28
 */
public class Clien {

    public static void main(String[] args) {
        //使用集成方式
//        MoveAble moveAble = new Car1();
//        moveAble.move();
        //使用聚合方式
//        Car car = new Car();
//        MoveAble moveAble = new CarTimeProxy(car);
//        moveAble.move();
        //叠加代理
        MoveAble car = new Car();
        MoveAble time = new CarTimeProxy(car);
        MoveAble log = new CarLogProxy(time);
        log.move();
    }
}
