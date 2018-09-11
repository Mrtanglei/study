package com.lei.tang.template;

/**
 * @author tanglei
 * @date 18/9/4 下午1:26
 */
public class Tea extends RefreshBeverageTemplate {

    @Override
    protected void brew() {
        System.out.println("大红袍准备中");
    }

    @Override
    protected void addCondiments() {
        System.out.println("加柠檬");
    }

    @Override
    protected boolean isWantsCondiments() {
        return false;
    }
}
