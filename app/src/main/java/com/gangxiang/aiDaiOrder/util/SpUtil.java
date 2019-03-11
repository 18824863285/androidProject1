package com.gangxiang.aiDaiOrder.util;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/24.
 */

public class SpUtil {
    public static void saveLoginInfo(JSONObject json){
        SharedUtils.put("Id",json.optString("Id"));
        SharedUtils.put("Role",json.optString("Role"));
        SharedUtils.put("UserName",json.optString("UserName"));
        SharedUtils.put("HeadImg",json.optString("HeadImg"));
        SharedUtils.put("NickName",json.optString("NickName"));
        SharedUtils.put("Gender",json.optString("Gender"));
        SharedUtils.put("RealName",json.optString("RealName"));
        SharedUtils.put("Balance",json.optString("Balance"));
        SharedUtils.put("Integral",json.optString("Integral"));
        SharedUtils.put("Status",json.optString("Status"));
        SharedUtils.put("CreateTime",json.optString("CreateTime"));
        SharedUtils.put("MobileCode",json.optString("MobileCode"));
        SharedUtils.put("Reference",json.optString("Reference"));

    }
}
