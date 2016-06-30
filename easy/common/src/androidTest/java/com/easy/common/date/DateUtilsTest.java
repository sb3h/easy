package com.easy.common.date;

import android.test.AndroidTestCase;

import com.easy.common.LogCatUtils;

public class DateUtilsTest extends AndroidTestCase {

    public void testGetShortDateFormat() {
        String time = "2016-06-30 11:20";
        time = DateUtils.getShortDateFormat(time);
        LogCatUtils.log(time);
    }

}
