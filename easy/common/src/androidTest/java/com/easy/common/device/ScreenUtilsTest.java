package com.easy.common.device;

import android.test.AndroidTestCase;

import com.easy.common.LogCatUtils;

public class ScreenUtilsTest extends AndroidTestCase {

    public void testGetWidthAndHeight() {
        int[] wh = ScreenUtils.getWidthAndHeight(getContext());
        LogCatUtils.log("screen width=" + wh[0] + "，screen height=" + wh[1]);
    }

    public void testGetRealWidthAndHeight() {
        int[] wh = ScreenUtils.getRealWidthAndHeight(getContext());
        assertNotNull(wh);
        LogCatUtils.log("screen real width=" + wh[0] + "，screen real height=" + wh[1]);
    }

    public void testGetDensity() {
        float density = ScreenUtils.getDensity(getContext());
        LogCatUtils.log("screen density=" + density);
    }

    public void testGetScaledDensity() {
        float scaledDensity = ScreenUtils.getScaledDensity(getContext());
        LogCatUtils.log("screen scaledDensity=" + scaledDensity);
    }

}
