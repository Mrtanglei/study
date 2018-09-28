package com.lei.tang.schedul;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tanglei
 * @date 18/9/28
 */
@Component
public class Schedul1 {

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "0/3 * * * * ?")
    public void test1() {
        System.out.println(format.format(new Date()) + "B" + new Date().getTime());
    }
}
