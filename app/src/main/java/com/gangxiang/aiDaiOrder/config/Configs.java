package com.gangxiang.aiDaiOrder.config;

/**
 * Created by Administrator on 2017/2/9.
 */

public class Configs {
    //http://api.17hrw.com/
    //http://apitest.17hrw.com/
    public static boolean isTest = false;
    public static String API =isTest ? "http://10.10.10.102/niuapi/": "http://dinghuo.17hrw.com/AiDaiDingHuo.asmx/";
    public static String IMG =isTest ? "http://10.10.10.102/niuapi":"http://dinghuo.17hrw.com";

    public static final String cktj = "http://dinghuo.17hrw.com/Show/aidai.aspx?userid=";

    //1459997202
    //http://api.17hrw.com/Wap/NewsDetail/12
}
