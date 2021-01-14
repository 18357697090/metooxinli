package com.metoo.order.alipay.api;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.loongya.core.util.ConstantUtil;
import com.loongya.core.util.OU;
import com.loongya.core.util.RE;
import com.metoo.api.order.NrOrderInvestApi;
import com.metoo.api.pay.alipay.AlipayApi;
import com.metoo.api.pay.wxpay.WxApi;
import com.metoo.order.alipay.config.AlipayConfig;
import com.metoo.order.alipay.config.AlipayNotifyParam;
import com.metoo.order.nr.dao.entity.NrOrderInvest;
import com.metoo.order.nr.service.NrOrderInvestService;
import com.metoo.order.wx.config.WXConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@DubboService
@Component
public class AlipayApiImpl implements AlipayApi {

    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    @Autowired
    private NrOrderInvestService nrOrderInvestService;

    @Autowired
    private NrOrderInvestApi nrOrderInvestApi;

    @Override
    public RE getOrderInfo(String orderNo) {
        NrOrderInvest pojo = nrOrderInvestService.findFirstByOrderNo(orderNo);
        if(OU.isBlack(pojo)){
            return RE.fail("没有该订单");
        }
        if(pojo.getStatus() != ConstantUtil.NrOrderInvestStatusEnum.WAIT_PAYING.getCode()){
            return RE.fail("订单状态不正确, 请重新下单");
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGNTYPE);
        //实例化客户端
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("充值订单支付");
        model.setSubject("充值订单支付");
        model.setOutTradeNo(pojo.getOrderNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(pojo.getPrice().divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).toString());
        model.setProductCode(AlipayConfig.QUICK_MSECURITY_PAY);
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.notify_url);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            request.setNotifyUrl(AlipayConfig.notify_url);
            log.info(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return RE.ok(response.getBody());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return RE.fail("支付失败");
    }

    @Override
    public RE callback(Map<String, String> params) {
        String paramsJson = JSON.toJSONString(params);
        log.info("支付宝回调，{}", paramsJson);
        try {
            // 调用SDK验证签名
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY,
                    AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
            if (signVerified) {
                log.info("支付宝回调签名认证成功");
                // 按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                if (!params.get("app_id").equals(AlipayConfig.APPID)) {
                    throw new AlipayApiException("app_id不一致");
                }
                String outTradeNo = params.get("out_trade_no");

                check(params, outTradeNo);
                // 另起线程处理业务
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        AlipayNotifyParam param = buildAlipayNotifyParam(params);
                        String trade_status = param.getTradeStatus();
                        // 支付成功
                        if (trade_status.equals("TRADE_SUCCESS")
                                || trade_status.equals("TRADE_FINISHED")){
                            // 处理支付成功逻辑
                            try {
                              nrOrderInvestApi.investOrderSuccessBack(outTradeNo);
                            } catch (Exception e) {
                                log.error("支付宝回调业务处理报错,params:" + paramsJson, e);
                            }
                        } else {
                            log.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}",trade_status,paramsJson);
                        }
                    }
                });
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
                return RE.ok();
            } else {
                log.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
                return RE.fail("fail");
            }
        } catch (AlipayApiException e) {
            log.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
            return RE.fail("fail");
        }
    }


    private AlipayNotifyParam buildAlipayNotifyParam(Map<String, String> params) {
        String json = JSON.toJSONString(params);
        return JSON.parseObject(json, AlipayNotifyParam.class);
    }


    /**
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     *
     * @param params
     * @throws AlipayApiException
     */
    private void check(Map<String, String> params, String orderNo) throws AlipayApiException {
        // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        NrOrderInvest order = nrOrderInvestService.findFirstByOrderNo(orderNo);

        if (OU.isBlack(order)) {
            throw new AlipayApiException("没有该订单");
        }

        // 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(100)).longValue();
        if (total_amount != order.getPrice().longValue()) {
            throw new AlipayApiException("支付金额有误");
        }

        // 3、校验通知中的seller_id（或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(AlipayConfig.APPID)) {
            throw new AlipayApiException("app_id不一致");
        }
    }

}
