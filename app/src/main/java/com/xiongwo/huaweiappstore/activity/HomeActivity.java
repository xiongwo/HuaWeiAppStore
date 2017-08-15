package com.xiongwo.huaweiappstore.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.adapter.FixViewPagerAdapter;
import com.xiongwo.huaweiappstore.base.BaseActivity;
import com.xiongwo.huaweiappstore.base.BaseFragment;
import com.xiongwo.huaweiappstore.factory.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主页
 * Created by 熊，我 on 2017/8/5.
 */

public class HomeActivity extends BaseActivity {

    private String[] titles = {"推荐","分类","排行","管理","我的"};
    private List<Fragment> mFragmentList;
    private FixViewPagerAdapter mFixViewPagerAdapter;

    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.main_viewpager)
    ViewPager mViewPager;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final int statusHeight = getStatusBarHeight();
            statusBar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = statusBar.getHeight();
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) statusBar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    statusBar.setLayoutParams(params);
                }
            });
        }
        initViewPagerFragment();
    }

    /**
     * 配置TabLayout和ViewPager
     */
    private void initViewPagerFragment() {

        mFragmentList = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            mFragmentList.add(FragmentFactory.createFragment(i));
        }

        mFixViewPagerAdapter = new FixViewPagerAdapter(getSupportFragmentManager());
        mFixViewPagerAdapter.setTitles(titles);
        mFixViewPagerAdapter.setFragments(mFragmentList);

        mViewPager.setAdapter(mFixViewPagerAdapter);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                BaseFragment baseFragment = FragmentFactory.createFragment(position);
                baseFragment.show();
            }
        });
    }
}
