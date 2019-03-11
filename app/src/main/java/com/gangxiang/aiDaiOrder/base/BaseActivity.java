package com.gangxiang.aiDaiOrder.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.config.Configs;
import com.gangxiang.aiDaiOrder.config.Constants;
import com.gangxiang.aiDaiOrder.util.FindView;
import com.gangxiang.aiDaiOrder.util.MessageManager;
import com.gangxiang.aiDaiOrder.util.PermissionUtil;
import com.gangxiang.aiDaiOrder.util.SharedUtils;
import com.gangxiang.aiDaiOrder.util.SimpleDraweeViewUtil;
import com.gangxiang.aiDaiOrder.util.ToastUtils;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

public abstract class BaseActivity extends FragmentActivity implements BaseInterface{

    public StringCallback mStringCallback;
    private Handler mHandler;
    private FrameLayout mActivityView;
    protected Activity mActivity;
    protected LoadingDiaolg mLoadingDiaolg;
    protected MessageManager mMessageManager;

    private locationSuccessListener mlocationSuccessListener;

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Fresco.initialize(this);
        setContentView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mActivity = this;
        mActivityView = findViewById(R.id.frameLayout);
        mActivityView.addView(View.inflate(this,attachLayoutRes(),null));
        mLoadingDiaolg = new LoadingDiaolg(mActivity);
        ButterKnife.bind(this);
        mMessageManager = MessageManager.getInstance();
        if(isHaveTitleBar()){
            if( f(R.id.tv_title) != null && setTitle() != null){
                setTvText(R.id.tv_title,setTitle());
            }

            c(R.id.back, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }else {
            if( f(R.id.titleBar_Root) != null){
                f(R.id.titleBar_Root).setVisibility(View.GONE);
            }
        }
        findViewById(R.id.tv_title);
        addMessageManger();
        initStringCallBack();
        init();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs)
    {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT))
        {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEARLAYOUT))
        {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVELAYOUT))
        {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageManager.getInstance().removeHandler(mHandler);
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
    public void messageCallBack(Message message) {

    }

    @Override
    public String setTitle() {
        return null;
    }

    @Override
    public boolean isHaveTitleBar() {
        return true;
    }

    public  <T extends View> T f(int id){
        return FindView.get(mActivity,id);
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

    public String getEtString(int id){
        return ((EditText)f(id)).getText().toString();
    }

    public void setTitleBar(int stringId){
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        if (titleTv != null){
            titleTv.setText(getString(stringId));
        }
    }

    public void setTitleBar(String string){
        TextView titleTv = (TextView) findViewById(R.id.tv_title);
        if (titleTv != null){
            titleTv.setText(string);
        }
    }

    public void setOtherTv(int stringId, View.OnClickListener onClickListener){
        TextView otherTv = (TextView) findViewById(R.id.tv_other);
        if(otherTv != null){
            otherTv.setText(getString(stringId));
            otherTv.setOnClickListener(onClickListener);
        }
    }

    public void setOtherTv(String text, View.OnClickListener onClickListener){
        TextView otherTv = (TextView) findViewById(R.id.tv_other);
        if(otherTv != null){
            otherTv.setText(text);
            otherTv.setOnClickListener(onClickListener);
        }
    }

    public void setNestWorkImage(int id, String url){
        SimpleDraweeViewUtil.setNetWorkImage((SimpleDraweeView) f(id), Configs.IMG+url);
    }

    public void setTvText(int id, String text){
        ((TextView)f(id)).setText(text);
    }

    public void setTvText(int id, int stringId){
        ((TextView)f(id)).setText(getString(stringId));
    }

    public void setTvColor(int id, String color){
        ((TextView)f(id)).setTextColor(Color.parseColor(color));
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

    public View inflaterView(int viewId){
        return View.inflate(mActivity,viewId,null);
    }

    public void setViewTvText(View view,int id, String text){
        ((TextView)view.findViewById(id)).setText(text);
    }

    public void setViewEtText(View view,int id, String text){
        ((TextView)view.findViewById(id)).setText(text);
    }

    public void setViewEtText(View view,int id, int textId){
        ((TextView)view.findViewById(id)).setText(getString(textId));
    }

    public void setViewTvColor(View view,int id, String color){
        ((TextView)view.findViewById(id)).setTextColor(Color.parseColor(color));
    }

    public void setViewTvBgColor(View view,int id, String color){
        ((TextView)view.findViewById(id)).setBackgroundColor(Color.parseColor(color));

    }

    public void setViewTvBg(View view,int id, int  resouseId){
        ((TextView)view.findViewById(id)).setBackgroundResource(resouseId);
    }

    public void c_view(View view, int id, View.OnClickListener onClickListener){
        (view.findViewById(id)).setOnClickListener(onClickListener);
    }

    public void showLong(String text){ ToastUtils.showLong(mActivity,text);
    }

    public void showLong(int stringId){
        ToastUtils.showLong(mActivity,getString(stringId));
    }

    public void showShort(String text){
        ToastUtils.showShort(mActivity,text);
    }

    public void showShort(int stringId){
        ToastUtils.showShort(mActivity,getString(stringId));
    }

    public void intent(Class c){
        startActivity(new Intent(mActivity,c));
    }

    public void intentWithFinish(Class c){
        startActivity(new Intent(mActivity,c));
        finish();
    }

    public boolean isPermissionsAllGranted(String[] permArray,int questCode){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        //获得批量请求但被禁止的权限列表
        List<String> deniedPerms = new ArrayList<String>();
        for(int i=0;permArray!=null&&i<permArray.length;i++){
            if(PackageManager.PERMISSION_GRANTED != checkSelfPermission(permArray[i])){
                deniedPerms.add(permArray[i]);
            }
        }
        //进行批量请求
        int denyPermNum = deniedPerms.size();
        if(denyPermNum != 0){
            requestPermissions(deniedPerms.toArray(new String[denyPermNum]),questCode);
            return false;
        }
        return true;
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(grantResults.length==0){
            return;
        }
        switch (requestCode) {
            case Constants.QUEST_CODE_ALL:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //popAlterDialog("位置","位置信息权限被禁止，将导致定位失败。。是否开启该权限？(步骤：应用信息->权限->'勾选'位置)");
                    PermissionUtil.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION, Constants.QUEST_CODE_LOCTION,mActivity);
                }
                else{
                    //showShortMsg("恭喜，用户已经授予位置权限");
                    if(mlocationSuccessListener != null){
                        mlocationSuccessListener.locationSuccess();
                    }
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    public interface locationSuccessListener{
        void locationSuccess();
    }

    public void  setLocationSuccessListener(locationSuccessListener locationSuccessListener){
        mlocationSuccessListener = locationSuccessListener;
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

    private void initStringCallBack(){
        mStringCallback = new StringCallback() {

            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
            }

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
                requestCallBack(response,id);
            }
        };
    }
}
