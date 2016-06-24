package com.easy.common;

import android.util.Log;

public class LogCatUtils {

    private static final String TAG = "My";

    public static void log(String message) {
        Log.i(TAG, message);
    }

}
