package com.xiongwo.huaweiappstore.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.xiongwo.huaweiappstore.di.scope.ContextLife;
import com.xiongwo.huaweiappstore.di.scope.PerFragment;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger2  Fragment
 * Created by 熊，我 on 2017/8/7.
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    @Inject
    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideFragmentContext() {
        return mFragment.getContext();
    }

    @Provides
    @PerFragment
    public Activity provideFragmentActivity() {
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    public Fragment provideFragment() {
        return mFragment;
    }
}
