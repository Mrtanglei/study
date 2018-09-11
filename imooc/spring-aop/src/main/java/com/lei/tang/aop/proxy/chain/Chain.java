package com.lei.tang.aop.proxy.chain;

import java.util.List;

public class Chain {

    List<ChainHandler> handlers;

    int index = 0;

    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }

    public void proceed(){
        if(index < handlers.size()){
            handlers.get(index++).excute(this);
        }
    }
}
