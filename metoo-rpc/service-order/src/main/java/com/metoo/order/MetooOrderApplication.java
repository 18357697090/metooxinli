package com.metoo.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.order", "com.metoo.comm.config"})
public class MetooOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetooOrderApplication.class, args);
    }
}
