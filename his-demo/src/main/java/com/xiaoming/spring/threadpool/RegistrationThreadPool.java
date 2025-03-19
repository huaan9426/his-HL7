/*package com.xiaoming.spring.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

// threadpool/RegistrationThreadPool.java
@Configuration
@EnableAsync
public class RegistrationThreadPool {

    @Bean(name = "registrationThreadPool")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Registration-Async-");
        executor.initialize();
        return executor;
    }
}*/
