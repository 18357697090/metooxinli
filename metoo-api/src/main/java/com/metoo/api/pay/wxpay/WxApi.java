package com.metoo.api.pay.wxpay;

import com.loongya.core.util.RE;


public interface WxApi {
    RE payBack(String resXml);

    RE doUnifiedOrder(String ip, String orderNo, Integer uid);
}
