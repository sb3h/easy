package com.easy.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateUtils {

    /**
     * 北京时区
     */
    private static final String TIME_ZONE_BEI_JING = "GMT+08:00";

    /**
     * 通过生日计算出年龄
     *
     * @param birthday 出生年月日，传入的格式为：yyyy-MM-dd
     * @return 今年的年龄，如果返回-1，则表明birthday格式有问题
     */
    public static int getAgeByBirthday(String birthday) {
        if (birthday == null || "".equals(birthday.trim()))
            throw new IllegalArgumentException("The birthday is null or \'\'");

        birthday = birthday.replaceAll("/", "-");
        Object parseResult = parseTime(birthday, "yyyy-MM-dd", -1);
        if (parseResult == Integer.valueOf(-1))
            return -1;
        Date birthDate = (Date) parseResult;

        Calendar calendar = getCalendar();
        Calendar tempCalendar = getCalendar();
        tempCalendar.setTime(birthDate);
        if (calendar.before(tempCalendar))
            throw new IllegalArgumentException("The now is before birthday");

        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH) + 1;
        int nowDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(birthDate);
        int birthYear = calendar.get(Calendar.YEAR);
        int birthMonth = calendar.get(Calendar.MONTH) + 1;
        int birthDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        int age = nowYear - birthYear;
        if (nowMonth <= birthMonth) {
            if (nowMonth == birthMonth) {
                if (nowDayOfMonth < birthDayOfMonth)
                    age--;
            } else
                age--;
        }
        return age;
    }

    /**
     * 根据当前时间计算出简短的时间格式，如：12:10、05-10 07:10、2016-06-30 18:18
     *
     * @param time 想要进行简短格式化的时间
     * @return 简短化的时间，如果返回null，则表明time格式有问题
     */
    public static String getShortDateFormat(long time) {
        SimpleDateFormat dateFormat = getSimpleDateFormat("yyyy-MM-dd HH:mm");
        String timeStr = dateFormat.format(new Date(time));
        return getShortDateFormat(timeStr);
    }

    /**
     * 根据当前时间计算出简短的时间格式，如：12:10、05-10 07:10、2016-06-30 18:18
     *
     * @param time 想要进行简短格式化的时间，传入的格式为：yyyy-MM-dd HH:mm
     * @return 简短化的时间，如果返回null，则表明time格式有问题
     */
    public static String getShortDateFormat(String time) {
        if (time == null || "".equals(time.trim()))
            throw new IllegalArgumentException("The time is null or \'\'");

        Calendar calendar = getCalendar();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH) + 1;
        int nowDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        time = time.replaceAll("/", "-");
        Object parseResult = parseTime(time, "yyyy-MM-dd HH:mm", null);
        if (parseResult == null)
            return null;
        calendar.setTime((Date) parseResult);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if (year == nowYear) {
            if (month == nowMonth && dayOfMonth == nowDayOfMonth)
                return getTwoDigit(hourOfDay) + ":" + getTwoDigit(minute);
            else
                return getTwoDigit(month) + "-" + getTwoDigit(dayOfMonth) + " " + getTwoDigit(hourOfDay) + ":" + getTwoDigit(minute);
        } else
            return year + "-" + getTwoDigit(month) + "-" + getTwoDigit(dayOfMonth) + " " + getTwoDigit(hourOfDay) + ":" + getTwoDigit(minute);
    }

    /**
     * 解析时间字符串，如果解析成功，则返回Date对象。
     * 注意：parseTime要和{@link #getSimpleDateFormat(String)}配套使用才能正确
     *
     * @param time       要解析的时间字符串
     * @param format     按此格式进行解析，如yyyy-MM-dd HH:mm
     * @param parseError 解析出错时返回的对象
     * @return 如果解析成功，那么返回Date对象，否则返回parseError对象
     */
    public static Object parseTime(String time, String format, Object parseError) {
        SimpleDateFormat dateFormat = getSimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return parseError;
        }
        return date;
    }

    /**
     * 获取北京时区的SimpleDateFormat对象。
     * 注意：当需要把"2016-10-11 19:11"字符串解析成Date对象，然后在格式化该对象时，
     * 需要调用{@link #parseTime(String, String, Object)}进行解析，然后再调用此方法；
     * 当需要把new Date()格式化时，则直接调用此方法即可
     *
     * @param format 时间格式，如yyyy-MM-dd
     * @return
     */
    public static SimpleDateFormat getSimpleDateFormat(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_BEI_JING));
        return dateFormat;
    }

    /**
     * 获取北京时区的Calendar对象。
     * 注意：当使用calendar.setTime(new Date())时，calendar通过{@link #getCalendar()}获取；
     * 但当使用calendar.setTime(new Date("2016-10-11 12:12"))时，calendar通过{@link Calendar#getInstance()}获取
     *
     * @return
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone(TIME_ZONE_BEI_JING));
    }

    /**
     * 判断是否为闰年
     *
     * @param year 要判断的年份
     * @return true为闰年
     */
    public static boolean isLeapYear(int year) {
        // （year能被4整除，并且不能被100整除）或者year能被400整除，则该年为闰年
        if ((year % 4 == 0 && year % 400 != 0) || year % 400 == 0)
            return true;
        else
            return false;
    }

    // *********************************************************************************************
    // 私有方法

    /**
     * 返回两位的数，如：09、11
     *
     * @param value
     * @return
     */
    private static String getTwoDigit(int value) {
        if (value < 10)
            return "0" + value;
        else
            return String.valueOf(value);
    }

}
