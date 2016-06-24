package com.easy.common.device;

import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * 获取屏幕宽高、密度
 */
public class ScreenUtils {

    public static int[] getWidthAndHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
//        int[] array = new int[]{display.getWidth(), display.getHeight()};
        Point point = new Point();
        display.getSize(point);
        int[] array = new int[]{point.x, point.y};
        return array;
    }

    public static float getDensity(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    public static float getScaledDensity(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.scaledDensity;
    }

}
