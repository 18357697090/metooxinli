package com.loongya.core.base.tips;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午11:58:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public abstract class Tip {

    protected int code;
    protected String msg;

}
