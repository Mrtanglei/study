package com.lei.tang.java.reflect.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassDemo {

    public static void main(String[] args) {
        //Foo的实例对象表示
        Foo foo = new Foo();

        //Foo这个类也是实例对象，Class类的实例对象
        //任何一个类都是Class类的实例对象，三中表达方式

        //1、实际上就是告诉我们任何一个类都有一个隐含的静态的成员变量class
        Class class1 = Foo.class;

        //2、已知该类的对象，通过getClass方法
        Class class2 = foo.getClass();

        //官网class1，class2表示Foo类的类类型（class type）
        log.info("class1 == class2,result={}",class1 == class2);

        //3
        try {
            Class class3 = Class.forName("com.lei.tang.reflect.demo.Foo");
            log.info("class1 == class2 == class3,result={}",class1 == class2 && class2 == class3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //通过类的类类型创建该类的实例对象，通过class1 or class2 or class3创建Foo的实例对象
        try {
            //需要无参的构造方法
            Foo foo2 = (Foo)class1.newInstance();
            foo2.print();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

@Slf4j
class Foo{

    void print(){
        log.info("foo print");
    }
}
