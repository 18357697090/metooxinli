package com.metoo.websocket;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.websocket", "com.metoo.comm.config"})
public class MetooWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetooWebsocketApplication.class, args);
    }
}




