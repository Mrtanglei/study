package com.lei.tang.strategy;

import com.lei.tang.strategy.impl.FlyWithWin;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tanglei
 * @date 18/9/11 上午11:19
 */
@Slf4j
public class ReadHeadDuck extends Duck {

    public ReadHeadDuck() {
        super.setFlyingStrategy(new FlyWithWin());
    }

    @Override
    public void display() {
        log.info("我的头是红色的");
    }
}
