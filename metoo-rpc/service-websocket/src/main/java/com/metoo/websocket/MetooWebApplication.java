package com.metoo.web;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.websocket", "com.metoo.comm.config"})
public class MetooWebsocketApplication {

    public static void main(String[] args) {
        ApplicationContext context  = SpringApplication.run(MetooWebsocketApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}




