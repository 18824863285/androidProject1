package com.gangxiang.aiDaiOrder.model;

import com.gangxiang.aiDaiOrder.config.Configs;
import com.gangxiang.aiDaiOrder.util.SharedUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/12/28.
 */

public class ApiService {

    /**
     * 获取首页tab数据
     * @param PPID
     *         1 爱戴
     * @param stringCallback
     * @param callBackId
     */

    public static void getProductSeries(int PPID, StringCallback stringCallback, int callBackId){
        HashMap<String,String> parmas = new HashMap<>();
        parmas.put("PPID",PPID+"");
        getRequest(parmas, "GetProductSeries",stringCallback,callBackId);
    }

    /**
     * 根据分类获取产品
     * @param PSID
     *         分类id
     * @param stringCallback
     * @param callBackId
     */
    public static void getProduct(String PSID, StringCallback stringCallback, int callBackId){
        HashMap<String,String> parmas = new HashMap<>();
        parmas.put("PSID",PSID+"");
        parmas.put("UserId",SharedUtils.getMemberId());
        getRequest(parmas, "GetProduct",stringCallback,callBackId);
    }

    /**
     * 获取当前内裤系列产品
     * @param PSID
     *          项目id  爱戴 1，
     * @param stringCallback
     * @param callBackId
     */
    public static void getNkProduct(int PSID, StringCallback stringCallback, int callBackId){
        HashMap<String,String> parmas = new HashMap<>();
        parmas.put("PPID",PSID+"");
        parmas.put("UserId",SharedUtils.getMemberId());
        getRequest(parmas, "GetProductPant",stringCallback,callBackId);
    }

    /**
     * 提交内裤订单
     * @param PSID
     * @param ProductId
     *          jsonArray 1(产品ID),1（颜色ID ）,100
     * @param stringCallback
     * @param callBackId
     */
    public static void commitNkOrder(int PSID, String ProductId,StringCallback stringCallback, int callBackId){
        HashMap<String,String> parmas = new HashMap<>();
        parmas.put("PPID",PSID+"");
        parmas.put("UserId",SharedUtils.getMemberId());
        parmas.put("ProductId",ProductId);
        System.out.println("====>parmas:"+parmas.toString());
        postRequest(parmas, "SetProductPant",stringCallback,callBackId);
    }

    /**
     *  提交内衣订单
     * @param PSID
     * @param ProductId
     *         产品ID--PID   颜色ID--PCID  杯型ID--PCTID  号码--PCSize  数量--OrderNumber
     * @param stringCallback
     * @param callBackId
     */
    public static void commitOrder(String PSID, String ProductId,StringCallback stringCallback, int callBackId){
        HashMap<String,String> parmas = new HashMap<>();
        parmas.put("PSID",PSID);
        parmas.put("UserId",SharedUtils.getMemberId());
        parmas.put("ProductId",ProductId);
        System.out.println("====>parmas:"+parmas.toString());
        postRequest(parmas, "SetProductOrder",stringCallback,callBackId);
    }

    /**
     * 用户登录
     * @param loginName
     * @param psw*
     * @param stringCallback
     * @param callBackId
     */
    public static void login(String loginName,String psw,StringCallback stringCallback, int callBackId){
        HashMap<String,String> parmas = new HashMap<>();
        parmas.put("UserName",loginName);
        parmas.put("UserPass",psw);
        parmas.put("PPID","1"); //爱戴 1  艾慕  2  诱惑密码 3
        System.out.println("====>parmas:"+parmas.toString());
        postRequest(parmas, "UserLogin",stringCallback,callBackId);
    }

    /**
     * 获取当前订货会ID
     * @param stringCallback
     * @param callBackId
     */
    public static void getProOrderMeeting( StringCallback stringCallback, int callBackId){
        HashMap<String,String> parmas = new HashMap<>();
        parmas.put("PPID",1+"");
        getRequest(parmas, "GetProOrderMeeting",stringCallback,callBackId);
    }

    public static void postRequest(HashMap<String, String> parmas, String url, StringCallback stringCallback, int id){
        OkHttpUtils
                .post()
                .url(Configs.API+url)
                .params(parmas)
                .id(id)
                .build()
                .execute(stringCallback);
    }

    public static void getRequest(HashMap<String,String> parmas, String url, StringCallback stringCallback, int id){
        OkHttpUtils
                .get()
                .url(Configs.API+url)
                .params(parmas)
                .id(id)
                .build()
                .execute(stringCallback);
    }

    public static void postJson(HashMap<String,String> parmas, String url, StringCallback stringCallback, int id){
        OkHttpUtils
                .postString()
                .url(Configs.API+url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .addHeader("Content-type","application/json;charset=utf-8")
                .addHeader("Token", SharedUtils.getToken())
                .addHeader("Mobile",SharedUtils.getMobile())
                .content(new Gson().toJson(parmas))
                .id(id)
                .build()
                .execute(stringCallback);
    }

    public static void postFiles(List<HashMap<String, String>> parmas, String url, StringCallback stringCallback, int id){
        OkHttpUtils
                .postString()
                .url(Configs.API+url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .addHeader("Content-type","application/json;charset=utf-8")
                .addHeader("Token", SharedUtils.getToken())
                .addHeader("Mobile",SharedUtils.getMobile())
                .content(new Gson().toJson(parmas))
                .id(id)
                .build()
                .execute(stringCallback);
    }

    public static void downFile(String downUrl ,FileCallBack fileBack){
        OkHttpUtils//
                .get()//
                .url(downUrl)//
                .addHeader("Token", SharedUtils.getToken())
                .addHeader("Mobile",SharedUtils.getMobile())
                .build()//
                .execute(fileBack);
    }
}
