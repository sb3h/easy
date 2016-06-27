package com.easy.common.device;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

import java.lang.reflect.InvocationTargetException;

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

    public static int[] getRealWidthAndHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point point = new Point();
            display.getRealSize(point);
            return new int[]{point.x, point.y};
        } else {
            try {
                int width = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                int height = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
                return new int[]{width, height};
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return null;
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
