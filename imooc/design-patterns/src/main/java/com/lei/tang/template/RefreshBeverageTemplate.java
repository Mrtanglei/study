package com.lei.tang.template;

/**
 * @author tanglei
 * @date 18/9/4 下午1:11
 *
 * 模板方法类
 */
public abstract class RefreshBeverageTemplate {

    public final void prepareBeverageTemplate(){
        //定义步骤
        boilWater();

        brew();

        pourInCup();

        if(isWantsCondiments()) {
            addCondiments();
        }
    }

    /**
     * Hook 钩子函数，提供一个默认或空的实现
     * 具体子类可提供是否挂钩或如何挂钩
     * @return
     */
    protected boolean isWantsCondiments(){
        return true;
    }

    private void boilWater() {
        System.out.println("将水煮沸");
    }

    protected abstract void brew();

    private void pourInCup(){
        System.out.println("将饮料导入杯中");
    }

    protected abstract void addCondiments();
}
