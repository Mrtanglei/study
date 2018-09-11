package com.lei.tang.proxy.cglibproxy;

/**
 * @author tanglei
 * @date 18/9/3 下午5:43
 */
public class Client {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        Tran tran = (Tran) cglibProxy.getProxy(Tran.class);
        tran.move();
    }
}
