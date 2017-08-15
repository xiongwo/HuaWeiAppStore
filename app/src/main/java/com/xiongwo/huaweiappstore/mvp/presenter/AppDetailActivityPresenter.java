package com.xiongwo.huaweiappstore.mvp.presenter;

import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.AppDetailActivityView;

/**
 * Created by 熊，我 on 2017/8/14.
 */

public interface AppDetailActivityPresenter extends BasePresenter<AppDetailActivityView> {
    void getAppDetailData(BaseActivity baseActivity, String packageName);
}
