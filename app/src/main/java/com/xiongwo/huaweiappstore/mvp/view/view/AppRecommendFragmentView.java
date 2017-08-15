package com.xiongwo.huaweiappstore.mvp.view.view;


import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.AppRecommendBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppRecommendFragmentView extends BaseView {
    void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean);
    void onAppRecommendDataError(String msg);
}
