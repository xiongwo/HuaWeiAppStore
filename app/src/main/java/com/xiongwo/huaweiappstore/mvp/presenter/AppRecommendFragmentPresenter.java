package com.xiongwo.huaweiappstore.mvp.presenter;


import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppRecommendFragmentView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppRecommendFragmentPresenter extends BasePresenter<AppRecommendFragmentView> {
    void getAppRecommendData(BaseActivity activity, String packageName);
}
