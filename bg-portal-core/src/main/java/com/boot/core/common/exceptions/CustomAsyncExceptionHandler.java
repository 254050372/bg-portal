package com.boot.core.common.exceptions;/**
 * @description
 * @autor xbwu on 2018/1/31.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * 定制线程池异常，默认实现为SimpleAsyncUncaughtExceptionHandler
 *
 * @author xbwu
 * @create 2018-01-31
 **/
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        this.logger.error("异步线程池异常，方法：'{}'，主要异常：'{}' ", method,throwable.getMessage(), throwable);
        /*for (Object param : obj) {
            System.out.println("Parameter value - " + param);
        }*/
    }

}