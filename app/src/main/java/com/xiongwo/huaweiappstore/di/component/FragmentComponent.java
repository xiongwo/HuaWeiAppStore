package com.xiongwo.huaweiappstore.di.component;

import android.app.Activity;
import android.content.Context;

import com.xiongwo.huaweiappstore.di.module.FragmentModule;
import com.xiongwo.huaweiappstore.di.scope.ContextLife;
import com.xiongwo.huaweiappstore.di.scope.PerFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.AppCommentFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.AppDetailIntroduceFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.AppRecommendFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.CategoryFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.RecommendFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.TopFragment;

import dagger.Component;

/**
 * Dagger2  Fragment
 * Created by 熊，我 on 2017/8/7.
 */
@PerFragment
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {

    Activity getActivity();

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    void inject(RecommendFragment fragment) ;
    void inject(CategoryFragment fragment) ;
    void inject(TopFragment fragment) ;
    void inject(AppDetailIntroduceFragment fragment) ;
    void inject(AppCommentFragment fragment) ;
    void inject(AppRecommendFragment fragment) ;
}
