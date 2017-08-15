package com.xiongwo.huaweiappstore.mvp.presenter.impl;



import com.xiongwo.huaweiappstore.api.AppRecommendApi;
import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.AppRecommendBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendInteractor {

    private IGetDataDelegate<AppRecommendBean> mDelegate ;

    @Inject
    public AppRecommendInteractor(){

    }

    public void loadAppRecommend(BaseActivity activity, String packageName, IGetDataDelegate<AppRecommendBean> delegate){
        this.mDelegate = delegate ;
        AppRecommendApi api = new AppRecommendApi(httpListener,activity,packageName);
        HttpManager httpListener = HttpManager.getInstance() ;
        httpListener.doHttpDeal(api);
    }

    private HttpOnNextListener<AppRecommendBean> httpListener = new HttpOnNextListener<AppRecommendBean>() {
        @Override
        public void onNext(AppRecommendBean appRecommendBean) {
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppRecommendBean appRecommendBean = JsonParserUtil.parseAppRecommendBean(string);
            mDelegate.getDataSuccess(appRecommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
