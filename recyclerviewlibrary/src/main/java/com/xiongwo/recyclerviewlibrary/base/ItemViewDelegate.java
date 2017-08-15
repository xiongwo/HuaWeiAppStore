package com.xiongwo.recyclerviewlibrary.base;

/**
 * Created by xzhang on 2017/5/21.
 */

public interface ItemViewDelegate<T> {

    /** 返回该类型Item的布局id **/
    int getItemViewLayoutId() ;

    /** 判断该Item的Type属性值是否与存储在SparseArrayCompat中的key值一致 **/
    boolean isForViewType(T item, int position) ;

    /** 设置控件的内容及属性 **/
    void convert(ViewHolder holder, T t, int position) ;
}
