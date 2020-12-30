package com.metoo.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.user", "com.metoo.comm.config"})
public class MetooUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetooUserApplication.class, args);
    }
}
