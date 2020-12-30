package com.metoo.web;

import com.metoo.web.config.SpringContextUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.web", "com.metoo.comm.config"})
public class MetooWebApplication {

    public static void main(String[] args) {
        ApplicationContext context  = SpringApplication.run(MetooWebApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}




