package com.metoo.im;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDubbo
@SpringBootApplication
public class MetooImApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetooImApplication.class, args);
    }
}
