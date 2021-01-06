package com.metoo.web;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Guns REST Web程序启动类
 *
 * @author fengshuonan
 * @date 2017年9月29日09:00:42
 */
public class MetooWebServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MetooWebApplication.class);
    }

}
