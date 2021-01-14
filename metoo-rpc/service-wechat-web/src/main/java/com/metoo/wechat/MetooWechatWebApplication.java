package com.metoo.wechat;

import com.metoo.wechat.config.SpringContextUtil;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.metoo.wechat", "com.metoo.comm.config"})
public class MetooWechatWebApplication {

    public static void main(String[] args) {
        ApplicationContext context  = SpringApplication.run(MetooWechatWebApplication.class, args);
        SpringContextUtil.setApplicationContext(context);
    }
}




