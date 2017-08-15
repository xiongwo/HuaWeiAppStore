package com.xiongwo.huaweiappstore.mvp.view.view;

import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.AppDetailBean;

/**
 * Created by 熊，我 on 2017/8/14.
 */

public interface AppDetailActivityView extends BaseView {
    void onAppDetailDataSuccess(AppDetailBean appDetailBean);
    void onAppDetailDataError(String errorMsg);
}
