package com.sg.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/6/19
 */
public class YyDateUtil {


    /**
     * 获取当天的开始时间
     */
    public static Date getZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static boolean check(String str) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sd.setLenient(false);
            sd.parse(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Date strToDate(String str) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sd.parse(str);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    public static String date2Str() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
        return sd.format(new Date());
    }

    public static String localDateToStr(Date date) {
        if (date != null) {
            Instant instant = date.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            LocalDate localDate = localDateTime.toLocalDate();
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            return localDate.format(timeFormat);
        }
        return "";
    }
}
