package com.lei.tang.factory;

/**
 * @author tanglei
 * @date 18/9/3 下午3:01
 */
public class Test {

    public static void main(String[] args) {
        Hair hair = Factory.getHair("left");
        hair.draw();
        hair = Factory.getHairByClazz(RightHair.class);
        hair.draw();
    }
}
