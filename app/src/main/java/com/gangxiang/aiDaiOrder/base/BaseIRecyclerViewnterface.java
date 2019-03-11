package com.gangxiang.aiDaiOrder.base;

import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.dl7.recycler.listener.OnRequestDataListener;

public interface BaseIRecyclerViewnterface<T> {

    //获取下拉刷新监听，由子类实现
    public abstract SwipeRefreshLayout.OnRefreshListener getOnRefreshListener();

    //获取分页加载监听，由子类实现
    public abstract OnRequestDataListener getOnRequestDataListener();

    //获取adapter
    public abstract BaseQuickAdapter getAdapter();

    //点击事件
    public abstract void onViewItemClick(View view, int position);

    //获取列表中的item布局
    @LayoutRes
    public abstract int attachItemLayoutRes();

    //获取列表中的headview布局
    @LayoutRes
    public abstract int attachHeadViewLayoutRes();

    //获取列表中的FoodView布局
    @LayoutRes
    public abstract int attachFoodViewLayoutRes();

    //获取列表类型，由以下4种
    //public static final int RecyclerView_v = 0;//垂直
    //public static final int RecyclerView_h = 1;//水平
    //public static final int RecyclerView_g = 2;//gridview
    //public static final int RecyclerView_sv = 3;//瀑布流
    public abstract int getRecyclerViewType();

    //假如列表类型是RecyclerView_g和RecyclerView_sv，则需要列数
    public abstract int getColumn();

    //获取加载列表item数据的接口的id，在requestCallBack()中会用到
    public abstract int getListDateInterfaceId();

    //item布局的数据填充
    public abstract void convertView(BaseViewHolder holder, T item);

    public abstract Class getBeanClass();

    public abstract String getDateListStr(String response);

}
