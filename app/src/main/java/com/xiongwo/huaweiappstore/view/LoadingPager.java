package com.xiongwo.huaweiappstore.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.xiongwo.huaweiappstore.R;
import com.xiongwo.huaweiappstore.util.UIUtil;

/**
 * 主页ViewPager中的Fragment布局，根据不同的状态，显示不同的布局
 * Created by 熊，我 on 2017/8/5.
 */

public abstract class LoadingPager extends FrameLayout {

    /** 默认状态 */
    public static final int STATE_UNKNOWN = 0 ;

    /** 加载中 */
    public static final int STATE_LOADING = 1 ;

    /** 加载失败 */
    public static final int STATE_ERROR = 2 ;

    /** 空 */
    public static final int STATE_EMPTY = 3 ;

    /** 加载成功状态 */
    public static final int STATE_SUCCESS = 4 ;

    /**  当前状态 */
    protected int state = STATE_UNKNOWN ;

    private View loadingView ;//加载中界面
    private View errorView ;//加载失败界面
    private View emptyView ;//空界面
    private View successView ;//加载成功界面

    public LoadingPager(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 创建并添加布局
     */
    private void init() {
        this.setBackgroundColor(UIUtil.getColor(R.color.bg_page));
        loadingView = createLoadingView();
        if (loadingView != null) {
            this.addView(loadingView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView();
        if(errorView != null){
            this.addView(errorView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        emptyView = createEmptyView();
        if(emptyView != null){
            this.addView(emptyView,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        showPager();
    }

    /** 根据状态显示界面 */
    private void showPager() {
        if(loadingView != null){
            loadingView.setVisibility(state == STATE_LOADING || state == STATE_UNKNOWN ? View.VISIBLE : View.GONE);
        }
        if(errorView != null){
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.GONE);
        }
        if(emptyView != null){
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.GONE);
        }
        // 请求数据成功后，才创建布局
        if(state == STATE_SUCCESS && successView == null){
            successView = createLoadedView();
            this.addView(successView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
    }

    /** 显示布局，用于在非成功加载的情况下，重新加载布局 */
    public void show() {
        // 恢复默认设置
        if(state == STATE_EMPTY || state == STATE_ERROR){
            state = STATE_UNKNOWN ;
        }
        if(state == STATE_UNKNOWN){
            state = STATE_LOADING ;
//            ThreadManager.creatLongPool().execute(new LoadingTask());
            load();
        }
        showPager();
    }

    /** 创建加载中界面 */
    private View createLoadingView() {
        return UIUtil.inflate(R.layout.loading_page) ;
    }

    /** 创建加载错误界面 */
    private View createErrorView() {
        View view = UIUtil.inflate(R.layout.loading_error_page) ;
//        Button settingBtn = (Button) view.findViewById(R.id.setting);
//        settingBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //进入手机中的设置界面
//            }
//        });
        //点击刷新界面
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingPager.this.show();
            }
        });
        return  view ;
    }

    /** 创建空界面 */
    private View createEmptyView() {
        return null ;
    }

    /** 服务器返回状态枚举 */
    public enum LoadResult {
        error(STATE_ERROR), empty(STATE_EMPTY), success(STATE_SUCCESS);

        int value ;

        LoadResult(int value){
            this.value = value ;
        }

        public int getValue(){
            return value ;
        }
    }

    /**
     * 在重写的load方法中调用，标识请求数据的结果
     * @param result
     */
    public void setState(LoadResult result){
        state = result.getValue() ;
        UIUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPager();
            }
        });
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
