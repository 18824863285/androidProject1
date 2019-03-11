package com.gangxiang.aiDaiOrder.base;

import android.os.Message;
import android.support.annotation.LayoutRes;

public interface BaseInterface {

    @LayoutRes
    public abstract int attachLayoutRes();

    public abstract void messageCallBack(Message message);

    public abstract void addMessageManger();

    public abstract String setTitle();

    public abstract void init();

    public abstract boolean isHaveTitleBar();

    public  abstract void requestCallBack(String response, int id);
}
