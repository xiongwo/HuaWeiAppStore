package com.xiongwo.huaweiappstore.util;

import android.util.Log;

/**
 * Created by 熊，我 on 2017/8/5.
 */

public class LogUtil {

    public static final String LOG_TAG = "AppStore";

    /**
     * 打印Log的开关
     */
    public static boolean DEBUG = true;

    public static void debug(String log) {
        if (DEBUG)
            Log.d(LOG_TAG, log);
    }

    public static void information(String log) {
        if (DEBUG)
            Log.i(LOG_TAG, log);
    }

    public static void error(String log) {
        if (DEBUG)
            Log.e(LOG_TAG, log);
    }

    public static void debugLog(String tag, String log) {
        if (DEBUG)
            Log.d(tag, log);
    }

    public static void informationLog(String tag, String log) {
        if (DEBUG)
            Log.d(tag, log);
    }

    public static void errorLog(String tag, String log) {
        if (DEBUG)
            Log.d(tag, log);
    }
}
