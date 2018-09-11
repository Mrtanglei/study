package com.lei.tang.aop.proxy.chain;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ChainHandlerClient {

    static class ChainHndlerA extends ChainHandler{

        @Override
        protected void handleProcess() {
            log.info("handle by ChainHndlerA");
        }
    }

    static class ChainHndlerB extends ChainHandler{

        @Override
        protected void handleProcess() {
            log.info("handle by ChainHndlerB");
        }
    }

    static class ChainHndlerC extends ChainHandler{

        @Override
        protected void handleProcess() {
            log.info("handle by ChainHndlerC");
        }
    }

    public static void main(String[] args) {
        List<ChainHandler> list = Arrays.asList(new ChainHndlerA(),new ChainHndlerB(),new ChainHndlerC());
        Chain chain=new Chain(list);
        chain.proceed();
    }
}
