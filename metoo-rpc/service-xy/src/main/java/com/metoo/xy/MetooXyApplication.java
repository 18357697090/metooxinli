package com.metoo.xy;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.xy", "com.metoo.comm"})
public class MetooXyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetooXyApplication.class, args);
    }
}
