package com.metoo.business;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.business", "com.metoo.comm.config"})
public class MetooBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetooBusinessApplication.class, args);
    }
}
