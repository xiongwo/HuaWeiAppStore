package com.xiongwo.huaweiappstore.mvp.view.view;

import com.xiongwo.huaweiappstore.base.mvpbase.BaseView;
import com.xiongwo.huaweiappstore.bean.RecommendBean;

/**
 * HomeActivity  推荐Fragment
 * MVP  View层，负责抽象UI界面的逻辑
 * Created by 熊，我 on 2017/8/7.
 */

public interface RecommendFragmentView extends BaseView {
    void onRecommendDataSuccess(RecommendBean recommendBean) ;
    void onRecommendDataMoreSuccess(RecommendBean recommendBean) ;
    void onRecommendDataError(String msg);
}
