package com.gangxiang.aiDaiOrder.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.util.GsonUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public abstract  class BaseRecyclerViewFragment<T> extends BaseFragment implements BaseIRecyclerViewnterface{

    protected BaseQuickAdapter<T> mAdapter;

    @BindView(R.id.recyclerView)
    public
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    public
    SwipeRefreshLayout mSwipeRefreshLayout;

    protected View mHeadView;
    protected View mFootView;

    protected static final int RecyclerView_v = 0;//垂直
    protected static final int RecyclerView_h = 1;//水平
    protected static final int RecyclerView_g = 2;//gridview
    protected static final int RecyclerView_sv = 3;//瀑布流
    protected static int mRecyclerViewType;

    protected boolean mIsLoadMore;
    protected int mPageIndex;
    protected int mPageSize = 10;

    public List<T> mDateList = new ArrayList<>();


    private void initRecyclerViewAcrtivity(){

        if(getAdapter() != null){
            mAdapter = getAdapter();
        }else{
            mAdapter  = new BaseQuickAdapter<T>(mActivity) {
                @Override
                protected int attachLayoutRes() {
                    return attachItemLayoutRes();
                }

                @Override
                protected void convert(BaseViewHolder holder, T item) {
                    convertView(holder, item);
                }
            };
        }

        mRecyclerViewType = getRecyclerViewType();

        switch (mRecyclerViewType){
            case RecyclerView_v:
                RecyclerViewHelper.initRecyclerViewV(mActivity,mRecyclerView,mAdapter);
                break;
            case RecyclerView_h:
                RecyclerViewHelper.initRecyclerViewH(mActivity,mRecyclerView,mAdapter);
                break;
            case RecyclerView_g:
                RecyclerViewHelper.initRecyclerViewG(mActivity,mRecyclerView,mAdapter,getColumn());
                break;
            case RecyclerView_sv:
                RecyclerViewHelper.initRecyclerViewSV(mActivity,mRecyclerView,mAdapter,getColumn());
                break;
            default:
                break;
        }

        if(getOnRefreshListener() != null){
            mSwipeRefreshLayout.setOnRefreshListener(getOnRefreshListener());
        }

        if(getOnRequestDataListener() != null){
            mAdapter.setRequestDataListener(getOnRequestDataListener());
        }

        if(attachHeadViewLayoutRes()!=0){
            mHeadView = inflaterView(attachHeadViewLayoutRes());
            mAdapter.addHeaderView(mHeadView);
        }

        if(attachFoodViewLayoutRes() != 0){
            mFootView = inflaterView(attachFoodViewLayoutRes());
            mAdapter.addFooterView(mFootView);
        }

        mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(mRecyclerViewType == 0){
                    if(mHeadView != null){
                        onViewItemClick(view, position - 1);
                    }else {
                        onViewItemClick(view, position);
                    }
                }else {
                    onViewItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int attachLayoutRes() {
        return R.layout.view_recyclerview;
    }

    @Override
    public void init() {
        initRecyclerViewAcrtivity();
    }

    @Override
    public int attachHeadViewLayoutRes() {
        return 0;
    }

    @Override
    public int attachFoodViewLayoutRes() {
        return 0;
    }

    @Override
    public int getRecyclerViewType() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 0;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        return null;
    }

    @Override
    public Class getBeanClass() {
        return null;
    }

    @Override
    public String getDateListStr(String response) {
        return null;
    }

    @Override
    public void requestCallBack(String response, int id) {
        if(id == getListDateInterfaceId()){
            try {
                JSONObject jsonObject = new JSONObject(response);

                String dateListStr = getDateListStr(response);

                if(dateListStr == null){
                    dateListStr = jsonObject.optString("data");
                }

                List<T> dateList = GsonUtils.fromJsonToList(dateListStr,
                        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
                if(mIsLoadMore){
                    mDateList.addAll(dateList);
                    mAdapter.addItems(dateList);
                }else{
                    mDateList =dateList;
                    mAdapter.updateItems(dateList);

                    if(dateList == null || dateList.size() == 0){
                        if(mHeadView == null){
                            f(R.id.iv_no_date).setVisibility(View.VISIBLE);
                            f(R.id.swipeRefreshLayout).setVisibility(View.GONE);
                        }else {
//                            View nodateView = mHeadView.findViewById(R.id.nodate);
//                            if(nodateView != null){
//                                nodateView.setVisibility(View.VISIBLE);
//                            }
                        }

                    }else {
                        if(mHeadView == null){
                            f(R.id.iv_no_date).setVisibility(View.GONE);
                            f(R.id.swipeRefreshLayout).setVisibility(View.VISIBLE);
                        }else {
//                            View nodateView = mHeadView.findViewById(R.id.nodate);
//                            if(nodateView != null){
//                                nodateView.setVisibility(View.GONE);
//                            }
                        }
                    }
                }
                mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.loadComplete();
                if(dateList == null || dateList.size() < mPageSize){
                    if(getOnRequestDataListener() != null){
                        mAdapter.noMoreData();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
