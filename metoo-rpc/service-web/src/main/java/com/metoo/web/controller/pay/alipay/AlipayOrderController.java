package com.metoo.web.controller.pay.alipay;

import com.loongya.core.util.RE;
import com.metoo.api.pay.alipay.AlipayApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/alipayorder")
@Slf4j
public class AlipayOrderController {

    @DubboReference
    private AlipayApi alipayApi;

    @PostMapping("/getOrderInfo")
    public RE getOrderInfo(String orderNo) throws Exception {
        return alipayApi.getOrderInfo(orderNo);
    }



    /**
     * <pre>
     * 第一步:验证签名,签名通过后进行第二步
     * 第二步:按一下步骤进行验证
     * 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
     * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
     * 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
     * 4、验证app_id是否为该商户本身。上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
     * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
     * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
     * </pre>
     *
     * @return
     */
    @RequestMapping("notify")
    @ResponseBody
    public RE callback(HttpServletRequest request) {
        // 将异步通知中收到的待验证所有参数都存放到map中
        return alipayApi.callback(convertRequestParamsToMap(request));
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();

//        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
//
//        for (Map.Entry<String, String[]> entry : entrySet) {
//            String name = entry.getKey();
//            String[] values = entry.getValue();
//            int valLen = values.length;
//
//            if (valLen == 1) {
//                retMap.put(name, values[0]);
//            } else if (valLen > 1) {
//                StringBuilder sb = new StringBuilder();
//                for (String val : values) {
//                    sb.append(",").append(val);
//                }
//                retMap.put(name, sb.toString().substring(1));
//            } else {
//                retMap.put(name, "");
//            }
//        }

        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            log.info(">>>>>参数" + name + ":" + valueStr);
            params.put(name, valueStr);
        }
        return params;
    }


}
