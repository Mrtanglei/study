package com.lei.tang.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanglei
 * @date 18/9/11 上午11:15
 */
@Slf4j
public abstract class Duck {

    private FlyingStrategy flyingStrategy;

    public void setFlyingStrategy(FlyingStrategy flyingStrategy) {
        this.flyingStrategy = flyingStrategy;
    }

    /**
     * 鸭子的叫声，通用行为，有超类实现
     */
    public void quack() {
        log.info("嘎嘎嘎");
    }

    /**
     * 显示鸭子的外观
     * 鸭子的外观个不相同，由子类实现
     */
    public abstract void display();

    public void fly(){
        flyingStrategy.fly();
    }
}
