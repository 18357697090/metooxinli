package com.loongya.core.util.aliyun.gaodemap;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class GaoDeMapUtil {

    private static final String KEY = "72c405579ce9d226ea2d41087fd6e246";

    private static final String GET_URL = "http://restapi.amap.com/v3/geocode/geo?key=" + KEY + "&s=rsv";

    private static final String BASE_PATH = "http://restapi.amap.com/v3";
    //地址逆编码
    private static final String GET_ADDRESS_URL = "https://restapi.amap.com/v3/geocode/regeo?key=" + KEY + "&location=";

    /**
     * 根据地址转经纬度
     */
    public static String getLngAndLat(String address) {
        try {
            String newUrl = GET_URL + "&address=" + address;
            URL url = new URL(newUrl);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            MapResModel mapResModel = JSONObject.parseObject(sb.toString(), MapResModel.class);
            return mapResModel.getGeocodes().get(0).getLocation();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据地址转经纬度
     */
    public static MapResAddressModel getAddress(Double Lng, Double Lat) {
        if (Lng == null || Lat == null)
            return null;

        String newUrl = GET_ADDRESS_URL + Lng + "," + Lat;
        String result = HttpUtil.get(newUrl);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String status = (String) jsonObject.get("status");
        //获取成功
        if ("1".equals(status)) {
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("regeocode");
            JSONObject sb = (JSONObject) jsonObject1.get("addressComponent");
            MapResAddressModel mapResAddressModel1 = JSONObject.parseObject(sb.toJSONString(), MapResAddressModel.class);
            if (mapResAddressModel1.getCity().equalsIgnoreCase("[]")) {
                mapResAddressModel1.setCity(mapResAddressModel1.getProvince());
            }
            return mapResAddressModel1;
        }
        return null;
    }

    /**
     * 高德地图WebAPI : 驾车路径规划 计算两地之间行驶的距离(米)
     * String origins:起始坐标
     * String destination:终点坐标
     */
    public static String distance(String origins, String destination) {
        int strategy = 2;
        /**
         * 0:速度优先（时间）; 1:费用优先（不走收费路段的最快道路）;2:距离优先; 3:不走快速路 4躲避拥堵;
         * 5:多策略（同时使用速度优先、费用优先、距离优先三个策略计算路径）;6:不走高速; 7:不走高速且避免收费;
         * 8:躲避收费和拥堵; 9:不走高速且躲避收费和拥堵
         */
        String url = BASE_PATH + "/direction/driving?" + "origin=" + origins + "&destination=" + destination
                + "&strategy=" + strategy + "&extensions=base&key=" + KEY;
        JSONObject jsonobject = JSONObject.parseObject(getHttpResponse(url));
        JSONArray pathArray = jsonobject.getJSONObject("route").getJSONArray("paths");
        String distanceString = pathArray.getJSONObject(0).getString("distance");
        return distanceString;
    }

    /**
     * 高德地图WebAPI : 地址转化为高德坐标
     * String address：高德地图地址
     */
    public static String coordinate(String address) {
        try {
            address = URLEncoder.encode(address, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String url = BASE_PATH + "/geocode/geo?address=" + address + "&output=json&key=" + KEY;
        JSONObject jsonobject = JSONObject.parseObject(getHttpResponse(url));
        JSONArray pathArray = jsonobject.getJSONArray("geocodes");
        String coordinateString = pathArray.getJSONObject(0).getString("location");
        return coordinateString;
    }

    /**
     * 高德地图WebAPI : gps坐标转化为高德坐标
     * String coordsys：高德地图坐标
     */
    public static String convert(String coordsys) {
        try {
            coordsys = URLEncoder.encode(coordsys, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = BASE_PATH + "/assistant/coordinate/convert?locations=" + coordsys + "&coordsys=gps&output=json&key=" + KEY;
        JSONObject jsonobject = JSONObject.parseObject(getHttpResponse(url));
        System.out.println(jsonobject.toString());
        String coordinateString = jsonobject.getString("locations");
        return coordinateString;
    }

    public static String getHttpResponse(String allConfigUrl) {
        BufferedReader in = null;
        StringBuffer result = null;
        try {
            // url请求中如果有中文，要在接收方用相应字符转码
            URI uri = new URI(allConfigUrl);
            URL url = uri.toURL();
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Content-type", "text/html");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "utf-8");
            connection.connect();
            result = new StringBuffer();
            // 读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }


    public static void main(String[] args) {
        // System.out.println(getLngAndLat("浙江省杭州市西湖区西湖街道西湖景区雷峰塔"));
        //1.计算两个经纬度之间的距离
        String origin = "120.2997550000" + "," + "30.4279360000";  // 格式:经度,纬度;注意：高德最多取小数点后六位
        String target = "120.194313" + "," + "30.185967";
        String distance = distance(origin, target);
        System.out.println("原坐标:{" + origin + "}，目标坐标:{" + target + "}--------->计算后距离：" + distance);

        //2.地址转换高德坐标
        String address = "成都市武侯区";
        String coordinate = coordinate(address);
        System.out.println("转换前地址:" + address + "--------->转变后坐标：" + coordinate);


        //3.gps坐标转化为高德坐标
        String coordsys = "121.43687,31.18826";
        String moordsys = convert(coordsys);
        System.out.println("转换前的经纬度：" + coordsys + "-------->转变后的经纬度:" + moordsys);

        //MapResAddressModel mapResAddressModel = getAddress("120.2997550000", "30.4279360000");
        //System.out.println(JSONObject.toJSONString(mapResAddressModel));//{"city":"杭州市","district":"余杭区","province":"浙江省"}

        System.out.println(getLngAndLat("杭州市滨江区越达巷79号"));
    }
}
