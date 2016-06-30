package com.easy.common.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 通过生日计算出年龄
     *
     * @param birthday 出生年月日，如：1991-09-09
     * @return 今年的年龄，如果返回-1，则表明birthday格式有问题
     */
    public static int getAgeByBirthday(String birthday) {
        if (birthday == null || "".equals(birthday.trim()))
            throw new IllegalArgumentException("The birthday is null or \'\'");

        birthday = birthday.replaceAll("-", "/");
        Date birthDate;
        try {
            birthDate = DateFormat.getInstance().parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }

        Calendar calendar = Calendar.getInstance();
        Calendar tempCalendar = Calendar.getInstance();
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
     * @param time 想要进行简短格式化的时间，如：2015-10-11 10:09
     * @return 简短化的时间，如果返回null，则表明time格式有问题
     */
    public static String getShortDateFormat(String time) {
        if (time == null || "".equals(time.trim()))
            throw new IllegalArgumentException("The time is null or \'\'");

        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH) + 1;
        int nowDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        time = time.replaceAll("-", "/");
        Date date;
        try {
            date = DateFormat.getInstance().parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        calendar.setTime(date);

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
