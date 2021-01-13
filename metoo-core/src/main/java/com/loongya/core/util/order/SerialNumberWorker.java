package com.loongya.core.util.order;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 流水号生成器
 */
public class SerialNumberWorker {
    /**
     * 序号
     */
    private long sequence = 0L;
    /**
     * 最后时间戳
     */
    private Long lastTimestamp = 0L;

    private static class ISerialNumberHolder {
        private static final SerialNumberWorker instance = new SerialNumberWorker();
    }

    public static SerialNumberWorker getInstance() {
        return ISerialNumberHolder.instance;
    }

    private SerialNumberWorker() {

    }

    public synchronized String nextId() {
        Long timestamp = timeGen();
        // 如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp.equals(timestamp)) {
            sequence++;
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return timestamp.toString() + sequence;
    }

    /**
     * ID生成 prefix区分业务
     *
     * @param prefix
     * @return
     */
    public String nextId(String prefix) {
        return prefix + nextId();
    }

    private Long timeGen() {
        return Long.valueOf(getFormatDateTime("yyyyMMddHHmmssSSS", getDateTime()));
    }

    public static void main(String[] args) {
        /*Set<String> s = new HashSet<>();
        for (int i = 0; i < 1000000; i++) {
            s.add(SerialNumberWorker.getInstance().nextId());
        }*/
        System.out.println(SerialNumberWorker.getInstance().nextId());
    }

    /**
     * 格式化指定日期
     *
     * @param pattern  传入日期格式"yyyy-MM-dd HH:mm:ss"
     * @param dateTime 传入的日期
     * @return 格式化后的字符串
     */
    public static String getFormatDateTime(String pattern, LocalDateTime dateTime) {
        if (null == dateTime) {
            return "";
        }

        if (StringUtils.isBlank(pattern)) {
            // 默认显示的时间格式
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 获取系统当前日期
     *
     * @return 日期
     */
    public static LocalDateTime getDateTime() {
        return LocalDateTime.now(ZoneId.of("UTC+08:00"));
    }
}