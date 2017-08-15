package com.xiongwo.huaweiappstore.di.module;

import android.content.Context;

import com.xiongwo.huaweiappstore.base.StoreApplication;
import com.xiongwo.huaweiappstore.di.scope.ContextLife;
import com.xiongwo.huaweiappstore.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2  Application
 * Created by 熊，我 on 2017/8/7.
 */
@Module
public class ApplicationModule {

    private StoreApplication mStoreApplication;

    public ApplicationModule(StoreApplication storeApplication) {
        mStoreApplication = storeApplication;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mStoreApplication.getApplicationContext();
    }
}
