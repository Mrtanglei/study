package com.lei.tang.java.reflect.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author tanglei
 * @date 18/9/3 上午9:56
 */
public class MethodDemo {

    public static void main(String[] args) {
        A a = new A();
        Class<? extends A> clazz = a.getClass();
        try {
            /**
             * getMethod获取的是public修饰的方法
             * clazz.getDeclaredMethod()类本身声明的所有方法
             */
            Method print = clazz.getMethod("print", int.class, int.class);
            //print.invoke(a,new Object[]{10,20});等同于
            print.invoke(a,10,20);
            Method print1 = clazz.getMethod("print", String.class, String.class);
            print1.invoke(a,"hello ","reflect");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
class A{
    public void print(int i,int j){
        System.out.println(i+j);
    }
    public void print(String i,String j){
        System.out.println(i+j);
    }
}
