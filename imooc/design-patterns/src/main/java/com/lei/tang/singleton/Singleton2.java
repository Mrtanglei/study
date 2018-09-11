package com.lei.tang.singleton;

/**
 * @author tanglei
 * @date 18/9/3 下午1:48
 *
 * 单例模式
 *  懒汉模式
 */
public class Singleton2 {

    private Singleton2(){

    }

    private static Singleton2 instance;

    public static Singleton2 getInstance(){
        if(instance == null){
            instance = new Singleton2();
        }
        return instance;
    }
}
