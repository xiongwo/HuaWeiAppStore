package com.xiongwo.huaweiappstore.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.activity.AppDetailActivity;
import com.xiongwo.huaweiappstore.adapter.section.AppCommentContactsSection;
import com.xiongwo.huaweiappstore.adapter.top.AppCommentTopWrapper;
import com.xiongwo.huaweiappstore.base.BaseMvpFragment;
import com.xiongwo.huaweiappstore.bean.AppCommentBean;
import com.xiongwo.huaweiappstore.mvp.presenter.impl.AppCommentFragmentPresenterImpl;
import com.xiongwo.huaweiappstore.mvp.view.view.AppCommentFragmentView;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.LoadingPager;
import com.xiongwo.recyclerviewlibrary.section.SectionRVAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppCommentFragment extends BaseMvpFragment<AppCommentFragmentPresenterImpl> implements AppCommentFragmentView {

    @BindView(R.id.rv)
    RecyclerView rv ;

    @Inject
    AppCommentFragmentPresenterImpl appCommentFragmentPresenter ;

    private String packageName ;
    private AppCommentBean appCommentBean ;
    private List<AppCommentBean.CommentsBean> hotList = new ArrayList<>();
    private List<AppCommentBean.CommentsBean> list = new ArrayList<>();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        packageName = ((AppDetailActivity)getActivity()).getAppPackageName();
        show();
    }

    @Override
    protected View createLoadedView() {
        View view = UIUtil.inflate(R.layout.fragment_app_comment) ;
        ButterKnife.bind(this,view) ;


        for(AppCommentBean.CommentsBean commentsBean : appCommentBean.getComments()){
            //type为1是精彩评论
            if(commentsBean.getCommentType().equals("1")){
                hotList.add(commentsBean);
            }else{
                list.add(commentsBean);
            }
        }

        SectionRVAdapter sectionAdapter = new SectionRVAdapter(getContext());

        if(hotList.size() > 0)
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(),"精彩评论",hotList));
        if(list.size() > 0)
            sectionAdapter.addSection(new AppCommentContactsSection(getContext(),"全部评论",list));

        AppCommentTopWrapper appCommentTopWrapper = new AppCommentTopWrapper(getContext(),sectionAdapter);
        appCommentTopWrapper.addDataAll(appCommentBean);
        rv.setAdapter(appCommentTopWrapper);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }


    @Override
    public void onAppCommentDataSuccess(AppCommentBean appCommentBean) {
        this.appCommentBean = appCommentBean ;
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onAppCommentDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    public void load() {
        appCommentFragmentPresenter.getAppCommentData(mBaseActivity,packageName);
    }

    @Override
    protected AppCommentFragmentPresenterImpl initInject() {
        mFragmentComponent.inject(this);
        return appCommentFragmentPresenter;
    }
}
