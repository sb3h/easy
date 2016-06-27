package com.easy.common.device;

import android.app.Activity;
import android.test.AndroidTestCase;

import com.easy.common.LogCatUtils;

public class ScreenUtilsTest extends AndroidTestCase {

    public void testGetWidthAndHeight() {
        int[] wh = ScreenUtils.getWidthAndHeight((Activity) getContext());
        LogCatUtils.log("screen width=" + wh[0] + "，screen height=" + wh[1]);
    }

    public void testGetRealWidthAndHeight() {
        int[] wh = ScreenUtils.getRealWidthAndHeight((Activity) getContext());
        assertNotNull(wh);
        LogCatUtils.log("screen real width=" + wh[0] + "，screen real height=" + wh[1]);
    }

    public void testGetDensity() {
        float density = ScreenUtils.getDensity((Activity) getContext());
        LogCatUtils.log("screen density=" + density);
    }

    public void testGetScaledDensity() {
        float scaledDensity = ScreenUtils.getScaledDensity((Activity) getContext());
        LogCatUtils.log("screen scaledDensity=" + scaledDensity);
    }

}
