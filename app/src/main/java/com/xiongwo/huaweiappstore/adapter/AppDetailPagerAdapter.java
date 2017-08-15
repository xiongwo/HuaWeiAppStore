package com.xiongwo.huaweiappstore.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.xiongwo.huaweiappstore.util.LogUtil;

import java.util.List;

/**
 * Created by 熊，我 on 2017/8/14.
 */

public class AppDetailPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList;

    public AppDetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) super.instantiateItem(container, position);
        } catch (Exception e) {
            LogUtil.error("AppDetailPagerAdapter instantiateItem --> " + e);
        }
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        mFragmentList = fragmentList;
    }
}
