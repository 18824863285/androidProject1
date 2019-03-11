package com.gangxiang.aiDaiOrder.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.bean.Product;

import java.util.ArrayList;
import java.util.List;

public class GoodSizeAdapter extends BaseQuickAdapter<Product.DataBean.ColorBean.CupBean>{

    private Context mContext;
    private SizeListAdapter.OnClickListen mOnClickListen;

    public GoodSizeAdapter(Context context) {
        super(context);
        mContext = context;
    }

    public void setOnClickListen(SizeListAdapter.OnClickListen onClickListen) {
        this.mOnClickListen = onClickListen;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.item_size;
    }

    @Override
    protected void convert(BaseViewHolder holder, Product.DataBean.ColorBean.CupBean item) {
        holder.setText(R.id.tv_cup,item.getPCTCup()+mContext.getString(R.string.cup));
        RecyclerView recyclerView = holder.getView(R.id.recyclerView_size_list);
        SizeListAdapter adapter  = new SizeListAdapter(mContext);
        if(mOnClickListen != null){
            adapter.setOnClickListen(mOnClickListen);
        }
        RecyclerViewHelper.initRecyclerViewV(mContext,recyclerView,adapter);
        adapter.updateItems(item.getSize());
    }

}
