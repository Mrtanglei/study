package com.lei.tang.singleton;

/**
 * @author tanglei
 * @date 18/9/3 下午1:39
 */
public class Test {

    public static void main(String[] args) {

        //饿汉模式
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        if(instance1 == instance2){
            System.out.println("instance1 == instance2");
        }

        //懒汉模式
        Singleton2 instance3 = Singleton2.getInstance();
        Singleton2 instance4 = Singleton2.getInstance();
        if(instance3 == instance4){
            System.out.println("instance3 == instance4");
        }
    }
}
