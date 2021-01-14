package com.metoo.order.wx.config;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.util.ClassUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class WXConfigUtil implements WXPayConfig {
    private byte[] certData;
    public static final String APP_ID = "wx3ba632a4eb6ac0ce";
    public static final String KEY = "8keRkl8IXEmkriRh4ZxC4npS43H24hcy";
    public static final String MCH_ID = "1605407877";
    public static final String NOTIFY_URL = "http://www.metooxinli.com:8088/wxorder/notify";//必须是外网地址，带域名的
    public static final String TRADE_TYPE_APP = "APP";//类型 APP、JSPI......

//    public WXConfigUtil() throws Exception {
//        String certPath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"/weixin/apiclient_cert.p12";//从微信商户平台下载的安全证书存放的路径
//        File file = new File(certPath);
//        InputStream certStream = new FileInputStream(file);
//        this.certData = new byte[(int) file.length()];
//        certStream.read(this.certData);
//        certStream.close();
//    }

    @Override
    public String getAppID() {
        return APP_ID;
    }

    //parnerid，商户号
    @Override
    public String getMchID() {
        return MCH_ID;
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
