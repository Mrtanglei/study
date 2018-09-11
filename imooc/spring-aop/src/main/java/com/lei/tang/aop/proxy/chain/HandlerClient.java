package com.lei.tang.aop.proxy.chain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerClient {

    static class HandlerA extends Handler{

        @Override
        protected void handleProcess() {
            log.info("handler by a");
        }
    }

    static class HandlerB extends Handler{

        @Override
        protected void handleProcess() {
            log.info("handler by b");
        }
    }

    static class HandlerC extends Handler{

        @Override
        protected void handleProcess() {
            log.info("handler by c");
        }
    }

    static class HandlerD extends Handler{

        @Override
        protected void handleProcess() {
            log.info("handler by d");
        }
    }

    public static void main(String[] args) {
        Handler a=new HandlerA();
        Handler b = new HandlerB();
        Handler c =new HandlerC();
        Handler d = new HandlerD();
        a.setSuccess(b);
        b.setSuccess(c);
        c.setSuccess(d);
        a.excute();
    }
}
