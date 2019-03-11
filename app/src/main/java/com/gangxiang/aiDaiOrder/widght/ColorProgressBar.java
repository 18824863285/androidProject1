package com.gangxiang.aiDaiOrder.widght;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ColorProgressBar extends LinearLayout{
	View _progressLeft = null;
	View _progressRight = null;
	LayoutParams _leftLP = null;
	LayoutParams _rightLP = null;
	
	public ColorProgressBar(Context context) {
		super(context);
		//initLayout(context);
	}
	
	public ColorProgressBar(Context context, AttributeSet attrs) {
		super (context, attrs);
		// TODO Auto-generated constructor stub
		initLayout(context);
	}
	
	private void initLayout(Context context){
		setOrientation(LinearLayout.HORIZONTAL);
		
		_progressLeft = new View(context);
		_progressLeft.setBackgroundColor(Color.parseColor("#F74D4D"));
		_leftLP = new LayoutParams(0, android.view.ViewGroup.LayoutParams.FILL_PARENT, 1);
		_progressLeft.setLayoutParams(_leftLP);
		addView(_progressLeft);
		
		_progressRight = new View(context);
		_progressRight.setBackgroundColor(Color.argb(255, 190, 190, 190));
		_rightLP = new LayoutParams(0, android.view.ViewGroup.LayoutParams.FILL_PARENT, 99);
		_progressRight.setLayoutParams(_rightLP);
		addView(_progressRight);
	}
	
	public void setProgress(int progress){
		if (getVisibility() == View.GONE){
			setVisibility(View.VISIBLE);
		}
		else if (progress == 100){
			setVisibility(View.GONE);
		}
		_leftLP.weight = progress;
		_progressLeft.setLayoutParams(_leftLP);
		
		_rightLP.weight = 100 - progress;
		_progressRight.setLayoutParams(_rightLP);
	}
}
