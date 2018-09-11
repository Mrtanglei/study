package com.lei.tang.cor.clien;

import com.lei.tang.cor.handler.PriceHandler;
import com.lei.tang.cor.handler.PriceHandlerFactory;

/**
 * @author tanglei
 * @date 18/9/10 下午4:49
 */
public class Customer {

    PriceHandler priceHandler;

    public void setPriceHandler(PriceHandler priceHandler) {
        this.priceHandler = priceHandler;
    }

    public void requetPrice(float discount) {
        priceHandler.processPrice(discount);
    }

    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setPriceHandler(PriceHandlerFactory.createPriceHandler());
        for (int i = 1; i <= 100; i++) {
            customer.requetPrice(Float.parseFloat(String.valueOf(Math.random() * 0.5)));
        }
    }
}
