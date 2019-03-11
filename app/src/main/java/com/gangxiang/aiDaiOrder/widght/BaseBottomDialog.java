package com.gangxiang.aiDaiOrder.widght;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gangxiang.aiDaiOrder.R;


public class BaseBottomDialog {
	public View mDialogView;
	public Dialog mDialog;
	/**
	 * @param context
	 * @param dialogLayoutId
	 * @param styleId
	 */
	public void initView(Context context,  int dialogLayoutId ,int styleId) {
		mDialogView = View.inflate(context,dialogLayoutId, null);
		mDialog = new Dialog(context, R.style.ActionSheetDialogStyle);
		mDialog.setContentView(mDialogView);
		Window window = mDialog.getWindow();
		// 设置显示动画
		if(styleId == 0){
			styleId = R.style.ActionSheetDialogAnimation;
		}
		window.setWindowAnimations(styleId);
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.x = 0;
		wl.y = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
		// 以下这两句是为了保证按钮可以水平满屏
		wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// 设置显示位置
		mDialog.onWindowAttributesChanged(wl);
		// 设置点击外围解散
		mDialog.setCanceledOnTouchOutside(true);
	}

	public void cancle() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.cancel();
		}
	}

	public void show() {
		if (mDialog != null && !mDialog.isShowing()) {
			mDialog.show();
		}
	}
	
}
