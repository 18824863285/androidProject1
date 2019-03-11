package com.gangxiang.aiDaiOrder.ui.activity;

import android.view.View;
import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.base.BaseActivity;
import com.gangxiang.aiDaiOrder.model.ApiService;
import com.gangxiang.aiDaiOrder.util.EmptyCheck;
import com.gangxiang.aiDaiOrder.util.SharedUtils;
import org.json.JSONException;
import org.json.JSONObject;
import butterknife.OnClick;
import static com.gangxiang.aiDaiOrder.model.RequestIds.id_login;

public class LoginActivity extends BaseActivity{

    private String mLoginName;
    private String mPsw;

    @Override
    public int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
       if(isLogin()){
           intent(MainActiivty.class);
           finish();
       }
    }

    @Override
    public boolean isHaveTitleBar() {
        return false;
    }

    @OnClick({R.id.btn_login})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
            default:
                break;
        }
    }

    private void login(){
        mLoginName = getEtString(R.id.loginName);
        mPsw = getEtString(R.id.loginPsw);
        if(EmptyCheck.isEmpty(mLoginName)){
           showShort(R.string.dlzhbkwk);
        }else if(EmptyCheck.isEmpty(mPsw)){
            showShort(R.string.dlmmbkwk);
        }else {
            mLoadingDiaolg.show();
            ApiService.login(mLoginName,mPsw,mStringCallback,id_login);
        }
    }

    @Override
    public void requestCallBack(String response, int id) {
       switch (id){
           case id_login:
               try {
                   JSONObject jsonObject = new JSONObject(response);
                   if(jsonObject.optBoolean("success")){
                       JSONObject modelJson = jsonObject.optJSONObject("data");
                       SharedUtils.put("Id",modelJson.optString("id"));
                       SharedUtils.put("Name",modelJson.optString("UserName"));
                       intent(MainActiivty.class);
                       finish();
                   }else {
                       showShort(jsonObject.optString("data"));
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
