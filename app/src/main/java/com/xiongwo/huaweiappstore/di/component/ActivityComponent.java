package com.xiongwo.huaweiappstore.di.component;

import android.app.Activity;
import android.content.Context;

import com.xiongwo.huaweiappstore.activity.AppDetailActivity;
import com.xiongwo.huaweiappstore.activity.AppMoreRecommendActivity;
import com.xiongwo.huaweiappstore.activity.CategorySubscribeActivity;
import com.xiongwo.huaweiappstore.di.module.ActivityModule;
import com.xiongwo.huaweiappstore.di.scope.ContextLife;
import com.xiongwo.huaweiappstore.di.scope.PerActivity;

import dagger.Component;

/**
 * Dagger2  Activity
 * Created by 熊，我 on 2017/8/7.
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    Activity getActivity();

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

//    void inject(CategoryToolActivity activity);
    void inject(CategorySubscribeActivity activity);
//    void inject(CategoryNecessaryActivity activity);
//    void inject(CategoryNewActivity activity);
//    void inject(CategorySubjectActivity activity);
    void inject(AppDetailActivity activity);
    void inject(AppMoreRecommendActivity activity);
}
