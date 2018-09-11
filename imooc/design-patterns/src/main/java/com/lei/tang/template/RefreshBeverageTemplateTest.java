package com.lei.tang.template;

/**
 * @author tanglei
 * @date 18/9/4 下午1:27
 */
public class RefreshBeverageTemplateTest {

    public static void main(String[] args) {
        RefreshBeverageTemplate template1 = new Coffee();
        template1.prepareBeverageTemplate();

        System.out.println("=================");
        RefreshBeverageTemplate template2 = new Tea();
        template2.prepareBeverageTemplate();;
    }
}
