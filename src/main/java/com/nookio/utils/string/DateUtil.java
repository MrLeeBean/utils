package com.nookio.utils.string;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nookio on 15/5/13.
 */
public class DateUtil {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    private static String datePattern = "yyyy-MM-dd";
    private static DateTimeFormatter localDateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter localDatePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static DateTimeFormatter localTimePattern = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static Date addDate(Date date, Integer field,Integer addNum){
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, addNum);
        Long time =calendar.getTimeInMillis();
        return  new Date(time);
    }

    public static Date formate(String before) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(before);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换失败");
        }
        System.out.println(date);
        return date;
    }

    public static Date timeMillToDate(String before) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(before);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换失败");
        }
        System.out.println(date);
        return date;
    }

    public static String getNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String result = simpleDateFormat.format(new Date());
        return result;
    }

    public static Date getToday(Date before) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = null;//simpleDateFormat.format(before);
        Date date = null;
        try {
            dateStr = simpleDateFormat.format(before);
            date = simpleDateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("日期转换失败");
        }
        //System.out.println(date);
        return date;
    }

    public static Date parseDate(String string) {
        try {
            return dateFormat.parse(string);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDateTime(String string) {
        try {
            return dateTimeFormat.parse(string);
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDateTime parseLocalDateTime(String string) {
        try {
            return LocalDateTime.parse(string, localDateTimePattern);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatDate(Date date) {
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatTime(Date date) {
        try {
            return timeFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatDateTime(Date date) {
        try {
            return dateTimeFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        try {
            return localDateTime.format(localDateTimePattern);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatLocalDate(LocalDate localDate) {
        try {
            return localDate.format(localDatePattern);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatLocalTime(LocalTime localTime) {
        try {
            return localTime.format(localTimePattern);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date addDay(Date date, Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static String plusDay(String date, Integer day) {
        LocalDate localDate = LocalDate.parse(date).plusDays(day);
        return localDate.format(DateTimeFormatter.ofPattern(datePattern));
    }

    public static LocalDateTime minusMinutes(LocalDateTime localDateTime, Integer minutes) {
        return localDateTime.minusMinutes(minutes);
    }

    public static Date getFirstDayOfMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date date1 = null;
        int firstNum = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date theDate = calendar.getTime();
        theDate.setDate(firstNum);
        String s = df.format(theDate);
        StringBuffer str = new StringBuffer().append(s).append(" T00:00:00.000+0800");
        try {
            date1 = df.parse(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date1;

    }

    private static Date getLastDayOfMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date1 = null;
        int lastNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date theDate = calendar.getTime();
        theDate.setDate(lastNum);
        String s = df.format(theDate);
        //StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
        try {
            date1 = df.parse(s);
        } catch (Exception e) {
        }

        return date1;

    }

    /**
     * 将date转换为"yyyy-MM-dd'T'HH:mm:ss.SSSZ" 格式。
     **/
    public static Date formateForClientWithStr(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换失败over!!!");
        }
        //System.out.println(date);
        return date;
    }


    public static Date formateForClientWithDate(Date date, int i) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar1.set(Calendar.DAY_OF_MONTH, i);
        Date nowDate = calendar1.getTime();
        String dateStr1 = df.format(nowDate);

        dateStr1 += "T00:00:00.000+0800";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date2 = null;
        try {
            date = simpleDateFormat.parse(dateStr1);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换失败over!!!");
        }
        //System.out.println(date);
        return date;
    }


}
