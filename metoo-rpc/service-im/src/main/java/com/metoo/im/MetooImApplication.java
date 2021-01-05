package com.metoo.im;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableDubbo
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.metoo.im", "com.metoo.comm.config"})
public class MetooImApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetooImApplication.class, args);
    }
}
