package com.xiongwo.huaweiappstore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiongwo.huaweiappstore.view.LoadingPager;

/**
 * 根据页面请求数据的情况，创建对应的布局，传递LoadingPager中的抽象方法给子类去具体实现布局的和数据的操作
 * Created by 熊，我 on 2017/8/5.
 */

public abstract class BaseFragment extends Fragment {

    private LoadingPager mLoadingPager;
    protected BaseActivity mBaseActivity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBaseActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoadingPager = new LoadingPager(getContext()) {
            @Override
            protected View createLoadedView() {
                return BaseFragment.this.createLoadedView();
            }

            @Override
            protected void load() {
                BaseFragment.this.load();
            }
        };
        return mLoadingPager;
    }

    public void show(){
        if(mLoadingPager != null)
            mLoadingPager.show();
    }

    public void setState(LoadingPager.LoadResult result){
        if(mLoadingPager != null){
            mLoadingPager.setState(result);
        }
    }

    /**
     * 加载成功界面
     * @return
     */
    protected abstract View createLoadedView();

    /**
     * 加载获取数据
     * */
    protected abstract void load() ;
}
