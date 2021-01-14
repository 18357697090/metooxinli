package com.metoo.api.pay.alipay;

import com.loongya.core.util.RE;

import java.util.Map;

public interface AlipayApi {

    RE getOrderInfo(String orderNo);

    RE callback(Map<String, String> params);
}
