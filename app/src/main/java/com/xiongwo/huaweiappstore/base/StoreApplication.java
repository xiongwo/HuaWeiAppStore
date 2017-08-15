package com.xiongwo.huaweiappstore.base;

import android.os.Handler;

import com.xiongwo.huaweiappstore.di.component.ApplicationComponent;
import com.xiongwo.huaweiappstore.di.component.DaggerApplicationComponent;
import com.xiongwo.huaweiappstore.di.module.ApplicationModule;
import com.xiongwo.recyclerviewlibrary.App;

/**
 * 初始化全局变量Handler、Application、Dagger2中的ApplicationComponent
 * Created by 熊，我 on 2017/8/5.
 */

public class StoreApplication extends App {

    private static int sMainThreadId;
    private static Handler sHandler;
    private static StoreApplication sContext;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sMainThreadId = (int) getMainLooper().getThread().getId(); // 可能不对
        sHandler = new Handler();
        sContext = this;

        initApplicationComponent();
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    /**
     * 返回主线程的pid
     * @return
     */
    public static int getMainThreadId() {
        return sMainThreadId;
    }

    /**
     * 返回Handler
     * @return
     */
    public static Handler getHandler() {
        return sHandler;
    }

    public static StoreApplication getContext(){
        return sContext;
    }
}
