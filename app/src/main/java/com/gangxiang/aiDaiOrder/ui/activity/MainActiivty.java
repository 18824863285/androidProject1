package com.gangxiang.aiDaiOrder.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.adapter.MyFragmentPagerAdapter;
import com.gangxiang.aiDaiOrder.base.BaseActivity;
import com.gangxiang.aiDaiOrder.bean.NkProduct;
import com.gangxiang.aiDaiOrder.bean.Product;
import com.gangxiang.aiDaiOrder.bean.ProductSeries;
import com.gangxiang.aiDaiOrder.config.Constants;
import com.gangxiang.aiDaiOrder.model.ApiService;
import com.gangxiang.aiDaiOrder.ui.fragment.GoodListFargment;
import com.gangxiang.aiDaiOrder.ui.fragment.NkGragment;
import com.gangxiang.aiDaiOrder.util.DensityUtils;
import com.gangxiang.aiDaiOrder.util.EmptyCheck;
import com.gangxiang.aiDaiOrder.util.GsonUtils;
import com.gangxiang.aiDaiOrder.util.SharedUtils;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import static com.gangxiang.aiDaiOrder.config.Configs.cktj;
import static com.gangxiang.aiDaiOrder.model.RequestIds.id_GetProOrderMeeting;
import static com.gangxiang.aiDaiOrder.model.RequestIds.id_GetService;
import static com.gangxiang.aiDaiOrder.model.RequestIds.id_cmmitNkOrder;
import static com.gangxiang.aiDaiOrder.model.RequestIds.id_cmmitOrder;

public class MainActiivty extends BaseActivity{

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;

    private List<Fragment> mFragmentList = new ArrayList<>();
    private ProductSeries mProductSeries;

    private NkGragment mNkGragment;
    private  LinePagerIndicator mLinePagerIndicator;

    private int mCurrPageIndex;
    private int mProOrderMeetingId;//当前订货会ID

    @Override
    public int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isHaveTitleBar() {
        return false;
    }

    @Override
    public void init() {
        mLoadingDiaolg.show();
        ApiService.getProductSeries(1,mStringCallback,id_GetService);
        ApiService.getProOrderMeeting(mStringCallback,id_GetProOrderMeeting);
        isPermissionsAllGranted(Constants.permArray, Constants.QUEST_CODE_ALL);
    }

