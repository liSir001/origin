package com.lisir.cn.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    private final static String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss.SSS)
     */
    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";


    /**
     * 返回当前时间
     *
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     *
     * @param localDateTime
     * @return
     */
    public static String getDateTimeAsString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return localDateTime.format(formatter);
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     *
     * @param localDateTime
     * @return
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        //return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     *
     * @param time
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDateTime.parse(time, df);
    }

    /**
     * 计算两个LocalDateTime 之间的毫秒数
     *
     * @param time1
     * @param time2
     * @return
     */
    public static Long minusToMillsLocalDateTime(LocalDateTime time1, LocalDateTime time2) {
        return Duration.between(time1, time2).toMillis();
    }

    /**
     * 日期格式转换
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date parseDate(String date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        return dateFormat.parse(date);
    }

    /**
     * 日期格式转换
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static String parseToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        return dateFormat.format(date);
    }

}
