package com.loongya.core.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * HTTP客户端工具包
 *
 * @author zxz
 * @date 2019-09-04
 */
public class HttpClientUtil {

    public static String getHttp(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result= "";
//	      创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
        HttpGet httpRequst = new HttpGet(url);
        try {
            //使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
            HttpResponse httpResponse = httpClient.execute(httpRequst);//其中HttpGet是HttpUriRequst的子类
            if(httpResponse.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
                // 一般来说都要删除多余的字符
                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            }else{
                httpRequst.abort();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String postHttp(String url ,Map<String ,String> map) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 第一步，创建HttpPost对象
            HttpPost httpPost = new HttpPost(url);
            // 第二步，设置HTTP POST请求参数，  使用json对象

            // 创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            // 把参数值放入到formparams 队列中
            Iterator<String> it = map.keySet().iterator();

            while (it.hasNext()) {
                String key = it.next();
                String value = map.get(key);
                formparams.add(new BasicNameValuePair(key, value));
            }

            UrlEncodedFormEntity uefEntity;
            uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(uefEntity);

            // 发起请求
            HttpResponse httpResponse = httpClient.execute(httpPost);
            // 请求结束，返回结果。并解析json。
            HttpEntity entity = httpResponse.getEntity();
            if(entity != null){
                String resData = EntityUtils.toString(entity,"UTF-8");
                return resData;
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static String postHttp(String url, String content) {
        String result = "";
        HttpURLConnection connection = null;
        BufferedReader reader = null ;
        try{
            URL postUrl = new URL(url);
            connection = (HttpURLConnection) postUrl
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.connect();
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close(); // flush and close
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
            String line = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            return result;
        }catch(Exception e){
            return null;
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }

    }


    /**
     * POST 提交 JSON格式
     * @param url
     * @param json
     * @return
     * @date 2017-3-29
     */
    public static String postHttpJSON(String url ,String json) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 第一步，创建HttpPost对象
            HttpPost httpPost = new HttpPost(url);

            // 第二步，设置HTTP POST请求参数，  使用json对象
            StringEntity entity;
            entity = new StringEntity(json,"UTF-8"); 		// 解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            // 发起请求
            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();
            // 请求结束，返回结果。并解析json。
            if(httpEntity != null){
                String resData = EntityUtils.toString(httpEntity,"UTF-8");
                return resData;
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            try {
                httpClient.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数,请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            System.out.println(urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("leuser-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}