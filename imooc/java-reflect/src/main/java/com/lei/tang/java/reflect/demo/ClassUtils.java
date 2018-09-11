package com.lei.tang.java.reflect.demo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author tanglei
 * @date 18/8/30 下午5:21
 */
@Slf4j
public class ClassUtils {

    /**
     * 打印类的信息、函数
     * @param obj
     */
    public static void printClassMethodMessage(Object obj){
        //获取类的信息，首先要获取类的类类型
        Class c = obj.getClass();

        log.info("class name ={}",c.getName());

        /**
         * Method类，方法对象
         * getMethods()方法获取的是所有的public的函数，包括从父类继承来的
         * getDeclaredMethods()获取的是该类自己声明的所有方法，不论访问权限
         */
        Method[] methods = c.getMethods();
        for (Method method : methods){
            Class returnType = method.getReturnType();
            Class[] parameterTypes = method.getParameterTypes();
            log.info("返回值name = {}，method name = {}",returnType.getName(),method.getName());
            for (Class clazz : parameterTypes){
                log.info("args = {}",clazz.getName());
            }
        }
    }

    /**
     * 打印类的成员变量
     * @param obj
     */
    public static void printClassFeildMessage(Object obj){
        Class c = obj.getClass();
        /**
         * java.lang.reflect.Field
         * Field封装了关于成员的变量的操作
         * getFields()获取所有的public的成员变量信息
         * getDeclaredFields()获取该类自己声明的所有成员变量信息
         */
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields){
            Class type = field.getType();
            log.info("成员变量类型：{}，成员变量名称：{}",type.getName(),field.getName());

        }
    }

    /**
     * 打印对象的构造函数信息
     * @param obj
     */
    public static void printClassConstuctors(Object obj){
        Class clazz = obj.getClass();
        /**
         *getConstructors()获取所有的public构造函数
         * getDeclaredConstructors获取所有的构造函数
         */
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + ",");
            }
            System.out.println();
        }
    }
}
