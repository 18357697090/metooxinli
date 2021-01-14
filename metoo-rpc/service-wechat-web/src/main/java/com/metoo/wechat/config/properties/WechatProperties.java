package com.metoo.wechat.config.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 项目相关配置
 *
 * @author fengshuonan
 * @date 2017年10月23日16:44:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = WechatProperties.REST_PREFIX)
public class WechatProperties {

    public static final String REST_PREFIX = "wechat";

    private String appid = "";

    private String secret = "";

    private String grantType = "";

    private String loginUrl = "";



}
