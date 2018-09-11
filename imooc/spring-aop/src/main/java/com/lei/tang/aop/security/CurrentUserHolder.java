package com.lei.tang.aop.security;

public class CurrentUserHolder {

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void set(String name){
        holder.set(name);
    }

    public static String get(){
       return holder.get() == null ? "unknown" : holder.get();
    }
}
