package com.easy.common.res;

import android.content.res.Resources;

/**
 * px、dp、sp之间相互转换。
 */
public class PxConvertUtil {

    public static int dp2px(Resources resources, float dpValue) {
        float scale = resources.getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Resources resources, float spValue) {
        float scale = resources.getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    public static int px2dp(Resources resources, float pxValue) {
        float scale = resources.getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Resources resources, float pxValue) {
        float scale = resources.getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }

}
