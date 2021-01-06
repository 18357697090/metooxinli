package com.metoo.web.config.auth.converter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 基础的传输bean
 *
 * @author loongya
 * @date 2017-08-25 15:52
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BaseTransferEntity {

    private String object; //base64编码的json字符串

    private String sign;   //签名


}
