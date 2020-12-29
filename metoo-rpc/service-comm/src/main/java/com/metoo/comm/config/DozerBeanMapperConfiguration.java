package com.metoo.comm.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerBeanMapperConfiguration {
    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }
}
