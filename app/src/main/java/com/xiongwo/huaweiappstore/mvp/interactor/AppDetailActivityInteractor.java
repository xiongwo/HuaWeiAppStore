package com.xiongwo.huaweiappstore.mvp.interactor;

import com.xiongwo.huaweiappstore.api.AppDetailApi;
import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.AppDetailBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by 熊，我 on 2017/8/14.
 */

public class AppDetailActivityInteractor {

    private IGetDataDelegate<AppDetailBean> mDelegate;

    @Inject
    public AppDetailActivityInteractor() {

    }

    private HttpOnNextListener<AppDetailBean> mHttpOnNextListener = new HttpOnNextListener<AppDetailBean>() {
        @Override
        public void onNext(AppDetailBean appDetailBean) {
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            mDelegate.getDataSuccess(JsonParserUtil.parseAppDetailBean(string));
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };

    public void loadAppDetailData(BaseActivity baseActivity, String packageName, IGetDataDelegate<AppDetailBean> delegate) {
        mDelegate = delegate;
        AppDetailApi appDetailApi = new AppDetailApi(mHttpOnNextListener, baseActivity, packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(appDetailApi);
    }
}
