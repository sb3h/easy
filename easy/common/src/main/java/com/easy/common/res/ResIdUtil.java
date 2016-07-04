package com.easy.common.res;

import android.content.res.Resources;

public class ResIdUtil {

    private String mPackageName;
    private Resources mResources;

    public ResIdUtil(String packageName, Resources resources) {
        mPackageName = packageName;
        mResources = resources;
    }

    public int getIdFromView(String name) {
        return mResources.getIdentifier(name, "id", mPackageName);
    }

    public int getIdFromLayout(String name) {
        return mResources.getIdentifier(name, "layout", mPackageName);
    }

    public int getIdFromDrawable(String name) {
        return mResources.getIdentifier(name, "drawable", mPackageName);
    }

    public int getIdFromString(String name) {
        return mResources.getIdentifier(name, "string", mPackageName);
    }

    public int getIdFromColor(String name) {
        return mResources.getIdentifier(name, "color", mPackageName);
    }

    public int getIdFromDimen(String name) {
        return mResources.getIdentifier(name, "dimen", mPackageName);
    }

    public int getIdFromAnim(String name) {
        return mResources.getIdentifier(name, "anim", mPackageName);
    }

    public int getIdFromAttr(String name) {
        return mResources.getIdentifier(name, "attr", mPackageName);
    }

    public int getIdFromStyleable(String name) {
        return mResources.getIdentifier(name, "styleable", mPackageName);
    }

}
