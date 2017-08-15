package com.xiongwo.huaweiappstore.mvp.presenter;


import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppIntroductionFragmentView;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppIntroductionFragmentPresenter extends BasePresenter<AppIntroductionFragmentView> {
    void getAppIntroductionData(BaseActivity activity, String packageName) ;
}
