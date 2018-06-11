package com.boot.core.common.asyn;/**
 * @description
 * @autor xbwu on 2018/1/29.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 测试异步多线程任务
 * @author xbwu
 * @create 2018-01-29 
 **/
@Component
//全局异步开启
@Async
public class TestAsynTask {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    public Future<String> doTask1() throws InterruptedException{
        logger.info("Task1 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();

        logger.info("Task1 finished, time elapsed: {} ms.", end-start);

        return new AsyncResult<>("Task1 accomplished!");
    }

    public Future<String> doTask2() throws InterruptedException{
        logger.info("Task2 started.");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();

        logger.info("Task2 finished, time elapsed: {} ms.", end-start);

        return new AsyncResult<>("Task2 accomplished!");
    }
}
