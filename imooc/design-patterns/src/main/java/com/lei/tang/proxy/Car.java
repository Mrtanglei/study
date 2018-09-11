package com.lei.tang.proxy;

import java.util.Random;

/**
 * @author tanglei
 * @date 18/9/3 下午4:22
 */
public class Car implements MoveAble {

    @Override
    public void move() {
        try {
            System.out.println("汽车行驶中");
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
