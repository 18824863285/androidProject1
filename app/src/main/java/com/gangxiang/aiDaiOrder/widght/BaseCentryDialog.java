package com.gangxiang.aiDaiOrder.widght;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gangxiang.aiDaiOrder.R;

public class BaseCentryDialog {

	private Context mContext;
	public Dialog mDialog;
	public View mDialogView;

	public void initDialog(int layoutId, Context context, boolean isCanCanceledOnTouchOutside) {
		this.mContext = context;
		mDialogView = View.inflate(context, layoutId, null);
		mDialog = new Dialog(mContext, R.style.Msg_Dialog);
		mDialog.setContentView(mDialogView);
		Window window = mDialog.getWindow();
		WindowManager.LayoutParams wl = window.getAttributes();
		wl.gravity = Gravity.CENTER;
		wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
		wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		mDialog.onWindowAttributesChanged(wl);
		mDialog.setCanceledOnTouchOutside(isCanCanceledOnTouchOutside);
		mDialog.setCancelable(isCanCanceledOnTouchOutside);
	}

	public void show() {
		if (mDialog != null && !mDialog.isShowing()) {
			mDialog.show();
		}
	}

	public void dismiss() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}


}
