package com.xiongwo.huaweiappstore.factory;

import com.xiongwo.huaweiappstore.base.BaseFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.AppManagerFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.CategoryFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.MyFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.RecommendFragment;
import com.xiongwo.huaweiappstore.mvp.view.fragment.TopFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 熊，我 on 2017/8/5.
 */

public class FragmentFactory {

    /**
     * 推荐
     */
    public static final int TAB_RECOMMEND = 0;

    /**
     * 分类
     */
    public static final int TAB_CATEGORY = 1;

    /**
     * 排行
     */
    public static final int TAB_TOP = 2;

    /**
     * 管理
     */
    public static final int TAB_APPMANAGER = 3;

    /**
     * 我的
     */
    public static final int TAB_MY = 4;

    private static Map<Integer, BaseFragment> mFragments = new HashMap<>();

    public static BaseFragment createFragment(int index) {
        BaseFragment fragment = mFragments.get(index);
        // 如果之前没有创建, 创建新的Fragment
        if (fragment == null) {
            switch (index) {
                case TAB_RECOMMEND:
                    fragment = new RecommendFragment();
                    break;
                case TAB_CATEGORY:
                    fragment = new CategoryFragment();
                    break;
                case TAB_TOP:
                    fragment = new TopFragment();
                    break;
                case TAB_APPMANAGER:
                    fragment = new AppManagerFragment();
                    break;
                case TAB_MY:
                    fragment = new MyFragment();
                    break;
            }
            // 把创建的Fragment 存起来
            mFragments.put(index, fragment);
        }
        return fragment;
    }
}
