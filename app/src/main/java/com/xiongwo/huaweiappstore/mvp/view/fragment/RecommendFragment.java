package com.xiongwo.huaweiappstore.mvp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.activity.AppDetailActivity;
import com.xiongwo.huaweiappstore.adapter.RecommendAdapter;
import com.xiongwo.huaweiappstore.adapter.top.RecommendTopWrapper;
import com.xiongwo.huaweiappstore.base.BaseMvpFragment;
import com.xiongwo.huaweiappstore.bean.RecommendBean;
import com.xiongwo.huaweiappstore.mvp.presenter.RecommendFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.presenter.impl.RecommendFragmentPresenterImpl;
import com.xiongwo.huaweiappstore.mvp.view.view.RecommendFragmentView;
import com.xiongwo.huaweiappstore.util.LogUtil;
import com.xiongwo.huaweiappstore.util.ToastUtil;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.LoadingPager;
import com.xiongwo.recyclerviewlibrary.pullrefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主页 --> 推荐
 * Created by 熊，我 on 2017/8/5.
 */

public class RecommendFragment extends BaseMvpFragment<RecommendFragmentPresenter> implements RecommendFragmentView {

    private RecommendBean mRecommendBean;
    private List<RecommendBean.RecommendAppBean> mAppBeen = new ArrayList<>();

    @BindView(R.id.ptr)
    public PullToRefreshView mPullToRefreshView;
    @BindView(R.id.rv_recommend)
    public RecyclerView mRecyclerView;

    @Inject
    public RecommendFragmentPresenterImpl mRecommendFragmentPresenterImpl;
    private RecommendAdapter mRecommendAdapter;
    private RecommendTopWrapper mTopWrapper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 为ViewPager中的第一个Fragment，要手动show
        show();
    }

    @Override
    protected View createLoadedView() {
        View view = UIUtil.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this, view);

        // 列表的数据适配器
        mRecommendAdapter = new RecommendAdapter(getContext(), mRecommendBean.getRecommendAppBeanList());

        // 顶部轮播图的适配器
        mTopWrapper = new RecommendTopWrapper(getContext(), mRecommendAdapter);
        mTopWrapper.addDataAll(mRecommendBean.getBannerList());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTopWrapper);

        // 禁止下拉
        mPullToRefreshView.setPullDownEnable(false);
        // 设置拉动监听
        mPullToRefreshView.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 下拉刷新的回调方法
            }

            @Override
            public void onLoadMore() {
                // 上拉加载的回调方法
                // 这里只是重新请求数据
                mRecommendFragmentPresenterImpl.getRecommendDataMore(mBaseActivity);
            }
        });

        // 点击Item跳转
        mRecommendAdapter.setOnAppDetailItemClickListener(new RecommendAdapter.OnAppDetailItemClickListener() {
            @Override
            public void goAppDetail(String packageName) {
                Intent intent = new Intent(mBaseActivity, AppDetailActivity.class);
                intent.putExtra("packageName", packageName);
                mBaseActivity.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    protected void load() {
        mRecommendFragmentPresenterImpl.getRecommendData(mBaseActivity);
    }

    @Override
    public void onRecommendDataSuccess(RecommendBean recommendBean) {
        mRecommendBean = recommendBean;
        setState(LoadingPager.LoadResult.success);
        LogUtil.information("Recommend --> " + recommendBean.getRecommendAppBeanList().size());
    }

    @Override
    public void onRecommendDataMoreSuccess(RecommendBean recommendBean) {
        // 轮播图不改变，所以只传入应用集合的数据
        mAppBeen = recommendBean.getRecommendAppBeanList();
        // 清空原有的数据
        mRecommendAdapter.clearData();
        // 重新添加数据
        mRecommendAdapter.addDataAll(mAppBeen);
        // 通知Wrapper，数据发生改变
        mTopWrapper.notifyDataSetChanged();
        // 告诉PullToRefreshView，已经加载完成，让设置恢复原样
        mPullToRefreshView.onFinishLoading();
        ToastUtil.showToast(mBaseActivity, "加载更多已完成");
    }

    @Override
    public void onRecommendDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected RecommendFragmentPresenter initInject() {
        // 完成注入
        mFragmentComponent.inject(this);
        // 返回PresenterImpl
        return mRecommendFragmentPresenterImpl;
    }
}
