package com.xiongwo.huaweiappstore.mvp.interactor;

import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.api.RecommendApi;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.RecommendBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by 熊，我 on 2017/8/10.
 */

public class RecommendFragmentInteractor {

    private IGetDataDelegate<RecommendBean> mDelegate;

    @Inject
    public RecommendFragmentInteractor() {

    }

    private HttpOnNextListener<RecommendBean> mHttpOnNextListener = new HttpOnNextListener<RecommendBean>() {
        @Override
        public void onNext(RecommendBean recommendBean) {
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            RecommendBean recommendBean = JsonParserUtil.parseRecommendBean(string);
            mDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

    public void loadRecommendData(BaseActivity baseActivity, IGetDataDelegate<RecommendBean> delegate) {
        mDelegate = delegate;
        RecommendApi recommendApi = new RecommendApi(mHttpOnNextListener, baseActivity);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(recommendApi);
    }
}
