package com.gangxiang.aiDaiOrder.widght;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gangxiang.aiDaiOrder.R;


public class PageGuide extends LinearLayout {

	private FrameLayout pointLayout[] = null;
	private View point[] = null;
	private int count;
	private Context context;
	private int resOn = R.drawable.shape_qiehuanyuan_pre,
			resOff = R.drawable.shape_qiehuanyuan_pnor;
	private int w;
	private int h;

	public PageGuide(Context context, AttributeSet attr) {
		super(context, attr);
		this.context = context;
	}

	public void setRes(int resOn, int resOff) {
		this.resOn = resOn;
		this.resOff = resOff;
	}

	public void setParams(int count, int w, int h) {
		this.w = w;
		this.h = h;
		removeAllViews();
		this.count = count;

		pointLayout = new FrameLayout[count];
		point = new View[count];

		setOrientation(LinearLayout.HORIZONTAL);
		for (int i = 0; i < count; i++) {
			pointLayout[i] = new FrameLayout(context);
			point[i] = new View(context);
			point[i].setBackgroundResource(resOff);
			pointLayout[i].addView(point[i], new FrameLayout.LayoutParams(w, h,
					Gravity.CENTER));
			addView(pointLayout[i], new LayoutParams(w
					+ dipToPixels(context, 6), h + dipToPixels(context, 10), 1));
		}

		if (count == 1)
			removeAllViews();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = (w + dipToPixels(context,10)) * count;
		int height = h + dipToPixels(context, 10);
		setMeasuredDimension(width, height);

	}

	public void setSelect(int position) {
		for (int i = 0; i < count; i++) {
			point[i].setBackgroundResource(resOff);
		}
		if (point[position] != null) {
			point[position].setBackgroundResource(resOn);
		}
		if (count == 1)
			removeAllViews();
	}

	public static int dipToPixels(Context context, float dip) {
		return (int) (context.getResources().getDisplayMetrics().density * dip);
	}
}
