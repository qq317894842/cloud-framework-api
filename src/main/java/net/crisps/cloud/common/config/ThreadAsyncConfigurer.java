package net.crisps.cloud.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 异步线程池配置类
 * @Author Created by yan.x on 2019-05-03 .
 * @Copyright © HOT SUN group.All Rights Reserved.
 **/
@Configuration
@EnableAsync
public class ThreadAsyncConfigurer implements AsyncConfigurer {

    private final static Logger log = LoggerFactory.getLogger(ThreadAsyncConfigurer.class);

    @Bean
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        threadPool.setCorePoolSize(2);
        // 设置最大线程数
        threadPool.setMaxPoolSize(8);
        // 线程池所使用的缓冲队列
        threadPool.setQueueCapacity(10);
        // 等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(60);
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化线程
        threadPool.initialize();
        return threadPool;
    }

    /**
     * 异常处理
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }

    /**
     * 自定义异常处理类
     */
    class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            log.error("==========================" + throwable.getMessage() + "=======================", throwable);
            log.error("exception method:" + method.getName());
            for (Object param : obj) {
                log.error("Parameter value - " + param);
            }
        }
    }
}