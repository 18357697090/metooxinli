package com.loongya.core.util.aliyun;

import com.alibaba.fastjson.JSONObject;
import com.loongya.core.exception.LoongyaException;
import com.loongya.core.util.OU;
import com.loongya.core.util.http.HttpClientUtil;

import java.io.InputStream;
import java.math.BigDecimal;

public class OSSUtil {

    public static final String PATH_PRE = "http://www.metooxinli.com/";
    public static final String PATH_VIDEO_PRE = "http://video.yuanyuejituan.com/";
    public static  final String PRE_IMG = "img/";
    public static final String PRE_VIDEO = "video/";

    public static void main(String[] args) throws Exception {
        BigDecimal amount = new BigDecimal(45);
        String roite = "10";
        BigDecimal fanyongAmount =  amount.multiply(new BigDecimal(roite).divide(new BigDecimal(100)));
        System.out.println(fanyongAmount);
    }
    public static Integer getVideoLength(String name){
        try {
            String url = PATH_VIDEO_PRE+name + "?avinfo";
            String http = HttpClientUtil.getHttp(url);
            JSONObject jsonObject = JSONObject.parseObject(http);
            JSONObject format = jsonObject.getJSONObject("format");
            Double duration = format.getDouble("duration");
            return duration.intValue();
        }catch (LoongyaException le){
            return 0;
        }
    }




    public static  String fillPath(String subpath){
        if(OU.isBlack(subpath)){
            return "";
        }
        if(subpath.startsWith("http://")||subpath.startsWith("https://")){
            return subpath;
        }
        if(subpath.startsWith("/")){
            subpath= subpath.substring(1);
        }
        return PATH_PRE + subpath;
    }
    public static  String fillVideoPath(String subpath){
        if(OU.isBlack(subpath)){
            return "";
        }
        if(subpath.startsWith("http://")||subpath.startsWith("https://")){
            return subpath;
        }
        if(subpath.startsWith("/")){
            subpath= subpath.substring(1);
        }
        return PATH_VIDEO_PRE + subpath;
    }

    public static String incisionImg(String subpath){
        String imgaddress = "";
        if(OU.isBlack(subpath)){
            return subpath;
        }
        if(subpath.startsWith(PATH_PRE)){
            return subpath.substring(PATH_PRE.length());
        }else{
            return imgaddress;
        }
    }


}
