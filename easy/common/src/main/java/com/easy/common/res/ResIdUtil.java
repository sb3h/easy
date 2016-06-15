package com.easy.common.res;

import android.content.res.Resources;

/**
 * 获取资源id，当代码导成jar包时，方便与资源文件进行分离。
 */
public class ResIdUtil {

    public static int getIdFromView(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "id", packageName);
    }

    public static int getIdFromLayout(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "layout", packageName);
    }

    public static int getIdFromDrawable(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "drawable", packageName);
    }

    public static int getIdFromString(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "string", packageName);
    }

    public static int getIdFromColor(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "color", packageName);
    }

    public static int getIdFromDimen(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "dimen", packageName);
    }

    public static int getIdFromAnim(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "anim", packageName);
    }

    public static int getIdFromAttr(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "attr", packageName);
    }

    public static int getIdFromStyleable(String packageName, Resources resources, String name) {
        return resources.getIdentifier(name, "styleable", packageName);
    }

}
