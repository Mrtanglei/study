package com.lei.tang.tasks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @author tanglei
 * @date 18/9/28
 *
 * 异步任务
 */
@Slf4j
@Component
public class AsyncTasks {

    @Async
    public Future<Boolean> asyncTasksA() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("asyncTasksA耗时"+(end-start));
        //为了从外部获取异步任务执行完成时的结果
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> asyncTasksB() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("asyncTaskB耗时"+(end-start));
        //为了从外部获取异步任务执行完成时的结果
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> asyncTasksC() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("asyncTasksC耗时"+(end-start));
        //为了从外部获取异步任务执行完成时的结果
        return new AsyncResult<>(true);
    }
}
