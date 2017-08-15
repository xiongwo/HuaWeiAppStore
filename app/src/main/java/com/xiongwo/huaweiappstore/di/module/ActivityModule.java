package com.xiongwo.huaweiappstore.di.module;

import android.app.Activity;
import android.content.Context;

import com.xiongwo.huaweiappstore.di.scope.ContextLife;
import com.xiongwo.huaweiappstore.di.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2  Activity
 * Created by 熊，我 on 2017/8/7.
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }
}
