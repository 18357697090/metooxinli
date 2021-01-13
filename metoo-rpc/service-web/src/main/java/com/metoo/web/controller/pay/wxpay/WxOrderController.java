package com.metoo.web.controller.pay.wxpay;

import com.loongya.core.util.AssertUtils;
import com.loongya.core.util.RE;
import com.metoo.api.pay.wxpay.WxApi;
import com.metoo.web.config.auth.ThreadLocal;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/wxorder")
public class WxOrderController {

    @DubboReference
    private WxApi wxApi;

    /**
     * 微信支付接口
     * @param request
     * @param orderNo
     * @return
     */
    @PostMapping("/wxpay")
    public RE wxAdd(HttpServletRequest request, String orderNo) {
        AssertUtils.checkParam(orderNo);
        return wxApi.doUnifiedOrder(request.getRemoteAddr(),orderNo, ThreadLocal.getUserId());
    }

    @PostMapping("/notify")
    @ApiOperation("微信回调")
    public String wxPayNotify(HttpServletRequest request) {
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            RE re = wxApi.payBack(resXml);
            String result = "";
            if(!re.isFail()){
                result = (String) re.getData();
            }
            return result;
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return result;
        }
    }
}
