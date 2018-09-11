package com.lei.tang.strategy.impl;

import com.lei.tang.strategy.FlyingStrategy;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanglei
 * @date 18/9/11 下午1:37
 */
@Slf4j
public class FlyWithWin implements FlyingStrategy {

    @Override
    public void fly() {
        log.info("展翅高飞");
    }
}
