package com.lei.tang.strategy.impl;

import com.lei.tang.strategy.FlyingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanglei
 * @date 18/9/11 下午1:52
 */
@Slf4j
public class FlyNoWay implements FlyingStrategy {

    @Override
    public void fly() {
        log.info("我不会飞");
    }
}
