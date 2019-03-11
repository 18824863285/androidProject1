package com.gangxiang.aiDaiOrder.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRequestDataListener;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.adapter.GoodSizeAdapter;
import com.gangxiang.aiDaiOrder.adapter.SizeListAdapter;
import com.gangxiang.aiDaiOrder.base.BaseRecyclerViewFragment;
import com.gangxiang.aiDaiOrder.bean.Product;
import com.gangxiang.aiDaiOrder.config.Configs;
import com.gangxiang.aiDaiOrder.model.ApiService;
import com.gangxiang.aiDaiOrder.model.RequestIds;
import com.gangxiang.aiDaiOrder.util.DensityUtils;
import com.gangxiang.aiDaiOrder.util.MessageManager;
import com.gangxiang.aiDaiOrder.util.SimpleDraweeViewUtil;
import com.gangxiang.aiDaiOrder.widght.FluidLayout;
import com.gangxiang.aiDaiOrder.zoomabledrawee.ZoomImageActivity;
import java.util.ArrayList;
import java.util.List;

public class GoodListFargment extends BaseRecyclerViewFragment<Product.DataBean> {

    public static final String ID = "id";
    private String mId;

    public static final String Time = "time";
    private String mTime;

    @Override
    public void init() {
        super.init();
        mSwipeRefreshLayout.setEnabled(false);
        Bundle bundle = getArguments();
        if(bundle != null){
            mLoadingDiaolg.show();
            mId = bundle.getString(ID);
            mTime = bundle.getString(Time);
            ApiService.getProduct(mId,mStringCallback, RequestIds.id_GetProduct);
        }
        mRecyclerView.setBackgroundColor(Color.parseColor("#f5f5f5"));
        mAdapter.updateItems(mDateList);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
    public int attachItemLayoutRes() {
        return R.layout.item_good;
    }

    @Override
    public int attachHeadViewLayoutRes() {
        return super.attachHeadViewLayoutRes();
    }

    @Override
    public int getListDateInterfaceId() {
        return RequestIds.id_GetProduct;
    }

    @Override
    public int getRecyclerViewType() {
        return 1;
    }

    @Override
    public void convertView(final BaseViewHolder holder, final Object item) {
        final Product.DataBean dataBean = (Product.DataBean) item;
        RecyclerView recyclerView = holder.getView(R.id.recyclerView_good_item);
        GoodSizeAdapter goodSizeAdapter = new GoodSizeAdapter(mActivity);

        goodSizeAdapter.setOnClickListen(new SizeListAdapter.OnClickListen() {
            @Override
            public void add() {
                cal(holder,dataBean);
            }

            @Override
            public void del() {
                cal(holder,dataBean);
            }

            @Override
            public void change(int num) {
                cal(holder,dataBean);
            }
        });

        RecyclerViewHelper.initRecyclerViewV(mActivity,recyclerView,goodSizeAdapter);
        View headView = inflaterView(R.layout.headview_good);
        goodSizeAdapter.addHeaderView(headView);
        if(dataBean.getColor() != null && dataBean.getColor().size() > 0){
            goodSizeAdapter.updateItems(dataBean.getColor().get(0).getCup());
            SimpleDraweeViewUtil.setNetWorkImage(headView.findViewById(R.id.head_img_tx), Configs.IMG+dataBean.getColor().get(0).getPCImg());
            headView.findViewById(R.id.head_img_tx).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String imgs1[] = {Configs.IMG+dataBean.getColor().get(0).getPCImg()};
                    Intent intent = new Intent(mActivity, ZoomImageActivity.class);
                    intent.putExtra(ZoomImageActivity.IAMGE,imgs1);
                    intent.putExtra(ZoomImageActivity.INDEX,0);
                    startActivity(intent);
                }
            });
        }
        ((TextView)headView.findViewById(R.id.good_name1)).setText(dataBean.getProTitle());
        ((TextView)headView.findViewById(R.id.dpj)).setText(getString(R.string.dpj)+dataBean.getProPrice());
        ((TextView)headView.findViewById(R.id.zkj)).setText(getString(R.string.zkj)+dataBean.getDiscountPrice());

        FluidLayout fluidLayout = headView.findViewById(R.id.fluidLayout);
        List<Product.DataBean.ColorBean> list1 = dataBean.getColor();

        initFluidLayout(fluidLayout,list1,goodSizeAdapter,headView);
        holder.setText(R.id.dj,getString(R.string.dj)+dataBean.getDiscountPrice());
        holder.setText(R.id.yjshsj,getString(R.string.yj)+mTime+getString(R.string.ch));

        cal(holder,dataBean);

    }

    private void cal(BaseViewHolder holder, Product.DataBean dataBean){
        //dataBean.setAllNum(dataBean.getAllNum() - 1);
        int buyNum = 0;
        List<Product.DataBean.ColorBean> colorBeans = dataBean.getColor();
        if(colorBeans != null && colorBeans.size() > 0){
            for (int i = 0; i < colorBeans.size() ; i ++){
                Product.DataBean.ColorBean colorBean = colorBeans.get(i);
                List<Product.DataBean.ColorBean.CupBean> cupBeans = colorBean.getCup();
                if(cupBeans != null && cupBeans.size() > 0){
                    for (int k = 0 ; k < cupBeans.size() ; k ++){
                        Product.DataBean.ColorBean.CupBean cupBean = cupBeans.get(k);
                        List<Product.DataBean.ColorBean.CupBean.SizeBean> sizeBeans = cupBean.getSize();
                        if(sizeBeans != null && sizeBeans.size() > 0){
                            for (int t = 0; t < sizeBeans.size() ; t++){
                                Product.DataBean.ColorBean.CupBean.SizeBean sizeBean = sizeBeans.get(t);
                                buyNum += Integer.valueOf(sizeBean.getPSNumber());
                            }
                        }
                    }
                }
            }
        }
        dataBean.setAllNum(buyNum);
        holder.setText(R.id.spzsl,   dataBean.getAllNum()+"");
        holder.setText(R.id.hj,getString(R.string.hj)+ dataBean.getAllNum() *Double.valueOf(dataBean.getDiscountPrice())+"");
    }

    private void initFluidLayout(FluidLayout mFluidLayout,
                                 final List<Product.DataBean.ColorBean> mDynamicTypeList,
                                 final GoodSizeAdapter goodSizeAdapter,final View headview){
        mFluidLayout.removeAllViews();
        mFluidLayout.setGravity(Gravity.TOP);
        final List<TextView> textViewList  = new ArrayList<>();
        for(int i = 0; i < mDynamicTypeList.size(); i++){
            final TextView tv = new TextView(mActivity);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13);
            if(i == 0){
                tv.setBackgroundResource(R.drawable.bg_bq);
                tv.setTextColor(Color.parseColor("#FFFFFF"));
            }else {
                tv.setBackgroundResource(0);
                tv.setTextColor(Color.parseColor("#999999"));
            }
            tv.setText(mDynamicTypeList.get(i).getPCTitle());
            int padding1 = DensityUtils.dp2px(mActivity,9);
            int padding2 = DensityUtils.dp2px(mActivity,2);
            tv.setPadding(padding1,padding2,padding1,padding2);
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            int margins1 = DensityUtils.dp2px(mActivity,8);
            params.setMargins(margins1, margins1, margins1, margins1);
            mFluidLayout.addView(tv, params);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0 ;i < textViewList.size(); i++ ){
                        TextView textView = textViewList.get(i);
                        textView.setBackgroundResource(0);
                        textView.setTextColor(Color.parseColor("#999999"));
                    }
                    tv.setBackgroundResource(R.drawable.bg_bq);
                    tv.setTextColor(Color.parseColor("#FFFFFF"));
                    for (int i = 0 ; i < textViewList.size() ; i ++){
                        if(tv ==  textViewList.get(i)){
                            goodSizeAdapter.updateItems(mDynamicTypeList.get(i).getCup());
                            SimpleDraweeViewUtil.setNetWorkImage(headview.findViewById(R.id.head_img_tx), Configs.IMG+  mDynamicTypeList.get(i).getPCImg());
                            final int finalI = i;
                            headview.findViewById(R.id.head_img_tx).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String imgs1[] = {Configs.IMG+  mDynamicTypeList.get(finalI).getPCImg()};
                                    Intent intent = new Intent(mActivity, ZoomImageActivity.class);
                                    intent.putExtra(ZoomImageActivity.IAMGE,imgs1);
                                    intent.putExtra(ZoomImageActivity.INDEX,0);
                                    startActivity(intent);
                                }
                            });

                            break;
                        }
                    }
                }
            });
            textViewList.add(tv);
        }
    }


}
