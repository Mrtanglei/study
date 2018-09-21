package com.lei.tang;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tanglei
 * @date 18/9/14
 */
public class SpringDataTest {

    private ApplicationContext applicationContext = null;

    @Before
    public void set() {
        applicationContext = new ClassPathXmlApplicationContext("spring-dao-jpa.xml");
    }

    @After
    public void releace() {
        applicationContext = null;
    }

    @Test
    public void testDataSource() {

    }
}
