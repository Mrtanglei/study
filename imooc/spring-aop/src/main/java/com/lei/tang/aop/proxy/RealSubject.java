package com.lei.tang.aop.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealSubject implements Subject {

    @Override
    public boolean save() {
        log.info("save");
        return true;
    }

    @Override
    public boolean delete() {
        log.info("delete");
        return true;
    }
}
