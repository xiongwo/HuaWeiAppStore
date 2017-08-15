package com.xiongwo.huaweiappstore.di.component;

import android.content.Context;

import com.xiongwo.huaweiappstore.di.module.ApplicationModule;
import com.xiongwo.huaweiappstore.di.scope.ContextLife;
import com.xiongwo.huaweiappstore.di.scope.PerApp;

import dagger.Component;

/**
 * Dagger2  Application
 * Created by 熊，我 on 2017/8/7.
 */
@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ContextLife("Application")
    Context getApplication();
}
