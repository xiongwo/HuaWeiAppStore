package com.xiongwo.huaweiappstore.mvp.interactor;



import com.xiongwo.huaweiappstore.api.AppMoreRecommendApi;
import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.AppMoreRecommendBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppMoreRecommendInteractor {

    private IGetDataDelegate<AppMoreRecommendBean> mDelegate ;

    @Inject
    public AppMoreRecommendInteractor(){

    }

    public void loadAppMoreRecommend(BaseActivity activity, String type, String packageName, IGetDataDelegate<AppMoreRecommendBean> delegate){
        this.mDelegate = delegate ;
        AppMoreRecommendApi api = new AppMoreRecommendApi(httpListener,activity,type,packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppMoreRecommendBean> httpListener = new HttpOnNextListener<AppMoreRecommendBean>() {
        @Override
        public void onNext(AppMoreRecommendBean appMoreRecommendBean) {
            mDelegate.getDataSuccess(appMoreRecommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppMoreRecommendBean appMoreRecommendBean = JsonParserUtil.parseAppMoreRecommendBean(string);
            mDelegate.getDataSuccess(appMoreRecommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
