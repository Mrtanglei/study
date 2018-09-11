package com.lei.tang.strategy;

/**
 * @author tanglei
 * @date 18/9/11 下午1:41
 */
public class DuckTest {

    public static void main(String[] args) {
        //        Duck duck = new MallarDuck();
//        Duck duck = new ReadHeadDuck();
        Duck duck = new BigYellowDuck();
        duck.display();
        duck.quack();
        duck.fly();
    }
}
