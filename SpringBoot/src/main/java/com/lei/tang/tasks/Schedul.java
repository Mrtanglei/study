package com.lei.tang.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tanglei
 * @date 18/9/28
 *
 * 定时任务
 */
@Component
public class Schedul {

    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * cron表达式：
     * “*”字符代表所有可能的值
     * “/”字符用来指定数值的增量
     * “？”字符仅被用于天（月）和天（星期）两个子表达式，表示不指定值
     * “L” 字符仅被用于天（月）和天（星期）两个子表达式，它是单词“last”的缩写，如果在“L”前有具体的内容，它就具有其他的含义了。例如：“6L”表示这个月的倒数第６天
     * W 字符代表着平日(Mon-Fri)，并且仅能用于日域中。它用来指定离指定日的最近的一个平日。大部分的商业处理都是基于工作周的，所以 W 字符可能是非常重要的
     * 例如，日域中的 15W 意味着 "离该月15号的最近一个平日。" 假如15号是星期六，那么 trigger 会在14号(星期五)触发，因为星期四比星期一离15号更近。
     * C：代表“Calendar”的意思。它的意思是计划所关联的日期，如果日期没有被关联，则相当于日历中所有日期。例如5C在日期字段中就相当于日历5日以后的第一天。1C在星期字段中相当于星期日后的第一天
     * 按顺序依次为
     * 秒（0~59）0-59  , - * /
     * 分钟（0~59）0-59  , - * /
     * 小时（0~23) 0-23
     * 天（0~31）1-31  , - * ? / L W C
     * 月（0~11）1-12 或者 JAN-DEC  , - * /
     * 星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）1-7 或者 SUN-SAT  , - * ? / L C #
     * 年（可选）留空, 1970-2099 , - * / spring boot不支持年
     * 其中每个元素可以是一个值(如6),一个连续区间(9-12),一个间隔时间(8-18/4)(/表示每隔4小时),一个列表(1,3,5),通配符。
     * 由于"月份中的日期"和"星期中的日期"这两个元素互斥的,必须要对其中一个设置?。
     */
    @Scheduled(cron = "0/3 * * * * ?")
    public void test() {
        System.out.println(format.format(new Date()) + "A" + new Date().getTime());
    }
}
