package com.xiongwo.huaweiappstore.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * 管理Activity
 * Created by 熊，我 on 2017/8/4.
 */

public class AppActivityManager {

    private static AppActivityManager sActivityManager;
    private static Stack<Activity> sActivityStack;

    private AppActivityManager() {

    }

    /**
     * 单例
     * @return
     */
    public static AppActivityManager getInstance() {
        if (sActivityManager == null) {
            sActivityManager = new AppActivityManager();
        }
        return sActivityManager;
    }

    /**
     * 添加Activity到堆栈中
     * @param activity
     */
    public void addActivityToStack(Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<>();
        }
        sActivityStack.add(activity);
    }

    /**
     * 从堆栈中移除Activity
     * @param activity
     */
    public void removeActivityFromStack(Activity activity) {
        sActivityStack.remove(activity);
    }


    /**
     * 获取栈顶Activity
     */
    public Activity getTopActivity() {
        return sActivityStack.lastElement();
    }

    /**
     * 结束栈顶Activity
     */
    public void killTopActivity() {
        Activity activity = sActivityStack.lastElement();
        killActivity(activity);
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        for (Activity activity : sActivityStack) {
            if (activity.getClass().equals(cls)) {
                killActivity(activity);
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    private void killActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束所有Activity
     */
    private void killAllActivity() {
        for (int i = 0, size = sActivityStack.size(); i < size; i++) {
            if (null != sActivityStack.get(i)) {
                sActivityStack.get(i).finish();
            }
        }
        sActivityStack.clear();
    }

    /**
     * 退出应用
     * @param context
     */
    public void exitApp(Context context) {
        try {
            killAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }
    }
}
