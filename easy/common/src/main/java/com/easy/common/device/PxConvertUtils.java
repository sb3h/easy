package com.easy.common.device;

import android.content.Context;


public class PxConvertUtils {

    /**
     * dp转换成px
     *
     * @param context
     * @param dpValue dp的值
     * @return 转换成px的值
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = ScreenUtils.getDensity(context);
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp转换成px
     *
     * @param context
     * @param spValue sp的值
     * @return 转换成px的值
     */
    public static int sp2px(Context context, float spValue) {
        float scale = ScreenUtils.getScaledDensity(context);
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * px转换成dp
     *
     * @param context
     * @param pxValue px的值
     * @return 转换成dp的值
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = ScreenUtils.getDensity(context);
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px转换成sp
     *
     * @param context
     * @param pxValue px的值
     * @return 转换成sp的值
     */
    public static int px2sp(Context context, float pxValue) {
        float scale = ScreenUtils.getScaledDensity(context);
        return (int) (pxValue / scale + 0.5f);
    }

}
