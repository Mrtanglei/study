package com.lei.tang.cor.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tanglei
 * @date 18/9/10 下午4:36
 */
@Slf4j
public class CEOHandler extends PriceHandler {

    @Override
    public void processPrice(float discount) {
        if (discount <= 0.20) {
            log.info("{}处理了{}折扣请求", getClass().getSimpleName(), discount);
        } else {
            log.info("CEO拒绝了折扣{}", discount);
        }
    }
}
