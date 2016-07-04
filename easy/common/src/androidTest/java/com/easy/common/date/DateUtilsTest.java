package com.easy.common.date;

import android.test.AndroidTestCase;

import com.easy.common.LogCatUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilsTest extends AndroidTestCase {

    private static final String LOG_CLASS_NAME = "【DateUtilsTest】";

    public void testGetAgeByBirthday() {
        String time1 = "2010-11-20";
        String time2 = "2010/01/10";
        String time3 = "2010-07-04 12:10";
        String time4 = "2010~10~11";

        LogCatUtils.log(LOG_CLASS_NAME + "（testGetAgeByBirthday）"
                + String.valueOf(DateUtils.getAgeByBirthday(time1)));
        LogCatUtils.log(LOG_CLASS_NAME + "（testGetAgeByBirthday）"
                + String.valueOf(DateUtils.getAgeByBirthday(time2)));
        LogCatUtils.log(LOG_CLASS_NAME + "（testGetAgeByBirthday）"
                + String.valueOf(DateUtils.getAgeByBirthday(time3)));
        assertEquals(-1, DateUtils.getAgeByBirthday(time4));
    }

    public void testGetShortDateFormat() {
        String time1 = "2016-06-30 11:20";
        String time2 = "2015/01/22 10:31";
        long time3 = new Date().getTime();
        String time4 = "2013-10-11";

        LogCatUtils.log(LOG_CLASS_NAME + "（testGetShortDateFormat）"
                + DateUtils.getShortDateFormat(time1));
        LogCatUtils.log(LOG_CLASS_NAME + "（testGetShortDateFormat）"
                + DateUtils.getShortDateFormat(time2));
        LogCatUtils.log(LOG_CLASS_NAME + "（testGetShortDateFormat）"
                + DateUtils.getShortDateFormat(time3));
        assertNull("解析是会出错的", DateUtils.getShortDateFormat(time4));
    }

    public void testParseTime() {
        // parseTime和getSimpleDateFormat配套使用才正确

        Object parseResult1 = DateUtils.parseTime("2010-11-21 10:08", "yyyy-MM-dd", null);
        Object parseResult2 = DateUtils.parseTime("2010-11-21 10:08", "yyyy-MM-dd HH:mm", null);
        Object parseResult3 = DateUtils.parseTime("2010-11-21", "yyyy-MM-dd HH:mm", null);

        LogCatUtils.log(LOG_CLASS_NAME + "（testParseTime）"
                + parseResult1.toString());
        LogCatUtils.log(LOG_CLASS_NAME + "（testParseTime）"
                + parseResult2.toString());
        assertNull("解析是会出错的", parseResult3);

        Date date = new Date("2010/11/21 10:08");
        LogCatUtils.log(LOG_CLASS_NAME + "（testParseTime）"
                + date.toString());
        LogCatUtils.log(LOG_CLASS_NAME + "（testParseTime）"
                + DateUtils.getSimpleDateFormat("yyyy-MM-dd HH:mm").format(date));
        LogCatUtils.log(LOG_CLASS_NAME + "（testParseTime）"
                + DateUtils.getSimpleDateFormat("yyyy-MM-dd HH:mm").format((Date) parseResult2));
        LogCatUtils.log(LOG_CLASS_NAME + "（testParseTime）"
                + String.valueOf(date.getTime()));
        LogCatUtils.log(LOG_CLASS_NAME + "（testParseTime）"
                + String.valueOf(((Date) parseResult2).getTime()));
    }

    public void testGetSimpleDateFormat() {
        String format = "yyyy-MM-dd HH:mm";
        Date date = new Date();

        SimpleDateFormat dateFormat1 = new SimpleDateFormat(format);
        SimpleDateFormat dateFormat2 = DateUtils.getSimpleDateFormat(format);

        LogCatUtils.log(LOG_CLASS_NAME + "（testGetSimpleDateFormat）"
                + dateFormat1.format(date));
        LogCatUtils.log(LOG_CLASS_NAME + "（testGetSimpleDateFormat）"
                + dateFormat2.format(date));
    }

    public void testGetCalendar() {
//        Calendar calendar = Calendar.getInstance();
        Calendar calendar = DateUtils.getCalendar();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        LogCatUtils.log(LOG_CLASS_NAME + "（testGetCalendar）"
                + year + "-" + month + "-" + dayOfMonth + " " + hourOfDay + ":" + minute);
    }

    public void testIsLeapYear() {
        int year1 = 2010;
        int year2 = 2004;

        LogCatUtils.log(LOG_CLASS_NAME + "（testIsLeapYear）"
                + DateUtils.isLeapYear(year1));
        LogCatUtils.log(LOG_CLASS_NAME + "（testIsLeapYear）"
                + DateUtils.isLeapYear(year2));
    }

}
