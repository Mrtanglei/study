package com.lei.tang.proxy;

/**
 * @author tanglei
 * @date 18/9/3 下午4:29
 */
public class CarTimeProxy implements MoveAble {

    private MoveAble moveAble;

    public CarTimeProxy(MoveAble moveAble) {
        this.moveAble = moveAble;
    }

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车开始行驶");
        moveAble.move();
        long endTime = System.currentTimeMillis();
        System.out.println("汽车停止行驶，行驶了"+(endTime-startTime)+"毫秒");
    }
}
