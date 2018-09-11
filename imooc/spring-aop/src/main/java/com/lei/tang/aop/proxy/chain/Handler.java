package com.lei.tang.aop.proxy.chain;

public abstract class Handler {

    private Handler success;

    public Handler getSuccess() {
        return success;
    }

    public void setSuccess(Handler success) {
        this.success = success;
    }

    public void excute(){
        handleProcess();
        if(success != null){
            success.excute();
        }
    }

    protected abstract void handleProcess();
}
