package com.lei.tang.strategy;

import com.lei.tang.strategy.impl.FlyNoWay;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanglei
 * @date 18/9/11 下午1:53
 */
@Slf4j
public class BigYellowDuck extends Duck {

    public BigYellowDuck() {
        super.setFlyingStrategy(new FlyNoWay());
    }

    @Override
    public void display() {
        log.info("我是大黄鸭");
    }

    @Override
    public void quack() {
        log.info("嘎~嘎~嘎~");
    }
}
