package com.xiaoming.spring.util;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        context = ctx;
    }

    public static Environment getEnvironment() {
        checkInitialized();
        return context.getEnvironment();
    }

    private static void checkInitialized() {
        if (context == null) {
            throw new IllegalStateException("Spring上下文未初始化完成，请检查以下情况：\n"
                    + "1. 确保@SpringBootApplication标注在启动类\n"
                    + "2. 检查@ComponentScan是否包含当前包\n"
                    + "3. 确认工具类已被Spring管理（添加@Component注解）");
        }
    }
}
