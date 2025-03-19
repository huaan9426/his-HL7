package com.xiaoming.spring;

import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Base64;
@SpringBootApplication(scanBasePackages = "com.xiaoming.spring")
@EnableCaching
@EnableAsync
@EnableTransactionManagement
public class HisDemoApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(HisDemoApplication.class);

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(HisDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        validateSecurityConfig();
    }

    private void validateSecurityConfig() {
        try {
            String key = env.getProperty("medical.encrypt.key");
            if (key == null) {
                logger.error("❌ 未配置医疗数据加密密钥");
                throw new BeanInitializationException("安全配置缺失");
            }
            byte[] keyBytes = Base64.getDecoder().decode(key);
            if (keyBytes.length != 32) {
                logger.error("⚠️ 密钥长度错误: {}字节 (需要32字节)", keyBytes.length);
                throw new BeanInitializationException("密钥长度无效");
            }
            logger.info("✅ 医疗数据加密配置验证通过");
        } catch (IllegalArgumentException e) {
            logger.error("🚫 Base64解码失败: {}", e.getMessage());
            throw new BeanInitializationException("密钥格式错误", e);
        }
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
}
