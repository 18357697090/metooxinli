package com.metoo.xy;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.xy", "com.metoo.comm.config"})
public class MetooXyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetooXyApplication.class, args);
    }
}
