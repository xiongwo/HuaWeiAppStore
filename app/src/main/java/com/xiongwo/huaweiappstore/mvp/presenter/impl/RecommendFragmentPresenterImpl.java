package com.xiongwo.huaweiappstore.mvp.presenter.impl;

import android.os.SystemClock;

import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenterImpl;
import com.xiongwo.huaweiappstore.bean.RecommendBean;
import com.xiongwo.huaweiappstore.mvp.interactor.RecommendFragmentInteractor;
import com.xiongwo.huaweiappstore.mvp.presenter.RecommendFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.RecommendFragmentView;
import com.xiongwo.huaweiappstore.view.LoadingPager;

import javax.inject.Inject;

/**
 * HomeActivity  推荐Fragment
 * MVP  Presenter层的实现类，负责实现业务的逻辑
 * Created by 熊，我 on 2017/8/7.
 */

public class RecommendFragmentPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter {

    @Inject
    public RecommendFragmentInteractor mRecommendFragmentInteractor;

    @Inject
    public RecommendFragmentPresenterImpl() {

    }

    @Override
    public void getRecommendData(BaseActivity activity) {
        mRecommendFragmentInteractor.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onRecommendDataSuccess(recommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onRecommendDataError(errmsg);
            }
        });
    }

    @Override
    public void getRecommendDataMore(BaseActivity activity) {
        mRecommendFragmentInteractor.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onRecommendDataMoreSuccess(recommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onRecommendDataError(errmsg);
            }
        });
    }
}
