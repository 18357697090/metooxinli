package com.metoo.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.web", "com.metoo.comm.config"})
public class MetooWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetooWebApplication.class, args);
    }
}




