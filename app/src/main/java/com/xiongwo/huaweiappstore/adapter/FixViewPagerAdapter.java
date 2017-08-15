package com.xiongwo.huaweiappstore.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.xiongwo.huaweiappstore.util.LogUtil;

import java.util.List;

/**
 * ViewPager
 * Created by 熊，我 on 2017/8/5.
 */

public class FixViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;
    private List<Fragment> mFragmentList;

    public FixViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) super.instantiateItem(container, position);
        } catch (Exception e) {
            LogUtil.error("FixViewPagerAdapter.instantiateItem --> " + e);
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    public void setTitles(String[] titles) {
        mTitles = titles;
    }

    public void setFragments(List<Fragment> fragmentList) {
        mFragmentList = fragmentList;
    }
}
