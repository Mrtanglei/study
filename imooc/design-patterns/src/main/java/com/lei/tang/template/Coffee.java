package com.lei.tang.template;

/**
 * @author tanglei
 * @date 18/9/4 下午1:24
 */
public class Coffee extends RefreshBeverageTemplate {

    @Override
    protected void brew() {
        System.out.println("咖啡准备中");
    }

    @Override
    protected void addCondiments() {
        System.out.println("加糖和牛奶");
    }
}