    @OnClick({R.id.tc,R.id.ll_nk,R.id.tj,R.id.cktj})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tc:
                intent(LoginActivity.class);
                SharedUtils.logout(mActivity);
                finish();
                break;
            case R.id.ll_nk:
                mViewPager.setCurrentItem(mFragmentList.size() - 1);
                break;
            case R.id.tj:
                if(mCurrPageIndex == mFragmentList.size() - 1){
                    commitNkOrder();
                }else {
                    commitOrder();
                }
                break;
            case R.id.cktj:
                startActivity(new Intent(mActivity,WebviewActivity.class)
                        .putExtra(WebviewActivity.URL,cktj+getMemberId()+"&pomid="+mProOrderMeetingId));
                break;
            default:
                break;
        }
    }

    /**
     * 提交内裤订单
     */
    private void commitNkOrder(){
        mLoadingDiaolg.show();
        NkGragment nkGragment = (NkGragment) mFragmentList.get(mCurrPageIndex);
        List<NkProduct.DataBean> dataBeans = nkGragment.mDateList;
        String productids = "";
        if(dataBeans != null && dataBeans.size() > 0){
            for (int i = 0 ; i < dataBeans.size() ; i++){
                NkProduct.DataBean dataBean = dataBeans.get(i);
                List<NkProduct.DataBean.SizeBean> sizeBeans = dataBean.getSize();
                if(sizeBeans != null && sizeBeans.size() > 0){
                    for (int j = 0 ; j < sizeBeans.size() ; j ++){
                        NkProduct.DataBean.SizeBean sizeBean = sizeBeans.get(j);
                        productids += dataBean.getId()+","+sizeBean.getId()+","+sizeBean.getPSNumber()+";";
                    }
                }
            }
        }

        if("".equals(productids)){
            showShort(R.string.slbkwL);
        }else {
            ApiService.commitNkOrder(1,productids.substring(0,productids.length() - 1),mStringCallback,id_cmmitNkOrder);
        }
    }

    /**
     * 提交内衣订单
     */
    private void commitOrder(){
        mLoadingDiaolg.show();
        GoodListFargment fragment = (GoodListFargment) mFragmentList.get(mCurrPageIndex);
        StringBuilder stringBuilder = new StringBuilder();
        List<Product.DataBean> dataBeans = fragment.mDateList;
        for (int i = 0 ; i < dataBeans.size() ; i ++){
            Product.DataBean dataBean = dataBeans.get(i);
            List<Product.DataBean.ColorBean> colorBeanList = dataBean.getColor();
            if(colorBeanList != null && colorBeanList.size() > 0){
                for (int j = 0 ; j < colorBeanList.size() ; j ++){
                    Product.DataBean.ColorBean colorBean = colorBeanList.get(j);
                    List<Product.DataBean.ColorBean.CupBean> cupBeans = colorBean.getCup();
                    if(cupBeans != null && cupBeans.size() > 0){
                        for (int k = 0 ; k < cupBeans.size() ; k++){
                            Product.DataBean.ColorBean.CupBean cupBean = cupBeans.get(k);
                            List<Product.DataBean.ColorBean.CupBean.SizeBean> sizeBeans = cupBean.getSize();
                            if(sizeBeans != null &&sizeBeans.size() > 0){
                              for (int t = 0 ;t < sizeBeans.size() ; t++){
                                  Product.DataBean.ColorBean.CupBean.SizeBean sizeBean = sizeBeans.get(t);
                                  stringBuilder
                                          .append(dataBean.getId()).append(",")
                                          .append(colorBean.getId()).append(",")
                                          .append(cupBean.getId()).append(",")
                                          .append(sizeBean.getPSSize()).append(",")
                                          .append(sizeBean.getPSNumber()).append(";");
                              }
                            }
                        }
                    }
                }
            }
        }

        String PSID = mProductSeries.getData().get(mCurrPageIndex).getId();
        String ProductId = stringBuilder.toString();
        ProductId = ProductId.substring(0,ProductId.length() - 1);

        if(EmptyCheck.isEmpty(ProductId)){
            showShort(R.string.slbkwL);
        }else {
            ApiService.commitOrder(PSID,ProductId,mStringCallback,id_cmmitOrder);
        }
    }

    private void initViewPager(){
        if(mProductSeries != null ){
            List<ProductSeries.DataBean> list = mProductSeries.getData();
            mViewPager.setOffscreenPageLimit(2);
            for(int i=0 ; i < list.size(); i++){
                GoodListFargment goodListFargment = new GoodListFargment();
                Bundle bundle = new Bundle();
                bundle.putString(GoodListFargment.Time,list.get(i).getPeoSeDate());
                bundle.putString(GoodListFargment.ID,list.get(i).getId());
                goodListFargment.setArguments(bundle);
                mFragmentList.add(goodListFargment);
            }
            mNkGragment = new NkGragment();
            mFragmentList.add(mNkGragment);
            mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragmentList));
        }
    }

    private void initMagicIndicator(){
        if(mProductSeries != null){
            CommonNavigator commonNavigator = new CommonNavigator(mActivity);
            final List<ProductSeries.DataBean> list = mProductSeries.getData();
            if(list.size() < 8){
                commonNavigator.setAdjustMode(true);
            }else{
                commonNavigator.setAdjustMode(false);
            }
            commonNavigator.setAdapter(new CommonNavigatorAdapter() {

                @Override
                public int getCount() {
                    return list.size();
                }

                @Override
                public IPagerTitleView getTitleView(Context context, final int index) {
                    SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                    simplePagerTitleView.setNormalColor(Color.parseColor("#FFFFFF"));
                    simplePagerTitleView.setSelectedColor(Color.parseColor("#FFFFFF"));
                    simplePagerTitleView.setText(list.get(index).getPeoSeTitle());
                    simplePagerTitleView.setTextSize(DensityUtils.px2dp(mActivity,18));
                    simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mViewPager.setCurrentItem(index);
                        }
                    });
                    return simplePagerTitleView;
                }

                @Override
                public IPagerIndicator getIndicator(Context context) {
                    mLinePagerIndicator = new LinePagerIndicator(context);
                    mLinePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                    mLinePagerIndicator.setLineHeight(2f);
                    mLinePagerIndicator.setColors(Color.parseColor("#FFFFFF"));
                    return mLinePagerIndicator;
                }
            });
            mMagicIndicator.setNavigator(commonNavigator);
            ViewPagerHelper.bind(mMagicIndicator, mViewPager);
            mViewPager.setCurrentItem(0);
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mCurrPageIndex = position;
                    if(position == mFragmentList.size() - 1){
                        f(R.id.view1).setVisibility(View.VISIBLE);
                        mLinePagerIndicator.setVisibility(View.GONE);
                    }else {
                        mLinePagerIndicator.setVisibility(View.VISIBLE);
                        f(R.id.view1).setVisibility(View.GONE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public void requestCallBack(String response, int id) {
        switch (id){
            case id_GetService:
                mProductSeries = GsonUtils.fromJson(response,ProductSeries.class);
                initViewPager();
                initMagicIndicator();
                break;
            case id_cmmitNkOrder:
            case id_cmmitOrder:
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    showShort(jsonObject.optString("data"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case id_GetProOrderMeeting:
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.optBoolean("success")){
                        JSONObject jsonObject1 =jsonObject.optJSONArray("data").optJSONObject(0);
                        mProOrderMeetingId = jsonObject1.optInt("id");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
