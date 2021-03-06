package com.metoo.order.wx.api;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.order.NrOrderInvestApi;
import com.metoo.api.pay.wxpay.WxApi;
import com.metoo.order.nr.dao.entity.NrOrderInvest;
import com.metoo.order.nr.service.NrOrderInvestService;
import com.metoo.order.wx.config.WXConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@DubboService
@Component
@Transactional
public class WxApiImpl implements WxApi {

    @Autowired
    private NrOrderInvestService nrOrderInvestService;
    @Autowired
    private NrOrderInvestApi nrOrderInvestApi;





    @Override
    public RE doUnifiedOrder(String ip,String orderNo, Integer uid)  {
        NrOrderInvest pojo = nrOrderInvestService.findFirstByOrderNo(orderNo);
        if(OU.isBlack(pojo)){
            return RE.fail("没有该订单");
        }
        if(pojo.getStatus() != ConstantUtil.NrOrderInvestStatusEnum.WAIT_PAYING.getCode()){
            return RE.fail("订单支付状态不正确,请重新下单");
        }
        try {
            BigDecimal price = pojo.getPrice();
            WXConfigUtil config = new WXConfigUtil();
            WXPay wxpay = new WXPay(config);
            Map<String, String> data = new HashMap<>();
            //生成商户订单号，不可重复
            data.put("appid", config.getAppID());
            data.put("mch_id", config.getMchID());
            data.put("nonce_str", WXPayUtil.generateNonceStr());
            String body = "充值订单支付";
            data.put("body", body);
            data.put("out_trade_no", pojo.getOrderNo());
            data.put("total_fee", price.longValue()+"");
            //自己的服务器IP地址
            data.put("spbill_create_ip", ip);
            //异步通知地址（请注意必须是外网）
            data.put("notify_url", WXConfigUtil.NOTIFY_URL);
            //交易类型
            data.put("trade_type", WXConfigUtil.TRADE_TYPE_APP);
            //附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
            data.put("attach", "");
            data.put("sign", WXPayUtil.generateSignature(data, config.getKey(),
                    WXPayConstants.SignType.MD5));
            //使用官方API请求预付订单
            Map<String, String> response = wxpay.unifiedOrder(data);
            System.out.println(response);
            if ("SUCCESS".equals(response.get("return_code"))) {//主要返回以下5个参数
                Map<String, String> param = new HashMap<>();
                param.put("appid", config.getAppID());
                param.put("partnerid", response.get("mch_id"));
                param.put("prepayid", response.get("prepay_id"));
                param.put("package", "Sign=WXPay");
                param.put("noncestr", WXPayUtil.generateNonceStr());
                param.put("timestamp", System.currentTimeMillis() / 1000 + "");
                param.put("sign", WXPayUtil.generateSignature(param, config.getKey(),
                        WXPayConstants.SignType.MD5));
                log.info(param.toString());
                return RE.ok(param);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RE.fail("下单失败");
        }
        return RE.fail("下单失败");
    }

    @Override
    public RE payBack(String resXml) {
        WXConfigUtil config = null;
        try {
            config = new WXConfigUtil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = new WXPay(config);
        String xmlBack = "";
        Map<String, String> notifyMap = null;
        try {
            notifyMap = WXPayUtil.xmlToMap(resXml);         // 调用官方SDK转换成map类型数据
            if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {//验证签名是否有效，有效则进一步处理

                String return_code = notifyMap.get("return_code");//状态
                String out_trade_no = notifyMap.get("out_trade_no");//商户订单号
                if (return_code.equals("SUCCESS")) {
                    if (out_trade_no != null) {
                        // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户的订单状态从退款改成支付成功
                        // 注意特殊情况：微信服务端同样的通知可能会多次发送给商户系统，所以数据持久化之前需要检查是否已经处理过了，处理了直接返回成功标志
                        //业务数据持久化
                        log.info("微信手机支付回调成功订单号:{}", out_trade_no);
                        nrOrderInvestApi.investOrderSuccessBack(out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                        return RE.ok(xmlBack);
                    } else {
                        System.err.println("微信手机支付回调失败订单号:{}");
                        System.err.println(out_trade_no);
                        xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                        return RE.fail(xmlBack);
                    }
                }
                return RE.fail(xmlBack);
            } else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                //失败的数据要不要存储？
                System.err.println("手机支付回调通知签名错误");
                xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
                return RE.fail(xmlBack);
            }
        } catch (Exception e) {
            System.err.println("手机支付回调通知签名错误");
            System.err.println(e);
            xmlBack = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return RE.fail(xmlBack);
        }
    }

}
