package com.xiongwo.huaweiappstore.mvp.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.activity.CategorySubscribeActivity;
import com.xiongwo.huaweiappstore.adapter.section.CategoryContactsSection;
import com.xiongwo.huaweiappstore.adapter.top.CategoryTopWrapper;
import com.xiongwo.huaweiappstore.base.BaseMvpFragment;
import com.xiongwo.huaweiappstore.bean.CategoryBean;
import com.xiongwo.huaweiappstore.mvp.presenter.CategoryFragmentPresenter;
import com.xiongwo.huaweiappstore.mvp.presenter.impl.CategoryFragmentPresenterImpl;
import com.xiongwo.huaweiappstore.mvp.view.view.CategoryFragmentView;
import com.xiongwo.huaweiappstore.util.LogUtil;
import com.xiongwo.huaweiappstore.util.UIUtil;
import com.xiongwo.huaweiappstore.view.LoadingPager;
import com.xiongwo.huaweiappstore.view.widget.ViewUpSearch;
import com.xiongwo.recyclerviewlibrary.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类
 * Created by 熊，我 on 2017/8/5.
 */

public class CategoryFragment extends BaseMvpFragment<CategoryFragmentPresenter> implements CategoryFragmentView {

    private CategoryBean mCategoryBean;

    // 指示搜索框是否为打开状态，true为展开状态，false为收缩状态，默认为打开状态
    private boolean isExpand = true;

    @BindView(R.id.rv)
    public RecyclerView mRecyclerView;
    @BindView(R.id.search)
    public ViewUpSearch mViewUpSearch;

    @Inject
    public CategoryFragmentPresenterImpl mCategoryFragmentPresenterImpl;

    @Override
    protected View createLoadedView() {
        View view = UIUtil.inflate(R.layout.fragment_category);
        ButterKnife.bind(this, view);

        SectionRVAdapter sectionRVAdapter = new SectionRVAdapter(getContext());
        sectionRVAdapter.addSection(new CategoryContactsSection(getContext(), mCategoryBean.getTitle(), mCategoryBean.getCategoryDataBeanList()));

        CategoryTopWrapper categoryTopWrapper = new CategoryTopWrapper(getContext(), sectionRVAdapter);
        categoryTopWrapper.addTopData(mCategoryBean.getCategoryTopBeanList());

        mRecyclerView.setAdapter(categoryTopWrapper);
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

        categoryTopWrapper.setOnItemClickListener(new CategoryTopWrapper.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 0) {
                    mBaseActivity.startActivity(new Intent(mBaseActivity, CategorySubscribeActivity.class));
                }
            }
        });

        return view;
    }

    @Override
    protected void load() {
        mCategoryFragmentPresenterImpl.getCategoryData(mBaseActivity);
    }

    @Override
    public void onCategoryDataSuccess(CategoryBean categoryBean) {
        mCategoryBean = categoryBean;
        setState(LoadingPager.LoadResult.success);
        LogUtil.information("Category --> " + categoryBean.getCategoryDataBeanList().size());
    }

    @Override
    public void onCategoryDataError(String msg) {

    }

    @Override
    protected CategoryFragmentPresenter initInject() {
        mFragmentComponent.inject(this);
        return mCategoryFragmentPresenterImpl;
    }
}
