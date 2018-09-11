package com.lei.tang.cor.handler;

/**
 * @author tanglei
 * @date 18/9/10 下午4:26
 */
public abstract class PriceHandler {

    //直接后继，用于传递请求
    PriceHandler successor;

    public void setSuccessor(PriceHandler successor) {
        this.successor = successor;
    }

    public abstract void processPrice(float discount);

}
