package com.gangxiang.aiDaiOrder.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRequestDataListener;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.adapter.NkSizeAdapter;
import com.gangxiang.aiDaiOrder.base.BaseRecyclerViewFragment;
import com.gangxiang.aiDaiOrder.bean.NkProduct;
import com.gangxiang.aiDaiOrder.config.Configs;
import com.gangxiang.aiDaiOrder.model.ApiService;
import com.gangxiang.aiDaiOrder.util.SimpleDraweeViewUtil;
import com.gangxiang.aiDaiOrder.zoomabledrawee.ZoomImageActivity;
import java.util.List;
import static com.gangxiang.aiDaiOrder.model.RequestIds.id_GetNkProduct;

public class NkGragment extends BaseRecyclerViewFragment<NkProduct.DataBean>{

    @Override
    public void init() {
        super.init();
        mAdapter.updateItems(mDateList);
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView.setBackgroundColor(Color.parseColor("#f5f5f5"));
        ApiService.getNkProduct(1,mStringCallback,id_GetNkProduct);
    }

    @Override
    public SwipeRefreshLayout.OnRefreshListener getOnRefreshListener() {
        return null;
    }

    @Override
    public OnRequestDataListener getOnRequestDataListener() {
        return null;
    }

    @Override
    public void onViewItemClick(View view, int position) {

    }

    @Override
    public int getRecyclerViewType() {
        return RecyclerView_sv;
    }

    @Override
    public int getColumn() {
        return 4;
    }

    @Override
    public int attachItemLayoutRes() {
        return R.layout.item_nk;
    }

    @Override
    public int getListDateInterfaceId() {
        return id_GetNkProduct;
    }

    @Override
    public void convertView(final BaseViewHolder holder, Object item) {
        final NkProduct.DataBean dataBean = (NkProduct.DataBean) item;
        holder.setText(R.id.good_name1,dataBean.getProTitle());
        holder.setText(R.id.zkj,"¥"+dataBean.getProPrice());
        holder.setVisible(R.id.dpj,false);
        NkSizeAdapter nkSizeAdapter = new NkSizeAdapter(mActivity);
        RecyclerView recyclerView = holder.getView(R.id.recyclerView_nksc);
        RecyclerViewHelper.initRecyclerViewV(mActivity,recyclerView,nkSizeAdapter);
        nkSizeAdapter.updateItems(dataBean.getSize());
        holder.getView(R.id.head_img_tx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imgs1[] = {Configs.IMG+dataBean.getProImg()};
                Intent intent = new Intent(mActivity, ZoomImageActivity.class);
                intent.putExtra(ZoomImageActivity.IAMGE,imgs1);
                intent.putExtra(ZoomImageActivity.INDEX,0);
                startActivity(intent);
            }
        });
        nkSizeAdapter.setOnClickListen(new NkSizeAdapter.OnClickListen() {
            @Override
            public void changeNum(String id) {
               cal(holder,dataBean);
            }

            @Override
            public void changeBuInput() {
                cal(holder,dataBean);
            }
        });
        cal(holder,dataBean);
        SimpleDraweeViewUtil.setNetWorkImage(holder.getView(R.id.head_img_tx), Configs.IMG+dataBean.getProImg());
    }

    private void cal(BaseViewHolder holder, NkProduct.DataBean dataBean ){
        int buyNum = 0;
        if(dataBean.getSize() != null && dataBean.getSize().size() > 0){
            List<NkProduct.DataBean.SizeBean> sizeBeans = dataBean.getSize();
            for (int i = 0 ; i < sizeBeans.size() ; i++){
                NkProduct.DataBean.SizeBean sizeBean = sizeBeans.get(i);
                int num = Integer.valueOf( sizeBean.getPSNumber());
                buyNum += num;
            }
        }
        holder.setText(R.id.spzsl,getString(R.string.sl)+buyNum);
        holder.setText(R.id.hj,getString(R.string.hj)+buyNum*Double.valueOf(dataBean.getProPrice()));
    }

    @Override
    public void requestCallBack(String response, int id) {
        super.requestCallBack(response, id);
    }
}
