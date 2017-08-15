package com.xiongwo.huaweiappstore.mvp.presenter;

import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.mvpbase.BasePresenter;
import com.xiongwo.huaweiappstore.mvp.view.view.RecommendFragmentView;

/**
 *  HomeActivity  推荐Fragment
 *  MVP  Presenter层，负责抽象业务的逻辑
 * Created by 熊，我 on 2017/8/7.
 */

public interface RecommendFragmentPresenter extends BasePresenter<RecommendFragmentView> {
    void getRecommendData(BaseActivity activity);
    void getRecommendDataMore(BaseActivity activity);
}
