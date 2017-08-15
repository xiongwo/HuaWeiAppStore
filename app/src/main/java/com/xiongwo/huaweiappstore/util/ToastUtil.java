package com.xiongwo.huaweiappstore.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 熊，我 on 2017/8/5.
 */

public class ToastUtil {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
