package com.jzwbxx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 配置 - 异步
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Value("${config.async.corePoolSize}")
    private int corePoolSize;
    @Value("${config.async.maxPoolSize}")
    private int maxPoolSize;
    @Value("${config.async.queueCapacity}")
    private int queueCapacity;
    @Value("${config.async.keepAliveSeconds}")
    private int keepAliveSeconds;

    @Bean(name = "taskExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("MyExecutor-");
        executor.initialize();
        // 关闭资源
        //executor.setWaitForTasksToCompleteOnShutdown(true);
        //executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}