package com.lei.tang.singleton;

/**
 * @author tanglei
 * @date 18/9/3 下午1:39
 *
 * 单例模式
 *  饿汉模式
 */
public class Singleton {

    private Singleton(){

    }

    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
