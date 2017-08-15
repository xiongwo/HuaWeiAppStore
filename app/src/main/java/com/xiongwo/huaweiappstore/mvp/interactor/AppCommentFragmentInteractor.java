package com.xiongwo.huaweiappstore.mvp.interactor;



import com.xiongwo.huaweiappstore.api.AppCommentApi;
import com.xiongwo.huaweiappstore.api.IGetDataDelegate;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.bean.AppCommentBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.http.HttpManager;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppCommentFragmentInteractor {

    private IGetDataDelegate<AppCommentBean> mDelegate ;

    @Inject
    public AppCommentFragmentInteractor(){

    }

    public void loadAppCommentData(BaseActivity activity, String packageName, IGetDataDelegate<AppCommentBean> delegate){
        this.mDelegate = delegate ;
        AppCommentApi api = new AppCommentApi(httpListener,activity,packageName);
        HttpManager httpManager = HttpManager.getInstance();
        httpManager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppCommentBean> httpListener = new HttpOnNextListener<AppCommentBean>() {
        @Override
        public void onNext(AppCommentBean appCommentBean) {
            mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppCommentBean appCommentBean = JsonParserUtil.parseAppCommentBean(string);
            mDelegate.getDataSuccess(appCommentBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
