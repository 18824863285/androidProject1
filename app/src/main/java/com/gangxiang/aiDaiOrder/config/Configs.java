package com.gangxiang.aiDaiOrder.config;

/**
 * Created by Administrator on 2017/2/9.
 */

public class Configs {
    //http://api.17hrw.com/
    //http://apitest.17hrw.com/
    public static boolean isTest = false;
    public static String API =isTest ? "http://10.10.10.102/niuapi/": "http://dinghuo.17hrw.com/AiDaiDingHuo.asmx/"
            ;
    public static String IMG =isTest ? "http://10.10.10.102/niuapi":"http://dinghuo.17hrw.com";
    public static String WX_APPID = "wxadea680c757f9616";
    public static String WX_ARRRANTID = "1488504962";
    public static String WX_KEY = "H3BVYvXgRwnnrxeY4oRXOQps00ZVdB48";
    public static int wx_pay_type = 1; //开通特权，2每月免费领 ,3入团,4总代,5充值

    public static final String ALIPAY_NOTIFY_URL_COLLAR = Configs.API+"API/Product/PostAliPayNotifyMsg/";
    public static final String WX_NOTIFY_URL_COLLAR = Configs.API+"API/Product/PostWeiXinNotifyMsg/";
    public static final String NOTIFY_URL_RECHARGE = Configs.API+"API/Finance/PostPayCallback/";

    public static final String PARTNER = "2088421498012146";
    public static final String SELLER = "pay@ihomebnb.com";
    public static final String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMq4LE1lcFWsAAi5Fk+WecaXqTO" +
            "uD1UdY9rw2txWA0qeLjDO89kffMqKfCPBmmHNL9a/8tkBzVOVO1W+QzH5rqqFCbchyxVZZWzc3WbTNkwfTGTxqNq5ZrvRP/PnQA7B" +
            "+1qjs8dBIbdUpLfm2YnzYeQqUVl8qeTd5qBhloV3mNP3AgMBAAECgYAg3YUJMzXoHc+Pmno8gvfRM4tR/pkrwm0K9Nt2t1cnRM" +
            "fWT+cGv20a5SLFJUEjNAHafhogBoPEVkBdYVBBjOo9Hx+TmrQi5UaaVvtpfjIKGyAIYLzu5QCDC5aAsrB6QC/GqTc9lbxknDIp" +
            "QyNyCXsdH8oLpOBTydlm7wHkC/POWQJBAOZJ3CyOWGzloHuhsNnCIWRIBLOSGvLRGn82u558X/SNPoFoOlsEIROLOKD04x3/8" +
            "/Vt2gXS65UPvtlO001FqFMCQQDhWlRKB9xt0w2EJs9Oipdd8CxG7IVfhi9H1LxpaF4YYuK98guompQxLcwmbuxxdev+EgmMq3" +
            "GbNmtda71M86FNAkASM6xPaUDb+ppgqzsLOyjggEC+4MF4h8aMX4scz7/V2IQkPlS+dOkYXlyvfCunZ1+k+nXNGAq+0WvISU6y" +
            "QNs5AkEAgrlEhgFTZD327D8bKMOKlQqalLXllIkhajjE5xyM4PaT832yN6cb6f9YGr9j11mBgUvzUC+v4krKM1IpaLc3TQJAW8T" +
            "4b/yaxMfZl3LOrLw5qz8rfAbv494N9XTrHAMMNkl/j3ha6oIrCgzKn6/hGjakdAYfOUpdpSbX/1lnlP+RBw==";

    public static final String ALIPAY_NOTIFY_URL = Configs.API+"API/Safe/PostPayCallback/3";
    public static final String WX_SHARE_APP_ID = "wxaeb8ca7554ae6295";
    public static final String WX_NOTIFY_URL = "http://www.tducn.com/PostWeChatPayCallback.ashx";
    public static final String Wap_Detail = Configs.API+"Wap/Common/Detail/";
    public static final String cktj = "http://dinghuo.17hrw.com/Show/aidai.aspx?userid=";

    //1459997202
    //http://api.17hrw.com/Wap/NewsDetail/12
}
