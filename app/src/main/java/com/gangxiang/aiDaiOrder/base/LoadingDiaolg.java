package com.gangxiang.aiDaiOrder.base;

import android.content.Context;

import com.gangxiang.aiDaiOrder.R;
import com.gangxiang.aiDaiOrder.widght.BaseCentryDialog;


/**
 * Created by Administrator on 2018/1/7 0007.
 */

public class LoadingDiaolg extends BaseCentryDialog {
    private Context mContext;

    public LoadingDiaolg(Context context) {
        this.mContext = context;
        initDialog(R.layout.dialog_loading,mContext,false);
    }
}
