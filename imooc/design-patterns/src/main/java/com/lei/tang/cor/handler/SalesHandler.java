package com.lei.tang.cor.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanglei
 * @date 18/9/10 下午4:31
 */
@Slf4j
public class SalesHandler extends PriceHandler {

    @Override
    public void processPrice(float discount) {
        if (discount <= 0.05) {
            log.info("{}处理了{}折扣请求", getClass().getSimpleName(), discount);
        } else {
            successor.processPrice(discount);
        }
    }
}
