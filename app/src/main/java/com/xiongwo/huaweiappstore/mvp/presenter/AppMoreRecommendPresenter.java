package com.xiongwo.huaweiappstore.mvp.presenter;


import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppMoreRecommendView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppMoreRecommendPresenter extends BasePresenter<AppMoreRecommendView> {
    void getAppMoreRecommendData(BaseActivity activity, String type, String packageName) ;
}
