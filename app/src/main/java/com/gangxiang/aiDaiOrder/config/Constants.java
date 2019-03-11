package com.gangxiang.aiDaiOrder.config;

import android.Manifest;


/**
 *@dscrible 常量和临时变量类
 *
 * Created by jiangdongguo on 2016-10-31 上午11:10:27
 */
public class Constants {
	/**位置信息权限请求标志*/
	public static final int QUEST_CODE_LOCTION = 1;
	/**发送短信权限请求标志*/
	public static final int QUEST_CODE_SEND_SMS = 2;
	/**摄像头权限标志*/
	public static final int QUEST_CODE_CAMERA = 3;
	/**批量请求权限*/
	public static final int QUEST_CODE_ALL  = 4;
	/**拨打电话权限*/
	public static final int QUEST_CODE_CALL_PHONE = 5;

	public static final int QUEST_CODE_WRITE_EXTERNAL_STORAGE = 6;

	//要申请的权限
	public static final  String[] permArray = {
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.READ_EXTERNAL_STORAGE,
	};
}
