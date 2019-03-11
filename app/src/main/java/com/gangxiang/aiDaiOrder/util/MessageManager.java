package com.gangxiang.aiDaiOrder.util;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class MessageManager {
	public static MessageManager manager;
	private ArrayList<Handler> mHandlers = new ArrayList<Handler>();
	public static final int MessageType_logout = 0;//退出登录
	public static final int MessageType_update_info_success = 1;//修改个人信息成功
	public static final int MessageType_add_zongchou_success = 2;//添加众筹成功
	public static final int MessageType_update_app_progress = 3;
	public static final int MessageType_update_number_jia = 4;//修改了数量
	public static final int MessageType_update_number_jian = 5;//修改了数量

	private MessageManager() {

	}

	public static MessageManager getInstance() {
		if (manager == null) {
			manager = new MessageManager();
		}
		return manager;
	}

	public void addHandler(Handler handler) {
		if (!mHandlers.contains(handler)) {
			mHandlers.add(handler);
		}
	}

	public void removeHandler(Handler handler) {
		mHandlers.remove(handler);
	}

	public void sendMessage(int msgType, Object object) {
		for (Handler handler : mHandlers) {
			Message msg = handler.obtainMessage();
			msg.what = msgType;
			msg.obj = object;
			handler.sendMessage(msg);
		}
	}

	public void sendMessage(int msgType) {
		for (Handler handler : mHandlers) {
			Message msg = handler.obtainMessage();
			msg.what = msgType;
			handler.sendMessage(msg);
		}
	}

	public void removeAllHandler(){
		if(mHandlers != null && mHandlers.size() > 0){
			mHandlers.clear();
		}
	}


}
