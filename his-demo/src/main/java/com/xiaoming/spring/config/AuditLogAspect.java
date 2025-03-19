package com.xiaoming.spring.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// config/AuditLogAspect.java
@Aspect
@Component
public class AuditLogAspect {

    @AfterReturning(pointcut = "execution(* com.xiaoming.spring.clinic..*.*(..))")
    public void logOperation(JoinPoint joinPoint) {
        // 记录操作日志到数据库
    }
}
