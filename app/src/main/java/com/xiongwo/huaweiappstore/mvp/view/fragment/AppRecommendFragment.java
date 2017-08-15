package com.xiongwo.huaweiappstore.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.activity.AppDetailActivity;
import com.xiongwo.huaweiappstore.adapter.section.AppRecommendHotSection;
import com.xiongwo.huaweiappstore.adapter.section.AppRecommendPopularSection;
import com.xiongwo.huaweiappstore.adapter.section.AppRecommendTasteSection;
import com.xiongwo.huaweiappstore.base.BaseMvpFragment;
import com.xiongwo.huaweiappstore.bean.AppRecommendBean;
import com.xiongwo.huaweiappstore.mvp.presenter.impl.AppRecommendFragmentPresenterImpl;
import com.xiongwo.huaweiappstore.mvp.view.view.AppRecommendFragmentView;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.LoadingPager;
import com.xiongwo.recyclerviewlibrary.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendFragment extends BaseMvpFragment<AppRecommendFragmentPresenterImpl> implements AppRecommendFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    AppRecommendFragmentPresenterImpl appRecommendFragmentPresenter ;

    private String packageName ;

    private AppRecommendBean appRecommendBean ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        packageName = ((AppDetailActivity)getActivity()).getAppPackageName();
        show();
    }

    @Override
    protected View createLoadedView() {
        View view = UIUtil.inflate(R.layout.fragment_app_recommend);
        ButterKnife.bind(this,view);

        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
        adapter.addSection(new AppRecommendPopularSection(getContext(),"流行应用",appRecommendBean.getPopularAppBeanList(),packageName));
        adapter.addSection(new AppRecommendTasteSection(getContext(),"兴趣相近的用户也安装了",appRecommendBean.getTasteAppBeanList(),packageName));
        adapter.addSection(new AppRecommendHotSection(getContext(),"本周热议的社区应用",appRecommendBean.getHotAppBeanList(),packageName));
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

    @Override
    public void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean) {
        this.appRecommendBean = appRecommendBean ;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppRecommendDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void load() {
        appRecommendFragmentPresenter.getAppRecommendData(mBaseActivity,packageName);
    }

    @Override
    protected AppRecommendFragmentPresenterImpl initInject() {
        mFragmentComponent.inject(this);
        return appRecommendFragmentPresenter;
    }
}
