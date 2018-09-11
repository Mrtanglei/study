package com.lei.tang.cor.handler;

/**
 * @author tanglei
 * @date 18/9/11 上午10:21
 */
public class PriceHandlerFactory {

    public static PriceHandler createPriceHandler() {
        PriceHandler sales = new SalesHandler();
        PriceHandler manager = new ManagerHandler();
        PriceHandler director = new DirectorHandler();
        PriceHandler ceo = new CEOHandler();
        sales.setSuccessor(manager);
        manager.setSuccessor(director);
        director.setSuccessor(ceo);
        return sales;
    }
}
