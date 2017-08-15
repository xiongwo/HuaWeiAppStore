package com.xiongwo.huaweiappstore.mvp.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.adapter.section.TopContactsSection;
import com.xiongwo.huaweiappstore.adapter.top.TopTopWrapper;
import com.xiongwo.huaweiappstore.base.BaseMvpFragment;
import com.xiongwo.huaweiappstore.bean.AppBean;
import com.xiongwo.huaweiappstore.bean.TopBean;
import com.xiongwo.huaweiappstore.mvp.presenter.TopFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.presenter.impl.TopFragmentPresenterImpl;
import com.xiongwo.huaweiappstore.mvp.view.view.TopFragmentView;
import com.xiongwo.huaweiappstore.util.LogUtil;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.LoadingPager;
import com.xiongwo.huaweiappstore.view.widget.ViewUpSearch;
import com.xiongwo.recyclerviewlibrary.section.SectionRVAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 排行
 * Created by 熊，我 on 2017/8/5.
 */

public class TopFragment extends BaseMvpFragment<TopFragmentPresenter> implements TopFragmentView {

    private TopBean mTopBean;

    // 指示搜索框是否为打开状态，true为展开状态，false为收缩状态，默认为打开状态
    private boolean isExpand = true;

    @BindView(R.id.rv)
    public RecyclerView mRecyclerView;
    @BindView(R.id.search)
    public ViewUpSearch mViewUpSearch;

    @Inject
    public TopFragmentPresenterImpl mTopFragmentPresenterImpl;

    @Override
    protected View createLoadedView() {
        View view = UIUtil.inflate(R.layout.fragment_top);
        ButterKnife.bind(this, view);

        // TopBean对象 String对应Section的title
        Map<String, List<AppBean>> appBeanMap = mTopBean.getAppBeanMap();
        Set<String> strings = appBeanMap.keySet();

        // Section区
        SectionRVAdapter sectionRVAdapter = new SectionRVAdapter(getContext());
        for (String key : strings) {
            List<AppBean> appBeanList = appBeanMap.get(key);
            TopContactsSection section = new TopContactsSection(getContext(), key, appBeanList);
            sectionRVAdapter.addSection(section);
        }

        // 头部区
        TopTopWrapper topWrapper = new TopTopWrapper(getContext(), sectionRVAdapter);
        topWrapper.addTopData(mTopBean.getTopTopBeanList());

        mRecyclerView.setAdapter(topWrapper);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstItemPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                // firstItemPosition = 0 --> 只在头部发生搜索栏的展开和收缩
                // dy > 0 --> 上滑；在头部时，上滑，收缩搜索栏
                // dy < 0 --> 下滑；在头部时，下滑，展开搜索栏
                if (firstItemPosition == 0 && dy > 0 && isExpand) {
                    // 当前为展开状态；满足上面条件时，变更为收缩状态
                    mViewUpSearch.updateShow(!isExpand);
                    isExpand = false; // 避免重复调用
                } else if (firstItemPosition == 0 && dy < 0 && !isExpand) {
                    // 当前为收缩状态；满足上面条件时，变更为展开状态
                    mViewUpSearch.updateShow(!isExpand);
                    isExpand = true; // 避免重复调用
                }
            }
        });

        return view;
    }

    @Override
    protected void load() {
        mTopFragmentPresenterImpl.getTopData(mBaseActivity);
    }

    @Override
    public void onTopDataSuccess(TopBean topBean) {
        mTopBean = topBean;
        setState(LoadingPager.LoadResult.success);
        LogUtil.information("Top --> " + topBean.getTopTopBeanList().size());
    }

    @Override
    public void onTopDataError(String errorMsg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected TopFragmentPresenter initInject() {
        mFragmentComponent.inject(this);
        return mTopFragmentPresenterImpl;
    }
}
