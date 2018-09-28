package com.lei.tang.controller;

import com.lei.tang.tasks.AsyncTasks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * @author tanglei
 * @date 18/9/28
 */
@Slf4j
@RestController
@RequestMapping("async")
public class AsyncTasksController {

    @Autowired
    AsyncTasks asyncTasks;

    @RequestMapping(value = "test")
    public String test(){
        long start = System.currentTimeMillis();
        Future<Boolean> a = asyncTasks.asyncTasksA();
        Future<Boolean> b = asyncTasks.asyncTasksB();
        Future<Boolean> c = asyncTasks.asyncTasksC();
        while (!a.isDone() || !b.isDone() || !c.isDone()){

        }
        long end = System.currentTimeMillis();
        log.info("总耗时"+(end-start));
        return String.valueOf(end-start);
    }
}
