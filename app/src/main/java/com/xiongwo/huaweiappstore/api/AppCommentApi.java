package com.xiongwo.huaweiappstore.api;


import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xiongwo.huaweiappstore.bean.AppCommentBean;
import com.xiongwo.huaweiappstore.util.JsonParserUtil;
import com.xiongwo.rxretrofitlibrary.api.BaseApi;
import com.xiongwo.rxretrofitlibrary.listener.HttpOnNextListener;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppCommentApi extends BaseApi<AppCommentBean> {

    private String packageName ;

    public AppCommentApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity, String packageName) {
        super(listener, rxAppCompatActivity);
        setMothed("AppStore/app/comment/"+packageName);
        this.packageName = packageName ;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getAppCommentData(packageName);
    }

    @Override
    public AppCommentBean call(ResponseBody responseBody) {
        String string = "";
        try {
            string = responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("string:"+string);
        return JsonParserUtil.parseAppCommentBean(string);
    }
}
