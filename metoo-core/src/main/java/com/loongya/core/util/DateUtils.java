package com.loongya.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static boolean verifyDateLegal(String date) {
        if ((date.contains("-") && date.contains("/"))
                || (date.contains("-") && date.contains("."))
                || (date.contains("/") && date.contains("."))){
            return false;
        }
        date.trim();
        StringBuilder timeSb = new StringBuilder();
        date = date.replaceAll("[\\.]|[//]", "-");
        String[] time = date.split(" ");
        timeSb.append(time[0]);
        timeSb.append(" ");
        if (time.length > 1) {
            timeSb.append(time[1]);
        }
        int i = time.length > 1 ? time[1].length() : 0;
        for ( ; i < 8 ; i ++) {
            if (i == 2 || i == 5){
                timeSb.append(":");
            } else {
                timeSb.append("0");
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            sdf.setLenient(false);
            sdf.parse(timeSb.toString());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static String getTime(){
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(date);
    }
    public static String getYear(){
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        return year+"";
    }
    public static String getHourse(){
        Calendar cal = Calendar.getInstance();

        int month = cal.get(Calendar.MONTH) + 1;
        return month+"";
    }
    public static String getDay(){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        return day+"";
    }
    public static String getTimeNewDate(){
        String day = DateUtils.getDay();
        String hourse=DateUtils.getHourse();
        String year=DateUtils.getYear();
        return year+'-'+hourse+'-'+day;
    }

    public static Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    public static String getDay(String date) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = df.parse(df.format(new Date()));//当前时间
            Date de = df.parse(date);//过去
            long l = now.getTime() - de.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            if(day>0){
                return day+"天前";
            }
            if(hour>0){
                return hour+"小时前";
            }
            if(min>0){
                return min+"分钟前";
            }
            if(s>0){
                return min+"秒前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "刚刚";
    }
    public static String messageId(){
        int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r2=(int)(Math.random()*(10));
        long now = System.currentTimeMillis();//一个13位的时间戳
        String paymentID0 =String.valueOf(r1)+String.valueOf(r2)+String.valueOf(now);
        int r3=(int)(Math.random()*(10));//产生2个0-9的随机数
        int r4=(int)(Math.random()*(10));
        String paymentID = paymentID0 +String.valueOf(r3)+String.valueOf(r4);// 订单ID
        return paymentID;
    }
}
