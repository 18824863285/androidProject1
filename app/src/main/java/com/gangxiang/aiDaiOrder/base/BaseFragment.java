package com.gangxiang.aiDaiOrder.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gangxiang.aiDaiOrder.config.Configs;
import com.gangxiang.aiDaiOrder.util.FindView;
import com.gangxiang.aiDaiOrder.util.MessageManager;
import com.gangxiang.aiDaiOrder.util.SharedUtils;
import com.gangxiang.aiDaiOrder.util.SimpleDraweeViewUtil;
import com.gangxiang.aiDaiOrder.util.ToastUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.MediaType;

public abstract class BaseFragment extends Fragment implements BaseInterface{

    public Activity mActivity;
    public View mFragmentView;
    public StringCallback mStringCallback;
    public LoadingDiaolg mLoadingDiaolg;
    private Handler mHandler;
    protected MessageManager mMessageManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        mLoadingDiaolg = new LoadingDiaolg(mActivity);
        mMessageManager = MessageManager.getInstance();
        initStringCallBack();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        MessageManager.getInstance().removeHandler(mHandler);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mActivity = getActivity();
            mLoadingDiaolg = new LoadingDiaolg(mActivity);
            if(attachLayoutRes() != 0){
                mFragmentView = inflater.inflate(attachLayoutRes(), null);
                ButterKnife.bind(this, mFragmentView);
                init();
            }
            ViewGroup parent = (ViewGroup) mFragmentView.getParent();
            if (parent != null) {
                parent.removeView(mFragmentView);
            }
        }
        addMessageManger();
        return mFragmentView;
    }


    @Override
    public void requestCallBack(String response, int id) {

    }

    @Override
    public void messageCallBack(Message message) {

    }

    @Override
    public void addMessageManger() {
        MessageManager.getInstance().addHandler(mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                messageCallBack(msg);
            }
        });
    }

    @Override
    public String setTitle() {
        return null;
    }

    @Override
    public boolean isHaveTitleBar() {
        return false;
    }

    public  <T extends View> T f(int id){
        return FindView.get(mFragmentView,id);
    }

    public void c(int id, View.OnClickListener onClickListener){
        f(id).setOnClickListener(onClickListener);
    }

    public boolean isLogin(){
        boolean isLogin = SharedUtils.isLogin();
        return isLogin;
    }

    public String getMemberId(){
        return SharedUtils.getMemberId();
    }

    public View inflaterView(int viewId){
        return View.inflate(mActivity,viewId,null);
    }

    public void setNetWorkImage(int id, String url){
        SimpleDraweeViewUtil.setNetWorkImage((SimpleDraweeView) f(id), Configs.IMG+url);
    }

    public void setTvText(int id, String text){
        ((TextView)f(id)).setText(text);
    }

    public void setTvText(int id, int stringId){
        ((TextView)f(id)).setText(getString(stringId));
    }

    public void setEtText(int id, String text){
        ((EditText)f(id)).setText(text);
    }

    public void setEtText(int id, int stringId){
        ((EditText)f(id)).setText(getString(stringId));
    }

    public void setImageResource(int id, int resoureId){
        ((ImageView)f(id)).setImageResource(resoureId);
    }

    public void setImageBitmap(int id, Bitmap bitmap){
        ((ImageView)f(id)).setImageBitmap(bitmap);
    }

    public void setTvColor(int id, String color){
        ((TextView)f(id)).setTextColor(Color.parseColor(color));
    }

    public void setViewTvText(View view,int id, String text){
        ((TextView)view.findViewById(id)).setText(text);
    }

    public void intent(Class c){
        startActivity(new Intent(mActivity,c));
    }

    public void postRequest(HashMap<String,String> parmas, String url, StringCallback stringCallback, int id){
        OkHttpUtils
                .post()
                .url(url)
                .params(parmas)
                .addHeader("Token", SharedUtils.getToken())
                .addHeader("Mobile",SharedUtils.getMobile())
                .id(id)
                .build()
                .execute(stringCallback);
    }

    public void getRequest(HashMap<String,String> parmas, String url, StringCallback stringCallback, int id){
        OkHttpUtils
                .get()
                .url(url)
                .params(parmas)
                .addHeader("Token", SharedUtils.getToken())
                .addHeader("Mobile",SharedUtils.getMobile())
                .id(id)
                .build()
                .execute(stringCallback);
    }

    public void postJson(HashMap<String,String> parmas, String url, StringCallback stringCallback, int id){
        String s = new Gson().toJson(parmas);
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .addHeader("Content-type","application/json;charset=utf-8")
                .addHeader("Token", SharedUtils.getToken())
                .addHeader("Mobile",SharedUtils.getMobile())
                .content(new Gson().toJson(parmas))
                .id(id)
                .build()
                .execute(stringCallback);
    }

    public JSONArray pingJsonArray(JSONArray jsonArray1, JSONArray jsonArray2){
        if(jsonArray1 != null && jsonArray2 != null && jsonArray2.length() >0){
            for(int i = 0;i<jsonArray2.length();i++){
                jsonArray1.put(jsonArray2.optJSONObject(i));
            }
        }
        return  jsonArray1;
    }

    public List<JSONObject> pingJsonArrayToList(JSONArray jsonArray){
        List<JSONObject> jsonObjectList = new ArrayList<>();
        if(jsonArray != null && jsonArray.length() > 0){
            for(int i = 0; i < jsonArray.length(); i++){
                jsonObjectList.add(jsonArray.optJSONObject(i));
            }
        }
        return jsonObjectList;
    }

    public void downFile(String downUrl ,FileCallBack fileBack){
        OkHttpUtils//
                .get()//
                .url(downUrl)//
                .addHeader("Token", SharedUtils.getToken())
                .addHeader("Mobile",SharedUtils.getMobile())
                .build()//
                .execute(fileBack);
    }

    public void showLong(String text){
        ToastUtils.showLong(mActivity,text);
    }

    public void showLong(int stringId){
        ToastUtils.showLong(mActivity,getString(stringId));
    }

    public void showShort(String text){ ToastUtils.showShort(mActivity,text); }

    public void showShort(int stringId){
        ToastUtils.showShort(mActivity,getString(stringId));
    }

    private void initStringCallBack(){
        mStringCallback = new StringCallback() {

            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                mLoadingDiaolg.dismiss();
            }


            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                requestCallBack(response, id);
            }
        };
    }
}
