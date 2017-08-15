package com.xiongwo.huaweiappstore.mvp.view.view;


import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.AppMoreRecommendBean;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface AppMoreRecommendView extends BaseView {
    void onAppMoreRecommendDataSuccess(AppMoreRecommendBean appMoreRecommendBean) ;
    void onAppMoreRecommendDataError(String msg) ;
}
