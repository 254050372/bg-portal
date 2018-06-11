package com.boot.core.common.asyn;/**
 * @description
 * @autor xbwu on 2018/1/29.
 */

import com.boot.core.common.exceptions.CustomAsyncExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池配置
 * <p>
 * 配置解释
 * 当一个任务通过execute(Runnable)方法欲添加到线程池时：
 * 1、 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态,也要创建新的线程来处理被添加的任务。
 * 2、 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
 * 3、如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
 * 4、 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。
 * 也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程 maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
 * 5、 当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
 * allowCoreThreadTimeout：允许核心线程超时
 * rejectedExecutionHandler：任务拒绝处理器
 * <p>
 * 两种情况会拒绝处理任务：
 * 当线程数已经达到maxPoolSize，切队列已满，会拒绝新任务
 * 当线程池被调用shutdown()后，会等待线程池里的任务执行完毕，再shutdown。如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务
 * 线程池会调用rejectedExecutionHandler来处理这个任务。如果没有设置默认是AbortPolicy，会抛出异常
 * ThreadPoolExecutor类有几个内部实现类来处理这类情况：
 * AbortPolicy 丢弃任务，抛运行时异常
 * CallerRunsPolicy 执行任务
 * DiscardPolicy 忽视，什么都不会发生
 * DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务
 * 实现RejectedExecutionHandler接口，可自定义处理器
 *
 * @author xbwu
 * @create 2018-01-29
 **/
@Configuration
//开启异步线程池功能
//实现AsyncConfigurer接口，自定义更加多的线程池事件
@EnableAsync
public class ThreadPool implements AsyncConfigurer {

    public static final String DEFAULT = "defaultExecutor";

    /**
     * Set the ThreadPoolExecutor's core pool size.
     */
    private int corePoolSize = 10;
    /**
     * Set the ThreadPoolExecutor's maximum pool size.
     */
    private int maxPoolSize = 200;
    /**
     * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     */
    private int queueCapacity = 10;

    /**
     * 默认线程池实现
     *
     * @return
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("默认线程池");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }
}

