package com.lei.tang.proxy;

/**
 * @author tanglei
 * @date 18/9/3 下午4:41
 */
public class CarLogProxy implements MoveAble {

    private MoveAble moveAble;

    public CarLogProxy(MoveAble moveAble) {
        this.moveAble = moveAble;
    }

    @Override
    public void move() {
        System.out.println("记录开始日志");
        moveAble.move();
        System.out.println("记录结束日志");
    }
}
