package com.easy.common.device;

import android.app.Activity;
import android.test.AndroidTestCase;

import com.easy.common.LogCatUtils;

public class ScreenUtilsTest extends AndroidTestCase {

    public void testGetWidthAndHeight() {
        int[] wh = ScreenUtils.getWidthAndHeight((Activity) getContext());
        LogCatUtils.log("w=" + wh[0] + "，h=" + wh[1]);
    }

}
