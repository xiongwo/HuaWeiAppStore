package com.xiongwo.huaweiappstore.mvp.interactor;



import com.xiongwo.huaweiappstore.api.AppIntroductionApi;
import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.AppIntroductionBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppIntroductionIntroductor {

    private IGetDataDelegate<AppIntroductionBean> mDelegate ;

    @Inject
    public AppIntroductionIntroductor(){

    }

    public void loadAppIntroductino(BaseActivity activity, String packageName, IGetDataDelegate<AppIntroductionBean> delegate){
        this.mDelegate = delegate ;
        AppIntroductionApi api = new AppIntroductionApi(httpListener,activity,packageName);
        HttpManager httpManager = HttpManager.getInstance() ;
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppIntroductionBean> httpListener = new HttpOnNextListener<AppIntroductionBean>() {
        @Override
        public void onNext(AppIntroductionBean appIntroductionBean) {
            mDelegate.getDataSuccess(appIntroductionBean);

        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppIntroductionBean appIntroductionBean = JsonParserUtil.parseAppIntroductionBean(string);
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
