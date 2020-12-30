package com.metoo.ps;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo"})
public class MetooPsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetooPsApplication.class, args);
    }
}
